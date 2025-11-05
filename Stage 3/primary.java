import java.io.*;
import java.util.HashSet;
import java.util.Set;
import java.util.Scanner;
public class primary
{

    private Scanner fileScanner;
    private Set<Restaurant> listRestaurants;

    public primary()
    {
        listRestaurants = createGenericRestaurants();
    }

    public Set<Restaurant> createGenericRestaurants()
    {
        Set<Restaurant> newList = new HashSet<>();
        String filePath = "C:\\Users\\Lithi\\OneDrive\\Documents\\GitHub\\OOP_Project\\Stage 3\\dummyDataFiles\\restaurants_list.txt";
        fileScanner = new Scanner(new File(filePath));

        return newList;
    }
}