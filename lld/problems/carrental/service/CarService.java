package service;

import models.CarInstance;
import models.CarModel;
import models.FuelType;
import models.GPSFeature;
import models.RentalCar;
import models.TransmissionType;

import java.util.ArrayList;
import java.util.List;

public class CarService {
    private static volatile CarService instance;
    private List<RentalCar> carList;

    private CarService() {
        carList = new ArrayList<>();

        // Creating 10 CarInstance objects
        for (int i = 1; i <= 10; i++) {
            CarModel carModel = new CarModel("Brand" + i, "Model" + i);
            List<String> features = new ArrayList<>();
            RentalCar car = CarInstance.builder()
                    .id("C" + i)
                    .licensePlateNumber("TN-10-AB-00" + i)
                    .fuelType(FuelType.PETROL)
                    .transmissionType(TransmissionType.AUTOMATIC)
                    .pricePerHour(90F)
                    .carModel(carModel)
                    .features(features)
                    .branchName("G")
                    .build();

            // Randomly add GPS feature
            if (i % 2 == 0) {
                car = new GPSFeature(new GPSFeature(car));
            }
            carList.add(car);
        }
    } // ✅ Private constructor

    public static CarService getInstance() { // ✅ Thread-safe Singleton
        if (instance == null) {
            synchronized (CarService.class) {
                if (instance == null) {
                    instance = new CarService();
                }
            }
        }
        return instance;
    }
    public List<RentalCar> getAllCars() {
        System.out.println(carList.get(1));

        return carList;
    }
}
