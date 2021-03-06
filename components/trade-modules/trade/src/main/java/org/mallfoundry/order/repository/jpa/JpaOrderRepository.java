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

package org.mallfoundry.order.repository.jpa;

import org.apache.commons.collections4.CollectionUtils;
import org.mallfoundry.order.OrderQuery;
import org.mallfoundry.util.CaseUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.Predicate;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public interface JpaOrderRepository extends JpaRepository<JpaOrder, String>, JpaSpecificationExecutor<JpaOrder> {

    default Specification<JpaOrder> createSpecification(OrderQuery orderQuery) {
        return (Specification<JpaOrder>) (root, query, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();

            if (Objects.nonNull(orderQuery.getCustomerId())) {
                predicate.getExpressions().add(criteriaBuilder.equal(root.get("customerId"), orderQuery.getCustomerId()));
            }
            if (Objects.nonNull(orderQuery.getStoreId())) {
                predicate.getExpressions().add(criteriaBuilder.equal(root.get("storeId"), orderQuery.getStoreId()));
            }
            if (CollectionUtils.isNotEmpty(orderQuery.getIds())) {
                predicate.getExpressions().add(criteriaBuilder.in(root.get("id")).value(orderQuery.getIds()));
            }
            if (Objects.nonNull(orderQuery.getName())) {
                predicate.getExpressions().add(criteriaBuilder.like(root.get("name"), "%" + orderQuery.getName() + "%"));
            }
            if (CollectionUtils.isNotEmpty(orderQuery.getStatuses())) {
                predicate.getExpressions().add(criteriaBuilder.in(root.get("status")).value(orderQuery.getStatuses()));
            }
            if (CollectionUtils.isNotEmpty(orderQuery.getDisputeStatuses())) {
                predicate.getExpressions().add(criteriaBuilder.in(root.get("disputeStatus")).value(orderQuery.getDisputeStatuses()));
            }
            if (CollectionUtils.isNotEmpty(orderQuery.getReviewStatuses())) {
                predicate.getExpressions().add(criteriaBuilder.in(root.get("reviewStatus")).value(orderQuery.getReviewStatuses()));
            }
            if (CollectionUtils.isNotEmpty(orderQuery.getTypes())) {
                predicate.getExpressions().add(criteriaBuilder.in(root.get("type")).value(orderQuery.getTypes()));
            }
            if (CollectionUtils.isNotEmpty(orderQuery.getSources())) {
                predicate.getExpressions().add(criteriaBuilder.in(root.get("source")).value(orderQuery.getSources()));
            }
            if (CollectionUtils.isNotEmpty(orderQuery.getPaymentMethods())) {
                predicate.getExpressions().add(criteriaBuilder.in(root.get("paymentMethod")).value(orderQuery.getPaymentMethods()));
            }
            if (Objects.nonNull(orderQuery.getPlacedTimeMin())) {
                predicate.getExpressions().add(criteriaBuilder.greaterThanOrEqualTo(root.get("placedTime"), orderQuery.getPlacedTimeMin()));
            }
            if (Objects.nonNull(orderQuery.getPlacedTimeMax())) {
                predicate.getExpressions().add(criteriaBuilder.lessThanOrEqualTo(root.get("placedTime"), orderQuery.getPlacedTimeMax()));
            }
            if (Objects.nonNull(orderQuery.getPlacingExpiredTimeMin())) {
                predicate.getExpressions().add(criteriaBuilder.greaterThanOrEqualTo(root.get("placingExpiredTime"), orderQuery.getPlacingExpiredTimeMin()));
            }
            if (Objects.nonNull(orderQuery.getPlacingExpiredTimeMax())) {
                predicate.getExpressions().add(criteriaBuilder.lessThanOrEqualTo(root.get("placingExpiredTime"), orderQuery.getPlacingExpiredTimeMax()));
            }
            return predicate;
        };
    }

    private Sort createSort(OrderQuery query) {
        return Optional.ofNullable(query.getSort())
                .map(org.mallfoundry.data.Sort::getOrders)
                .filter(CollectionUtils::isNotEmpty)
                .map(orders -> Sort.by(orders.stream()
                        .peek(sortOrder -> sortOrder.setProperty(CaseUtils.camelCase(sortOrder.getProperty())))
                        .map(sortOrder -> sortOrder.getDirection().isDescending()
                                ? Sort.Order.desc(sortOrder.getProperty())
                                : Sort.Order.asc(sortOrder.getProperty()))
                        .collect(Collectors.toUnmodifiableList())))
                .orElseGet(() -> Sort.by("placedTime").descending());
    }

    default Page<JpaOrder> findAll(OrderQuery query) {
        var sort = this.createSort(query);
        return this.findAll(this.createSpecification(query), PageRequest.of(query.getPage() - 1, query.getLimit(), sort));
    }

    default long count(OrderQuery orderQuery) {
        return this.count(this.createSpecification(orderQuery));
    }
}
