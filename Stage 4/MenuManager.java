/**
 * Author: Lazo McCarroll
 * Assignment: Project
 */
package swiftbytes;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
public class MenuManager
{
    // Instance variables
    public Map<String, Double> singles = new HashMap<>();
    public Map<String, Double> combos = new HashMap<>();
    public final Scanner sc = new Scanner(System.in);

    /**
     * This method allows the user to to add new items and prices to the list of a la carte menu items.
     * @return Map<String, Double> - returns the updated map of a la carte menu items and their prices.
     */
    public Map<String, Double> createSingles()
    {
        while (true)
        {
            System.out.print("Enter single item name (or 'done' to finish): ");
            String name = sc.nextLine().trim();
            if (name.equalsIgnoreCase("done")) {
                break;
            }
            System.out.print("Enter price for " + name + ": ");
            double price = Double.parseDouble(sc.nextLine().trim());
            singles.put(name, price);
        }
        return singles;
    }

    /**
     * This method allows the user to to add new items and prices to the list of combo menu items.
     * @return Map<String, Double> - returns the updated map of combo items and their prices.
     */
    public Map<String, Double> createCombos()
    {
        while (true)
        {
            System.out.print("Enter combo item name (or 'done' to finish): ");
            String name = sc.nextLine().trim();
            if (name.equalsIgnoreCase("done")) {
                break;
            }
            System.out.print("Enter price for " + name + ": ");
            double price = Double.parseDouble(sc.nextLine().trim());
            combos.put(name, price);
        }
        return combos;
    }
}
