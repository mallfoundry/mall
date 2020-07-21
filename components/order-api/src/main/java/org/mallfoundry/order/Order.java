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

import org.mallfoundry.inventory.InventoryDeduction;
import org.mallfoundry.payment.PaymentMethod;
import org.mallfoundry.payment.PaymentStatus;
import org.mallfoundry.shipping.Address;
import org.mallfoundry.util.ObjectBuilder;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;

/**
 * 订单对象。
 *
 * @author Zhi Tang
 */
public interface Order extends ObjectBuilder.ToBuilder<Order.Builder> {

    String getId();

    void setId(String id);

    /**
     * 订单收货地址对象。
     *
     * @return 收货地址对象
     */
    Address getShippingAddress();

    void setShippingAddress(Address shippingAddress);

    String getCustomerId();

    void setCustomerId(String customerId);

    String getStoreId();

    void setStoreId(String storeId);

    String getStoreName();

    void setStoreName(String storeName);

    OrderStatus getStatus();

    /**
     * 商家员工备注。
     *
     * @return 备注信息
     */
    String getStaffNotes();

    void setStaffNotes(String staffNotes);

    Integer getStaffStars();

    void setStaffStars(Integer staffStars);

    String getSourceName();

    void setSourceName(String sourceName);

    OrderSource getSource();

    void setSource(OrderSource source);

    OrderItem createItem(String itemId);

    void addItem(OrderItem item);

    Optional<OrderItem> getItem(String itemId);

    List<OrderItem> getItems(List<String> itemIds);

    List<OrderItem> getItems();

    void setItems(List<OrderItem> items);

    int getTotalItems();

    OrderShipment createShipment(String shipmentId);

    void addShipment(OrderShipment shipment);

    List<OrderShipment> getShipments();

    Optional<OrderShipment> getShipment(String shipmentId);

    List<OrderShipment> getShipments(Set<String> shipmentIds);

    void updateShipment(OrderShipment shipment);

    void updateShipments(List<OrderShipment> shipments);

    void removeShipment(OrderShipment shipment);

    void removeShipments(List<OrderShipment> shipments);

    int getShippedItems();

    /**
     * 创建一个订单退款对象。
     *
     * @param refundId 订单退款对象标识
     * @return 一个新的订单退款对象
     */
    OrderRefund createRefund(String refundId);

    /**
     * 获得与此订单对象所关联的所有的订单退款对象集合。
     *
     * @return 订单退款对象集合
     */
    List<OrderRefund> getRefunds();

    /**
     * 根据订单退款对象标识获得订单退款对象。
     *
     * @param refundId 订单退款对象标识
     * @return 订单退款对象
     */
    Optional<OrderRefund> getRefund(String refundId);

    /**
     * 申请订单退款。
     *
     * @param refund 订单退款对象
     * @throws OrderRefundException 退款金额小于等于零，退款对象所关联的订单对象的订单项不存在，或者已全额退款
     */
    OrderRefund applyRefund(OrderRefund refund) throws OrderRefundException;

    /**
     * 批准退款。
     *
     * @param refundId 订单退款对象标识
     * @throws OrderRefundException 订单退款对象不存在
     */
    void approveRefund(String refundId) throws OrderRefundException;

    /**
     * 不批准退款。
     *
     * @param refundId          订单退款对象标识
     * @param disapprovedReason 不批准的运用
     * @throws OrderRefundException 订单退款对象不存在
     */
    void disapproveRefund(String refundId, String disapprovedReason) throws OrderRefundException;

    /**
     * 主动退款操作是 {@link #applyRefund(OrderRefund)} 和 {@link #approveRefund(String)}
     * 两个方法的组合。
     *
     * @param refund 订单退款对象
     * @throws OrderRefundException 请查看 {@link #applyRefund(OrderRefund)} 异常原因
     */
    void activeRefund(OrderRefund refund) throws OrderRefundException;

    /**
     * 取消退款，取消退款后订单退款对象将被删除。
     *
     * @param refundId 订单退款对象标识
     * @throws OrderRefundException 订单退款对象不存在，或者已经同意申请
     */
    void cancelRefund(String refundId) throws OrderRefundException;

    /**
     * 成功付款。
     *
     * @param refundId 订单退款对象标识
     * @throws OrderRefundException 订单退款对象不存在
     */
    void succeedRefund(String refundId) throws OrderRefundException;

    /**
     * 付款失败。
     *
     * @param refundId 订单退款对象标识
     * @throws OrderRefundException 订单退款对象不存在
     */
    void failRefund(String refundId, String failReason) throws OrderRefundException;

    BigDecimal getTotalDiscountAmount();

    BigDecimal getTotalShippingCost();

    BigDecimal getTotalDiscountShippingCost();

    BigDecimal getTotalPrice();

    BigDecimal getTotalAmount();

    BigDecimal getSubtotalAmount();

    String getPaymentId();

    void setPaymentId(String paymentId);

    PaymentStatus getPaymentStatus();

    void setPaymentStatus(PaymentStatus paymentStatus);

    PaymentMethod getPaymentMethod();

    void setPaymentMethod(PaymentMethod paymentMethod);

    /**
     * 订单对象扣减库存时所使用的扣减模式。
     *
     * @return 所使用的扣减模式
     */
    InventoryDeduction getInventoryDeduction();

    void setInventoryDeduction(InventoryDeduction inventoryDeduction);

    int getPlacingExpires();

    boolean isPlaced();

    boolean isPaid();

    boolean isPlacingExpired();

    Date getPlacedTime();

    Date getPlacingExpiredTime();

    Date getPaidTime();

    Date getFulfilledTime();

    Date getShippedTime();

    Date getSignedTime();

    Date getReceivedTime();

    String getCancelReason();

    Date getCancelledTime();

    void discounts(Map<String, BigDecimal> amounts);

    void discountShippingCosts(Map<String, BigDecimal> shippingCosts);

    void place(int placingExpires) throws OrderException;

    void pay(OrderPayment payment) throws OrderException;

    /**
     * 订单对象打包。
     *
     * @throws OrderException 订单对象已完成打包状态
     */
    void fulfil() throws OrderException;

    String getSignMessage();

    /**
     * 订单对象签收，sign 和 receipt 区别是签收可以是自签收也可以是他人代签收。
     *
     * @throws OrderException 订单对象已完成签收状态
     */
    void sign(String message) throws OrderException;

    /**
     * 确认收货。
     *
     * @throws OrderException 订单对象已完成确认收货状态
     */
    void receipt() throws OrderException;

    void cancel(String reason) throws OrderException;

    interface Builder extends ObjectBuilder<Order> {

        Builder customerId(String customerId);

        Builder shippingAddress(Address shippingAddress);

        Builder pay(OrderPayment payment);

        Builder item(OrderItem item);

        Builder item(Function<Order, OrderItem> item);
    }
}
