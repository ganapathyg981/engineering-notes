package models;

import java.util.List;

public interface RentalCar {
    String getId();

    String getLicensePlateNumber();

    FuelType getFuelType();

    TransmissionType getTransmissionType();

    Float getPricePerHour();

    CarModel getCarModel();

    List<String> getFeatures();

    String getBranchName();


}
