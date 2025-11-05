/**
 * Author: Lazo McCarroll
 * Assignment: Project
 */
import java.util.Set;
public class Order
{
    private PaymentManager paymentManager;
    private String orderID;
    private String[][] orderItems;
    private Payment payment;
    private Set<DiscountCoupon> appliedCoupons;

    public Order(String newOrderID, String newPaymentID, String[][] newOrderItems, Set<DiscountCoupon> newAppliedCoupons)
    {
        orderID = newOrderID;
        appliedCoupons = newAppliedCoupons;
        payment = new Payment(newPaymentID, 0.0, false);
        setOrderItems(newOrderItems);
    }

    // GETTERS

    public String getOrderID()
    {
        return orderID;
    }

    public String[][] getOrderItems()
    {
        return orderItems;
    }

    public Payment getPayment()
    {
        return payment;
    }

    public Set<DiscountCoupon> getCoupons()
    {
        return appliedCoupons;
    }

    // SETTERS

    public void setOrderID(String newOrderID)
    {
        orderID = newOrderID;
    }

    public void setOrderItems(String[][] newOrderItems)
    {
        orderItems = newOrderItems;
        payment.setPrice(paymentManager.calculatePrice(orderItems, appliedCoupons));
    }
    
    // MISC

    public void displayDetails()
    {
        
    }
}
