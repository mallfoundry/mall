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

package org.mallfoundry.customer;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "customer_shipping_addresses")
public class InternalShippingAddress implements ShippingAddress {

    @Id
    @Column(name = "id_")
    private String id;

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    @Column(name = "country_code_")
    private String countryCode;

    @Column(name = "mobile_")
    private String mobile;

    @Column(name = "zip_")
    private String zip;

    @Column(name = "location_")
    private String location;

    @Column(name = "address_")
    private String address;

    @Column(name = "defaulted_")
    private boolean defaulted;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "created_time_")
    private Date createdTime;

    public void defaulted() {
        this.setDefaulted(true);
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InternalShippingAddress that = (InternalShippingAddress) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public static class Builder {

        private final InternalShippingAddress address;

        public Builder() {
            this(new InternalShippingAddress());
        }

        public Builder(InternalShippingAddress address) {
            this.address = address;
        }

        public Builder firstName(String firstName) {
            this.address.setFirstName(firstName);
            return this;
        }

        public Builder lastName(String lastName) {
            this.address.setLastName(lastName);
            return this;
        }

        public Builder countryCode(String countryCode) {
            this.address.setCountryCode(countryCode);
            return this;
        }

        public Builder mobile(String mobile) {
            this.address.setMobile(mobile);
            return this;
        }

        public Builder zip(String zip) {
            this.address.setZip(zip);
            return this;
        }

        public Builder location(String location) {
            this.address.setLocation(location);
            return this;
        }

        public Builder address(String address) {
            this.address.setAddress(address);
            return this;
        }

        public Builder defaulted() {
            this.address.defaulted();
            return this;
        }

        public InternalShippingAddress build() {
            this.address.setCreatedTime(new Date());
            return this.address;
        }
    }
}
