package deltix.qsrv.hf.security.rules;

import deltix.qsrv.hf.security.SecurityConfigurator;
import deltix.qsrv.hf.security.TimeBasePermissions;
import deltix.util.lang.StringUtils;
import deltix.util.security.AccessControlRule;
import deltix.util.security.AccessControlRule.ResourceType;
import deltix.util.security.GroupEntry;
import deltix.util.text.WildcardMatcher;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;


/** Processes user-specified Access Control Rules and builds internal structures optimized for fast permission check */
class AccessControlRulesProcessor {
    private static final Logger LOGGER = SecurityConfigurator.LOGGER;
    private static final String LOGPREFIX = SecurityConfigurator.LOGPREFIX;

    private static final boolean USE_IMPLICIT_SELF_OWNERSHIP_PERMISSION = ! Boolean.getBoolean("Security.disableSelfOwnership");

    private final TreeMap<String, UserPermissionsSet> permissionByUser = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

    AccessControlRulesProcessor(Principal[] users, Principal[] groups, AccessControlRule[] rules) {
        // Each user has full permissions over his own creations
        if (USE_IMPLICIT_SELF_OWNERSHIP_PERMISSION)
            processImplicitSelfOwnershipRule(users, groups);

        loadPermissions(users, groups, rules);

        simplifyPermissionRules();
    }

    private void processImplicitSelfOwnershipRule(Principal[] knownUsers, Principal[] knownGroups) {
        for (Principal user : knownUsers) {
            getOrCreate(AccessControlRule.RuleEffect.Allow, user.getName(), DefaultAuthorizationController.ANY_PERMISSION).add(user.getName(), ResourceType.Principal, AccessControlRule.ResourceFormat.Text);
            checkGroupNameClash(user, knownGroups);
        }
    }

    private void loadPermissions(Principal[] knownUsers, Principal[] knownGroups, AccessControlRule[] rules) {
        for (AccessControlRule rule : rules) {
            AccessControlRule.ResourceFormat resourceFormat = rule.getResourceFormat() == null ? AccessControlRule.DEFAULT_RESOURCE_FORMAT : rule.getResourceFormat();
            AccessControlRule.ResourceType resourceType = rule.getResourceType() == null ? AccessControlRule.DEFAULT_RESOURCE_TYPE : rule.getResourceType();
            AccessControlRule.RuleEffect ruleEffect = rule.getEffect() == null ? AccessControlRule.DEFAULT_RULE_EFFECT : rule.getEffect();
            Principal principal = rule.getPrincipal();
            String permission = rule.getPermission();
            if (StringUtils.isEmpty(principal.getName()) || principal.getName().equals("*")) {
                LOGGER.log(Level.WARNING, LOGPREFIX + "Skipping rule have empty principal: {0}", rule);
                continue;
            }
            if (StringUtils.isEmpty(permission)) {
                LOGGER.log(Level.WARNING, LOGPREFIX + "Skipping rule that does not have permission key: {0}", rule);
                continue;
            }
            if (TimeBasePermissions.IMPERSONATE_PERMISSION.equals (permission) && resourceType == ResourceType.Stream) {
                LOGGER.log(Level.WARNING, LOGPREFIX + "Skipping IMPERSONATE rule with stream resource type: {0}", rule);
                continue;
            }

            List<String> currentRuleUsers = expandRuleUsers(principal, knownUsers, knownGroups);
            if ( ! currentRuleUsers.isEmpty()) {
                List<String> currentRuleResources = expandRuleResources(getResource(rule), resourceType, resourceFormat, knownUsers, knownGroups);

                for (String currentRuleUser : currentRuleUsers) {
                    UserPermission userPermission = getOrCreate(ruleEffect, currentRuleUser, permission);
                    if (currentRuleResources.isEmpty()) {
                        LOGGER.log(Level.WARNING, LOGPREFIX + "Skipping permission rule that does not have any matching resources: {0}", rule);
                    } else {
                        for (String currentRuleResource : currentRuleResources)
                            userPermission.add(currentRuleResource, resourceType, resourceFormat);
                    }
                }
            }
        }
    }

    private void simplifyPermissionRules() {
        for(UserPermissionsSet userPermissionsSet : permissionByUser.values()) {
            userPermissionsSet.simplifyPermissionRules();
        }
    }

    private static void checkGroupNameClash(Principal user, Principal[] knownGroups) {
        if (contains(knownGroups, user))
            LOGGER.log(Level.WARNING, LOGPREFIX + "Name \"{0}\" is used by both a user and a group (will ignore group)", user.getName());
    }

    private static String getResource(AccessControlRule rule) {
        String result = rule.getResource();
        if (result == null) {
            result = DefaultAuthorizationController.ANY_RESOURCE;
        } else {
            if (result.equals("null"))
                throw new IllegalArgumentException("Attempt to process rule for \"null\" resource: " + rule);
        }
        return result;
    }

