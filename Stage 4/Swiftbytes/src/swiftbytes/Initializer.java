/**
 * Author: Amadeo Pena
 * Assignment: Project
 */
package swiftbytes;

import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Initializer {

    private final Set<Customer> listCustomers = new HashSet<>();
    private final Set<Restaurant> listRestaurants = new HashSet<>();
    private final Set<DeliveryVehicle> listVehicles = new HashSet<>();
    private final Set<DiscountCoupon> listCoupons = new HashSet<>();
    private final Set<DeliveryPerson> listDrivers = new HashSet<>();
    private final List<Order> listOrders = new ArrayList<>();
    private final List<Payment> listPayments = new ArrayList<>();
    private final List<Menu> listMenus = new ArrayList<>();

    // Default admin user
    private final Admin admin = new Admin(
        "admin", "admin123", "System Admin", "admin@swift.com", "555-0000", "ADM"
    );

    private final Scanner sc = new Scanner(System.in);

    public void initForGUI() {
        initData();
        
    }

    // Old console entry point still works
    public void Run() {
        initData();
    }

    // Seed data only once
    private void initData() {
        if (!listCustomers.isEmpty()) return; // already initialized

        createGenericCustomers();
        createGenericRestaurantsAndMenus();
        createGenericDriversAndVehicles();
        createGenericOrdersandPayments();
        createGenericCoupons();
    }

    // Getters for GUI
    public Set<Customer> getCustomers() { 
        return listCustomers; 
    }
    
    public Set<DeliveryPerson> getDrivers() { 
        return listDrivers; 
    }
    
    public Admin getAdmin() { 
        return admin; 
    }
    
    public Set<Restaurant> getRestaurants() { 
        return listRestaurants; 
    }
    
    public Set<DiscountCoupon> getCoupons() { 
        return listCoupons; 
    }
    
    public Set<DeliveryVehicle> getVehicles() { 
        return listVehicles; 
    }
    
    public List<Order> getOrders() { 
        return listOrders; 
    }
    
    public List<Payment> getPayments() { 
        return listPayments; 
    }
    
    public List<Menu> getMenu() { 
        return listMenus; 
    }

    /**
     * This method reads discount coupon data from a file named "coupons.txt"
     * and creates DiscountCoupon objects which are added to the listCoupons set.
     */
    private void createGenericCoupons() {
        // Coupon list creation
        String filePath = "list_coupons.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");
                String code = parts[0].trim().replace("_", " ");
                double discountAmount = Double.parseDouble(parts[1].trim().replace("_", " "));
                boolean validity = Boolean.parseBoolean(parts[2].trim().replace("_", " "));
                listCoupons.add(new DiscountCoupon(code, discountAmount, validity));
            }
        }
        catch (IOException e) {
            System.out.println("Error reading coupons file: " + e.getMessage());
        }
    }


    /**
     * This method reads order and payment data from files named "orders.txt" and "payments.txt"
     * and creates Order and Payment objects which are added to the respective lists.
     */
    private void createGenericOrdersandPayments() {
        // Payment list creation
        String filePath = "list_payments.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");
                String orderID = parts[0].trim().replace("_", " ");
                double price = Double.parseDouble(parts[1].trim().replace("_", " ")); 
                String driverName = parts[2].trim().replace("_", " ");
                String paymentID = parts[3].trim().replace("_", " ");
                String customerName = parts[4].trim().replace("_", " ");
                boolean status = Boolean.parseBoolean(parts[5].trim().replace("_", " "));
                listPayments.add(new Payment(paymentID, price, driverName, orderID, customerName, status));
            }
        }
        catch (IOException e) {
            System.out.println("Error reading payments file: " + e.getMessage());
        }

        // Order list creation
        filePath = "list_orders.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");
                String orderID = parts[0].trim().replace("_", " ");
                String[] itemNames = parts[1].trim().replace("_", " ").split(",");
                String[] itemPrices = parts[2].trim().replace("_", " ").split(",");
                String[][] items = new String[itemNames.length][2];
                for (int i = 0; i < itemNames.length; i++) {
                    items[i][0] = itemNames[i];
                    items[i][1] = itemPrices[i];
                }
                String restaurantName = parts[3].trim().replace("_", " ");
                String customerName = parts[4].trim().replace("_", " ");
                String driverID = parts[5].trim().replace("_", " ");
                Set<DiscountCoupon> appliedCoupons = new HashSet<>();
                for (DiscountCoupon coupon : listCoupons) {
                    for (String cID : parts[6].trim().replace("_", " ").split(",")) {
                        if (coupon.getCode().equals(cID)) {
                            appliedCoupons.add(coupon);
                        }
                    }
                }
                listOrders.add(new Order(orderID, items, restaurantName, customerName, driverID, appliedCoupons));
            }
            for (Order order : listOrders) {
                for (Payment payment : listPayments) {
                    if (order.getOrderID().equals(payment.getOrderID())) {
                        order.setPayment(payment);
                    }
                }
            }
        }
        catch (IOException e){
            System.out.println("Error reading orders file: " + e.getMessage());
        }
    }


    /**
     * This method reads customer data from a file named "customers.txt"
     * and creates Customer objects which are added to the listCustomers set.
     */
    private void createGenericCustomers(){
        // Customer list creation
        String filePath = "list_customers.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");
                String username = parts[0].trim().replace("_", " "); 
                String password = parts[1].trim().replace("_", " ");
                String name = parts[2].trim().replace("_", " ");
                String email = parts[3].trim().replace("_", " ");
                String phone = parts[4].trim().replace("_", " ");
                String address = parts[5].trim().replace("_", " ");
                String customerID = parts[6].trim().replace("_", " ");
                listCustomers.add(new Customer(username, password, name, email, phone, address, customerID));
            }
        }
        catch (IOException e) {
            System.out.println("Error reading customers file: " + e.getMessage());
        }
    }


    /**
     * This method reads restaurant data from a file named "restaurants.txt"
     * and creates Restaurant objects which are added to the listRestaurants set.
     */
