package service;

import models.Booking;

import java.time.Duration;
import java.time.LocalDateTime;

public class Utils {

    public static Boolean isBetween(LocalDateTime target, LocalDateTime start, LocalDateTime end) {
        return (target.isAfter(start) || target.equals(start)) &&
                (target.isBefore(end) || target.equals(end));
    }

    public static long getRoundedUpHoursBetween(LocalDateTime start, LocalDateTime end) {
        long minutes = Duration.between(start, end).toMinutes(); // Get total minutes
        return (long) Math.ceil(minutes / 60.0); // Divide by 60 and round up
    }
}
