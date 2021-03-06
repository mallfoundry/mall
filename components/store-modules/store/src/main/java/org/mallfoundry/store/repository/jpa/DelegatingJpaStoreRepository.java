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

package org.mallfoundry.store.repository.jpa;

import org.mallfoundry.data.PageList;
import org.mallfoundry.data.SliceList;
import org.mallfoundry.store.Store;
import org.mallfoundry.store.StoreId;
import org.mallfoundry.store.StoreQuery;
import org.mallfoundry.store.StoreRepository;
import org.springframework.data.util.CastUtils;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class DelegatingJpaStoreRepository implements StoreRepository {

    private final JpaStoreRepository repository;

    public DelegatingJpaStoreRepository(JpaStoreRepository repository) {
        this.repository = repository;
    }

    @Override
    public Store create(StoreId id) {
        return new JpaStore(id);
    }

    @Override
    public Store save(Store store) {
        return this.repository.save(JpaStore.of(store));
    }

    @Override
    public void delete(Store store) {
        this.repository.delete(JpaStore.of(store));
    }

    @Override
    public Optional<Store> findById(StoreId id) {
        return CastUtils.cast(this.repository.findById(id.getId()));
    }

    @Override
    public List<Store> findAllById(Collection<StoreId> ids) {
        return CastUtils.cast(
                this.repository.findAllById(
                        ids.stream().map(StoreId::getId).collect(Collectors.toUnmodifiableSet())));
    }

    @Override
    public SliceList<Store> findAll(StoreQuery query) {
        return PageList.of(this.repository.findAll(query));
    }
}
