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

package org.mallfoundry.page;

import org.mallfoundry.store.StoreOwnership;
import org.mallfoundry.util.ObjectBuilder;

import java.util.Date;

public interface PageView extends StoreOwnership, ObjectBuilder.ToBuilder<PageView.Builder> {

    String getId();

    void setId(String id);

    String getPageId();

    void setPageId(String pageId);

    PageType getPageType();

    void setPageType(PageType pageType);

    String getBrowserId();

    void setBrowserId(String browserId);

    String getBrowserIp();

    void setBrowserIp(String browserIp);

    Date getBrowsingTime();

    void browsing();

    interface Builder extends ObjectBuilder<PageView> {

        Builder id(String id);

        Builder pageId(String pageId);

        Builder pageType(PageType pageType);

        Builder browserId(String browserId);

        Builder browserIp(String browserIp);
    }
}
