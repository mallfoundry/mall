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

package org.mallfoundry.catalog.product.review;

import org.mallfoundry.data.QueryBuilderSupport;
import org.mallfoundry.data.QuerySupport;

import java.util.Set;

public abstract class ReviewQuerySupport extends QuerySupport implements ReviewQuery {

    @Override
    public Builder toBuilder() {
        return new BuilderSupport(this) {
        };
    }

    protected abstract static class BuilderSupport
            extends QueryBuilderSupport<ReviewQuery, Builder> implements Builder {

        private final ReviewQuerySupport query;

        public BuilderSupport(ReviewQuerySupport query) {
            super(query);
            this.query = query;
        }

        @Override
        public Builder productId(String productId) {
            this.query.setProductId(productId);
            return this;
        }

        @Override
        public Builder variantId(String variantId) {
            this.query.setVariantId(variantId);
            return this;
        }

        @Override
        public Builder ratings(Set<Integer> ratings) {
            this.query.setRatings(ratings);
            return this;
        }
    }
}
