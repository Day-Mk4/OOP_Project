/**
 * Author: Amadeo Pena
 * Assignment: Project
 */
package swiftbytes;

import java.util.Scanner;

public class DriverMenu {
    private final DeliveryPerson dp;
    private final Scanner sc;

    // Constructor for driver menu
    public DriverMenu(DeliveryPerson dp, Scanner sc) {
        this.dp = dp;
        this.sc = sc;
    }

    // Runs the driver menu loop
    public void run() {
        while (true) {
            System.out.println("\nDelivery Menu - " + dp.getName());
            System.out.println("1) View Assigned Orders");
            System.out.println("2) Complete Order");
            System.out.println("0) Back");
            String ch = sc.nextLine().trim();

            switch (ch) {
                case "1" -> dp.displayAssignedOrders();
                case "2" -> {
                    System.out.print("Order ID: ");
                    String oid = sc.nextLine().trim();
                    dp.completeOrder(oid);
                }
                case "0" -> { return; }
                default -> System.out.println("Bad choice.");
            }
        }
    }
}
