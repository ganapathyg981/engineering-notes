package models;

import java.util.List;

public abstract class CarFeature implements RentalCar {
    RentalCar rentalCar;

    CarFeature(RentalCar rentalCar) {
        this.rentalCar = rentalCar;
    }

    @Override
    public String getId() {
        return rentalCar.getId();
    }

    @Override
    public String getLicensePlateNumber() {
        return rentalCar.getLicensePlateNumber();
    }

    @Override
    public String getBranchName() {
        return rentalCar.getBranchName();
    }

    @Override
    public FuelType getFuelType() {
        return rentalCar.getFuelType();
    }

    @Override
    public TransmissionType getTransmissionType() {
        return rentalCar.getTransmissionType();
    }

    @Override
    public CarModel getCarModel() {
        return rentalCar.getCarModel();
    }

}
