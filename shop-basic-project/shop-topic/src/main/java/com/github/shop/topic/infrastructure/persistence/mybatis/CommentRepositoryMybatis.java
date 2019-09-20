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

package com.github.shop.topic.infrastructure.persistence.mybatis;

import com.github.shop.topic.Comment;
import com.github.shop.topic.CommentRepository;
import org.springframework.stereotype.Repository;

@Repository
public class CommentRepositoryMybatis implements CommentRepository {

    private final CommentMapper commentMapper;

    public CommentRepositoryMybatis(CommentMapper commentMapper) {
        this.commentMapper = commentMapper;
    }

    @Override
    public void save(Comment comment) {
        this.commentMapper.insertComment(comment);
    }

    @Override
    public void delete(String commentId) {

    }
}
