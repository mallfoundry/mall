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

package org.mallfoundry.following;

import org.mallfoundry.data.QueryBuilderSupport;
import org.mallfoundry.data.QuerySupport;

public abstract class FollowingProductQuerySupport extends QuerySupport implements FollowingProductQuery {

    @Override
    public Builder toBuilder() {
        return new BuilderSupport(this) {
        };
    }

    protected abstract static class BuilderSupport
            extends QueryBuilderSupport<FollowingProductQuery, Builder> implements Builder {
        private final FollowingProductQuerySupport query;

        public BuilderSupport(FollowingProductQuerySupport query) {
            super(query);
            this.query = query;
        }

        @Override
        public Builder followerId(String followerId) {
            this.query.setFollowerId(followerId);
            return this;
        }

        @Override
        public Builder productId(String productId) {
            this.query.setProductId(productId);
            return this;
        }
    }
}
