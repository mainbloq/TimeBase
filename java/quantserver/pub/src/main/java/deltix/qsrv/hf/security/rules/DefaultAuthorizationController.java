package deltix.qsrv.hf.security.rules;

import deltix.gflog.Log;
import deltix.gflog.LogEntry;
import deltix.gflog.LogFactory;
import deltix.gflog.LogLevel;
import deltix.qsrv.hf.security.SecurityConfigurator;
import deltix.util.security.AccessControlRule;
import deltix.util.security.UserDirectory;

import java.security.AccessControlException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

public class DefaultAuthorizationController implements ManagedAuthorizationController {

    private static final Log LOG = LogFactory.getLog(SecurityConfigurator.UAC_LOGGER_NAME);

    private static final String LOGPREFIX = SecurityConfigurator.LOGPREFIX;

    public static final String ANY_PERMISSION = "*";
    public static final String ANY_RESOURCE = "*";

    private final AccessControlRulesProcessor permissionChecker;

    public DefaultAuthorizationController(UserDirectory userDirectory, AccessControlRule[] rules) {
        permissionChecker = new AccessControlRulesProcessor(userDirectory.users(), userDirectory.groups(), rules);
    }

    @Override
    public void checkPermission(Principal principal, String permission, ProtectedResource resource)
        throws AccessControlException
    {
        if ( ! hasPermission(principal, permission, resource)) {
            String resourceName;
            if (resource instanceof NamedProtectedResource)
                resourceName = "\"" + ((NamedProtectedResource)resource).getKey() + '"';
            else
            if (resource instanceof OwnedProtectedResource)
                resourceName = "of user \"" + ((OwnedProtectedResource)resource).getOwner() + '"';
            else
                resourceName = String.valueOf(resource);

            throw new AccessControlException("Access to resource " + resourceName + " is denied for " + principal.getName());
        }
    }

    @Override
    public void checkPermission(Principal principal, String permission)
        throws AccessControlException
    {
        if ( ! hasPermission(principal, permission))
            throw new AccessControlException("Permission \"" + permission + "\" is denied for " + principal.getName());
    }

    @Override
    public boolean hasPermission(Principal principal, String permission, ProtectedResource resource) {
        boolean result = _hasPermission(principal, permission, resource);
        if (LOG.isEnabled(LogLevel.DEBUG)) {
            LogEntry log = LOG.log(LogLevel.DEBUG).append(LOGPREFIX).append("hasPermission (")
                    .append(appendable(principal)).append(", ")
                    .append(permission).append(", ");

            append(resource, log);

            log.append("): ")
            .append(result)
            .commit();
        }
        return result;
    }

    @Override
    public boolean hasPermissionOverPrincipal(Principal principal, String permission, String anotherPrincipal) {
        boolean result = _hasPermissionOverPrincipal(principal, permission, anotherPrincipal);
        if (LOG.isEnabled(LogLevel.DEBUG)) {
            LOG.log(LogLevel.DEBUG).append(LOGPREFIX).append("hasPermissionOverPrincipal (")
                    .append(appendable(principal)).append(", ")
                    .append(permission).append(", ")
                    .append(anotherPrincipal).append("): ")
                    .append(result)
                    .commit();
        }
        return result;
    }

    @Override
    public boolean hasPermission(Principal principal, String permission) {
        boolean result = _hasPermission(principal, permission);
        if (LOG.isEnabled(LogLevel.DEBUG)) {
            LOG.log(LogLevel.DEBUG).append(LOGPREFIX).append("hasPermission (")
                    .append(appendable(principal)).append(", ")
                    .append(permission).append("): ")
                    .append(result)
                    .commit();
        }
        return result;
    }

    private boolean _hasPermission(Principal principal, String permission, ProtectedResource resource) {
        if (principal == null)
            return false;

        // Resources that do not have owners belong to people who have system level permissions
        if (resource == null)
            return _hasPermission(principal, permission);


        AccessControlRulesProcessor checker = permissionChecker; //freeze
        UserPermissionsSet userPermissions = checker.get(principal.getName());

        if(userPermissions == null)
            return false;


        AllowDenyUserPermission r = userPermissions.get(permission);
        if (r == null)
            r = userPermissions.get(ANY_PERMISSION); // { User, *, ...}

        if (r != null) {
            if (r.match(resource)) {
                return true;
            }
        }

        return false;
    }

    private boolean _hasPermissionOverPrincipal(Principal principal, String permission, String anotherPrincipal) {
        if (principal == null)
            return false;

        // Resources that do not have owners belong to people who have system level permissions
        if (anotherPrincipal == null) {
            return hasPermission(principal, permission);
        }

        AccessControlRulesProcessor checker = permissionChecker; //freeze
        UserPermissionsSet userPermissions = checker.get(principal.getName());

        if(userPermissions != null) {
            AllowDenyUserPermission r = userPermissions.get(permission);
            if (r == null)
                r = userPermissions.get(ANY_PERMISSION); // { User, *, ...}

            if (r != null) {
                if (r.match(anotherPrincipal, AccessControlRule.ResourceType.Principal)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean _hasPermission(Principal principal, String permission) {
        if (principal == null)
            return false;

        AccessControlRulesProcessor checker = permissionChecker; // freeze
        UserPermissionsSet rules = checker.get(principal.getName());

        if(rules == null)
            return false;

        AllowDenyUserPermission r = rules.get(permission);
        if (r == null)
            r = rules.get(ANY_PERMISSION);

        if (r != null) {
            if (r.matchAny())
                return true;
        }

        return false;
    }

    @Override
    public List<AccessControlEntry> getEffectivePermissions() {
        final List<AccessControlEntry> result = new ArrayList<>(32);

        visit(new PermissionVisitor() {
            @Override
            public void visit(AccessControlRule.RuleEffect ruleEffect, String user, String permission, String resource, AccessControlRule.ResourceType type) {
                String resourceDisplayName = resource;
                if (type != null)
                    resourceDisplayName += ":" + type;
                result.add(new AccessControlEntry(ruleEffect, user, permission, resourceDisplayName));
            }
        });
        return result;
    }

    @Override
    public String toString() {
        return permissionChecker.toString();
    }

    @Override
    public void visit(PermissionVisitor visitor) {
        permissionChecker.visit(visitor);
    }

    private static String appendable(Principal principal) {
        if (principal == null)
            return "null";
        return principal.getName();
    }


    private static void append(ProtectedResource resource, LogEntry log) {
        if (resource == null) {
            log.append("null");
        } else {
            boolean appended = false;
            if (resource instanceof OwnedProtectedResource) {
                String owner = ((OwnedProtectedResource) resource).getOwner();
                if (owner != null)
                    log.append(owner);
                else
                    log.append("<owner:null>");
                appended = true;
            }

            if (resource instanceof NamedProtectedResource) {
                if (appended)
                    log.append(' '); // separator

                String name = ((NamedProtectedResource) resource).getKey();
                if (name != null)
                    log.append(name);
                else
                    log.append("<name:null>");
                appended = true;
            }

            if (!appended) {
                log.append(String.valueOf(resource));
            }
        }
    }
}
