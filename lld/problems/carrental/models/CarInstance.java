package models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class CarInstance implements RentalCar {
    private String id;
    private String licensePlateNumber;
    private FuelType fuelType;
    private TransmissionType transmissionType;
    private Float pricePerHour;
    private CarModel carModel;
    @NonNull
    private List<String> features;
    private String branchName;

    // Deep Copy Method
    public CarInstance deepCopy() {
        CarModel copiedCarModel = new CarModel(this.carModel.getMake(), this.carModel.getModel()); // Deep copy of CarModel
        return new CarInstance(this.id,this.licensePlateNumber, this.fuelType
                        , this.transmissionType, this.pricePerHour, copiedCarModel, this.features, this.branchName);
    }

}
