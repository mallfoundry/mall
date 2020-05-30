package org.mallfoundry.store;

import java.util.Date;

public interface Store {

    String getId();

    void setId(String id);

    String getName();

    void setName(String name);

    StoreStatus getStatus();

    String getDomain();

    void setDomain(String domain);

    String getLogoUrl();

    void setLogoUrl(String logoUrl);

    String getOwnerId();

    void setOwnerId(String ownerId);

    String getIndustry();

    void setIndustry(String industry);

    String getDescription();

    void setDescription(String description);

    Date getCreatedTime();

    void initialize();

    default Builder toBuilder() {
        return new Builder(this);
    }

    class Builder {
        private final Store store;

        public Builder(Store store) {
            this.store = store;
        }

        public Builder name(String name) {
            this.store.setName(name);
            return this;
        }

        public Builder logoUrl(String logoUrl) {
            this.store.setLogoUrl(logoUrl);
            return this;
        }

        public Builder ownerId(String ownerId) {
            this.store.setOwnerId(ownerId);
            return this;
        }

        Store build() {
            return this.store;
        }
    }
}
