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

package org.mallfoundry.security.token;

import org.apache.commons.lang3.StringUtils;
import org.mallfoundry.http.ErrorMessage;
import org.mallfoundry.security.authentication.Credentials;
import org.mallfoundry.security.authentication.DefaultCaptchaCredentials;
import org.mallfoundry.security.authentication.DefaultMobilePasswordCredentials;
import org.mallfoundry.security.authentication.DefaultUsernamePasswordCredentials;
import org.mallfoundry.security.authentication.CredentialsType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/v1")
public class AccessTokenEndpointV1 {

    private final AccessTokenAuthenticationManager tokenAuthenticationManager;

    private final AccessTokenService tokenService;

    public AccessTokenEndpointV1(AccessTokenAuthenticationManager tokenAuthenticationManager,
                                 AccessTokenService tokenService) {
        this.tokenAuthenticationManager = tokenAuthenticationManager;
        this.tokenService = tokenService;
    }

    @PostMapping("/token")
    public ResponseEntity<?> authenticate(@RequestParam(name = "grant_type", required = false) String grantType,
                                          HttpServletRequest request) {
        var type = CredentialsType.valueOf(StringUtils.upperCase(grantType));
        Credentials credentials = null;
        if (type == CredentialsType.USERNAME_PASSWORD) {
            credentials = new DefaultUsernamePasswordCredentials(request.getParameter("username"), request.getParameter("password"));
        } else if (type == CredentialsType.MOBILE_PASSWORD) {
            credentials = new DefaultMobilePasswordCredentials(
                    request.getParameter("country_code"),
                    request.getParameter("mobile"),
                    request.getParameter("password"));
        } else if (type == CredentialsType.CAPTCHA) {
            credentials = new DefaultCaptchaCredentials(request.getParameter("token"), request.getParameter("code"));
        }
        try {
            var token = this.tokenAuthenticationManager.authenticate(credentials);
            return ResponseEntity.ok(token);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorMessage.error("invalid_grant", e.getMessage()));
        }
    }

    @DeleteMapping("/token")
    public void deleteToken(HttpServletRequest request) {
        String token = AccessTokenConverter.convert(request);
        this.tokenService.deleteAccessToken(token);
    }
}