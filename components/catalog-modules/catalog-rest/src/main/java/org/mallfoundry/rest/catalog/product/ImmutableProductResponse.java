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

import org.mallfoundry.catalog.OptionSelection;
import org.mallfoundry.catalog.option.Option;
import org.mallfoundry.catalog.product.Product;
import org.mallfoundry.catalog.product.ProductAttribute;
import org.mallfoundry.catalog.product.ProductException;
import org.mallfoundry.catalog.product.ProductOrigin;
import org.mallfoundry.catalog.product.ProductStatus;
import org.mallfoundry.catalog.product.ProductType;
import org.mallfoundry.catalog.product.ProductVariant;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ImmutableProductResponse extends Product {

    @Override
    default void setTotalSales(Long totalSales) throws ProductException {

    }

    @Override
    default void setMonthlySales(Long monthlySales) throws ProductException {

    }

    @Override
    default void addImageUrl(String imageUrl) {

    }

    @Override
    default void addVideoUrl(String url) {

    }

    @Override
    default ProductOrigin createOrigin() {
        return null;
    }

    @Override
    default void freeShipping() {

    }

    @Override
    default void adjustInventoryQuantity(String variantId, int quantityDelta) throws ProductException {

    }

    @Override
    default ProductVariant createVariant(String id) {
        return null;
    }

    @Override
    default void addVariant(ProductVariant variant) {

    }

    @Override
    default void addVariants(List<ProductVariant> variants) {

    }

    @Override
    default Optional<ProductVariant> findVariant(String variantId) {
        return Optional.empty();
    }

    @Override
    default void clearVariants() {

    }

    @Override
    default Option createOption(String id) {
        return null;
    }

    @Override
    default void addOption(Option option) {

    }

    @Override
    default ProductAttribute createAttribute() {
        return null;
    }

    @Override
    default ProductAttribute createAttribute(String name, String value) {
        return null;
    }

    @Override
    default ProductAttribute createAttribute(String namespace, String name, String value) {
        return null;
    }

    @Override
    default void addAttribute(ProductAttribute attribute) {

    }

    @Override
    default void addAttributes(List<ProductAttribute> attributes) {

    }

    @Override
    default void clearAttributes() {

    }

    @Override
    default void create() {

    }

    @Override
    default void removeImageUrl(String imageUrl) {

    }

    @Override
    default void removeVideoUrl(String url) {

    }

    @Override
    default Optional<ProductVariant> selectionVariant(List<OptionSelection> selections) {
        return Optional.empty();
    }

    @Override
    default void removeVariant(ProductVariant variant) {

    }

    @Override
    default void removeVariants(List<ProductVariant> variants) {

    }

    @Override
    default Optional<OptionSelection> selectOption(String name, String label) {
        return Optional.empty();
    }

    @Override
    default void updateOptions(List<Option> options) {

    }

    @Override
    default void removeAttribute(ProductAttribute attribute) {

    }

    @Override
    default void publish() {

    }

    @Override
    default void unpublish() {

    }

    @Override
    default Builder toBuilder() {
        return null;
    }

    @Override
    default ProductVariant getVariant(String variantId) {
        return null;
    }

    @Override
    default Optional<Option> getOption(String name) {
        return Optional.empty();
    }

    @Override
    default Optional<ProductAttribute> getAttribute(String namespace, String name) {
        return Optional.empty();
    }

    @Override
    default void setId(String id) {

    }

    @Override
    default void setName(String name) {

    }

    @Override
    default void setShortName(String shortName) {

    }

    @Override
    default void setType(ProductType type) {

    }

    @Override
    default void setStatus(ProductStatus status) {

    }

    @Override
    default void setDescription(String description) {

    }

    @Override
    default void setCategories(List<String> categories) {

    }

    @Override
    default void setBrandId(String brandId) {

    }

    @Override
    default void setCollections(Set<String> collections) {

    }

    @Override
    default void setImageUrls(List<String> imageUrls) {

    }

    @Override
    default void setVideoUrls(List<String> videoUrls) {

    }

    @Override
    default void setOrigin(ProductOrigin origin) {

    }

    @Override
    default void setFixedShippingCost(BigDecimal fixedShippingCost) throws ProductException {

    }

    @Override
    default void setShippingRateId(String shippingRateId) throws ProductException {

    }

    @Override
    default void setBody(String body) {

    }

    @Override
    default void setStoreId(String storeId) {

    }

    @Override
    default void setTenantId(String tenantId) {

    }
}
