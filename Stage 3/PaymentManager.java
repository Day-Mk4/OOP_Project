/**
 * Author: 
 * Assignment: Project
 */
public class PaymentManager {

    public boolean process(Payment payment, String method, String details) {
        if (payment == null) {
            return false;
        }

        boolean ok = details != null && !details.trim().isEmpty();

        if (ok) {
            payment.updatePaidStatus(true);
            System.out.println("Payment processed via " + method + " for $" + payment.getPrice());
        } else {
            System.out.println("Payment failed: invalid details.");
        }

        return ok;
    }
}
