/**
 * Author: Lazo McCarroll
 * Assignment: Project
 */
import java.util.ArrayList;
import java.util.List;
 public class Menu
{
    private String restaurantID;
    private String[][] singleItemList;
    private String[][] comboItemList;

    public Menu(String[][] newSingleItemList, String[][] newComboItemList)
    {
        singleItemList = newSingleItemList;
        comboItemList = newComboItemList;
    }

    public void addSingleItem (String item, double price, byte index)
    {
        String[][] newList = new String[singleItemList.length+1][2];
        int newListCounter = 0;
        for (int x = 0; x < singleItemList.length; x++)
        {
            if (x == index)
            {
                newList[x][]
            }
            newList[x][0] = item;
            newList[x][1] = String.valueOf(price);
            newListCounter++;
        }
    }
}
