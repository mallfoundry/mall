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

package com.mallfoundry.topic.domain;

import java.util.UUID;

/**
 * Topic object identifier.
 */
public class TopicIdentifier {

    /**
     * Generate a new comment id.
     *
     * @return comment id
     */
    public static String newCommentId() {
        return uuid();
    }

    /**
     * Generate a new reply id.
     *
     * @return comment id
     */
    public static String newReplyId() {
        return uuid();
    }

    private static String uuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
