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

package org.mallfoundry.edw.page;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ImmutablePageFactKey implements PageFactKey {

    private String tenantKey;

    private String storeKey;

    private String pageKey;

    private String browserKey;

    private String browserIpKey;

    private int dateKey;

    private int timeKey;

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof ImmutablePageFactKey)) {
            return false;
        }
        ImmutablePageFactKey that = (ImmutablePageFactKey) object;
        return dateKey == that.dateKey
                && timeKey == that.timeKey
                && Objects.equals(tenantKey, that.tenantKey)
                && Objects.equals(storeKey, that.storeKey)
                && Objects.equals(pageKey, that.pageKey)
                && Objects.equals(browserKey, that.browserKey)
                && Objects.equals(browserIpKey, that.browserIpKey);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tenantKey, storeKey, pageKey, browserKey, browserIpKey, dateKey, timeKey);
    }
}
