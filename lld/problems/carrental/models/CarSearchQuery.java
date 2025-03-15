package models;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.time.LocalDateTime;
import java.util.List;
@Data
@Builder
public class CarSearchQuery { //TODO check if we can do better
    private List<String> make;
    private List<String> model;
    private List<FuelType> fuelType;
    private TransmissionType transmissionType;
    private Float pricePerHourLte;
    private Float pricePerHourGte;
    private String branchName;
    @NonNull
    private LocalDateTime availableFrom;
    @NonNull
    private LocalDateTime availableTo;
}