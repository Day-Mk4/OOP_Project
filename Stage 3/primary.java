import java.io.File;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
public class Primary
{
    private Set<Restaurant> listRestaurants;
    private Scanner scanner;

    public primary()
    {
        listRestaurants = createGenericRestaurants();
    }

    public Set<Restaurant> createGenericRestaurants()
    {
        Set<Restaurant> newList = new HashSet<>();
        File restaurantsFile = new File("restaurants_list.txt");
        scanner = new Scanner(restaurantsFile);
        return newList;
    }
}