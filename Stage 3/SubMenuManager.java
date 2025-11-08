import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class SubMenuManager
{
    private Set<Employee> listEmployees;
    private Set<Customer> listCustomers;
    private List<Menu> listMenus;
    private Set<Restaurant> listRestaurants;
    private Set<DeliveryVehicle> listVehicles;
    private Set<DiscountCoupon> listCoupons;
    private final Scanner sc = new Scanner(System.in);

    public SubMenuManager(Set<Employee> list1, Set<Customer> list2, List<Menu> list3, Set<Restaurant> list4, Set<DeliveryVehicle> list5, Set<DiscountCoupon> list6)
    {
        listEmployees = list1;
        listCustomers = list2;
        listMenus = list3;
        listRestaurants = list4;
        listVehicles = list5;
        listCoupons = list6;
    }

    public void loginMenu() {
        while (true) {
            System.out.println("\nLogin as: 1) Admin  2) Customer  3) Delivery  0) Exit");
            String choice = sc.nextLine().trim();
            if ("0".equals(choice)) break;

            System.out.print("Username: ");
            String u = sc.nextLine().trim();
            System.out.print("Password: ");
            String p = sc.nextLine().trim();

            Login login = new Login(u, p, listEmployees, listCustomers, listDrivers, admin);

            switch (choice) {
                case "1":
                    if (login.authenticateAsAdmin()) adminMenu();
                    else System.out.println("Invalid admin credentials.");
                    break;
                case "2":
                    Customer cust = login.authenticateAsCustomer();
                    if (cust != null) customerMenu(cust);
                    else System.out.println("Invalid customer credentials.");
                    break;
                case "3":
                    DeliveryPerson dp = login.authenticateAsDeliveryPerson();
                    if (dp != null) deliveryPersonMenu(dp);
                    else System.out.println("Invalid driver credentials.");
                    break;
                default:
                    System.out.println("Unknown choice.");
            }
        }
        System.out.println("Goodbye!");
    }

    // ===========================
    // Customer Menu
    // ===========================
    private void customerMenu(Customer cust) {
        while (true) {
            System.out.println("\nCustomer Menu - " + cust.getName());
            System.out.println("1) Browse Restaurants");
            System.out.println("2) View Order History");
            System.out.println("3) Place Order");
            System.out.println("4) Pay For Last Order");
            System.out.println("0) Back");
            String ch = sc.nextLine().trim();

            if ("0".equals(ch)) break;

            switch (ch) {
                case "1":
                    listRestaurants.forEach(Restaurant::displayDetails);
                    break;
                case "2":
                    cust.displayOrderHistory();
                    break;
                case "3":
                    placeOrderFlow(cust);
                    break;
                case "4":
                    payLastOrderFlow(cust);
                    break;
                default:
                    System.out.println("Bad choice.");
            }
        }
    }

    private Restaurant chooseRestaurant() {
        List<Restaurant> list = new ArrayList<>(listRestaurants);
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

    private void placeOrderFlow(Customer cust) {
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
            for (DiscountCoupon c : listCoupons) {
                if (c.getCode().equalsIgnoreCase(code) && c.isValid()) {
                    used.add(c);
                    c.invalidate();
                    break;
                }
            }
            if (used.isEmpty()) System.out.println("Coupon invalid or already used.");
        }

        DeliveryPerson dp = listDrivers.iterator().next();
        String[][] arr = items.toArray(new String[0][0]);
        Order order = new Order(arr, selected, cust, dp, used);

        selected.addOrder(order);
        listOrders.add(order);
        admin.recordOrder(order);
        dp.assignOrder(order);
        cust.recordOrder(order);

        System.out.println("Created order:");
        order.displayDetails();
    }

    private void payLastOrderFlow(Customer cust) {
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
        listPayments.add(pay);
        admin.recordPayment(pay);

        PaymentManager pm = new PaymentManager();
        System.out.print("Enter payment method (card/cash): ");
        String method = sc.nextLine().trim();
        System.out.print("Enter payment details (e.g., last4 or receipt): ");
        String details = sc.nextLine().trim();
        pm.process(pay, method, details);
        pay.displayDetails();
    }

    // ===========================
    // Admin Menu
    // ===========================
    private void adminMenu() {
        while (true) {
            System.out.println("\nAdmin Menu");
            System.out.println("1) List Customers");
            System.out.println("2) Add Customer");
            System.out.println("3) Remove Customer");
            System.out.println("4) List Restaurants");
            System.out.println("5) Add Restaurant");
            System.out.println("6) Remove Restaurant");
            System.out.println("7) List Drivers");
            System.out.println("8) Add Driver");
            System.out.println("9) Remove Driver");
            System.out.println("10) List Vehicles");
            System.out.println("11) Add Vehicle");
            System.out.println("12) Remove Vehicle");
            System.out.println("13) View Orders");
            System.out.println("14) View Payments");
            System.out.println("0) Back");

            String ch = sc.nextLine().trim();
            if ("0".equals(ch)) break;

            switch (ch) {
                case "1":
                    listCustomers.forEach(c -> System.out.println(c.getCustomerID() + " - " + c.getName()));
                    break;
                case "2":
                    System.out.print("New username: ");
                    String u = sc.nextLine().trim();
                    System.out.print("Password: ");
                    String p = sc.nextLine().trim();
                    System.out.print("Name: ");
                    String n = sc.nextLine().trim();
                    Customer c = new Customer(u, p, n, n + "@mail", "555-0000", "NM",
                            "CUST-" + (100 + listCustomers.size()));
                    listCustomers.add(c);
                    admin.addCustomer(c);
                    System.out.println("Added.");
                    break;
                case "3":
                    System.out.print("CustomerID to remove: ");
                    String cid = sc.nextLine().trim();
                    listCustomers.removeIf(cc -> cc.getCustomerID().equals(cid));
                    admin.removeCustomer(cid);
                    System.out.println("Removed if existed.");
                    break;
                case "4":
                    listRestaurants.forEach(Restaurant::displayDetails);
                    break;
                case "5":
                    System.out.print("ID: ");
                    String id = sc.nextLine().trim();
                    System.out.print("Name: ");
                    String rn = sc.nextLine().trim();
                    System.out.print("Address: ");
                    String adr = sc.nextLine().trim();
                    System.out.print("Phone: ");
                    String ph = sc.nextLine().trim();
                    System.out.print("Rating: ");
                    double rt = Double.parseDouble(sc.nextLine().trim());
                    Restaurant r = new Restaurant(id, rn, adr, ph, rt);
                    r.setMenu(new Menu(r));
                    listRestaurants.add(r);
                    admin.addRestaurant(r);
                    System.out.println("Added.");
                    break;
                case "6":
                    System.out.print("RestaurantID to remove: ");
                    String rid = sc.nextLine().trim();
                    listRestaurants.removeIf(rr -> rr.getID().equals(rid));
                    admin.removeRestaurant(rid);
                    System.out.println("Removed if existed.");
                    break;
                case "7":
                    listDrivers.forEach(d -> System.out.println(d.getID() + " - " + d.getName()));
                    break;
                case "8":
                    System.out.print("Username: ");
                    String du = sc.nextLine().trim();
                    System.out.print("Password: ");
                    String dpw = sc.nextLine().trim();
                    System.out.print("Name: ");
                    String dn = sc.nextLine().trim();
                    DeliveryPerson d = new DeliveryPerson(du, dpw, dn, dn + "@mail", "555-1234", "NM");
                    listDrivers.add(d);
                    admin.addDeliveryPerson(d);
                    System.out.println("Added.");
                    break;
                case "9":
                    System.out.print("DeliveryPerson ID to remove: ");
                    String did = sc.nextLine().trim();
                    listDrivers.removeIf(dd -> dd.getID().equals(did));
                    admin.removeDeliveryPerson(did);
                    System.out.println("Removed if existed.");
                    break;
                case "10":
                    listVehicles.forEach(DeliveryVehicle::displayDetails);
                    break;
                case "11":
                    System.out.print("Name: ");
                    String vn = sc.nextLine().trim();
                    System.out.print("Make: ");
                    String mk = sc.nextLine().trim();
                    System.out.print("Model: ");
                    String md = sc.nextLine().trim();
                    System.out.print("Year: ");
                    String yr = sc.nextLine().trim();
                    System.out.print("Color: ");
                    String cl = sc.nextLine().trim();
                    DeliveryVehicle v = new DeliveryVehicle(vn, mk, md, yr, cl);
                    listVehicles.add(v);
                    admin.addDeliveryVehicle(v);
                    System.out.println("Added.");
                    break;
                case "12":
                    System.out.print("Vehicle name to remove: ");
                    String vnr = sc.nextLine().trim();
                    listVehicles.removeIf(vh -> vh.getName().equalsIgnoreCase(vnr));
                    admin.removeDeliveryVehicle(vnr);
                    System.out.println("Removed if existed.");
                    break;
                case "13":
                    listOrders.forEach(Order::displayDetails);
                    break;
                case "14":
                    listPayments.forEach(Payment::displayDetails);
                    break;
                default:
                    System.out.println("Bad choice.");
            }
        }
    }

    // ===========================
    // Delivery Person Menu
    // ===========================
    private void deliveryPersonMenu(DeliveryPerson dp) {
        while (true) {
            System.out.println("\nDelivery Menu - " + dp.getName());
            System.out.println("1) View Assigned Orders");
            System.out.println("2) Complete Order");
            System.out.println("0) Back");
            String ch = sc.nextLine().trim();

            if ("0".equals(ch)) break;

            switch (ch) {
                case "1":
                    dp.displayAssignedOrders();
                    break;
                case "2":
                    System.out.print("Order ID: ");
                    String oid = sc.nextLine().trim();
                    dp.completeOrder(oid);
                    break;
                default:
                    System.out.println("Bad choice.");
            }
        }
    }
}