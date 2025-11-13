<<<<<<< Updated upstream
import java.util.Set;
public class CustomerMenu
{
    private CustomerMenu(Customer cust, Set<Restaurant> listRestaurants, Set<Order> listOrders) {
=======
/**
 * Author: Amadeo Pena
 * Assignment: Project
 */
import java.util.*;

public class CustomerMenu {
    private final Customer cust;
    private final Set<Restaurant> restaurants;
    private final Set<DiscountCoupon> coupons;
    private final Set<DeliveryPerson> drivers;
    private final List<Order> orders;
    private final List<Payment> payments;
    private final Admin admin;
    private final Scanner sc;

    public CustomerMenu(Customer cust, Set<Restaurant> restaurants, Set<DiscountCoupon> coupons,
                        Set<DeliveryPerson> drivers, List<Order> orders,
                        List<Payment> payments, Admin admin, Scanner sc) {
        this.cust = cust;
        this.restaurants = restaurants;
        this.coupons = coupons;
        this.drivers = drivers;
        this.orders = orders;
        this.payments = payments;
        this.admin = admin;
        this.sc = sc;
    }

    public void run() {
>>>>>>> Stashed changes
        while (true) {
            System.out.println("\nCustomer Menu - " + cust.getName());
            System.out.println("1) Browse Restaurants");
            System.out.println("2) View Order History");
            System.out.println("3) Place Order");
            System.out.println("4) Pay For Last Order");
            System.out.println("0) Back");
            String ch = sc.nextLine().trim();

            switch (ch) {
                case "1" -> restaurants.forEach(Restaurant::displayDetails);
                case "2" -> cust.displayOrderHistory();
                case "3" -> placeOrderFlow();
                case "4" -> payLastOrderFlow();
                case "0" -> { return; }
                default -> System.out.println("Bad choice.");
            }
        }
    }

    private Restaurant chooseRestaurant() {
        List<Restaurant> list = new ArrayList<>(restaurants);
        if (list.isEmpty()) return null;
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + ") " + list.get(i).getName() + " (" + list.get(i).getID() + ")");
        }
        System.out.print("Choose #: ");
        try {
            int idx = Integer.parseInt(sc.nextLine().trim()) - 1;
            if (idx >= 0 && idx < list.size()) return list.get(idx);
        } catch (Exception ignored) {}
        System.out.println("Invalid choice.");
        return null;
    }

    private void placeOrderFlow() {
        Restaurant selected = chooseRestaurant();
        if (selected == null) return;
        if (selected.getMenu() == null) {
            System.out.println("Restaurant has no menu.");
            return;
        }
        selected.getMenu().displayCompleteMenu();

        List<String[]> items = new ArrayList<>();
        while (true) {
            System.out.print("Enter item name (or 'done'): ");
            String name = sc.nextLine().trim();
            if ("done".equalsIgnoreCase(name)) break;

            Double price = selected.getMenu().getSingles().get(name);
            if (price == null) price = selected.getMenu().getCombos().get(name);
            if (price == null) {
                System.out.println("Unknown item.");
                continue;
            }

            System.out.print("Quantity: ");
            int qty = Integer.parseInt(sc.nextLine().trim());
            items.add(new String[]{name, String.valueOf(price), String.valueOf(qty)});
        }

        Set<DiscountCoupon> used = new HashSet<>();
        System.out.print("Coupon code (or blank): ");
        String code = sc.nextLine().trim();
        if (!code.isEmpty()) {
            for (DiscountCoupon c : coupons) {
                if (c.getCode().equalsIgnoreCase(code) && c.isValid()) {
                    used.add(c);
                    c.invalidate();
                    break;
                }
            }
            if (used.isEmpty()) System.out.println("Coupon invalid or already used.");
        }

        DeliveryPerson dp = drivers.iterator().next();
        String[][] arr = items.toArray(new String[0][0]);
        Order order = new Order(arr, selected, cust, dp, used);

        selected.addOrder(order);
        orders.add(order);
        admin.recordOrder(order);
        dp.assignOrder(order);
        cust.recordOrder(order);

        System.out.println("Created order:");
        order.displayDetails();
    }

    private void payLastOrderFlow() {
        List<Order> history = cust.getOrderHistory();
        if (history.isEmpty()) {
            System.out.println("No orders.");
            return;
        }
        Order last = history.get(history.size() - 1);
        if (last.getPayment() != null && last.getPayment().getPaidStatus()) {
            System.out.println("Last order already paid.");
            return;
        }

        Payment pay = new Payment(last.getPrice(), last.getDeliveryPerson(), last, cust, new HashSet<>());
        last.setPayment(pay);
        payments.add(pay);
        admin.recordPayment(pay);

        PaymentManager pm = new PaymentManager();
        System.out.print("Enter payment method (card/cash): ");
        String method = sc.nextLine().trim();
        System.out.print("Enter payment details (e.g., last4 of Card): ");
        String details = sc.nextLine().trim();
        pm.process(pay, method, details);
        pay.displayDetails();
    }
}
