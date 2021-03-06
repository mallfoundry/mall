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

package org.mallfoundry.catalog.collection;

import org.mallfoundry.catalog.product.Product;

import java.util.Date;

public abstract class CollectionSupport implements MutableCollection {

    @Override
    public void addProduct(Product product) {
        this.setProductsCount(this.getProductsCount() + 1);
    }

    @Override
    public void removeProduct(Product product) {
        this.setProductsCount(this.getProductsCount() - 1);
    }

    @Override
    public void create() {
        this.setCreatedTime(new Date());
    }

    @Override
    public Builder toBuilder() {
        return new BuilderSupport(this) {
        };
    }

    protected abstract static class BuilderSupport implements Builder {

        private final CollectionSupport collection;

        protected BuilderSupport(CollectionSupport collection) {
            this.collection = collection;
        }

        @Override
        public Builder id(String id) {
            this.collection.setId(id);
            return this;
        }

        @Override
        public Builder storeId(String storeId) {
            this.collection.setStoreId(storeId);
            return this;
        }

        @Override
        public Builder name(String name) {
            this.collection.setName(name);
            return this;
        }

        @Override
        public Collection build() {
            return this.collection;
        }
    }
}
