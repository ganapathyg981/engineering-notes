package models;

import lombok.Data;

@Data
public class Payment {

    private Float amountPaid;
    private Float amountToBeRefunded;
    private String id;
    private PaymentMode paymentMode;
    private PaymentStatus paymentStatus; //TODO checking

}