    private static List<String> expandRuleResources(String resource, AccessControlRule.ResourceType resourceType, AccessControlRule.ResourceFormat resourceFormat, Principal[]knownUsers, Principal[] knownGroups) {
        assert resourceType != null;

        List<String> currentRuleResources = new ArrayList<>();

        if (resource.equals(DefaultAuthorizationController.ANY_RESOURCE)) {
            currentRuleResources.add(resource);
        } else if (resourceType == AccessControlRule.ResourceType.Principal) {
            if ( ! StringUtils.isEmpty(resource)) {
                boolean expanded = false;

                // Match users
                List<Principal> matchingUsers = match(knownUsers, resource, resourceFormat);
                if ( ! matchingUsers.isEmpty())
                    for (Principal matchingUser : matchingUsers) {
                        currentRuleResources.add(matchingUser.getName()); // user
                        expanded = true;
                    }

                // Match groups
                List<Principal> matchingGroups = match(knownGroups, resource, resourceFormat);
                if ( ! matchingGroups.isEmpty())
                    for (Principal matchingGroup:matchingGroups) {
                        appendGroupUsers(knownGroups, matchingGroup, currentRuleResources); // all users of specified group
                        expanded = true;
                    }

                if ( ! expanded) {
                    LOGGER.log(Level.INFO, LOGPREFIX + "Permission specifies unknown user or group: {0}", resource);
                }
            }
        } else {
            if ( ! StringUtils.isEmpty(resource))
                currentRuleResources.add(resource);
        }
        return currentRuleResources;
    }

    /**
     * @return List of 'atomic' users defined by given principal (which can be a group or just single user)
     */
    private static List<String>  expandRuleUsers(Principal principal, Principal[] knownUsers, Principal[] knownGroups) {
        List<String> currentRuleUsers = new ArrayList<>();
        if (principal != null) {
            if (contains(knownUsers, principal)) {
                currentRuleUsers.add(principal.getName());
            } else if (contains(knownGroups, principal)) {
                appendGroupUsers(knownGroups, principal, currentRuleUsers);
            } else {
                LOGGER.log(Level.WARNING, LOGPREFIX + "Skip permission resolving for unknown user or group: \"{0}\"", principal.getName());
            }
        }
        return currentRuleUsers;
    }

    private UserPermission getOrCreate(AccessControlRule.RuleEffect ruleEffect, String userId, String permissionId) {
        UserPermissionsSet userPermissions = permissionByUser.get(userId);
        if (userPermissions == null) {
            userPermissions = new UserPermissionsSet();
            permissionByUser.put(userId, userPermissions);
        }
        return userPermissions.getOrCreate(ruleEffect, permissionId);
    }

    UserPermissionsSet get(String userId) {
        return permissionByUser.get(userId);
    }

    /// Helper methods


    private static void appendGroupUsers(Principal[] groups, Principal group, List<String> result) {
        assert group != null;

        if (group instanceof GroupEntry) {
            for (Principal member : ((GroupEntry) group).getPrincipals()) {
                Principal subGroup = find (groups, member);
                if (subGroup != null)
                    appendGroupUsers (groups, subGroup, result);
                else
                    result.add(member.getName());
            }
        }
    }

    private static boolean contains(Principal[] principals, Principal principal) {
        return contains (principals, principal.getName(), AccessControlRule.ResourceFormat.Text);
    }

    private static boolean contains(Principal[] principals, String principal, AccessControlRule.ResourceFormat format) {
        switch (format) {
            case Text:
                for (Principal p : principals) {
                    if (p.getName().equalsIgnoreCase(principal))
                        return true;
                }
                break;
            case Wildcard:
                for (Principal p : principals) {
                    if (WildcardMatcher.match(p.getName(), principal))
                        return true;
                }
                break;
            case RegEx:
                Pattern pattern = Pattern.compile(principal);
                for (Principal p : principals) {
                    if (pattern.matcher(p.getName()).matches())
                        return true;
                }
                break;
        }
        return false;
    }

    private static Principal find (Principal[] principals, Principal principal) {
        for (Principal p : principals) {
            if (p.getName().equalsIgnoreCase(principal.getName())) {
                return p;
            }
        }
        return null;
    }


    private static List<Principal> match (Principal[] principals, String principal, AccessControlRule.ResourceFormat format) {
        List<Principal> result = new ArrayList<>();
        switch (format) {
            case Text:
                for (Principal p : principals) {
                    if (p.getName().equalsIgnoreCase(principal))
                        result.add(p);
                }
                break;
            case Wildcard:
                for (Principal p : principals) {
                    if (WildcardMatcher.match(p.getName(), principal))
                        result.add(p);
                }
                break;
            case RegEx:
                Pattern pattern = Pattern.compile(principal);
                for (Principal p : principals) {
                    if (pattern.matcher(p.getName()).matches())
                        result.add(p);
                }
                break;
        }
        return result;
    }

    void visit(ManagedAuthorizationController.PermissionVisitor visitor) {
        for(Map.Entry<String, UserPermissionsSet> entry : permissionByUser.entrySet()) {
            entry.getValue().visit (visitor, entry.getKey());
        }
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(256);

        visit(new ManagedAuthorizationController.PermissionVisitor() {
            @Override
            public void visit(AccessControlRule.RuleEffect ruleEffect, String user, String permission, String resource, AccessControlRule.ResourceType type) {
                if (sb.length() > 0)
                    sb.append('\n');
                sb.append(ruleEffect).append(' ')
                    .append(user).append(',')
                    .append(permission).append(',')
                    .append(resource);
                if (type != null)
                    sb.append(':').append(type);
            }
        });
        return sb.toString();
    }
}


