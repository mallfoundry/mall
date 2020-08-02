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

import org.mallfoundry.data.SliceList;

public interface FollowService {

    FollowProductQuery createFollowProductQuery();

    FollowStoreQuery createFollowStoreQuery();

    FollowProduct followProduct(String followerId, String productId);

    void unfollowProduct(String followerId, String productId);

    boolean checkFollowingProduct(String followerId, String productId);

    SliceList<FollowProduct> getFollowingProducts(FollowProductQuery query);

    long getFollowingProductCount(FollowProductQuery query);

    long getProductFollowerCount(String productId);

    void followStore(String followerId, String storeId);

    void unfollowStore(String followerId, String storeId);

    boolean checkFollowingStore(String followerId, String storeId);

    SliceList<FollowStore> getFollowingStores(FollowStoreQuery query);

    long countFollowingStores(FollowStoreQuery query);
}