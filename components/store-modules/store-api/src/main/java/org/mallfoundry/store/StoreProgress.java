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

package org.mallfoundry.store;

import com.fasterxml.jackson.annotation.JsonValue;
import org.mallfoundry.util.Position;

import java.util.Date;
import java.util.List;

public interface StoreProgress {

    ProgressState getState();

    List<ProgressStage> getStages();

    ProgressStage addStage(String message);

    void initializing();

    void initialized();

    void closing();

    void closed();

    void failed();

    enum ProgressState {
        NEW,
        INITIALIZING,
        INITIALIZED,
        CLOSING,
        CLOSED,
        FAILED;

        @JsonValue
        @Override
        public String toString() {
            return this.name().toLowerCase();
        }
    }

    interface ProgressStage extends Position {

        StageStatus getStatus();

        String getMessage();

        Date getOccurredTime();

        void success();

        void failure();

        enum StageStatus {
            PENDING, SUCCESS, FAILURE;

            @JsonValue
            @Override
            public String toString() {
                return this.name().toLowerCase();
            }
        }
    }
}
