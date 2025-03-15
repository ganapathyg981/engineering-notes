package models;

import java.util.List;

public class InsuranceFeature extends CarFeature {


    InsuranceFeature(RentalCar rentalCar) {
        super(rentalCar);
    }

    @Override
    public Float getPricePerHour() {
        return rentalCar.getPricePerHour() + 30.0F;
    }

    @Override
    public List<String> getFeatures() {
        rentalCar.getFeatures().add("Insurance");
        return rentalCar.getFeatures();
    }
}
