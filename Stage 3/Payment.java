/**
 * Author: Lazo McCarroll
 * Assignment: Project
 */
import java.util.Set;
public class Payment
{
    private String paymentID;
    private Double price;
    private Boolean paidStatus;

    /**
     * This method initializes the instance variables.
     * 
     * NOTE TO AMADEO 11/1:  Not all of the UML diagram's variables are here. I'm reconsidering what variables
     * that this class should have - see Prof. notes on Stage 2
     * @param newPaymentID
     * @param newPrice
     * @param newPaidStatus
     */
    public Payment(String newPaymentID, double newPrice, Boolean newPaidStatus)
    {
        paymentID = newPaymentID;
        price = newPrice;
        paidStatus = newPaidStatus;
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

    // MISC

    /**
     * This method displays all of the details of the payment
     */
    public void displayPaymentDetails()
    {
        System.out.println("Payment ID: "+paymentID+"\nTotal price: "+price+"\nStatus of Payment: "+paidStatus);
    }
}
