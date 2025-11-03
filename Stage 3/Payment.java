/**
 * Author: Lazo McCarroll
 * Assignment: Project
 */
public class Payment
{
    private String paymentID;
    private Double price;
    private Boolean paidStatus;
    private Set<DiscountCoupon> appliedCoupons;

    /**
     * This method initializes the instance variables.
     * 
     * NOTE TO AMADEO 10/27:  Not all of the UML diagram's variables are here. I'm reconsidering what variables
     * that this class should have - see Prof. notes on Stage 2
     * @param Price
     * @param appliedCoupons
     */
    public payment(String newPaymentID, double newPrice, Boolean newPaidStatus, Set<DiscountCoupon> newAppliedCoupons)
    {
        paymentID = newPaymentID;
        price = newPrice;
        paidStatus = newPaidStatus;
        appliedCoupons = newAppliedCoupons;
    }

    // GETTERS

    /**
     * This method returns the payment ID.
     * @return
     */
    public String getPaymentID()
    {
        return paymentID;
    }

    /**
     * This method returns the price.
     * @return
     */
    public double getPrice()
    {
        return price;
    }

    /**
     * This method returns the paid status.
     * @return
     */
    public Boolean getPaidStatus()
    {
        return paidStatus;
    }

    // SETTERS

    /**
     * This method sets a new value for the payment ID.
     * @param newPaymentID
     */
    public void setPaymentID(String newPaymentID)
    {
        paymentID = newPaymentID;
    }

    /**
     * This method sets a new value for the price.
     * @param newPrice
     */
    public void setPrice(double newPrice)
    {
        price = newPrice;
    }

    /**
     * This method sets a new value for the paid status.
     * @param newPaidStatus
     */
    public void setPaidStatus(Boolean newPaidStatus)
    {
        paidStatus = newPaidStatus;
    }
}
