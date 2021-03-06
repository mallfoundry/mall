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

import org.apache.commons.collections4.ListUtils;
import org.mallfoundry.data.SliceList;
import org.mallfoundry.identity.UserService;
import org.mallfoundry.processor.Processors;
import org.mallfoundry.store.Store;
import org.mallfoundry.store.StoreService;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DefaultFollowingStoreService implements FollowingStoreService, FollowingStoreProcessorInvoker {

    private List<FollowingStoreProcessor> processors = Collections.emptyList();

    private final UserService userService;

    private final StoreService storeService;

    private final StoreFollowingRepository storeFollowingRepository;

    public DefaultFollowingStoreService(UserService userService,
                                        StoreService storeService,
                                        StoreFollowingRepository storeFollowingRepository) {
        this.userService = userService;
        this.storeService = storeService;
        this.storeFollowingRepository = storeFollowingRepository;
    }

    public void setProcessors(List<FollowingStoreProcessor> processors) {
        this.processors = ListUtils.emptyIfNull(processors);
    }

    @Override
    public FollowingStoreQuery createFollowStoreQuery() {
        return new DefaultFollowingStoreQuery();
    }

    private FollowingStore requiredFollowingStore(StoreFollowing following) {
        return this.storeService.findStore(this.storeService.createStoreId(following.getStoreId()))
                .<FollowingStore>map(store -> new DelegatingImmutableFollowingStore(store, following))
                .orElseGet(() -> new NullFollowingStore(following));
    }

    private Follower getFollower(String followerId) {
        var userId = this.userService.createUserId(followerId);
        var user = this.userService.getUser(userId);
        return new DelegatingImmutableFollower(user);
    }

    @Override
    public void followStore(String followerId, String storeId) {
        var following = this.storeFollowingRepository.create(followerId, storeId);
        var followingStore = Function.<FollowingStore>identity()
                .<FollowingStore>compose(aStore -> this.invokePreProcessBeforeFollowStore(this.getFollower(followerId), aStore))
                .compose(this::requiredFollowingStore).apply(following);
        following.setStoreId(followingStore.getId());
        if (this.storeFollowingRepository.exists(following)) {
            throw new FollowingException("The follower has followed to this store");
        }
        following.following();
        this.storeFollowingRepository.save(following);
    }

    @Override
    public void unfollowStore(String followerId, String storeId) {
        var following = this.storeFollowingRepository.create(followerId, storeId);
        var followingStore = Function.<FollowingStore>identity()
                .<FollowingStore>compose(aStore -> this.invokePreProcessBeforeUnfollowStore(this.getFollower(followerId), aStore))
                .compose(this::requiredFollowingStore).apply(following);
        following.setStoreId(followingStore.getId());
        this.storeFollowingRepository.delete(following);
    }

    @Override
    public boolean checkFollowingStore(String followerId, String storeId) {
        var following = this.storeFollowingRepository.create(followerId, storeId);
        var followingStore = Function.<FollowingStore>identity()
                .<FollowingStore>compose(aStore -> this.invokePreProcessBeforeCheckFollowingStore(this.getFollower(followerId), aStore))
                .compose(this::requiredFollowingStore).apply(following);
        following.setStoreId(followingStore.getId());
        return this.storeFollowingRepository.exists(following);
    }

    private FollowingStore getFollowStore(List<Store> stores, StoreFollowing following) {
        return stores.stream()
                .filter(aStore -> Objects.equals(aStore.getId(), following.getStoreId()))
                .findFirst()
                .<FollowingStore>map(aStore -> new DelegatingImmutableFollowingStore(aStore, following))
                .orElseGet(() -> new NullFollowingStore(following));
    }

    @Override
    public SliceList<FollowingStore> getFollowingStores(FollowingStoreQuery query) {
        var sliceFollowings = Function.<SliceList<StoreFollowing>>identity()
                .compose(this.storeFollowingRepository::findAll)
                .compose(this::invokePreProcessBeforeGetFollowingStores)
                .apply(query);
        var storeIds = ListUtils.emptyIfNull(sliceFollowings.getElements()).stream()
                .map(StoreFollowing::getStoreId)
                .map(this.storeService::createStoreId)
                .collect(Collectors.toUnmodifiableList());
        if (CollectionUtils.isEmpty(storeIds)) {
            return sliceFollowings.elements(Collections.emptyList());
        }
        var stores = this.storeService.getStores(storeIds);
        var followStores = sliceFollowings.getElements().stream()
                .map(following -> this.getFollowStore(stores, following))
                .collect(Collectors.toUnmodifiableList());
        return sliceFollowings.elements(followStores);
    }

    @Override
    public long countFollowingStores(FollowingStoreQuery query) {
        return Function.<Long>identity()
                .compose(this.storeFollowingRepository::count)
                .compose(this::invokePreProcessBeforeCountFollowingStores)
                .apply(query);
    }

    @Override
    public FollowingStore invokePreProcessBeforeFollowStore(Follower follower, FollowingStore store) {
        return Processors.stream(this.processors)
                .<FollowingStore>map((processor, identity) -> processor.preProcessBeforeFollowStore(follower, identity))
                .apply(store);
    }

    @Override
    public FollowingStore invokePreProcessBeforeUnfollowStore(Follower follower, FollowingStore store) {
        return Processors.stream(this.processors)
                .<FollowingStore>map((processor, identity) -> processor.preProcessBeforeUnfollowStore(follower, identity))
                .apply(store);
    }

    @Override
    public FollowingStore invokePreProcessBeforeCheckFollowingStore(Follower follower, FollowingStore store) {
        return Processors.stream(this.processors)
                .<FollowingStore>map((processor, identity) -> processor.preProcessBeforeCheckFollowingStore(follower, identity))
                .apply(store);
    }

    @Override
    public FollowingStoreQuery invokePreProcessBeforeGetFollowingStores(FollowingStoreQuery query) {
        return Processors.stream(this.processors)
                .map(FollowingStoreProcessor::preProcessBeforeGetFollowingStores)
                .apply(query);
    }

    @Override
    public FollowingStoreQuery invokePreProcessBeforeCountFollowingStores(FollowingStoreQuery query) {
        return Processors.stream(this.processors)
                .map(FollowingStoreProcessor::preProcessBeforeCountFollowingStores)
                .apply(query);
    }
}
