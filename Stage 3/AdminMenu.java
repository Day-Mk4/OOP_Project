/**
 * Author: Amadeo Pena
 * Assignment: Project
 */

import java.util.*;

public class AdminMenu {

    private final Admin admin;
    private final Set<Customer> customers;
    private final Set<Restaurant> restaurants;
    private final Set<DeliveryPerson> drivers;
    private final Set<DeliveryVehicle> vehicles;
    private final Set<DiscountCoupon> coupons;
    private final List<Order> orders;
    private final List<Payment> payments;
    private final Scanner sc;

    // FULL constructor
    public AdminMenu(Admin admin,
                     Set<Customer> customers,
                     Set<Restaurant> restaurants,
                     Set<DeliveryPerson> drivers,
                     Set<DeliveryVehicle> vehicles,
                     Set<DiscountCoupon> coupons,
                     List<Order> orders,
                     List<Payment> payments,
                     Scanner sc) {

        this.admin = admin;
        this.customers = customers;
        this.restaurants = restaurants;
        this.drivers = drivers;
        this.vehicles = vehicles;
        this.coupons = coupons;
        this.orders = orders;
        this.payments = payments;
        this.sc = sc;
    }

    // ============================================================
    // MAIN ADMIN MENU
    // ============================================================

    public void run() {
        while (true) {
            System.out.println("\n=== Admin Menu ===");
            System.out.println("1) Customer Sub Menu");
            System.out.println("2) Restaurant Sub Menu");
            System.out.println("3) Driver Sub Menu");
            System.out.println("4) Vehicle Sub Menu");
            System.out.println("5) Manage Menus");
            System.out.println("6) Coupon Sub Menu");
            System.out.println("7) View Orders Report");
            System.out.println("8) View Payments Report");

            System.out.println("0) Back");

            String ch = sc.nextLine().trim();

            switch (ch) {
                case "1" -> custSubMenu();
                case "2" -> restSubMenu();
                case "3" -> driverSubMenu();
                case "4" -> vehicleSubMenu();
                case "5" -> manageMenus();
                case "6" -> couponSubMenu();
                case "7" -> viewOrdersReport();
                case "8" -> viewPaymentsReport();
                case "0" -> { return; }
                default -> System.out.println("Invalid option.");
            }
        }
    }

    // ============================================================
    // CUSTOMER CRUD
    // ============================================================

    private void listCustomers() {
        if (customers.isEmpty()) {
            System.out.println("No customers found.");
            return;
        }
        customers.forEach(c -> System.out.println(c.getCustomerID() + " - " + c.getName()));
    }

    private void addCustomer() {
        clearBuffer();

        System.out.print("Username: ");
        String u = sc.nextLine();

        System.out.print("Password: ");
        String p = sc.nextLine();

        System.out.print("Name: ");
        String n = sc.nextLine();

        System.out.print("Email: ");
        String e = sc.nextLine();

        System.out.print("Phone: ");
        String ph = sc.nextLine();

        System.out.print("Address: ");
        String a = sc.nextLine();

        String id = "CUST-" + (customers.size() + 1);
        customers.add(new Customer(u, p, n, e, ph, a, id));

        System.out.println("Customer added.");
    }

    private void updateCustomer() {
        System.out.print("Customer ID: ");
        String id = sc.nextLine();

        Customer c = findCustomer(id);
        if (c == null) {
            System.out.println("Customer not found.");
            return;
        }

        while (true) {
            System.out.println("\n=== Update Customer [" + c.getCustomerID() + "] ===");
            System.out.println("1) Name");
            System.out.println("2) Email");
            System.out.println("3) Phone");
            System.out.println("4) Address");
            System.out.println("0) Back");

            String ch = sc.nextLine();

            switch (ch) {
                case "1" -> { System.out.print("New Name: "); c.setName(sc.nextLine()); }
                case "2" -> { System.out.print("New Email: "); c.setEmail(sc.nextLine()); }
                case "3" -> { System.out.print("New Phone: "); c.setPhone(sc.nextLine()); }
                case "4" -> { System.out.print("New Address: "); c.setAddress(sc.nextLine()); }
                case "0" -> { return; }
                default -> System.out.println("Invalid.");
            }
        }
    }

