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

import lombok.Getter;
import org.mallfoundry.security.access.AllAuthorities;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import java.util.Collection;

public class SystemUserAuthentication extends UsernamePasswordAuthenticationToken {

    private static final String PASSWORD = "N/A";

    private static final Collection<GrantedAuthority> AUTHORITIES =
            AuthorityUtils.createAuthorityList(AllAuthorities.SUPER_ADMIN, AllAuthorities.ADMIN);

    @Getter
    private final Authentication suspended;

    public SystemUserAuthentication(Authentication suspended, SystemUser systemUser) {
        super(systemUser, PASSWORD, AUTHORITIES);
        this.suspended = suspended;
    }
}
