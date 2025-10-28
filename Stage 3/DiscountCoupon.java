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
    if (!validity) {
        return price;
    }

    double result = price - discountAmount;

    if (result < 0) {
        return 0;
        } else {
            return result;
        }
    }


    public boolean isValid() 
        { return validity; 
    }
    
    public String getCode() 
        { return code; 
    }
    
    public double getDiscountAmount() 
        { return discountAmount; 
    }
    
    public boolean getValidity() 
        { return validity; 
    }

    public void displayDetails() {
        System.out.println("Coupon " + code + " amount=" + discountAmount + " valid=" + validity);
    }
}
