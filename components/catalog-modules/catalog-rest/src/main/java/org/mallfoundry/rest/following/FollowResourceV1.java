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

package org.mallfoundry.rest.following;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.mallfoundry.data.SliceList;
import org.mallfoundry.following.FollowingProduct;
import org.mallfoundry.following.FollowingProductService;
import org.mallfoundry.following.FollowingStore;
import org.mallfoundry.following.FollowingStoreService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Customer Follow")
@RestController
@RequestMapping("/v1")
public class FollowResourceV1 {

    private final FollowingProductService productService;

    private final FollowingStoreService storeService;

    public FollowResourceV1(FollowingProductService productService, FollowingStoreService storeService) {
        this.productService = productService;
        this.storeService = storeService;
    }

    @PostMapping("/customers/{customer_id}/following-products/{product_id}")
    public void followProduct(@PathVariable("customer_id") String customerId,
                              @PathVariable("product_id") String productId) {
        this.productService.followProduct(customerId, productId);
    }

    @DeleteMapping("/customers/{customer_id}/following-products/{product_id}")
    public void unfollowProduct(@PathVariable("customer_id") String customerId,
                                @PathVariable("product_id") String productId) {
        this.productService.unfollowProduct(customerId, productId);
    }

    @GetMapping("/customers/{customer_id}/following-products/{product_id}")
    public boolean checkFollowingProduct(@PathVariable("customer_id") String customerId,
                                         @PathVariable("product_id") String productId) {
        return this.productService.checkFollowingProduct(customerId, productId);
    }

    @GetMapping("/customers/{customer_id}/following-products")
    public SliceList<FollowingProduct> getFollowProducts(@PathVariable("customer_id") String customerId) {
        return this.productService.getFollowingProducts(this.productService.createFollowProductQuery()
                .toBuilder().followerId(customerId).build());
    }

    @GetMapping("/customers/{customer_id}/following-products/count")
    public long getFollowingProductCount(@PathVariable("customer_id") String customerId) {
        return this.productService.countFollowingProducts(this.productService.createFollowProductQuery()
                .toBuilder().followerId(customerId).build());
    }

    @GetMapping("/products/{product_id}/followers/count")
    public long getProductFollowerCount(@PathVariable("product_id") String productId) {
        return this.productService.countFollowingProducts(this.productService.createFollowProductQuery()
                .toBuilder().productId(productId).build());
    }

    @PostMapping("/customers/{customer_id}/following-stores/{store_id}")
    public void followStore(@PathVariable("customer_id") String customerId,
                            @PathVariable("store_id") String storeId) {
        this.storeService.followStore(customerId, storeId);
    }

    @DeleteMapping("/customers/{customer_id}/following-stores/{store_id}")
    public void unfollowStore(@PathVariable("customer_id") String customerId,
                              @PathVariable("store_id") String storeId) {
        this.storeService.unfollowStore(customerId, storeId);
    }

    @GetMapping("/customers/{customer_id}/following-stores/{store_id}")
    public boolean checkFollowingStore(@PathVariable("customer_id") String customerId,
                                       @PathVariable("store_id") String storeId) {
        return this.storeService.checkFollowingStore(customerId, storeId);
    }

    @GetMapping("/customers/{customer_id}/following-stores")
    public SliceList<FollowingStore> getFollowStores(@PathVariable("customer_id") String customerId) {
        return this.storeService.getFollowingStores(this.storeService.createFollowStoreQuery()
                .toBuilder().followerId(customerId).build());
    }

    @GetMapping("/customers/{customer_id}/following-stores/count")
    public long getFollowingStoreCount(@PathVariable("customer_id") String customerId) {
        return this.storeService.countFollowingStores(
                this.storeService.createFollowStoreQuery()
                        .toBuilder().followerId(customerId).build());
    }

    @GetMapping("/stores/{store_id}/followers/count")
    public long getStoreFollowerCount(@PathVariable("store_id") String storeId) {
        return this.storeService.countFollowingStores(
                this.storeService.createFollowStoreQuery()
                        .toBuilder().storeId(storeId).build());
    }
}
