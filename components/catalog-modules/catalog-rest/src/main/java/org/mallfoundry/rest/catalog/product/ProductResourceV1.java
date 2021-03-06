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

package org.mallfoundry.rest.catalog.product;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.lang3.StringUtils;
import org.mallfoundry.catalog.product.Product;
import org.mallfoundry.catalog.product.ProductService;
import org.mallfoundry.catalog.product.ProductStatus;
import org.mallfoundry.catalog.product.ProductType;
import org.mallfoundry.data.SliceList;
import org.mallfoundry.inventory.InventoryStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Tag(name = "Products")
@RestController
@RequestMapping("/v1")
public class ProductResourceV1 {

    private final ProductService productService;

    public ProductResourceV1(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public SliceList<Product> getProducts(@RequestParam(name = "page", defaultValue = "1") Integer page,
                                          @RequestParam(name = "limit", defaultValue = "20") Integer limit,
                                          @RequestParam(name = "name", required = false) String name,
                                          @RequestParam(name = "store_id", required = false) String storeId,
                                          @RequestParam(name = "collections", required = false) Set<String> collections,
                                          @RequestParam(name = "types", required = false) Set<String> types,
                                          @RequestParam(name = "statuses", required = false) Set<String> statuses,
                                          @RequestParam(name = "inventory_statuses", required = false) Set<String> inventoryStatuses,
                                          @RequestParam(name = "min_price", required = false) BigDecimal minPrice,
                                          @RequestParam(name = "max_price", required = false) BigDecimal maxPrice,
                                          @RequestParam(name = "sort", required = false) String sort) {
        return this.productService.getProducts(this.productService.createProductQuery().toBuilder()
                .page(page).limit(limit)
                .sort(aSort -> aSort.from(sort))
                .name(name).storeId(storeId).priceMin(minPrice).priceMax(maxPrice)
                .types(() -> Stream.ofNullable(types).flatMap(Set::stream).filter(StringUtils::isNotEmpty)
                        .map(StringUtils::upperCase).map(ProductType::valueOf).collect(Collectors.toSet()))
                .statuses(() -> Stream.ofNullable(statuses).flatMap(Set::stream).filter(StringUtils::isNotEmpty)
                        .map(StringUtils::upperCase).map(ProductStatus::valueOf).collect(Collectors.toSet()))
                .inventoryStatuses(() -> Stream.ofNullable(inventoryStatuses).flatMap(Set::stream).filter(StringUtils::isNotEmpty)
                        .map(StringUtils::upperCase).map(InventoryStatus::valueOf).collect(Collectors.toSet()))
                .collections(collections).build());
    }

    @GetMapping("/products/count")
    public long countProducts(@RequestParam(name = "name", required = false) String name,
                              @RequestParam(name = "store_id", required = false) String storeId,
                              @RequestParam(name = "collections", required = false) Set<String> collections,
                              @RequestParam(name = "types", required = false) Set<String> types,
                              @RequestParam(name = "statuses", required = false) Set<String> statuses,
                              @RequestParam(name = "inventory_statuses", required = false) Set<String> inventoryStatuses,
                              @RequestParam(name = "min_price", required = false) BigDecimal minPrice,
                              @RequestParam(name = "max_price", required = false) BigDecimal maxPrice) {
        return this.productService.countProducts(this.productService.createProductQuery().toBuilder()
                .name(name).storeId(storeId).priceMin(minPrice).priceMax(maxPrice)
                .types(() -> Stream.ofNullable(types).flatMap(Set::stream).filter(StringUtils::isNotEmpty)
                        .map(StringUtils::upperCase).map(ProductType::valueOf).collect(Collectors.toSet()))
                .statuses(() -> Stream.ofNullable(statuses).flatMap(Set::stream).filter(StringUtils::isNotEmpty)
                        .map(StringUtils::upperCase).map(ProductStatus::valueOf).collect(Collectors.toSet()))
                .inventoryStatuses(() -> Stream.ofNullable(inventoryStatuses).flatMap(Set::stream).filter(StringUtils::isNotEmpty)
                        .map(StringUtils::upperCase).map(InventoryStatus::valueOf).collect(Collectors.toSet()))
                .collections(collections).build());
    }

    @GetMapping("/products/{id}")
    public Optional<Product> findProduct(@PathVariable("id") String id) {
        return this.productService.findProduct(id);
    }

    @PostMapping("/products")
    public Product addProduct(@RequestBody AddProductRequest request) {
        return this.productService.addProduct(request.assignTo(this.productService.createProduct(null)));
    }

    @PatchMapping("/products/{id}")
    public Product updateProduct(@PathVariable("id") String id, @RequestBody ProductRequest request) {
        return this.productService.updateProduct(request.assignTo(this.productService.createProduct(id)));
    }

    @DeleteMapping("/products/{id}")
    public void deleteProduct(@PathVariable("id") String id) {
        this.productService.deleteProduct(id);
    }

    @PostMapping("/products/{id}/publish")
    public void publishProduct(@PathVariable("id") String id) {
        this.productService.publishProduct(id);
    }

    @PostMapping("/products/publish/batch")
    public void publishProducts(@RequestBody Set<String> ids) {
        this.productService.publishProducts(ids);
    }

    @DeleteMapping("/products/{id}/publish")
    public void unpublishProduct(@PathVariable("id") String id) {
        this.productService.unpublishProduct(id);
    }

    @DeleteMapping("/products/publish/batch")
    public void unpublishProducts(@RequestBody Set<String> ids) {
        this.productService.unpublishProducts(ids);
    }
}
