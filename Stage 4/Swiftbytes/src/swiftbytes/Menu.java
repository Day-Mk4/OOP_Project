/**
 * Author: Lazo McCarroll
 * Assignment: Project
 */
package swiftbytes;

import java.util.HashMap;
import java.util.Map;

public class Menu {

    // Instance variables
    private final String restaurantID;
    private final Map<String, Double> singles = new HashMap<>();
    private final Map<String, Double> combos = new HashMap<>();

    /**
     * This constructor initializes the instance variables.
     * @param restaurantID
     */
    public Menu(String restaurantID) {
        this.restaurantID = restaurantID;
    }

    /**
     * This method returns the restaurant ID.
     * @return
     */
    public String getRestaurantID() {
        return restaurantID;
    }

    /**
     * This method adds an item to the a la carte menu.
     * @param name
     * @param price
     */
    public void addSingleItem(String name, double price) {
        singles.put(name, price);
    }

    /**
     * This method updates an item in the a la carte menu.
     * @param name
     * @param price
     */
    public void updateSingleItem(String name, double price) {
        singles.put(name, price);
    }

    /**
     * This method removes an item from the a la carte menu.
     * @param name
     */
    public void removeSingleItem(String name) {
        singles.remove(name);
    }

    /**
     * This method adds an item to the combo menu.
     * @param name
     * @param price
     */
    public void addComboItem(String name, double price) {
        combos.put(name, price);
    }

    /**
     * This method updates an item from the combo menu.
     * @param name
     * @param price
     */
    public void updateComboItem(String name, double price) {
        combos.put(name, price);
    }

    /**
     * This method removes an item from the combo menu
     * @param name
     */
    public void removeComboItem(String name) {
        combos.remove(name);
    }

    /**
     * This method returns the a la carte menu.
     * @return
     */
    public Map<String, Double> getSingles() {
        return singles;
    }

    /**
     * This method returns the combo menu.
     * @return
     */
    public Map<String, Double> getCombos() {
        return combos;
    }

    /**
     * This method prints the a la carte menu.
     */
    public void displaySinglesMenu() {
        System.out.println("-- Singles --");
        singles.forEach((k, v) -> System.out.println("  " + k + " .... $" + v));
    }

    /**
     * This method prints the combo menu.
     */
    public void displayCombosMenu() {
        System.out.println("-- Combos --");
        combos.forEach((k, v) -> System.out.println("  " + k + " .... $" + v));
    }

    /**
     * This method prints the complete menu.
     */
    public void displayCompleteMenu() {
        displaySinglesMenu();
        displayCombosMenu();
    }
}
