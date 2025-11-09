import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class DriverMenu
{
    DeliveryPerson self;
    public DriverMenu(DeliveryPerson dp)
    {
        self = dp;

        while (true) {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nDelivery Menu - " + self.getName());
        System.out.println("1) View Assigned Orders");
        System.out.println("2) Complete Order");
        System.out.println("0) Back");
        String ch = sc.nextLine().trim();
        switch (ch) {
            case "0":
                return;
            case "1":
                self.displayAssignedOrders();
                break;
            case "2":
                System.out.print("Order ID: ");
                String oid = sc.nextLine().trim();
                self.completeOrder(oid);
                break;
            default:
                System.out.println("Bad choice.");
            }
        }
    }

    public DeliveryPerson getDP()
    {
        return self;
    }

    public List<Order> getListOrders()
    {
        List<Order> newList = new ArrayList<>();
        newList.addAll(self.getAssignedOrders());
        newList.addAll(self.getPastOrders());
        return newList;
    }

    public List<Payment> getListPayments()
    {
        return self.getDriverPayments();
    }
}
