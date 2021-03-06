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

package org.mallfoundry.analytics.schema;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class DefaultObjectTypeManager implements ObjectTypeManager {

    private final ObjectTypeRepository repository;

    public DefaultObjectTypeManager(ObjectTypeRepository repository) {
        this.repository = repository;
    }

    @Override
    public ObjectType createObjectType(String typeId) {
        return this.repository.create(typeId);
    }

    @Transactional
    @Override
    public ObjectType addObjectType(ObjectType objectType) {
        return this.repository.save(objectType);
    }

    @Override
    public Optional<ObjectType> getObjectType(String typeId) {
        return this.repository.findById(typeId);
    }

    @Transactional
    @Override
    public void deleteObjectType(String typeId) {
        var objectType = this.repository.findById(typeId).orElseThrow();
        this.repository.delete(objectType);
    }
}
