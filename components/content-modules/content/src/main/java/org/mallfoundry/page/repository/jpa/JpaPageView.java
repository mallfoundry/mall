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

package org.mallfoundry.page.repository.jpa;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.mallfoundry.page.PageType;
import org.mallfoundry.page.PageView;
import org.mallfoundry.page.PageViewSupport;
import org.springframework.beans.BeanUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "mf_page_view")
public class JpaPageView extends PageViewSupport {

    @NotBlank
    @Id
    @Column(name = "id_")
    private String id;

    @NotBlank
    @Column(name = "tenant_id_")
    private String tenantId;

    @NotBlank
    @Column(name = "store_id_")
    private String storeId;

    @NotBlank
    @Column(name = "page_id_")
    private String pageId;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "page_type_")
    private PageType pageType;

    @NotBlank
    @Column(name = "browser_id_")
    private String browserId;

    @NotBlank
    @Column(name = "browser_ip_")
    private String browserIp;

    @NotNull
    @Column(name = "browsing_time_")
    private Date browsingTime;

    public JpaPageView(String id) {
        this.id = id;
    }

    public static JpaPageView of(PageView pageView) {
        if (pageView instanceof JpaPageView) {
            return (JpaPageView) pageView;
        }
        var target = new JpaPageView(pageView.getId());
        BeanUtils.copyProperties(pageView, target);
        return target;
    }
}
