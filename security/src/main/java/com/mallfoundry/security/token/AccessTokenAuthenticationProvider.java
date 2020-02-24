/*
 * Copyright 2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mallfoundry.security.token;

import com.mallfoundry.access.token.AccessTokenService;
import com.mallfoundry.identity.User;
import com.mallfoundry.identity.UserService;
import com.mallfoundry.security.SecurityUser;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
public class AccessTokenAuthenticationProvider implements AuthenticationProvider {

    private final AccessTokenService tokenService;

    private final UserService userService;

    public AccessTokenAuthenticationProvider(AccessTokenService tokenService,
                                             UserService userService) {
        this.tokenService = tokenService;
        this.userService = userService;
    }

    @Override
    @Transactional
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        AccessTokenAuthentication tokenAuthentication = (AccessTokenAuthentication) authentication;
        String username =
                this.tokenService
                        .readAccessToken(tokenAuthentication.getName())
                        .orElseThrow(() -> new BadCredentialsException("Bad credentials"))
                        .getUsername();
        Optional<User> userOptional = this.userService.getUser(username);
        SecurityUser securityUser = new SecurityUser(userOptional.orElseThrow());
        return new UsernamePasswordAuthenticationToken(securityUser, "N/A", securityUser.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return AccessTokenAuthentication.class.isAssignableFrom(authentication);
    }
}
