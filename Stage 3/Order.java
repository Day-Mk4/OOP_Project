/**
 * Author: Lazo McCarroll
 * Assignment: Project
 */
import java.util.Set;
public class Order
{
    private String orderID;
    private String[][] orderItems;
    private Payment payment;
    private final String restaurantName;
    private final String customerName;
    private String deliveryPersonID;
    private Set<DiscountCoupon> appliedCoupons;

    public Order(String newOrderID, String newPaymentID, String[][] newOrderItems, Set<DiscountCoupon> newAppliedCoupons, String newRestName, String newCustName, String newDriverID)
    {
        orderID = newOrderID;
        appliedCoupons = newAppliedCoupons;
        payment = new Payment(newPaymentID, 0.0, false);
        restaurantName = newRestName;
        customerName  = newCustName;
        deliveryPersonID = newDriverID;
        setOrderItems(newOrderItems);
    }

    /////////
    /// GETTERS
    /////////

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

    /////////
    /// SETTERS & ADDERS
    /////////

    public void setOrderID(String newOrderID)
    {
        orderID = newOrderID;
    }

    public void setOrderItems(String[][] newOrderItems)
    {
        orderItems = newOrderItems;
        payment.setPrice(payment.calculatePrice(orderItems, appliedCoupons));
    }
    
    /////////
    /// MISC
    /////////

    public void displayDetails()
    {
        System.out.println("Order " + orderID + " @ " + restaurantName + " for " + customerName);

        for (String[] row : orderItems) {
            System.out.println("  - " + row[0] + " x" + row[2] + " @ $" + row[1]);
        }

        System.out.println("Total: $" + payment.getPrice());
    }
}
