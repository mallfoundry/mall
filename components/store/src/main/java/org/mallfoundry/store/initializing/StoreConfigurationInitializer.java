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

package org.mallfoundry.store.initializing;

import lombok.Getter;
import lombok.Setter;
import org.mallfoundry.configuration.ConfigurationHolder;
import org.mallfoundry.store.Store;

@Getter
@Setter
public class StoreConfigurationInitializer implements StoreInitializer {

    private int position = INITIAL_POSITION;

    @Override
    public void doInitialize(Store store) {
        var stage = StoreInitializingResources.getStoreInitializing(store.getId()).addStage("商铺配置信息初始化");
        try {
            ConfigurationHolder.emptyConfiguration(store);
            stage.success();
        } catch (RuntimeException e) {
            stage.failure();
            throw e;
        }
    }
}
