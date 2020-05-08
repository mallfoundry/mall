package com.mallfoundry.acl;

import com.mallfoundry.security.acl.AccessControlService;
import com.mallfoundry.security.acl.Permission;
import com.mallfoundry.security.acl.Principal;
import com.mallfoundry.security.acl.Resource;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
public class Acl2Tests {

    @Autowired
    private AccessControlService acService;

    @Transactional
    @Rollback(false)
    @Test
    @WithUserDetails("tangzhi")
    public void testCreateAcl() {
        Principal principal = this.acService.createPrincipal("user", "1");
        Principal principal2 = this.acService.createPrincipal("user", "2");
        Permission read = this.acService.createPermission("READ");
        Resource userResource = this.acService.createResource(new User("test_1"));
        var accessControl = this.acService.createAccessControl(principal, userResource);
        accessControl.grant(principal2, read);
        this.acService.saveAccessControl(accessControl);
    }

    @Transactional
    @Rollback(false)
    @Test
    @WithUserDetails("tangzhi")
    public void testGrantAcl() {
        Principal principal2 = this.acService.createPrincipal("user", "2");
        Principal principal3 = this.acService.createPrincipal("user", "3");
        Permission delete = this.acService.createPermission("delete");
        Permission write = this.acService.createPermission("write");
        Resource userResource = this.acService.createResource(new User("test_1"));
        var accessControl = this.acService.getAccessControl(userResource).orElseThrow();
        accessControl.grant(principal2, delete);
        accessControl.grant(principal3, write);
        this.acService.saveAccessControl(accessControl);
    }


    @Transactional
    @Rollback(false)
    @Test
    @WithUserDetails("tangzhi")
    public void testGrantedAcl() {
        Principal principal2 = this.acService.createPrincipal("user", "2");
        Permission read = this.acService.createPermission("read");
        Resource userResource = this.acService.createResource(new User("test_1"));
        var accessControl = this.acService.getAccessControl(userResource).orElseThrow();
        var granted = accessControl.granted(principal2, read);
        System.out.println(granted);
    }

    @Transactional
    @Rollback(false)
    @Test
    @WithUserDetails("tangzhi")
    public void testGrantedAcl2() {
        Principal principal2 = this.acService.createPrincipal("user", "2");
        Permission read = this.acService.createPermission("read");
        Resource userResource = this.acService.createResource(new User("test_1"));
        var accessControl = this.acService.getAccessControl(userResource, List.of(principal2)).orElseThrow();
        var granted = accessControl.granted(principal2, read);
        System.out.println(granted);
    }


    @Getter
    @AllArgsConstructor
    static class User {
        private final String id;
    }
}
