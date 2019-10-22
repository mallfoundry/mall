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

package com.mallfoundry.identity.infrastructure.persistence.mybatis;

import com.mallfoundry.identity.domain.User;
import com.mallfoundry.identity.domain.UserRepository;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryMybatis implements UserRepository {

    @Override
    public void save(User user) {

    }

    @Override
    public void update(User user) {

    }

    @Override
    public void delete(String username) {

    }

    @Override
    public User findByUsername(String username) {
        return null;
    }
}
