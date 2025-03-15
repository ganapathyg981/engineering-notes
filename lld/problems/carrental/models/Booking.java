package models;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class Booking {
    private String id;
    private String userId;
    private String carId;
    private LocalDateTime bookedFrom;
    private LocalDateTime bookedTill;
    private BookingStatus status;
    private LocalDateTime time;
    private String paymentId;
}
