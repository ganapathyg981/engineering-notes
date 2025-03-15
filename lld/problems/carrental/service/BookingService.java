package service;

import models.Booking;
import models.BookingStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


public class BookingService {
    private static volatile BookingService instance;
    private List<Booking> bookingList;

    private BookingService() {
        bookingList = new ArrayList<>();
    } // ✅ Private constructor

    public static BookingService getInstance() { // ✅ Thread-safe Singleton
        if (instance == null) {
            synchronized (BookingService.class) {
                if (instance == null) {
                    instance = new BookingService();
                }
            }
        }
        return instance;
    }

    public synchronized Boolean bookCar(String userId, String carId, LocalDateTime bookingFrom, LocalDateTime bookingTill, String paymentId) {
        boolean isCarBooked = getOverlappingBookings(bookingFrom, bookingTill).stream().anyMatch(booking -> booking.getCarId().equals(carId));
        if(isCarBooked) {
            System.out.println("Car is already booked");
            return false;
        }
        Booking booking = Booking.builder()
                .id(UUID.randomUUID().toString())
                .bookedFrom(bookingFrom)
                .bookedTill(bookingTill)
                .carId(carId)
                .userId(userId)
                .status(BookingStatus.ACTIVE)
                .paymentId(paymentId)
                .build();
        bookingList.add(booking);
        return true;
    }

    public synchronized List<Booking> getOverlappingBookings(LocalDateTime startTime, LocalDateTime endTime) {
        return bookingList.stream()
                .filter(booking -> booking.getStatus() == BookingStatus.ACTIVE)
                .filter(booking -> Utils.isBetween(startTime, booking.getBookedFrom(), booking.getBookedTill()) ||
                        Utils.isBetween(endTime, booking.getBookedFrom(), booking.getBookedTill())).
                collect(Collectors.toList());
    }



    public synchronized List<Booking> getBookingsForUser(String userId) {
        return bookingList.stream()
                .filter(booking -> booking.getUserId().equals(userId)).
                collect(Collectors.toList());
    }
    public synchronized List<Booking> getBookingsForUser(String userId, LocalDateTime startTime, LocalDateTime endTime) {
        List<Booking> overlappingBookings = getOverlappingBookings(startTime, endTime);
        return overlappingBookings.stream().filter(booking-> booking.getUserId().equals(userId))
                .toList();
    }

    public Booking cancelBooking(String bookingId) {
        Booking booking = getBooking(bookingId);
        if(booking == null) {
            System.out.println("gotha booking eh illada");
            return null;
        }
        if(booking.getStatus().equals(BookingStatus.CANCELLED)) {
            System.out.println("gotha booking already cancelled da");
            return null;
        }
        booking.setStatus(BookingStatus.CANCELLED);
        return booking;
    }

    private Booking getBooking(String bookingId) {
        return bookingList.stream().filter(booking -> booking.getId().equals(bookingId)).findFirst().get();
    }
}