    private void removeCustomer() {
        System.out.print("Customer ID: ");
        String id = sc.nextLine();
        customers.removeIf(c -> c.getCustomerID().equals(id));
        System.out.println("Customer removed.");
    }

    // ============================================================
    // RESTAURANT CRUD
    // ============================================================

    private void listRestaurants() {
        if (restaurants.isEmpty()) {
            System.out.println("No restaurants found.");
            return;
        }
        restaurants.forEach(Restaurant::displayDetails);
    }

    private void addRestaurant() {
        clearBuffer();

        System.out.print("ID: ");
        String id = sc.nextLine();

        System.out.print("Name: ");
        String rn = sc.nextLine();

        System.out.print("Address: ");
        String addr = sc.nextLine();

        System.out.print("Phone: ");
        String ph = sc.nextLine();

        System.out.print("Rating: ");
        double rt = Double.parseDouble(sc.nextLine());

        restaurants.add(new Restaurant(id, rn, addr, ph, rt));

        System.out.println("Restaurant added.");
    }

    private void updateRestaurant() {
        System.out.print("Restaurant ID: ");
        String id = sc.nextLine();

        Restaurant r = findRestaurant(id);
        if (r == null) {
            System.out.println("Restaurant not found.");
            return;
        }

        while (true) {
            System.out.println("\n=== Update Restaurant [" + r.getID() + "] ===");
            System.out.println("1) Name");
            System.out.println("2) Address");
            System.out.println("3) Phone");
            System.out.println("4) Rating");
            System.out.println("0) Back");

            String ch = sc.nextLine();

            switch (ch) {
                case "1" -> { System.out.print("New Name: "); r.setName(sc.nextLine()); }
                case "2" -> { System.out.print("New Address: "); r.setAddress(sc.nextLine()); }
                case "3" -> { System.out.print("New Phone: "); r.setPhone(sc.nextLine()); }
                case "4" -> {
                    System.out.print("New Rating: ");
                    r.setRating(Double.parseDouble(sc.nextLine()));
                }
                case "0" -> { return; }
                default -> System.out.println("Invalid.");
            }
        }
    }

    private void removeRestaurant() {
        System.out.print("Restaurant ID: ");
        String id = sc.nextLine();
        restaurants.removeIf(r -> r.getID().equals(id));
        System.out.println("Restaurant removed.");
    }

    // ============================================================
    // DRIVER CRUD
    // ============================================================

    private void listDrivers() {
        if (drivers.isEmpty()) {
            System.out.println("No drivers found.");
            return;
        }
        drivers.forEach(d -> System.out.println(d.getID() + " - " + d.getName()));
    }

    private void addDriver() {
        clearBuffer();

        System.out.print("Username: ");
        String u = sc.nextLine();

        System.out.print("Password: ");
        String p = sc.nextLine();

        System.out.print("Name: ");
        String n = sc.nextLine();

        System.out.print("Email: ");
        String e = sc.nextLine();

        System.out.print("Phone: ");
        String ph = sc.nextLine();

        System.out.print("Address: ");
        String a = sc.nextLine();

        drivers.add(new DeliveryPerson(u, p, n, e, ph, a));

        System.out.println("Driver added.");
    }

    private void updateDriver() {
        System.out.print("Driver ID: ");
        String id = sc.nextLine();

        DeliveryPerson d = findDriver(id);
        if (d == null) {
            System.out.println("Driver not found.");
            return;
        }

        while (true) {
            System.out.println("\n=== Update Driver [" + d.getID() + "] ===");
            System.out.println("1) Name");
            System.out.println("2) Email");
            System.out.println("3) Phone");
            System.out.println("4) Address");
            System.out.println("0) Back");

            String ch = sc.nextLine();

            switch (ch) {
                case "1" -> { System.out.print("New Name: "); d.setName(sc.nextLine()); }
                case "2" -> { System.out.print("New Email: "); d.setEmail(sc.nextLine()); }
                case "3" -> { System.out.print("New Phone: "); d.setPhone(sc.nextLine()); }
                case "4" -> { System.out.print("New Address: "); d.setAddress(sc.nextLine()); }
                case "0" -> { return; }
                default -> System.out.println("Invalid.");
            }
        }
    }

