package service;

import models.Booking;
import models.CarSearchItem;
import models.CarSearchQuery;
import models.RentalCar;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CarRentalService {
    private static volatile CarRentalService instance;
    CarService carService;
    BookingService bookingService;
    PaymentService paymentService;
    private static final Integer CANCELLATION_CHARGE_PERCENTAGE = 30;


    private CarRentalService() {
        this.carService = CarService.getInstance();
        this.bookingService = BookingService.getInstance();
        this.paymentService = PaymentService.getInstance();
    }

    public static CarRentalService getInstance() {
        if (instance == null) {
            synchronized (CarRentalService.class) {
                if (instance == null) {
                    instance = new CarRentalService();
                }
            }
        }
        return instance;
    }

    public List<CarSearchItem> searchForCar(CarSearchQuery carSearchQuery) {
        List<Booking> overlappingBookings =
                bookingService.getOverlappingBookings(carSearchQuery.getAvailableFrom(), carSearchQuery.getAvailableTo());
        Set<String> unAvailableCars = overlappingBookings.stream().map(Booking::getCarId).collect(Collectors.toSet());
        return carService.getAllCars().stream()
                .filter(car -> !unAvailableCars.contains(car.getId()))
                .filter(car -> carSearchQuery.getMake() == null || carSearchQuery.getMake().contains(car.getCarModel().getMake()))
                .filter(car -> carSearchQuery.getModel() == null || carSearchQuery.getModel().contains(car.getCarModel().getModel()))
                .filter(car -> carSearchQuery.getFuelType() == null || carSearchQuery.getFuelType().contains(car.getFuelType()))
                .filter(car -> carSearchQuery.getTransmissionType() == null || carSearchQuery.getTransmissionType().equals(car.getTransmissionType()))
                .filter(car -> carSearchQuery.getPricePerHourLte() == null || car.getPricePerHour() <= carSearchQuery.getPricePerHourLte())
                .filter(car -> carSearchQuery.getPricePerHourGte() == null || car.getPricePerHour() >= carSearchQuery.getPricePerHourGte())
                .filter(car -> carSearchQuery.getBranchName() == null || car.getBranchName().equals(carSearchQuery.getBranchName()))
                .map(car -> new CarSearchItem(car, getPrice(car, carSearchQuery)))
                .toList();
    }

    private Double getPrice(RentalCar car, CarSearchQuery carSearchQuery) {
        Long hours = Utils.getRoundedUpHoursBetween(carSearchQuery.getAvailableFrom(), carSearchQuery.getAvailableTo());
        return (double) (hours * car.getPricePerHour());
    }

    public boolean bookCar(String userId, String carId, LocalDateTime bookingFrom, LocalDateTime bookingTill, String paymentId) {
        if (!paymentService.isPaymentSuccessful(paymentId)) {
            System.out.println("PAYMENT NOT AVAILABLE");
            return false;
        }
        List<Booking> bookingsForUser = bookingService.getBookingsForUser(userId, bookingFrom, bookingTill);
        if (!bookingsForUser.isEmpty()) {
            paymentService.markForRefund(paymentId,0);
            System.out.println("User has a booking already in the current timeslot");
            return false;
        }
        Boolean bookingStatus = bookingService.bookCar(userId, carId, bookingFrom, bookingTill, paymentId);
        if (Boolean.FALSE.equals(bookingStatus)) {
            paymentService.markForRefund(paymentId,0);
        }
        return bookingStatus;
    }

    private Boolean cancelBooking(String bookingId) {
        Booking booking = bookingService.cancelBooking(bookingId);
        if (booking == null) {
            return false;
        }
        paymentService.markForRefund(booking.getPaymentId(), CANCELLATION_CHARGE_PERCENTAGE);
        return true;
    }
}
