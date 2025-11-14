/**
 * Author:
 * Assignment: Project
 */
import java.util.HashMap;
import java.util.Map;

public class Menu {

    private final Restaurant restaurant;
    private final Map<String, Double> singles = new HashMap<>();
    private final Map<String, Double> combos = new HashMap<>();

    // Constructor to create a menu for the given restaurant
    public Menu(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    // @return the restaurant
    public Restaurant getRestaurant() {
        return restaurant;
    }

    // Adds a new single item.
    public void addSingleItem(String name, double price) {
        singles.put(name, price);
    }

    // Updates a single item.
    public void updateSingleItem(String name, double price) {
        singles.put(name, price);
    }

    // Removes a single item.
    public void removeSingleItem(String name) {
        singles.remove(name);
    }

    // Adds a combo item.
    public void addComboItem(String name, double price) {
        combos.put(name, price);
    }

    // Updates a combo item.
    public void updateComboItem(String name, double price) {
        combos.put(name, price);
    }

    // Removes a combo item.
    public void removeComboItem(String name) {
        combos.remove(name);
    }

    // @return singles
    public Map<String, Double> getSingles() {
        return singles;
    }

    // @return combos
    public Map<String, Double> getCombos() {
        return combos;
    }

    // Prints all single items.
    public void displaySinglesMenu() {
        System.out.println("-- Singles --");
        singles.forEach((k, v) -> System.out.println("  " + k + " .... $" + v));
    }

    // Prints all combo items.
    public void displayCombosMenu() {
        System.out.println("-- Combos --");
        combos.forEach((k, v) -> System.out.println("  " + k + " .... $" + v));
    }

    // Prints all complete items.
    public void displayCompleteMenu() {
        displaySinglesMenu();
        displayCombosMenu();
    }
}
