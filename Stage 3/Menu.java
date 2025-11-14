/**
 * Author: Lazo McCarroll
 * Assignment: Project
 */
import java.util.HashMap;
import java.util.Map;

public class Menu {

    // Instance variables
    private final String restaurantID;
    private final MenuManager mm = new MenuManager();;
    private Map<String, Double> singles = new HashMap<>();
    private Map<String, Double> combos = new HashMap<>();

    /**
     * Thos costructor initializes the instance variable restaurantID.
     * @param id
     */
    public Menu(String id) {
        this.restaurantID = id;
    }

    /**
     * This method adds a single item to the a la carte menu.
     * @param name
     * @param price
     */
    public void addSingleItem(String name, double price) {
        singles.put(name, price);
    }

    /**
     * This method updates the price of an existing a la carte menu item.
     * @param name
     * @param price
     */
    public void updateSingleItem(String name, double price) {
        singles.put(name, price);
    }

    /**
     * This method removes an a la carte menu item.
     * @param name
     */
    public void removeSingleItem(String name) {
        singles.remove(name);
    }

    /**
     * This method adds a combo item to the combo menu.
     * @param name
     * @param price
     */
    public void addComboItem(String name, double price) {
        combos.put(name, price);
    }

    /**
     * This method updates the price of an existing combo menu item.
     * @param name
     * @param price
     */
    public void updateComboItem(String name, double price) {
        combos.put(name, price);
    }

    /**
     * This method removes a combo menu item.
     * @param name
     */
    public void removeComboItem(String name) {
        combos.remove(name);
    }

    /**
     * This method returns the a la carte menu items and their prices.
     * @return
     */
    public Map<String, Double> getSingles() {
        return singles;
    }

    /**
     * This method returns the combo menu items and their prices.
     * @return
     */
    public Map<String, Double> getCombos() {
        return combos;
    }

    /**
     * This method displays the a la carte menu items and their prices.
     */
    public void displaySinglesMenu() {
        System.out.println("-- Singles --");
        singles.forEach((k, v) -> System.out.println("  " + k + " .... $" + v));
    }

    /**
     * This method displays the combo menu items and their prices.
     */
    public void displayCombosMenu() {
        System.out.println("-- Combos --");
        combos.forEach((k, v) -> System.out.println("  " + k + " .... $" + v));
    }

    /**
     * This method displays the complete menu with a la carte and combo items.
     */
    public void displayCompleteMenu() {
        displaySinglesMenu();
        displayCombosMenu();
    }

    /**
     * This method calls the MenuManager method createSingles() to populate the a la carte menu.
     */
    public void createSingles() {
        singles = mm.createSingles();
    }

    /**
     * This method calls the MenuManager method createCombos() to populate the combo menu.
     */
    public void createCombos() {
        combos = mm.createCombos();
    }
}
