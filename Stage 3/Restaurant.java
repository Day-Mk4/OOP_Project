/**
 * Author: Amadeo Pena
 * Assignment: Project
 */
import java.util.ArrayList;

public class Restaurant {
    private String restaurantName;
    private String restaurantID;
    private String address;
    private Menu menu;
    private double rating;
    private String phone;
    private ArrayList<Order> orders = new ArrayList<>();

    public Restaurant(String ID, String name, String address, String phone, double rating, Menu menu) {
        this.restaurantID = ID;
        this.restaurantName = name;
        this.address = address; 
        this.phone = phone;
        this.rating = rating;
        this.menu = menu;
    }

    public String getName() 
        { return restaurantName; 
    }
    
    public String getID() 
        { return restaurantID; 
    }
    
    public String getAddress() 
        { return address; 
    }
    
    public double getRating() 
        { return rating; 
    }
    
    public String getPhone() 
        { return phone; 
    }
     
    public ArrayList<Order> getOrders() 
        { return orders; 
    }
    
    public void addOrder(Order order) 
        { if (order != null) orders.add(order); 
    }
    
    public void removeOrder(String orderID) {
    for (int i = 0; i < orders.size(); i++) {
        Order o = orders.get(i);
        if (o != null && orderID != null && orderID.equals(o.getOrderID())) {
            orders.remove(i);
            break;
        }
    }
}
    
    public void displayOrders() {
        System.out.println("Orders for restaurant " + restaurantName + ":");
        for (Order o : orders) o.displayDetails();
    }

    public Menu getMenu() 
        { return menu; 
    }
    
    public void setMenu(Menu menu) 
        { this.menu = menu; 
    }

    public void updateMenu(String name, double price) {
        if (menu != null) menu.updateSingleItem(name, price);
    }

    public void displayDetails() {
    System.out.println("Restaurant ID: " + restaurantID);
    System.out.println("Name: " + restaurantName);
    System.out.println("Address: " + address);
    System.out.println("Phone: " + phone);
    System.out.println("Rating: " + rating);
    System.out.println("Menu: " + menu);
}

}
