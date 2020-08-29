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

package org.mallfoundry.order.aftersales;

import java.util.Date;

public interface MutableOrderDispute extends OrderDispute {

    void setItemStatus(ItemStatus itemStatus);

    void setStatus(OrderDisputeStatus status);

    void setApplyingExpiredTime(Date applyingExpiredTime);

    void setAppliedTime(Date appliedTime);

    void setCancelledTime(Date cancelledTime);

    void setDisapprovalReason(String disapprovalReason);

    void setDisapprovedTime(Date disapprovedTime);

    void setApprovedTime(Date approvedTime);

    void setSucceededTime(Date succeededTime);

    void setFailReason(String failReason);

    void setFailedTime(Date failedTime);
}