    private void removeDriver() {
        System.out.print("Driver ID: ");
        String id = sc.nextLine();
        drivers.removeIf(d -> d.getID().equals(id));
        System.out.println("Driver removed.");
    }

    // ============================================================
    // VEHICLE CRUD
    // ============================================================

    private void listVehicles() {
        if (vehicles.isEmpty()) {
            System.out.println("No vehicles found.");
            return;
        }
        vehicles.forEach(DeliveryVehicle::displayDetails);
    }

    private void addVehicle() {
        clearBuffer();

        System.out.print("Vehicle Name: ");
        String name = sc.nextLine();

        System.out.print("Make: ");
        String mk = sc.nextLine();

        System.out.print("Model: ");
        String md = sc.nextLine();

        System.out.print("Year: ");
        String yr = sc.nextLine();

        System.out.print("Color: ");
        String cl = sc.nextLine();

        System.out.print("Condition: ");
        String cond = sc.nextLine();

        vehicles.add(new DeliveryVehicle(name, mk, md, yr, cl, cond, null));
        System.out.println("Vehicle added.");
    }

    private void updateVehicle() {
        System.out.print("Vehicle Name: ");
        String id = sc.nextLine();

        DeliveryVehicle v = findVehicle(id);
        if (v == null) {
            System.out.println("Vehicle not found.");
            return;
        }

        while (true) {
            System.out.println("\n=== Update Vehicle [" + v.getName() + "] ===");
            System.out.println("1) Make");
            System.out.println("2) Model");
            System.out.println("3) Year");
            System.out.println("4) Color");
            System.out.println("5) Condition");
            System.out.println("0) Back");

            String ch = sc.nextLine();

            switch (ch) {
                case "1" -> { System.out.print("New Make: "); v.setMake(sc.nextLine()); }
                case "2" -> { System.out.print("New Model: "); v.setModel(sc.nextLine()); }
                case "3" -> { System.out.print("New Year: "); v.setYear(sc.nextLine()); }
                case "4" -> { System.out.print("New Color: "); v.setColor(sc.nextLine()); }
                case "5" -> { System.out.print("New Condition: "); v.setCondition(sc.nextLine()); }
                case "0" -> { return; }
                default -> System.out.println("Invalid.");
            }
        }
    }

    private void removeVehicle() {
        System.out.print("Vehicle Name: ");
        String id = sc.nextLine();
        vehicles.removeIf(v -> v.getName().equals(id));
        System.out.println("Vehicle removed.");
    }

    // ============================================================
    // MENU CRUD (tied to restaurants)
    // ============================================================

