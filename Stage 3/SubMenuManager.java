import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
public class SubMenuManager
{
    private Set<Employee> listEmployees;
    private Set<Customer> listCustomers;
    private List<Menu> listMenus;
    private Set<Restaurant> listRestaurants;
    private Set<DeliveryVehicle> listVehicles;
    private Set<DiscountCoupon> listCoupons;

    public SubMenuManager(Set<Employee> list1, Set<Customer> list2, List<Menu> list3, Set<Restaurant> list4, Set<DeliveryVehicle> list5, Set<DiscountCoupon> list6)
    {
        listEmployees = list1;
        listCustomers = list2;
        listMenus = list3;
        listRestaurants = list4;
        listVehicles = list5;
        listCoupons = list6;
    }
}
