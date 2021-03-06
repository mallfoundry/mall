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

package org.mallfoundry.security.access;

import org.mallfoundry.i18n.Messages;

import java.util.List;

import static org.mallfoundry.i18n.MessageHolder.message;

public abstract class AccessMessages {

    private static final String SECURITY_ACCESS_CONTROL_NOT_FOUND_MESSAGE_CODE_KEY = Messages.getKeys(AccessControl.class).codeKey("notFound");

    private static final String SECURITY_ACCESS_PRINCIPAL_NOT_FOUND_MESSAGE_CODE_KEY = Messages.getKeys(Principal.class).codeKey("notFound");

    private static final String SECURITY_ACCESS_RESOURCE_NOT_FOUND_MESSAGE_CODE_KEY = Messages.getKeys(Resource.class).codeKey("notFound");

    public abstract static class AccessControl {
        public static String notFound(String type, String name) {
            return message(SECURITY_ACCESS_CONTROL_NOT_FOUND_MESSAGE_CODE_KEY, List.of(type, name), String.format("AccessControl %s %s not found", type, name));
        }
    }

    public abstract static class Principal {
        public static String notFound(String type, String name) {
            return message(SECURITY_ACCESS_PRINCIPAL_NOT_FOUND_MESSAGE_CODE_KEY, List.of(type, name), String.format("Principal %s %s not found", type, name));
        }
    }

    public abstract static class Resource {
        public static String notFound(String type, String name) {
            return message(SECURITY_ACCESS_RESOURCE_NOT_FOUND_MESSAGE_CODE_KEY, List.of(type, name), String.format("Resource %s %s not found", type, name));
        }
    }
}
