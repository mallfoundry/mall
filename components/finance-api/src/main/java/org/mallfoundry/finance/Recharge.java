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

package org.mallfoundry.finance;

import org.mallfoundry.finance.account.BalanceTransaction;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface Recharge {

    String getId();

    void setId(String id);

    String getAccountId();

    void setAccountId(String accountId);

    BigDecimal getAmount();

    void setAmount(BigDecimal amount);

    String getCurrency();

    void setCurrency(String currency);

    RechargeStatus getStatus();

    Transaction createTransaction();

    List<Transaction> getTransactions();

    List<BalanceTransaction> getBalanceTransactions();

    Date getCanceledTime();

    Date getSucceededTime();

    String getFailureReason();

    Date getFailedTime();

    void pending(List<BalanceTransaction> balanceTransactions);

    void cancel();

    void succeed();

    void fail(String failureReason);
}
