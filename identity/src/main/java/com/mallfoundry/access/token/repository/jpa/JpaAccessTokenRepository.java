/*
 * Copyright 2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mallfoundry.access.token.repository.jpa;

import com.mallfoundry.access.token.AccessToken;
import com.mallfoundry.access.token.AccessTokenRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface JpaAccessTokenRepository
        extends AccessTokenRepository,
        JpaRepository<AccessToken, String> {

    @Modifying
    @Query("delete from AccessToken where username = ?1")
    @Override
    void deleteByUsername(String username);

    @Modifying
    @Query("delete from AccessToken where token = ?1")
    @Override
    void deleteByToken(String tokenValue);

    @Query("from AccessToken where token = ?1")
    @Override
    Optional<AccessToken> findByToken(String tokenValue);

    @Query("from AccessToken where username = ?1")
    @Override
    Optional<AccessToken> findByUsername(String username);
}
