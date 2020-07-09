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

import org.mallfoundry.payment.PaymentStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
@NoArgsConstructor
@Embeddable
public class InternalPaymentDetails implements PaymentDetails {

    @Column(name = "payment_id_")
    private String paymentId;

    @Column(name = "payment_provider_")
    private String provider;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_status_")
    private PaymentStatus status;

    public InternalPaymentDetails(String paymentId, String provider, PaymentStatus status) {
        this.paymentId = paymentId;
        this.provider = provider;
        this.status = status;
    }

    public static InternalPaymentDetails of(PaymentDetails details) {
        if (details instanceof InternalPaymentDetails) {
            return (InternalPaymentDetails) details;
        }
        var target = new InternalPaymentDetails();
        BeanUtils.copyProperties(details, target);
        return target;
    }
}
