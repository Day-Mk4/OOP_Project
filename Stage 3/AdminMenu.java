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
    private final List<Order> orders;
    private final List<Payment> payments;
    private final Scanner sc;

    public AdminMenu(Admin admin, Set<Customer> customers, Set<Restaurant> restaurants,
                     Set<DeliveryPerson> drivers, Set<DeliveryVehicle> vehicles,
                     List<Order> orders, List<Payment> payments, Scanner sc) {
        this.admin = admin;
        this.customers = customers;
        this.restaurants = restaurants;
        this.drivers = drivers;
        this.vehicles = vehicles;
        this.orders = orders;
        this.payments = payments;
        this.sc = sc;
    }

    public void run() {
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
                case "1" -> customers.forEach(c -> System.out.println(c.getCustomerID() + " - " + c.getName()));
                case "2" -> addCustomer();
                case "3" -> removeCustomer();
                case "4" -> restaurants.forEach(Restaurant::displayDetails);
                case "5" -> addRestaurant();
                case "6" -> removeRestaurant();
                case "7" -> drivers.forEach(d -> System.out.println(d.getID() + " - " + d.getName()));
                case "8" -> addDriver();
                case "9" -> removeDriver();
                case "10" -> vehicles.forEach(DeliveryVehicle::displayDetails);
                case "11" -> addVehicle();
                case "12" -> removeVehicle();
                case "13" -> orders.forEach(Order::displayDetails);
                case "14" -> payments.forEach(Payment::displayDetails);
                default -> System.out.println("Bad choice.");
            }
        }
    }

    private void addCustomer() {
        System.out.print("New username: ");
        String u = sc.nextLine().trim();
        System.out.print("Password: ");
        String p = sc.nextLine().trim();
        System.out.print("Name: ");
        String n = sc.nextLine().trim();
        Customer c = new Customer(u, p, n, n + "@mail", "555-0000", "NM", "CUST-" + (100 + customers.size()));
        customers.add(c);
        admin.addCustomer(c);
        System.out.println("Added.");
    }

    private void removeCustomer() {
        System.out.print("CustomerID to remove: ");
        String cid = sc.nextLine().trim();
        customers.removeIf(cc -> cc.getCustomerID().equals(cid));
        admin.removeCustomer(cid);
        System.out.println("Removed if existed.");
    }

    private void addRestaurant() {
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
        Menu mu = new Menu(id);
        mu.createSingles();
        mu.createCombos();
        r.setMenu(mu);
        restaurants.add(r);
        admin.addRestaurant(r);
        System.out.println("Added.");
    }

    private void removeRestaurant() {
        System.out.print("RestaurantID to remove: ");
        String rid = sc.nextLine().trim();
        restaurants.removeIf(rr -> rr.getID().equals(rid));
        admin.removeRestaurant(rid);
        System.out.println("Removed if existed.");
    }

    private void addDriver() {
        System.out.print("Username: ");
        String du = sc.nextLine().trim();
        System.out.print("Password: ");
        String dpw = sc.nextLine().trim();
        System.out.print("Name: ");
        String dn = sc.nextLine().trim();
        DeliveryPerson d = new DeliveryPerson(du, dpw, dn, dn + "@mail", "555-1234", "NM");
        drivers.add(d);
        admin.addDeliveryPerson(d);
        System.out.println("Added.");
    }

    private void removeDriver() {
        System.out.print("DeliveryPerson ID to remove: ");
        String did = sc.nextLine().trim();
        drivers.removeIf(dd -> dd.getID().equals(did));
        admin.removeDeliveryPerson(did);
        System.out.println("Removed if existed.");
    }

    private void addVehicle() {
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
        vehicles.add(v);
        admin.addDeliveryVehicle(v);
        System.out.println("Added.");
    }

    private void removeVehicle() {
        System.out.print("Vehicle name to remove: ");
        String vnr = sc.nextLine().trim();
        vehicles.removeIf(vh -> vh.getName().equalsIgnoreCase(vnr));
        admin.removeDeliveryVehicle(vnr);
        System.out.println("Removed if existed.");
    }
}
