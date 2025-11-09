/**
 * Author: Lazo McCarroll
 * Assignment: Project
 */
import java.util.Set;
public class PaymentManager
{
    /////////
    /// MISC
    /////////

    public boolean process(Payment payment, String method, String details) {
        if (payment == null) {
            return false;
        }

        boolean ok = details != null && !details.trim().isEmpty();

        if (ok) {
            payment.setPaidStatus(true);
            System.out.println("Payment processed via " + method + " for $" + payment.getPrice());
        } else {
            System.out.println("Payment failed: invalid details.");
        }

        return ok;
    }

    public double calculatePrice(String[][] orderItems, Set<DiscountCoupon> appliedCoupons)
    {
        double sum = 0.0;

        for (String[] row : orderItems) {
            double p = Double.parseDouble(row[1]);
            int q = Integer.parseInt(row[2]);
            sum += p * q;
        }

        double discounted = sum;
        if (appliedCoupons != null) {
            for (DiscountCoupon c : appliedCoupons) {
                discounted = c.applyDiscount(discounted);
            }
        }

        return discounted;
    }
}
