import java.util.Scanner;
import java.util.Set;
public class AdminMenu
{
    Admin admin;
    public AdminMenu(Admin admin)
    {
        this.admin = admin;
        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.println("\nAdmin Menu");
            System.out.println("1) List Customers");
            System.out.println("2) Add Customer");
            System.out.println("3) Remove Customer");
            System.out.println("4) List Restaurants");
            System.out.println("5) Add Restaurant");
            System.out.println("6) Remove Restaurant");
            System.out.println("7) List Drivers");
            System.out.println("8) Add Driver");
            System.out.println("9) Remove Driver");
            System.out.println("10) List Vehicles");
            System.out.println("11) Add Vehicle");
            System.out.println("12) Remove Vehicle");
            System.out.println("13) View Orders");
            System.out.println("14) View Payments");
            System.out.println("0) Back");

            String ch = sc.nextLine().trim();

            switch (ch) {
                case"0":
                    return;
                case "1":
                    admin.getManagedCustomers().forEach(c -> System.out.println(c.getCustomerID() + " - " + c.getName()));
                    break;
                case "2":
                    System.out.print("New username: ");
                    String u = sc.nextLine().trim();
                    System.out.print("Password: ");
                    String p = sc.nextLine().trim();
                    System.out.print("Name: ");
                    String n = sc.nextLine().trim();
                    Customer c = new Customer(u, p, n, n + "@mail", "555-0000", "NM",
                            "CUST-" + (100 + admin.getManagedCustomers().size()));
                    admin.getManagedCustomers().add(c);
                    admin.addCustomer(c);
                    System.out.println("Added.");
                    break;
                case "3":
                    System.out.print("CustomerID to remove: ");
                    String cid = sc.nextLine().trim();
                    admin.getManagedCustomers().removeIf(cc -> cc.getCustomerID().equals(cid));
                    admin.removeCustomer(cid);
                    System.out.println("Removed if existed.");
                    break;
                case "4":
                    admin.getManagedRestaurants().forEach(Restaurant::displayDetails);
                    break;
                case "5":
                    System.out.print("ID: ");
                    String id = sc.nextLine().trim();
                    System.out.print("Name: ");
                    String rn = sc.nextLine().trim();
                    System.out.print("Address: ");
                    String adr = sc.nextLine().trim();
                    System.out.print("Phone: ");
                    String ph = sc.nextLine().trim();
                    System.out.print("Rating: ");
                    double rt = Double.parseDouble(sc.nextLine().trim());
                    Restaurant r = new Restaurant(id, rn, adr, ph, rt);
                    r.setMenu(new Menu(r));
                    admin.getManagedRestaurants().add(r);
                    admin.addRestaurant(r);
                    System.out.println("Added.");
                    break;
                case "6":
                    System.out.print("RestaurantID to remove: ");
                    String rid = sc.nextLine().trim();
                    admin.getManagedRestaurants().removeIf(rr -> rr.getID().equals(rid));
                    admin.removeRestaurant(rid);
                    System.out.println("Removed if existed.");
                    break;
                case "7":
                    admin.getManagedDrivers().forEach(d -> System.out.println(d.getID() + " - " + d.getName()));
                    break;
                case "8":
                    System.out.print("Username: ");
                    String du = sc.nextLine().trim();
                    System.out.print("Password: ");
                    String dpw = sc.nextLine().trim();
                    System.out.print("Name: ");
                    String dn = sc.nextLine().trim();
                    DeliveryPerson d = new DeliveryPerson(du, dpw, dn, dn + "@mail", "555-1234", "NM");
                    admin.getManagedDrivers().add(d);
                    admin.addDeliveryPerson(d);
                    System.out.println("Added.");
                    break;
                case "9":
                    System.out.print("DeliveryPerson ID to remove: ");
                    String did = sc.nextLine().trim();
                    admin.getManagedDrivers().removeIf(dd -> dd.getID().equals(did));
                    admin.removeDeliveryPerson(did);
                    System.out.println("Removed if existed.");
                    break;
                case "10":
                    admin.getManagedVehicles().forEach(DeliveryVehicle::displayDetails);
                    break;
                case "11":
                    System.out.print("Name: ");
                    String vn = sc.nextLine().trim();
                    System.out.print("Make: ");
                    String mk = sc.nextLine().trim();
                    System.out.print("Model: ");
                    String md = sc.nextLine().trim();
                    System.out.print("Year: ");
                    String yr = sc.nextLine().trim();
                    System.out.print("Color: ");
                    String cl = sc.nextLine().trim();
                    DeliveryVehicle v = new DeliveryVehicle(vn, mk, md, yr, cl);
                    admin.getManagedVehicles().add(v);
                    admin.addDeliveryVehicle(v);
                    System.out.println("Added.");
                    break;
                case "12":
                    System.out.print("Vehicle name to remove: ");
                    String vnr = sc.nextLine().trim();
                    admin.getManagedVehicles().removeIf(vh -> vh.getName().equalsIgnoreCase(vnr));
                    admin.removeDeliveryVehicle(vnr);
                    System.out.println("Removed if existed.");
                    break;
                case "13":
                    admin.getManagedOrders().forEach(Order::displayDetails);
                    break;
                case "14":
                    admin.getManagedPayments().forEach(Payment::displayDetails);
                    break;
                default:
                    System.out.println("Bad choice.");
            }
        }

    }

    public Set<Customer> getUpdatedListCustomers() {return admin.getManagedCustomers();}
    public Set<Restaurant> getUpdatedListRestaurants() {return admin.getManagedRestaurants();}
    public Set<DeliveryPerson> getUpdatedListDrivers() {return admin.getManagedDrivers();}
    public Set<DeliveryVehicle> getUpdatedListVehicles() {return admin.getManagedVehicles();}
}
