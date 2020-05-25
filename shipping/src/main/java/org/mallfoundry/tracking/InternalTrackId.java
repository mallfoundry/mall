package org.mallfoundry.tracking;


import org.mallfoundry.shipping.CarrierCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public class InternalTrackId implements Serializable {

    private CarrierCode carrierCode;

    private String trackingNumber;

    public InternalTrackId(CarrierCode carrierCode, String trackingNumber) {
        this.carrierCode = carrierCode;
        this.trackingNumber = trackingNumber;
    }

    public static InternalTrackId of(CarrierCode carrierCode, String trackingNumber) {
        return new InternalTrackId(carrierCode, trackingNumber);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InternalTrackId that = (InternalTrackId) o;
        return Objects.equals(carrierCode, that.carrierCode) &&
                Objects.equals(trackingNumber, that.trackingNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(carrierCode, trackingNumber);
    }
}
