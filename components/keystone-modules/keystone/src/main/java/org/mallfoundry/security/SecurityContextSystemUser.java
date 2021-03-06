/*
 * Copyright (C) 2019-2020 the original author or authors.
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */

package org.mallfoundry.security;

import lombok.AccessLevel;
import lombok.Getter;
import org.apache.commons.lang3.NotImplementedException;
import org.mallfoundry.identity.User;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.concurrent.Callable;

@Getter
public class SecurityContextSystemUser implements SystemUser {
    private final String id = "SystemUser";
    private final String username = "SystemUser";
    private final String nickname = "SystemUser";
    private final String tenantId = DEFAULT_TENANT_ID;
    private final String avatar = null;
    private final SubjectType type = SubjectType.SYSTEM_USER;

    @Getter(AccessLevel.PRIVATE)
    private SystemUserAuthentication authentication;

    @Override
    public void doRun(Runnable runnable) {
        try {
            this.switchTo();
            runnable.run();
        } finally {
            this.exit();
        }
    }

    @Override
    public <V> V doCall(Callable<V> callable) throws RuntimeException {
        try {
            this.switchTo();
            return callable.call();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            this.exit();
        }
    }

    private void switchTo() {
        var suspendedAuthentication = SecurityContextHolder.getContext().getAuthentication();
        this.authentication = new SystemUserAuthentication(suspendedAuthentication, this);
        SecurityContextHolder.getContext().setAuthentication(this.authentication);
    }

    public void exit() {
        var suspended = this.authentication.getSuspended();
        SecurityContextHolder.getContext().setAuthentication(suspended);
    }

    @Override
    public User toUser() {
        throw new NotImplementedException("toUser");
    }
}
