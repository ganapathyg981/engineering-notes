package models;

import java.util.List;

public class BabySeatFeature extends CarFeature {
    BabySeatFeature(RentalCar rentalCar) {
        super(rentalCar);

    }
    @Override
    public Float getPricePerHour() {
        return rentalCar.getPricePerHour() + 30.0F;
    }

    @Override
    public List<String> getFeatures() {
        rentalCar.getFeatures().add("Hello");
        return rentalCar.getFeatures();
    }
}
