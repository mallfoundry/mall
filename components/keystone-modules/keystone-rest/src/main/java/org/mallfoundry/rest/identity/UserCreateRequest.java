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

package org.mallfoundry.rest.identity;

import lombok.Getter;
import lombok.Setter;
import org.mallfoundry.identity.User;
import org.mallfoundry.identity.UserRegistration;

@Getter
@Setter
public class UserCreateRequest implements UserRegistration {
    private String nickname;
    private String password;
    private Mode mode;
    private String email;
    private String countryCode;
    private String phone;
    private String captchaToken;
    private String captchaCode;

    @Override
    public User assignToUser(User user) {
        return user.toBuilder().nickname(this.nickname).password(this.password)
                .countryCode(this.countryCode).phone(this.phone)
                .email(this.email).build();
    }
}
