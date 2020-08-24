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

package org.mallfoundry.util;

import org.apache.commons.lang3.compare.ComparableUtils;

import java.math.BigDecimal;

public abstract class DecimalUtils {

    public static boolean equals(BigDecimal a, BigDecimal b) {
        return ComparableUtils.is(a).equalTo(b);
    }

    public static boolean notEquals(BigDecimal a, BigDecimal b) {
        return !equals(a, b);
    }

    public static boolean lessThan(BigDecimal a, BigDecimal b) {
        return ComparableUtils.is(a).lessThan(b);
    }

    public static boolean lessThanOrEqualTo(BigDecimal a, BigDecimal b) {
        return ComparableUtils.is(a).lessThanOrEqualTo(b);
    }

    public static boolean greaterThan(BigDecimal a, BigDecimal b) {
        return ComparableUtils.is(a).greaterThan(b);
    }

    public static boolean greaterThanOrEqualTo(BigDecimal a, BigDecimal b) {
        return ComparableUtils.is(a).greaterThanOrEqualTo(b);
    }
}
