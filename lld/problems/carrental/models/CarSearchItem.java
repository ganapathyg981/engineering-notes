package models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CarSearchItem {
    RentalCar rentalCar;
    Double price;
}
