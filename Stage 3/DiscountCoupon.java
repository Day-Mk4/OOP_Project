/**
 * Author: Amadeo Pena
 * Assignment: Project
 */
public class DiscountCoupon {

    private String code;
    private double discountAmount; 
    private boolean validity;


    public DiscountCoupon(String code, double discountAmount, boolean isValid) {
        this.code = code;
        this.discountAmount = discountAmount;
        this.validity = isValid;
    }


    public double applyDiscount(double price) {
    if (!isValid()) {
        return price;
    }
    double newPrice;
    
    if (discountAmount < 1.0) {
        newPrice = price * (1.0 - discountAmount);
    } else {
        newPrice = Math.max(0.0, price - discountAmount);
    }
    return Math.round(newPrice * 100.0) / 100.0;
    }

    public boolean isValid() {
        return validity;
    }

    public String getCode() {
        return code;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    public boolean getValidity() {
        return validity;
    }

    public void invalidate() {
        this.validity = false;
    }

    public void displayDetails() {
        System.out.println(
            "Coupon { " +
            "Code='" + code + '\'' +
            ", Amount=" + discountAmount +
            ", Valid=" + validity +
            " }"
        );
    }
}
