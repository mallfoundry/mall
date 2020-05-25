package org.mallfoundry.security.acl;

import java.io.Serializable;
import java.util.List;

public interface AccessControlEntry extends Serializable {

    Principal getPrincipal();

    List<Permission> getPermissions();

    void addPermission(Permission permission);

    void removePermission(Permission permission);

    boolean checkPermission(Permission permission);

    boolean checkAnyPermission(List<Permission> permissions);
}
