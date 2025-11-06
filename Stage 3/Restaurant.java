/**
 * Author: Amadeo Pena
 * Assignment: Project
 */
import java.util.ArrayList;
import java.util.List;

public class Restaurant {

    private final String restaurantID;
    private final String restaurantName;
    private final String address;
    private final double rating;
    private final String phone;
    private final List<Order> orders = new ArrayList<>();
    private Menu menu;


    public Restaurant(String ID, String name, String address, String phone, double rating) {
        this.restaurantID = ID;
        this.restaurantName = name;
        this.address = address;
        this.phone = phone;
        this.rating = rating;
    }

    public String getName() {
        return restaurantName;
    }

    public String getID() {
        return restaurantID;
    }

    public String getDescription() {
        return address;
    }

    public double getRating() {
        return rating;
    }

    public String getPhone() {
        return phone;
    }

    public List<Order> getOrders() {
        return new ArrayList<>(orders);
    }

    public void addOrder(Order order) {
        if (order != null) {
            orders.add(order);
        }
    }

    public void removeOrder(String orderID) {
        orders.removeIf(o -> o.getOrderID().equals(orderID));
    }

    public void displayOrders() {
        for (Order o : orders) {
            o.displayDetails();
        }
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public void updateMenu(String name, double price) {
        if (menu != null) {
            menu.updateSingleItem(name, price);
        }
    }

    public void displayDetails() {
        System.out.println(
            "Restaurant [" + restaurantID + "] " + restaurantName +
            ", Phone=" + phone +
            ", Rating=" + rating +
            ", Address=" + address
        );
    }
}
