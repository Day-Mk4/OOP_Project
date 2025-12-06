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
                String datatowrite = coupon.getCode() + " " + coupon.getDiscountAmount() + " " + Boolean.toString(coupon.isValid());
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
                String datatowrite = customer.getUsername() + " " + customer.getPassword() + " " + customer.getName() + " " + customer.getEmail() + " " + customer.getPhone() + " " + customer.getAddress() + " " + customer.getCustomerID();
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
        String filePath = "list_drivers.txt";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (DeliveryPerson driver : drivers) {
                // Process each driver
                String datatowrite = driver.getUsername() + " " + driver.getPassword() + " " + driver.getName() + " " + driver.getEmail() + " " + driver.getPhone() + " " + driver.getAddress();
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
                bw.write("ID : " + menu.getRestaurantID());
                bw.newLine();
                for (Map.Entry<String, Double> entry : menu.getSingles().entrySet()) {
                    String datatowrite = "S " + entry.getKey() + " " + entry.getValue();
                    bw.write(datatowrite);
                    bw.newLine();
                }
                for (Map.Entry<String, Double> entry : menu.getCombos().entrySet()) {
                    String datatowrite = "C " + entry.getKey() + " " + entry.getValue();
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
                String datatowrite = order.getOrderID() + " " + orderItems + " " + orderPrices + " " + order.getRestaurant() + " " + order.getCustomer() + " " + order.getDeliveryPerson() + " " + appliedCoupons;
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
                String datatowrite = payment.getPaymentID() + " " + payment.getPrice() + " " + payment.getDeliveryPerson() + " " + payment.getOrderID() + " " + payment.getCustomer() + " " + Boolean.toString(payment.getPaidStatus());
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
                String datatowrite = restaurant.getName() + " " + restaurant.getID() + " " + restaurant.getAddress() + " " + restaurant.getRating() + " " + restaurant.getPhone() + " ";
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
                String datatowrite = vehicle.getName() + " " + vehicle.getMake() + " " + vehicle.getModel() + " " + vehicle.getYear() + " " + vehicle.getColor() + " " + vehicle.getCondition() + " " + vehicle.getAssignedDriver();
                bw.write(datatowrite);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
