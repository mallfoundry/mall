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

package org.mallfoundry.catalog;

import org.mallfoundry.data.Pageable;
import org.mallfoundry.data.PageableBuilder;
import org.mallfoundry.data.PageableBuilderSupport;

import java.util.Set;

public interface BrandQuery extends Pageable {

    Set<String> getCategories();

    void setCategories(Set<String> categories);

    default Builder toBuilder() {
        return new BuilderSupport(this) {
        };
    }

    interface Builder extends PageableBuilder<BrandQuery, Builder> {
        Builder categories(Set<String> categories);
    }

    abstract class BuilderSupport extends PageableBuilderSupport<BrandQuery, Builder> implements Builder {

        private final BrandQuery query;

        public BuilderSupport(BrandQuery query) {
            super(query);
            this.query = query;
        }

        @Override
        public Builder categories(Set<String> categories) {
            this.query.setCategories(categories);
            return this;
        }

        @Override
        public BrandQuery build() {
            return this.query;
        }
    }
}
