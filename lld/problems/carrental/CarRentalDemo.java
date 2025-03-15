import models.CarSearchItem;
import models.CarSearchQuery;
import models.FuelType;
import models.TransmissionType;
import service.CarRentalService;
import service.PaymentService;

import java.time.LocalDateTime;
import java.util.List;

public class CarRentalDemo {
    public static void main(String[] args) {

        CarRentalService carRentalService = CarRentalService.getInstance();
        PaymentService paymentService = PaymentService.getInstance();

        CarSearchQuery carSearchQuery = CarSearchQuery.builder()
                .fuelType(List.of(FuelType.PETROL, FuelType.DIESEL)) // Petrol & Diesel cars
                .transmissionType(TransmissionType.AUTOMATIC) // Only automatic cars
                .branchName("G") // Searching in a specific branch
                .availableFrom(LocalDateTime.of(2025, 3, 16, 01, 0)) // Available from 10 AM
                .availableTo(LocalDateTime.of(2025, 3, 16, 23, 0)) // Available till 6 PM
                .build();

        List<CarSearchItem> carSearchItems = carRentalService.searchForCar(carSearchQuery);
        System.out.println(carSearchItems.size());

        paymentService.makePayment(200F, "123");
        boolean bookingStatus = carRentalService.bookCar("123", "C1", LocalDateTime.of(2025, 3,
                16, 02, 0), LocalDateTime.of(2025, 3,
                17, 05, 0), "123");

        System.out.println(bookingStatus);
        carSearchItems = carRentalService.searchForCar(carSearchQuery);
        System.out.println(carSearchItems.size());
    }
}
