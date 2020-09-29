package deltix.qsrv.dtb.fs.azure;

import com.microsoft.azure.RestClient;
import com.microsoft.azure.credentials.ApplicationTokenCredentials;
import com.microsoft.azure.management.datalake.store.DataLakeStoreFileSystemManagementClient;
import com.microsoft.azure.management.datalake.store.implementation.DataLakeStoreFileSystemManagementClientImpl;
import deltix.qsrv.dtb.fs.azure2.AzureFsBase;
import deltix.qsrv.dtb.fs.pub.AbstractPath;
import deltix.util.lang.Util;

import javax.annotation.Nullable;
import java.util.concurrent.TimeUnit;


/**
 * Azure Data Lake integration that uses "azure-mgmt-datalake-store" client API.
 */
public class AzureFS extends AzureFsBase {

    private static final String         DATA_LAKE_FS_URL_DEF = "https://{accountName}.{adlsFileSystemDnsSuffix}";
    private static final int            LONG_OPERATIONS_TIMEOUT = 10 * 60; /* 10 minutes */
    private static final int            CONNECTION_TIMEOUT = Integer.getInteger(AZURE_TIMEOUT_PROP, 60);
    private static final int            READ_TIMEOUT = CONNECTION_TIMEOUT;


    DataLakeStoreFileSystemManagementClient adlsFSClient;
    String                              accountName;

    private AzureFS(DataLakeStoreFileSystemManagementClient adlsFSClient, String accountName, @Nullable Long reopenOnSeekThreshold) {
        super(reopenOnSeekThreshold, 0);
        this.adlsFSClient = adlsFSClient;
        this.accountName = accountName;
    }

    @Override
    public AbstractPath createPath(String path) {
        return new AzurePathImpl(path, this);
    }

    @Override
    public AbstractPath createPath(AbstractPath parent, String child) {
        AzurePathImpl p = (AzurePathImpl) Util.unwrap(parent);

        return new AzurePathImpl(p.getPathString() + AZURE_PATH_SEPARATOR + child, this);
    }

    public static AzureFS create() {
        String account = System.getProperty(AZURE_ACCOUTN_PROP);
        String clientId = System.getProperty(AZURE_CLIENT_ID_PROP);
        String tenantId = System.getProperty(AZURE_TENANT_ID_PROP);
        String secretDef = System.getProperty(AZURE_SECRET_PROP);
        Long reopenOnSeekThreshold = Long.getLong(AZURE_REOPEN_ON_SEEK_THRESHOLD_PROP);

        return create(clientId, tenantId, secretDef, account, reopenOnSeekThreshold);
    }

    public static AzureFS create(String clientId, String tenantId, String clientSecret, String accountName, @Nullable Long reopenOnSeekThreshold) {
        ApplicationTokenCredentials creds = new ApplicationTokenCredentials(clientId, tenantId, clientSecret, null);

        DataLakeStoreFileSystemManagementClient adlsFSClient = new DataLakeStoreFileSystemManagementClientImpl(
                (new RestClient.Builder())
                        .withBaseUrl(DATA_LAKE_FS_URL_DEF)
                        .withCredentials(creds)
                        .withConnectionTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
                        .withReadTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                        .build()
        );

        return new AzureFS(
                adlsFSClient.withLongRunningOperationRetryTimeout(LONG_OPERATIONS_TIMEOUT),
                accountName,
                reopenOnSeekThreshold
        );
    }

    public static boolean isAccountNameSet() {
        return System.getProperty(AZURE_ACCOUTN_PROP) != null;
    }

    @Override
    public String getSeparator() {
        return "/";
    }
}