    private void manageMenus() {
        System.out.print("Restaurant ID: ");
        String id = sc.nextLine();

        Menu menu = findMenu(id);
        if (menu == null) {
            System.out.println("Menu not found.");
            return;
        }

        String rName = restaurants.stream()
                .filter(r -> r.getID().equals(id))
                .map(Restaurant::getName)
                .findFirst().orElse("Unknown");

        while (true) {
            System.out.println("\n=== Manage Menu for " + rName + " ===");
            System.out.println("1) Add Single Item");
            System.out.println("2) Add Combo Item");
            System.out.println("3) Update Single Item");
            System.out.println("4) Update Combo Item");
            System.out.println("5) Remove Single Item");
            System.out.println("6) Remove Combo Item");
            System.out.println("7) View Menu");
            System.out.println("0) Back");

            String ch = sc.nextLine();

            switch (ch) {
                case "1" -> {
                    System.out.print("Item Name: ");
                    String n = sc.nextLine();
                    System.out.print("Price: ");
                    double p = Double.parseDouble(sc.nextLine());
                    menu.addSingleItem(n, p);
                    System.out.println("Added.");
                }
                case "2" -> {
                    System.out.print("Combo Name: ");
                    String n = sc.nextLine();
                    System.out.print("Price: ");
                    double p = Double.parseDouble(sc.nextLine());
                    menu.addComboItem(n, p);
                    System.out.println("Added.");
                }
                case "3" -> {
                    System.out.print("Item Name: ");
                    String n = sc.nextLine();
                    System.out.print("New Price: ");
                    double p = Double.parseDouble(sc.nextLine());
                    menu.updateSingleItem(n, p);
                    System.out.println("Updated.");
                }
                case "4" -> {
                    System.out.print("Combo Name: ");
                    String n = sc.nextLine();
                    System.out.print("New Price: ");
                    double p = Double.parseDouble(sc.nextLine());
                    menu.updateComboItem(n, p);
                    System.out.println("Updated.");
                }
                case "5" -> {
                    System.out.print("Item Name: ");
                    menu.removeSingleItem(sc.nextLine());
                    System.out.println("Removed.");
                }
                case "6" -> {
                    System.out.print("Combo Name: ");
                    menu.removeComboItem(sc.nextLine());
                    System.out.println("Removed.");
                }
                case "7" -> menu.displayCompleteMenu();
                case "0" -> { return; }
                default -> System.out.println("Invalid.");
            }
        }
    }

    // ============================================================
    // COUPON CRUD
    // ============================================================

    private void listCoupons() {
        if (coupons.isEmpty()) {
            System.out.println("No coupons found.");
            return;
        }
        coupons.forEach(c ->
                System.out.println(c.getCode() + " Discount: " + c.getValue() + "  Valid: " + c.isValid()));
    }

    private void addCoupon() {
        clearBuffer();

        System.out.print("Coupon Code: ");
        String code = sc.nextLine();

        System.out.print("Value (decimal % or flat amount): ");
        double v = Double.parseDouble(sc.nextLine());

        System.out.print("Valid? (true/false): ");
        boolean b = Boolean.parseBoolean(sc.nextLine());

        coupons.add(new DiscountCoupon(code, v, b));

        System.out.println("Coupon added.");
    }

    private void updateCoupon() {
        System.out.print("Coupon Code: ");
        String code = sc.nextLine();

        DiscountCoupon c = findCoupon(code);
        if (c == null) {
            System.out.println("Coupon not found.");
            return;
        }

        while (true) {
            System.out.println("\n=== Update Coupon [" + c.getCode() + "] ===");
            System.out.println("1) Value");
            System.out.println("2) Toggle Validity");
            System.out.println("0) Back");

            String ch = sc.nextLine();

            switch (ch) {
                case "1" -> {
                    System.out.print("New Value: ");
                    c.setValue(Double.parseDouble(sc.nextLine()));
                }
                case "2" -> {
                    c.setValid(!c.isValid());
                    System.out.println("Validity toggled.");
                }
                case "0" -> { return; }
                default -> System.out.println("Invalid.");
            }
        }
    }

    private void removeCoupon() {
        System.out.print("Coupon Code: ");
        String code = sc.nextLine();
        coupons.removeIf(c -> c.getCode().equals(code));
        System.out.println("Coupon removed.");
    }

    // ============================================================
    // REPORTS
    // ============================================================

    private void viewOrdersReport() {
        if (orders.isEmpty()) {
            System.out.println("No orders.");
            return;
        }
        orders.forEach(Order::displayDetails);
    }

    private void viewPaymentsReport() {
        if (payments.isEmpty()) {
            System.out.println("No payments.");
            return;
        }
        payments.forEach(Payment::displayDetails);
    }

    // ============================================================
    // FINDER METHODS
    // ============================================================

    private Customer findCustomer(String id) {
        return customers.stream()
                .filter(c -> c.getCustomerID().equals(id))
                .findFirst().orElse(null);
    }

    private Restaurant findRestaurant(String id) {
        return restaurants.stream()
                .filter(r -> r.getID().equals(id))
                .findFirst().orElse(null);
    }

