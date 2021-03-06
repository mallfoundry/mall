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

import org.mallfoundry.data.SliceList;
import org.mallfoundry.order.dispute.OrderRefund;
import org.mallfoundry.order.shipping.OrderShipment;

import java.util.List;

/**
 * 订单对象处理器。
 *
 * @author Zhi Tang
 */
public interface OrderProcessor {

    default Order postProcessAfterGetOrder(Order order) {
        return order;
    }

    default OrderQuery preProcessBeforeGetOrders(OrderQuery query) {
        return query;
    }

    default SliceList<Order> postProcessAfterGetOrders(SliceList<Order> orders) {
        return orders;
    }

    default List<Order> preProcessBeforePlaceOrders(List<Order> orders) {
        return orders;
    }

    default List<Order> preProcessAfterPlaceOrders(List<Order> orders) {
        return orders;
    }

    default List<Order> postProcessAfterPlaceOrders(List<Order> orders) {
        return orders;
    }

    default Order preProcessBeforeUpdateOrder(Order order) {
        return order;
    }

    default Order preProcessAfterUpdateOrder(Order order) {
        return order;
    }

    default List<OrderDiscount> preProcessBeforeDiscountOrder(Order order, List<OrderDiscount> discounts) {
        return discounts;
    }

    default Order preProcessBeforeFulfilOrder(Order order) {
        return order;
    }

    default String preProcessBeforeSignOrder(Order order, String message) {
        return message;
    }

    default Order preProcessBeforeReceiptOrder(Order order) {
        return order;
    }

    default String preProcessBeforeCancelOrder(Order order, String reason) {
        return reason;
    }

    default String preProcessBeforeCloseOrders(List<Order> orders, String reason) {
        return reason;
    }

    default String preProcessBeforeDeclineOrder(Order order, String reason) {
        return reason;
    }

    default OrderShipment preProcessBeforeAddOrderShipment(Order order, OrderShipment shipment) {
        return shipment;
    }

    default OrderShipment postProcessAfterGetOrderShipment(Order order, OrderShipment shipment) {
        return shipment;
    }

    default List<OrderShipment> postProcessAfterGetOrderShipments(Order order, List<OrderShipment> shipments) {
        return shipments;
    }

    default OrderShipment preProcessBeforeUpdateOrderShipment(Order order, OrderShipment shipment) {
        return shipment;
    }

    default List<OrderShipment> preProcessBeforeUpdateOrderShipments(Order order, List<OrderShipment> shipments) {
        return shipments;
    }

    default OrderShipment preProcessBeforeRemoveOrderShipment(Order order, OrderShipment shipment) {
        return shipment;
    }

    default List<OrderShipment> preProcessBeforeRemoveOrderShipments(Order order, List<OrderShipment> shipments) {
        return shipments;
    }

    default OrderRefund preProcessBeforeApplyOrderRefund(Order order, OrderRefund refund) {
        return refund;
    }

    default OrderRefund preProcessAfterApplyOrderRefund(Order order, OrderRefund refund) {
        return refund;
    }

    default OrderRefund postProcessAfterApplyOrderRefund(Order order, OrderRefund refund) {
        return refund;
    }

    default List<OrderRefund> preProcessBeforeApplyOrderRefunds(Order order, List<OrderRefund> refunds) {
        return refunds;
    }

    default List<OrderRefund> preProcessAfterApplyOrderRefunds(Order order, List<OrderRefund> refunds) {
        return refunds;
    }

    default List<OrderRefund> postProcessAfterApplyOrderRefunds(Order order, List<OrderRefund> refunds) {
        return refunds;
    }

    default OrderRefund preProcessBeforeCancelOrderRefund(Order order, OrderRefund refund) {
        return refund;
    }

    default OrderRefund preProcessAfterCancelOrderRefund(Order order, OrderRefund refund) {
        return refund;
    }

    default OrderRefund postProcessAfterCancelOrderRefund(Order order, OrderRefund refund) {
        return refund;
    }

    default OrderRefund preProcessBeforeApproveOrderRefund(Order order, OrderRefund refund) {
        return refund;
    }

    default OrderRefund preProcessAfterApproveOrderRefund(Order order, OrderRefund refund) {
        return refund;
    }

    default OrderRefund postProcessAfterApproveOrderRefund(Order order, OrderRefund refund) {
        return refund;
    }

    default OrderRefund preProcessBeforeDisapproveOrderRefund(Order order, OrderRefund refund) {
        return refund;
    }

    default OrderRefund preProcessAfterDisapproveOrderRefund(Order order, OrderRefund refund) {
        return refund;
    }

    default OrderRefund postProcessAfterDisapproveOrderRefund(Order order, OrderRefund refund) {
        return refund;
    }

    default OrderRefund preProcessBeforeReapplyOrderRefund(Order order, OrderRefund refund) {
        return refund;
    }

    default OrderRefund preProcessAfterReapplyOrderRefund(Order order, OrderRefund refund) {
        return refund;
    }

    default OrderRefund postProcessAfterReapplyOrderRefund(Order order, OrderRefund refund) {
        return refund;
    }

    default OrderRefund preProcessBeforeActiveOrderRefund(Order order, OrderRefund refund) {
        return refund;
    }

    default OrderRefund preProcessAfterActiveOrderRefund(Order order, OrderRefund refund) {
        return refund;
    }

    default OrderRefund postProcessAfterActiveOrderRefund(Order order, OrderRefund refund) {
        return refund;
    }

    default OrderRefund postProcessAfterGetOrderRefund(Order order, OrderRefund refund) {
        return refund;
    }

    default List<OrderReview> preProcessBeforeReviewOrder(Order order, List<OrderReview> reviews) {
        return reviews;
    }

    default List<OrderRating> postProcessAfterGetOrderRatings(Order order, List<OrderRating> ratings) {
        return ratings;
    }

    default List<OrderRating> preProcessBeforeRatingOrder(Order order, List<OrderRating> ratings) {
        return ratings;
    }

    default void postProcessAfterCompletion() {
    }
}
