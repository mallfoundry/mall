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

package org.mallfoundry.order;

import com.fasterxml.jackson.annotation.JsonValue;

public enum OrderStatus {
    INCOMPLETE /* 准备下单 */,
    PENDING /* 开始结算流程 */,
    AWAITING_PAYMENT /* 等待付款 */,
    AWAITING_FULFILLMENT /* 等待打包 */,

    AWAITING_SHIPMENT /* 等待揽收 */,
    PARTIALLY_SHIPPED /* 部分已发货 */,
    SHIPPED /* 已发货 */,

    AWAITING_PICKUP /* 等待收货 */,

    AWAITING_REFUND /* 等待退款 */,
    PARTIALLY_REFUNDED /* 部分已退款 */,
    REFUNDED /* 已退款 */,

    AWAITING_REVIEW /* 等待评论 */,
    REVIEWED /* 已评论 */,

    /*MANUAL_VERIFICATION_REQUIRED *//* 人工已验证 *//*,*/
    DISPUTED /* 有争议的 */,
    CANCELLED /* 已取消 */,
    COMPLETED /* 完成 */,
    DECLINED /* 已拒绝 */,
    CLOSED /* 已关闭 */;

    @JsonValue
    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}
