package service;

import models.Payment;
import models.PaymentMode;
import models.PaymentStatus;

import java.util.HashMap;
import java.util.Map;


public class PaymentService {
    private static volatile PaymentService instance;
    private Map<String, Payment> payments = new HashMap<>();

    private PaymentService() { } // ✅ Private constructor

    public static PaymentService getInstance() { // ✅ Thread-safe Singleton
        if (instance == null) {
            synchronized (PaymentService.class) {
                if (instance == null) {
                    instance = new PaymentService();
                }
            }
        }
        return instance;
    }

    public boolean isPaymentSuccessful(String paymentId) {
        return payments.containsKey(paymentId);
    }

    public void markForRefund(String paymentId, Integer cancellationChargeInPercentage) {
        if(cancellationChargeInPercentage > 100) {
            System.out.println("bad shit");
            return;
        }
        Payment payment = payments.get(paymentId);
        payment.setAmountToBeRefunded(payment.getAmountPaid()*((100-cancellationChargeInPercentage)/100));
        payment.setPaymentStatus(PaymentStatus.TO_BE_REFUNDED);
    }

    public void makePayment(Float amount, String id) {
        Payment payment = new Payment();
        payment.setId(id);
        payment.setPaymentMode(PaymentMode.UPI);
        payment.setAmountPaid(amount);
        payment.setPaymentStatus(PaymentStatus.SUCCESS);

        payments.put(id, payment);
    }
}
