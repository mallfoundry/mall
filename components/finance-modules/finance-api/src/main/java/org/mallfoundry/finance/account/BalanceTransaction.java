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

package org.mallfoundry.finance.account;

import org.mallfoundry.finance.CurrencyCode;
import org.mallfoundry.finance.TransactionDirection;
import org.mallfoundry.finance.TransactionType;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface BalanceTransaction {

    String getId();

    void setId(String id);

    String getAccountId();

    void setAccountId(String accountId);

    CurrencyCode getCurrencyCode();

    void setCurrencyCode(CurrencyCode currencyCode);

    BigDecimal getAmount();

    void setAmount(BigDecimal amount);

    TransactionDirection getDirection();

    void setDirection(TransactionDirection direction);

    TransactionType getType();

    void setType(TransactionType type);

    List<BalanceSource> getSources();

    void setSources(List<BalanceSource> sources);

    BigDecimal getEndingBalance();

    void setEndingBalance(BigDecimal endingBalance);

    String getMemo();

    void setMemo(String memo);

    String getDescription();

    void setDescription(String description);

    Date getCreatedTime();

    void create();
}
