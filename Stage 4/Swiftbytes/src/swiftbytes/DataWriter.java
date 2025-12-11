/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package swiftbytes;

/**
 *
 * @author Lithi
 */

import java.util.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
public class DataWriter {
    private final Set<DiscountCoupon> coupons;
    private final Set<Customer> customers;
    private final Set<DeliveryPerson> drivers;
    private final List<Menu> menus;
    private final List<Order> orders;
    private final List<Payment> payments;
    private final Set<Restaurant> restaurants;
    private final Set<DeliveryVehicle> vehicles;

     /**
     * The constructor initializes the instance variables.
     */
    
    public DataWriter(Set<DiscountCoupon> coupons, Set<Customer> customers, Set<DeliveryPerson> drivers, List<Menu> menus, List<Order> orders, List<Payment> payments, Set<Restaurant> restaurants, Set<DeliveryVehicle> vehicles)
    {
        this.coupons = coupons;
        this.customers = customers;
        this.drivers = drivers;
        this.menus = menus;
        this.orders = orders;
        this.payments = payments;
        this.restaurants = restaurants;
        this.vehicles = vehicles;
    }

    public void writeData()
    {
        // Implementation for writing data to files
        writeCoupons();
        writeCustomers();
        writeDrivers();
        writeMenus();
        writeOrders();
        writePayments();
        writeRestaurants();
        writeVehicles();
    }

    public void writeCoupons()
    {
        // Implementation for writing coupons to file
        String filePath = "list_coupons.txt";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (DiscountCoupon coupon : coupons) {
                // Process each coupon
                String datatowrite = coupon.getCode().replace(' ', '_') + " " + coupon.getDiscountAmount() + " " + Boolean.toString(coupon.isValid());
                bw.write(datatowrite);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeCustomers()
    {
        // Implementation for writing customers to file
        String filePath = "list_customers.txt";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (Customer customer : customers) {
                // Process each customer
                String datatowrite = customer.getUsername().replace(' ', '_') + " " + customer.getPassword().replace(' ', '_') + " " + customer.getName().replace(' ', '_') + " " + customer.getEmail().replace(' ', '_') + " " + customer.getPhone().replace(' ', '_') + " " + customer.getAddress().replace(' ', '_') + " " + customer.getCustomerID().replace(' ', '_');
                bw.write(datatowrite);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeDrivers()
    {
        // Implementation for writing drivers to file
        String filePath = "list_employees.txt";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (DeliveryPerson driver : drivers) {
                // Process each driver
                String datatowrite = driver.getUsername().replace(' ', '_') + " " + driver.getPassword().replace(' ', '_') + " " + driver.getName().replace(' ', '_') + " " + driver.getEmail().replace(' ', '_') + " " + driver.getPhone().replace(' ', '_') + " " + driver.getAddress().replace(' ', '_');
                bw.write(datatowrite);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeMenus()
    {
        // Implementation for writing menus to file
        String filePath = "list_menus.txt";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (Menu menu : menus) {
                // Process each menu
                bw.write("ID " + menu.getRestaurantID());
                bw.newLine();
                for (Map.Entry<String, Double> entry : menu.getSingles().entrySet()) {
                    String datatowrite = "S " + entry.getKey().replace(' ', '_') + " " + entry.getValue();
                    bw.write(datatowrite);
                    bw.newLine();
                }
                for (Map.Entry<String, Double> entry : menu.getCombos().entrySet()) {
                    String datatowrite = "C " + entry.getKey().replace(' ', '_') + " " + entry.getValue();
                    bw.write(datatowrite);
                    bw.newLine();
                }
                bw.write("break");
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeOrders()
    {
        // Implementation for writing orders to file
        String filePath = "list_orders.txt";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (Order order : orders) {
                // Process each order
                String orderItems = "";
                String orderPrices = "";
                String appliedCoupons = "";
                for (String[] item : order.getOrderItems()) {
                    orderItems += item[0] + ",";
                    orderPrices += item[1] + ",";
                }
                for (DiscountCoupon coupon : order.getAppliedCoupons()) {
                    // Process each applied coupon
                    appliedCoupons += coupon.getCode() + ",";
                }
                String datatowrite = order.getOrderID().replace(' ', '_') + " " + orderItems.replace(' ', '_') + " " + orderPrices.replace(' ', '_') + " " + order.getRestaurant().replace(' ', '_') + " " + order.getCustomer().replace(' ', '_') + " " + order.getDeliveryPerson().replace(' ', '_') + " " + appliedCoupons.replace(' ', '_');
                bw.write(datatowrite);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writePayments()
    {
        // Implementation for writing payments to file
        String filePath = "list_payments.txt";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (Payment payment : payments) {
                // Process each payment
                String datatowrite = payment.getPaymentID().replace(' ', '_') + " " + payment.getPrice() + " " + payment.getDeliveryPerson().replace(' ', '_') + " " + payment.getOrderID().replace(' ', '_') + " " + payment.getCustomer().replace(' ', '_') + " " + Boolean.toString(payment.getPaidStatus());
                bw.write(datatowrite);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeRestaurants()
    {
        // Implementation for writing restaurants to file
        String filePath = "list_restaurants.txt";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (Restaurant restaurant : restaurants) {
                // Process each restaurant
                String datatowrite = restaurant.getName().replace(' ', '_') + " " + restaurant.getID().replace(' ', '_') + " " + restaurant.getAddress().replace(' ', '_') + " " + restaurant.getRating() + " " + restaurant.getPhone().replace(' ', '_');
                bw.write(datatowrite);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeVehicles()
    {
        // Implementation for writing vehicles to file
        String filePath = "list_vehicles.txt";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (DeliveryVehicle vehicle : vehicles) {
                // Process each vehicle
                String datatowrite = vehicle.getName().replace(' ', '_') + " " + vehicle.getMake().replace(' ', '_') + " " + vehicle.getModel().replace(' ', '_') + " " + vehicle.getYear().replace(' ', '_') + " " + vehicle.getColor().replace(' ', '_') + " " + vehicle.getCondition().replace(' ', '_') + " " + vehicle.getAssignedDriver().replace(' ', '_');
                bw.write(datatowrite);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
