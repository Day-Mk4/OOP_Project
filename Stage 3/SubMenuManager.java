import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class SubMenuManager
{
    private Set<Admin> listAdmins;
    private Set<DeliveryPerson> listDrivers;
    private Set<Customer> listCustomers;
    private List<Menu> listMenus;
    private Set<Restaurant> listRestaurants;
    private Set<DeliveryVehicle> listVehicles;
    private Set<DiscountCoupon> listCoupons;
    private List<Payment> listPayments;
    private List<Order> listOrders;
    private final Scanner sc = new Scanner(System.in);

    public SubMenuManager(Set<Admin> list1, Set<DeliveryPerson> list2, Set<Customer> list3, List<Menu> list4, Set<Restaurant> list5, Set<DeliveryVehicle> list6, Set<DiscountCoupon> list7, List<Payment> list8, List<Order> list9)
    {
        listAdmins = list1;
        listDrivers = list2;
        listCustomers = list3;
        listMenus = list4;
        listRestaurants = list5;
        listVehicles = list6;
        listCoupons = list7;
        listPayments = list8;
        listOrders = list9;
    }

    public void loginMenu() {
        while (true) {
            System.out.println("\nLogin as: 1) Admin  2) Customer  3) Delivery  0) Exit");
            String choice = sc.nextLine().trim();
            if ("0".equals(choice)) break;

            System.out.print("Username: ");
            String u = sc.nextLine().trim();
            System.out.print("Password: ");
            String p = sc.nextLine().trim();

            Login login = new Login(u, p, listEmployees, listCustomers, listDrivers, admin);

            switch (choice) {
                case "1":
                    if (login.authenticateAsAdmin()) adminMenu();
                    else System.out.println("Invalid admin credentials.");
                    break;
                case "2":
                    Customer cust = login.authenticateAsCustomer();
                    if (cust != null) customerMenu(cust);
                    else System.out.println("Invalid customer credentials.");
                    break;
                case "3":
                    DeliveryPerson dp = login.authenticateAsDeliveryPerson();
                    if (dp != null) deliveryPersonMenu(dp);
                    else System.out.println("Invalid driver credentials.");
                    break;
                default:
                    System.out.println("Unknown choice.");
            }
        }
        System.out.println("Goodbye!");
    }

    // ===========================
    // Customer Menu
    // ===========================
    private void customerMenu(Customer cust) {
        CustomerMenu custMenu = new CustomerMenu(cust, listRestaurants, listOrders)
    }

    // ===========================
    // Admin Menu
    // ===========================
    private void adminMenu(Admin admin) {
        AdminMenu adminMenu = new AdminMenu(admin);
    }

    // ===========================
    // Delivery Person Menu
    // ===========================
    private void deliveryPersonMenu(DeliveryPerson dp) {
        DriverMenu driverMenu = new DriverMenu(dp);
        DeliveryPerson updatedDP = driverMenu.getDP();
        List<Payment> updatedListDPPayments = driverMenu.getListPayments();
        List<Order> updatedListDPOrders = driverMenu.getListOrders();

        for (DeliveryPerson d : listDrivers)
        {
            if (updatedDP.getID() == d.getID())
            {
                d = updatedDP;
            }
        }
    }

    ////// GETTERS
    
    public Set<Admin> getUpdatedListAdmins() {return listAdmins;}
    public Set<DiscountCoupon> getUpdatedListCoupons() {return listCoupons;}
    public Set<Customer> getUpdatedListCustomers() {return listCustomers;}
    public Set<DeliveryPerson> getUpdatedListDrivers() {return listDrivers;}
    public List<Menu> getUpdatedListMenus() {return listMenus;}
    public List<Order> getUpdatedListOrders() {return listOrders;}
    public List<Payment> getUpdatedListPayments() {return listPayments;}
    public Set<Restaurant> getUpdatedListRestaurants() {return listRestaurants;}
    public Set<DeliveryVehicle> getUpdatedListVehicles() {return listVehicles;}

}