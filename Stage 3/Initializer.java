import java.io.File;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
public class Initializer
{
    public Set<Employee> list1;
    public Set<Customer> list2;
    public List<Menu> list3;
    public Set<Restaurant> list4;
    public Set<DeliveryVehicle> list5;
    public Set<DiscountCoupon> list6;
    private Scanner scanner;

    public Initializer()
    {
        list1 = createGenericEmployees();
        list2 = createGenericCustomers();
        list3 = createGenericMenus();
        list4 = createGenericRestaurants();
        list5 = createGenericVehicles();
        list6 = createGenericCoupons();
    }

    public Set<Employee> createGenericEmployees()
    {
        Set<Employee> newList = new HashSet<>();
        return newList;
    }

    public Set<Customer> createGenericCustomers()
    {
        Set<Customer> newList = new HashSet<>();
        Scanner scanner = new Scanner(new File ("list_customers.txt"));
        while (scanner.hasNextLine())
        {
            String username = scanner.next();
            String password = scanner.next();
            String name = scanner.next().replace("_", " ");
            String email = scanner.next();
            String phone = scanner.next();
            String address = scanner.next().replace("_", " ");
            String customerID = scanner.next().replace("_", " ");
            newList.add(new Customer(username, password, name, email, phone, address, customerID));
        }
        return newList;
    }

    /**
     * This method reads a .txt file
     * @return
     */
    public List<Menu> createGenericMenus()
    {
        List<Menu> newList = new ArrayList<>();
        Scanner scanner = new Scanner(new File ("list_menus.txt"));
        while(scanner.hasNextLine())
        {
            List<String> singleItems = new ArrayList<>();
            List<String> singlePrices = new ArrayList<>();
            List<String> comboItems = new ArrayList<>();
            List<String> comboPrices = new ArrayList<>();
            String ID = "";
            while (!scanner.hasNext(""))
            {
                String next = scanner.next();
                if (next == "ID")
                {
                    ID = scanner.next();
                }
                else if (next == "S")
                {
                    singleItems.add(scanner.next());
                    singlePrices.add(scanner.next());
                }
                else if (next == "C")
                {
                    comboItems.add(scanner.next());
                    comboPrices.add(scanner.next());
                }
            }
            String[][] singlesList = new String[singleItems.size()][2];
            String[][] combosList = new String[comboItems.size()][2];
            for (int x = 0; x < singleItems.size(); x++)
            {
                singlesList[x][0] = singleItems.get(x);
                singlesList[x][1] = singlePrices.get(x);
            }
            for (int x = 0; x < comboItems.size(); x++)
            {
                combosList[x][0] = comboItems.get(x);
                combosList[x][1] = comboPrices.get(x);
            }
            newList.add(new Menu(ID, singlesList, combosList));
        }
        return newList;
    }

    /**
     * This method reads a .txt file
     * @return
     */
    public Set<Restaurant> createGenericRestaurants()
    {
        Set<Restaurant> newList = new HashSet<>();
        Scanner scanner = new Scanner(new File ("list_restaurants.txt"));
        while(scanner.hasNextLine())
        {
            String name = scanner.next().replace("_", " ");
            String ID = scanner.next();
            String address = scanner.next().replace("_", " ");
            Double rating = scanner.nextDouble();
            String phone = scanner.next();
            Menu menu = null;
            for (Menu m : list3)
            {
                if (m.getRestaurantID() == ID) {menu = m;}
            }
            newList.add(new Restaurant(ID, name, address, phone, rating, menu));
        }
        return newList;
    }

    public Set<DeliveryVehicle> createGenericVehicles()
    {
        Set<DeliveryVehicle> newList = new HashSet<>();
        Scanner scanner = new Scanner(new File ("list_vehicles.txt"));
        while(scanner.hasNextLine())
        {
            String name = scanner.next().replace("_", " ");
            String make = scanner.next().replace("_", " ");
            String model = scanner.next().replace("_", " ");
            String year = scanner.next().replace("_", " ");
            String color = scanner.next().replace("_", " ");
            String condition = scanner.next().replace("_", " ");
        }

        return newList;
    }

    /**
     * This method reads a .txt file
     * @return
     */
    public Set<DiscountCoupon> createGenericCoupons()
    {
        Set<DiscountCoupon> newList = new HashSet<>();
        Scanner scanner = new Scanner(new File ("list_coupons.txt"));
        while (scanner.hasNextLine())
        {
            String code = scanner.next();
            double amount = scanner.nextDouble();
            Boolean validity = Boolean.valueOf(scanner.next());
            newList.add(new DiscountCoupon(code, amount, validity));
        }
        return newList;
    }
}