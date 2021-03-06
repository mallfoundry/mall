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

package org.mallfoundry.processor;

import java.util.List;
import java.util.function.BiFunction;

public class ProcessorsPipeline<T, U, R extends U> {
    private final List<T> processors;
    private final BiFunction<T, U, R> function;

    public ProcessorsPipeline(List<T> processors, BiFunction<T, U, R> function) {
        this.processors = processors;
        this.function = function;
    }

    @SuppressWarnings("unchecked")
    public R apply(U u) {
        U result = u;
        for (var processor : this.processors) {
            result = this.function.apply(processor, result);
        }
        return (R) result;
    }
}
