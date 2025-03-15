package models;

import java.util.List;

public class GPSFeature extends CarFeature {


    public GPSFeature(RentalCar rentalCar) {
        super(rentalCar);
    }

    @Override
    public Float getPricePerHour() {
        return rentalCar.getPricePerHour() + 30.0F;
    }

    @Override
    public List<String> getFeatures() {
        rentalCar.getFeatures().add("GPS");
        return rentalCar.getFeatures();
    }
}
