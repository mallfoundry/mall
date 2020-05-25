/*
 * Copyright 2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.mallfoundry.follow.repository.jpa;

import org.mallfoundry.data.PageList;
import org.mallfoundry.data.SliceList;
import org.mallfoundry.follow.FollowProductQuery;
import org.mallfoundry.follow.FollowProductRepository;
import org.mallfoundry.follow.InternalFollowProduct;
import org.mallfoundry.follow.InternalFollowProductId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.Predicate;
import java.util.Objects;

@Repository
public interface JpaFollowProductRepository extends
        FollowProductRepository,
        JpaRepository<InternalFollowProduct, InternalFollowProductId>,
        JpaSpecificationExecutor<InternalFollowProduct> {

    @Override
    default SliceList<InternalFollowProduct> findAll(FollowProductQuery followQuery) {
        Page<InternalFollowProduct> page = this.findAll((Specification<InternalFollowProduct>) (root, query, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();
            if (Objects.nonNull(followQuery.getFollowerId())) {
                predicate.getExpressions().add(criteriaBuilder.equal(root.get("followerId"), followQuery.getFollowerId()));
            }
            return predicate;
        }, PageRequest.of(followQuery.getPage() - 1, followQuery.getLimit()));
        return PageList.of(page.getContent()).page(page.getNumber()).limit(followQuery.getLimit()).totalSize(page.getTotalElements());
    }

    @Override
    default long count(FollowProductQuery followQuery) {
        return this.count((Specification<InternalFollowProduct>) (root, query, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();
            if (Objects.nonNull(followQuery.getFollowerId())) {
                predicate.getExpressions().add(criteriaBuilder.equal(root.get("followerId"), followQuery.getFollowerId()));
            }
            return predicate;
        });
    }
}
