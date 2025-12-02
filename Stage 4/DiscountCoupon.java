/**
 * Author: Amadeo Pena
 * Assignment: Project
 */
package swiftbytes;

public class DiscountCoupon {

    private String code;
    private double discountAmount; 
    private boolean validity;

    // Constructor
    public DiscountCoupon(String code, double discountAmount, boolean isValid) {
        this.code = code;
        this.discountAmount = discountAmount;
        this.validity = isValid;
    }

    /**
     * Applies the discount to a given price.
     * @param price the original price
     * @return the discounted price (rounded to 2 decimals)
     */
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

    // @return true if the coupon is valid 
    public boolean isValid() {
        return validity;
    }

    // @return the coupon code
    public String getCode() {
        return code;
    }

    // @return the discount amount
    public double getDiscountAmount() {
        return discountAmount;
    }

    // @return the value 
    public double getValue() {
        return discountAmount;
    }

    // Sets Value
    public void setValue(double value) {
        this.discountAmount = value;
    }

    // @return the coupon validity
    public boolean getValidity() {
        return validity;
    }

    // ALIAS: AdminMenu expects setValid()
    public void setValid(boolean valid) {
        this.validity = valid;
    }

    // Invalidates the coupon
    public void invalidate() {
        this.validity = false;
    }

    // Prints the coupon information.
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
