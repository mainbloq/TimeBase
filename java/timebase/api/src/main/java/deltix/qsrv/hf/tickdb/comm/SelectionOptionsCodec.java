package deltix.qsrv.hf.tickdb.comm;

import deltix.qsrv.hf.pub.ChannelCompression;
import deltix.qsrv.hf.pub.ChannelPerformance;
import deltix.qsrv.hf.pub.util.SerializationUtils;
import deltix.qsrv.hf.tickdb.pub.SelectionOptions;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.DataInputStream;

/**
 * 
 */
public abstract class SelectionOptionsCodec {

    public static final int VERSION_WITH_FIXED_STREAM_TYPE_SUPPORT = 99;
    public static final int VERSION_WITH_SPACES_SUPPORT = 110;

    public static void write(DataOutputStream out, SelectionOptions options, int serverVersion)
            throws IOException
    {
        out.writeBoolean(options.live);
        out.writeBoolean(options.reversed);
        out.writeBoolean(options.allowLateOutOfOrder);
        out.writeLong(options.shiftOffset);
        out.writeBoolean (options.rebroadcast);

        if (serverVersion <= VERSION_WITH_FIXED_STREAM_TYPE_SUPPORT) {
            out.writeUTF (options.channelPerformance.toString());
            out.writeUTF (options.compression.toString());
        } else {
            out.writeInt(options.channelPerformance.ordinal());
            out.writeInt(options.compression.ordinal());
        }
        out.writeBoolean (options.realTimeNotification);
        out.writeBoolean (options.versionTracking);
        out.writeBoolean (options.ordered);

        if (serverVersion >= VERSION_WITH_FIXED_STREAM_TYPE_SUPPORT)
            out.writeBoolean(options.restrictStreamType);

        if (serverVersion >= VERSION_WITH_SPACES_SUPPORT) {
            SerializationUtils.writeNullableString(options.space, out);
        }
    }

    public static void read(DataInputStream in, SelectionOptions options, int clientVersion) throws IOException {
        options.raw = true;
        options.live = in.readBoolean();
        options.reversed = in.readBoolean();
        options.allowLateOutOfOrder = in.readBoolean();
        options.shiftOffset = in.readLong();
        options.rebroadcast = in.readBoolean();

        if (clientVersion <= VERSION_WITH_FIXED_STREAM_TYPE_SUPPORT) {
            options.channelPerformance = Enum.valueOf(ChannelPerformance.class, in.readUTF());
            options.compression = Enum.valueOf(ChannelCompression.class, in.readUTF());
        } else {
            options.channelPerformance = ChannelPerformance.values()[in.readInt()];
            options.compression = ChannelCompression.values()[in.readInt()];
        }

        options.realTimeNotification = in.readBoolean();
        options.versionTracking = in.readBoolean();
        options.ordered = in.readBoolean();

        options.restrictStreamType = false;
        if (clientVersion >= 99)
            options.restrictStreamType = in.readBoolean();

        if (clientVersion >= VERSION_WITH_SPACES_SUPPORT) {
            options.space = SerializationUtils.readNullableString(in);
        }
    }
}