private void createGenericRestaurantsAndMenus() {

    String filePath = "list_menus.txt";

    try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

        String line;
        String currentID = "";
        List<String> singleItems = new ArrayList<>();
        List<String> singlePrices = new ArrayList<>();
        List<String> comboItems = new ArrayList<>();
        List<String> comboPrices = new ArrayList<>();

        while ((line = br.readLine()) != null) {

            String[] parts = line.split(" ");
            System.out.println(Arrays.toString(parts));

            if (parts[0].trim().equals("ID")) {
                currentID = parts[1].trim();
            }
            else if (parts[0].trim().equals("S")) {
                singleItems.add(parts[1].trim().replace("_", " "));
                singlePrices.add(parts[2].trim());
            }
            else if (parts[0].trim().equals("C")) {
                comboItems.add(parts[1].trim().replace("_", " "));
                comboPrices.add(parts[2].trim());
            }
            else if (parts[0].trim().equals("break")) {

                Menu menu = new Menu(currentID);

                for (int i = 0; i < singleItems.size(); i++) {
                    menu.addSingleItem(
                        singleItems.get(i),
                        Double.parseDouble(singlePrices.get(i))
                    );
                }

                for (int i = 0; i < comboItems.size(); i++) {
                    menu.addComboItem(
                        comboItems.get(i),
                        Double.parseDouble(comboPrices.get(i))
                    );
                }

                listMenus.add(menu);

                currentID = "";
                singleItems.clear();
                singlePrices.clear();
                comboItems.clear();
                comboPrices.clear();
            }
        }

    } catch (IOException e) {
        System.out.println("Error reading menus file: " + e.getMessage());
    }


    // Restaurant list creation
    filePath = "list_restaurants.txt";
    try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

        String line;

        while ((line = br.readLine()) != null) {
            String[] parts = line.split(" ");

            String name = parts[0].trim().replace("_", " ");
            String ID = parts[1].trim();
            String address = parts[2].trim().replace("_", " ");
            double rating = Double.parseDouble(parts[3]);
            String phone = parts[4].trim();

            listRestaurants.add(new Restaurant(ID, name, address, phone, rating));
        }

    } catch (IOException e) {
        System.out.println("Error reading restaurants file: " + e.getMessage());
    }

    //Link Menus to Restaurants
    for (Restaurant r : listRestaurants) {
        for (Menu m : listMenus) {
            if (m.getRestaurantID().equals(r.getID())) {
                r.setMenu(m);
            }
        }
    }
}


    // Creates sample delivery drivers and vehicles
    private void createGenericDriversAndVehicles() {
        // Employee list creation
        String filepath = "list_employees.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");
                String username = parts[0].trim().replace("_", " "); 
                String password = parts[1].trim().replace("_", " ");
                String name = parts[2].trim().replace("_", " ");
                String email = parts[3].trim().replace("_", " ");
                String phone = parts[4].trim().replace("_", " ");
                String address = parts[5].trim().replace("_", " ");
                DeliveryPerson driver = new DeliveryPerson(username, password, name, email, phone, address);
                listDrivers.add(driver);
            }
        }
        catch (IOException e) {
            System.out.println("Error reading drivers file: " + e.getMessage());
        }

        // Vehicle list creation
        filepath = "list_vehicles.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");
                String vehicleName = parts[0].trim().replace("_", " "); 
                String make = parts[1].trim().replace("_", " ");
                String model = parts[2].trim().replace("_", " ");
                String year = parts[3].trim().replace("_", " ");
                String color = parts[4].trim().replace("_", " ");
                String condition = parts[5].trim().replace("_", " ");
                String driverUsername = parts[6].trim().replace("_", " "); // Store for vehicle assignment
                DeliveryVehicle vehicle = new DeliveryVehicle(vehicleName, make, model, year, color, condition, driverUsername);
                listVehicles.add(vehicle);
            }
        }
        catch (IOException e) {
            System.out.println("Error reading vehicles file: " + e.getMessage());
        }
    }


    // Handles login attempts for admin, customer, and delivery driver.
    public void loginMenu() {
        while (true) {
            System.out.println("\nLogin as: 1) Admin  2) Customer  3) Delivery  0) Exit");
            String choice = sc.nextLine().trim();
            if ("0".equals(choice)) break;

            System.out.print("Username: ");
            String u = sc.nextLine().trim();
            System.out.print("Password: ");
            String p = sc.nextLine().trim();

            Login login = new Login(u, p, listCustomers, listDrivers, admin);

            switch (choice) {
                case "1" -> {
                    if (login.authenticateAsAdmin())
                        new AdminMenu(
                            admin,
                            listCustomers,
                            listRestaurants,
                            listDrivers,
                            listVehicles,
                            listCoupons, 
                            listOrders,
                            listPayments,
                            sc
                        ).run();
                    else System.out.println("Invalid admin credentials.");
                }
                case "2" -> {
                    Customer cust = login.authenticateAsCustomer();
                    if (cust != null)
                        new CustomerMenu(cust, listRestaurants, listCoupons, listDrivers, listOrders, listPayments, admin, sc).run();
                    else System.out.println("Invalid customer credentials.");
                }
                case "3" -> {
                    DeliveryPerson dp = login.authenticateAsDeliveryPerson();
                    if (dp != null)
                        new DriverMenu(dp, sc).run();
                    else System.out.println("Invalid driver credentials.");
                }
                default -> System.out.println("Unknown choice.");
            }
        }
        System.out.println("Goodbye!");
    }
    
    public void writeData() {
        new DataWriter(listCoupons, listCustomers, listDrivers, listMenus, listOrders, listPayments, listRestaurants, listVehicles).writeData();
    }
}
