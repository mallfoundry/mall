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

package org.mallfoundry.security.access.repository.jpa;

import org.mallfoundry.security.access.Principal;
import org.mallfoundry.security.access.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.Optional;

public interface JpaAccessControlRepository extends JpaRepository<JpaAccessControl, String> {

    Optional<JpaAccessControl> findByResource(Resource resource);

    @Query("select ac from JpaAccessControl ac JOIN ac.entries ace where ac.resource = ?1 and ace.principal in (?2)")
    Optional<JpaAccessControl> findByResourceAndPrincipals(JpaResource resource, Collection<Principal> principals);
}
