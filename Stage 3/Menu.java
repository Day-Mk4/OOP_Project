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


    public Menu(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public void addSingleItem(String name, double price) {
        singles.put(name, price);
    }

    public void updateSingleItem(String name, double price) {
        singles.put(name, price);
    }

    public void removeSingleItem(String name) {
        singles.remove(name);
    }

    public void addComboItem(String name, double price) {
        combos.put(name, price);
    }

    public void updateComboItem(String name, double price) {
        combos.put(name, price);
    }

    public void removeComboItem(String name) {
        combos.remove(name);
    }

    
    public Map<String, Double> getSingles() {
        return singles;
    }

    public Map<String, Double> getCombos() {
        return combos;
    }

    public void displaySinglesMenu() {
        System.out.println("-- Singles --");
        singles.forEach((k, v) -> System.out.println("  " + k + " .... $" + v));
    }

    public void displayCombosMenu() {
        System.out.println("-- Combos --");
        combos.forEach((k, v) -> System.out.println("  " + k + " .... $" + v));
    }

    public void displayCompleteMenu() {
        displaySinglesMenu();
        displayCombosMenu();
    }
}
