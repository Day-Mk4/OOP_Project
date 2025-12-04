/**
 * Author: Amadeo Pena
 * Assignment: Project
 */
package swiftbytes;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {

    private String restaurantID;
    private String restaurantName;
    private String address;
    private double rating;
    private String phone;
    private List<Order> orders = new ArrayList<>();
    private Menu menu;


    // Constructor for Restaurant
    public Restaurant(String ID, String name, String address, String phone, double rating) {
        this.restaurantID = ID;
        this.restaurantName = name;
        this.address = address;
        this.phone = phone;
        this.rating = rating;
    }

    // @return the restaurant name 
    public String getName() {
        return restaurantName;
    }


    // @return the restaurant id 
    public String getID() {
        return restaurantID;
    }

    // @return the restaurant address 
    public String getAddress() {
        return address;
    }


    // @return the restaurant rating 
    public double getRating() {
        return rating;
    }


    // @return the restaurant phone 
    public String getPhone() {
        return phone;
    }

    /**
     * Returns list of orders.
     * @return a copy of the orders list
     */
    public List<Order> getOrders() {
        return new ArrayList<>(orders);
    }

    /**
     * Adds an order to the restaurant.
     * @param order the order to add
     */
    public void addOrder(Order order) {
        if (order != null) {
            orders.add(order);
        }
    }

    /**
     * Removes an order by ID.
     * @param orderID the ID of the order to remove
     */
    public void removeOrder(String orderID) {
        orders.removeIf(o -> o.getOrderID().equals(orderID));
    }

    // Displays all orders placed at this restaurant.
    public void displayOrders() {
        for (Order o : orders) {
            o.displayDetails();
        }
    }

     // @return the menu
    public Menu getMenu() {
        return menu;
    }

    // Sets menu
    public void setMenu(Menu menu) {
        this.menu = menu;
    }
    
    // Sets Name
    public void setName(String name) {
        this.restaurantName  = name;
    }

    // Sets Address
    public void setAddress(String address) {
        this.address = address;
    }

    // Sets Phone
    public void setPhone(String phone) {
        this.phone = phone;
    }

    // Sets Rating
    public void setRating(double rating) {
        this.rating = rating;
    }


    /**
     * Updates a menu item's price.
     * @param name  the item name
     * @param price the new price
     */
    public void updateMenu(String name, double price) {
        if (menu != null) {
            menu.updateSingleItem(name, price);
        }
    }

    // Displays restaurant details.
    public void displayDetails() {
        System.out.println(
            "Restaurant [" + restaurantID + "] " + restaurantName +
            ", Phone: " + phone +
            ", Rating: " + rating +
            ", Address: " + address
        );
    }
}
