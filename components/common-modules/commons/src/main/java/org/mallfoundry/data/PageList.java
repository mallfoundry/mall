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

package org.mallfoundry.data;

import org.springframework.data.domain.Page;
import org.springframework.data.util.CastUtils;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PageList<T> implements SliceList<T> {

    private int size;

    private List<T> elements;

    private long totalSize;

    public PageList(List<T> elements) {
        this.elements = Objects.isNull(elements)
                ? Collections.emptyList()
                : Collections.unmodifiableList(elements);
        this.size = this.elements.size();
    }

    public static <T> PageList<T> of(List<T> elements) {
        return new PageList<>(elements);
    }

    public static <E, T> PageList<T> of(Page<E> page) {
        return CastUtils.cast(PageList.of(page.getContent())
                .size(page.getSize())
                .totalSize(page.getTotalElements()));
    }

    public static <T> PageList<T> empty() {
        return new EmptyPage<>();
    }

    public PageList<T> size(int size) {
        this.size = size;
        return this;
    }

    public PageList<T> totalSize(long totalSize) {
        this.totalSize = totalSize;
        return this;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public int getTotalPages() {
        var size = this.getSize();
        var totalSize = this.getTotalSize();
        return size == 0 ? 1 : (int) Math.ceil((double) totalSize / (double) size);
    }

    @Override
    public long getTotalSize() {
        return totalSize;
    }

    @Override
    public <R> SliceList<R> map(Function<T, R> mapper) {
        return new PageList<>(this.elements.stream().map(mapper).collect(Collectors.toUnmodifiableList()))
                .totalSize(this.totalSize);
    }

    @Override
    public <R> SliceList<R> elements(List<R> elements) {
        return new PageList<>(elements).totalSize(this.totalSize);
    }

    @Override
    public <R> SliceList<R> cast() {
        return CastUtils.cast(this);
    }

    public void setTotalSize(long totalSize) {
        this.totalSize = totalSize;
    }

    @Override
    public List<T> getElements() {
        return elements;
    }

    public void setElements(List<T> elements) {
        this.elements = elements;
    }

    private static class EmptyPage<E> extends PageList<E> {

        EmptyPage() {
            super(Collections.emptyList());
        }

        @Override
        public <R> SliceList<R> map(Function<E, R> mapper) {
            return CastUtils.cast(this);
        }
    }

}
