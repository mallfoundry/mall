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

package org.mallfoundry.rest.catalog;

import lombok.Getter;
import lombok.Setter;
import org.mallfoundry.catalog.Category;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class CategoryResponse extends CategoryRequest {

    private String id;

    private Integer position;

    private List<CategoryResponse> children;

    public CategoryResponse(Category category) {
        this(category, 0);
    }

    public CategoryResponse(Category category, int level) {
        BeanUtils.copyProperties(category, this, "children");
        if (0 < level) {
            this.children =
                    category.getChildren()
                            .stream()
                            .map(child -> new CategoryResponse(child, level - 1))
                            .collect(Collectors.toUnmodifiableList());
        }
    }
}