    private DeliveryPerson findDriver(String id) {
        return drivers.stream()
                .filter(d -> d.getID().equals(id))
                .findFirst().orElse(null);
    }

    private DeliveryVehicle findVehicle(String name) {
        return vehicles.stream()
                .filter(v -> v.getName().equals(name))
                .findFirst().orElse(null);
    }

    private Menu findMenu(String restId) {
        Restaurant r = findRestaurant(restId);
        return (r != null) ? r.getMenu() : null;
    }

    private DiscountCoupon findCoupon(String code) {
        return coupons.stream()
                .filter(c -> c.getCode().equals(code))
                .findFirst().orElse(null);
    }

    // ============================================================
    // INPUT FIX (avoids nextLine skipping bug)
    // ============================================================

    private void clearBuffer() {
        if (sc.hasNextLine()) sc.nextLine();
    }

    // ============================================================
    // SUBMENUS
    // ============================================================

    private void custSubMenu() {
        while (true) {
            System.out.println("\n=== Admin: Customer Sub Menu ===");
            System.out.println("1) List Customers");
            System.out.println("2) Add Customer");
            System.out.println("3) Update Customer");
            System.out.println("4) Remove Customer");
            System.out.println("0) Back");

            String ch = sc.nextLine().trim();

            switch (ch) {
                case "1" -> listCustomers();
                case "2" -> addCustomer();
                case "3" -> updateCustomer();
                case "4" -> removeCustomer();
                case "0" -> { return; }
                default -> System.out.println("Invalid option.");
            }
        }
    }

    private void restSubMenu() {
        while (true) {
            System.out.println("\n=== Admin: Restaurant Sub Menu ===");
            System.out.println("1) List Restaurants");
            System.out.println("2) Add Restaurant");
            System.out.println("3) Update Restaurant");
            System.out.println("4) Remove Restaurant");
            System.out.println("0) Back");

            String ch = sc.nextLine().trim();

            switch (ch) {
                case "1" -> listRestaurants();
                case "2" -> addRestaurant();
                case "3" -> updateRestaurant();
                case "4" -> removeRestaurant();
                case "0" -> { return; }
                default -> System.out.println("Invalid option.");
            }
        }
    }

    private void driverSubMenu() {
        while (true) {
            System.out.println("\n=== Admin: Driver Sub Menu ===");
            System.out.println("1) List Drivers");
            System.out.println("2) Add Driver");
            System.out.println("3) Update Driver");
            System.out.println("4) Remove Driver");
            System.out.println("0) Back");

            String ch = sc.nextLine().trim();

            switch (ch) {
                case "1" -> listDrivers();
                case "2" -> addDriver();
                case "3" -> updateDriver();
                case "4" -> removeDriver();
                case "0" -> { return; }
                default -> System.out.println("Invalid option.");
            }
        }
    }

    private void vehicleSubMenu() {
        while (true) {
            System.out.println("\n=== Admin: Vehicle Sub Menu ===");
            System.out.println("1) List Vehicles");
            System.out.println("2) Add Vehicle");
            System.out.println("3) Update Vehicle");
            System.out.println("4) Remove Vehicle");
            System.out.println("0) Back");

            String ch = sc.nextLine().trim();

            switch (ch) {
                case "1" -> listVehicles();
                case "2" -> addVehicle();
                case "3" -> updateVehicle();
                case "4" -> removeVehicle();
                case "0" -> { return; }
                default -> System.out.println("Invalid option.");
            }
        }
    }

    private void couponSubMenu() {
        while (true) {
            System.out.println("\n=== Admin: Coupon Sub Menu ===");
            System.out.println("1) List Coupons");
            System.out.println("2) Add Coupon");
            System.out.println("3) Update Coupon");
            System.out.println("4) Remove Coupon");
            System.out.println("0) Back");

            String ch = sc.nextLine().trim();

            switch (ch) {
                case "1" -> listCoupons();
                case "2" -> addCoupon();
                case "3" -> updateCoupon();
                case "4" -> removeCoupon();
                case "0" -> { return; }
                default -> System.out.println("Invalid option.");
            }
        }
    }
}
