import java.io.File;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
public class Initializer
{
    private Set<Employee> listEmployees;
    private Set<Customer> listCustomers;
    private List<Menu> listMenus;
    private Set<Restaurant> listRestaurants;
    private Set<DeliveryVehicle> listVehicles;
    private Set<DiscountCoupon> listCoupons;
    private Scanner scanner;

    public Initializer()
    {
        listEmployees = createGenericEmployees();
        listCustomers = createGenericCustomers();
        listMenus = createGenericMenus();
        listRestaurants = createGenericRestaurants();
        listVehicles = createGenericVehicles();
        listCoupons = createGenericCoupons();
    }

    public Set<Employee> createGenericEmployees()
    {
        Set<Employee> newList = new HashSet<>();
        return newList;
    }

    public Set<Customer> createGenericCustomers()
    {
        Set<Customer> newList = new HashSet<>();
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
            while (!scanner.hasNext(""))
            {
                if (scanner.next() == "S")
                {
                    singleItems.add(scanner.next());
                    singlePrices.add(scanner.next());
                }
                else if (scanner.next() == "C")
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
            Menu menu = new Menu(singlesList, combosList);
            newList.add(menu);
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
        int menuCounter = 0;
        while(scanner.hasNextLine())
        {
            String name = scanner.next().replace("_", " ");
            String ID = scanner.next();
            String address = scanner.next().replace("_", " ");
            Double rating = scanner.nextDouble();
            String phone = scanner.next();
            Menu menu = listMenus.get(menuCounter);
            newList.add(new Restaurant(ID, name, address, phone, rating, menu));
        }
        return newList;
    }

    public Set<DeliveryVehicle> createGenericVehicles()
    {
        Set<DeliveryVehicle> newList = new HashSet<>();
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