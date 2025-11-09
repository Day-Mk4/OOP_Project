/**
 * Author: Lazo McCarroll
 * Assignment: Project
 */
 public class Menu
{
    private MenuManager menuManager;
    private String restaurantID;
    private String[][] singleItemList;
    private String[][] comboItemList;

    public Menu(String newID, String[][] newSingleItemList, String[][] newComboItemList)
    {
        menuManager = new MenuManager();
        restaurantID = newID;
        singleItemList = newSingleItemList;
        comboItemList = newComboItemList;
    }

    /////////
    /// GETTERS
    /////////
    
    public String[][] getSingleItemList()
    {
        return singleItemList;
    }

    public String[][] getComboItemList()
    {
        return comboItemList;
    }

    public String getRestaurantID()
    {
        return restaurantID;
    }

    /////////
    /// SETTERS & ADDERS
    /////////
    
    /**
     * This method adds a new item to the singles list by calling the MenuManager method addSingleItem()
     * @param item
     * @param price
     * @param index
     */
    public void addSingleItem (String item, double price, byte index)
    {
        singleItemList = menuManager.addSingleItem(item, price, index, singleItemList);
    }

    /**
     * This method adds a new item to the combos list by calling the MenuManager method addComboItem()
     * @param item
     * @param price
     * @param index
     */
    public void addComboItem (String item, double price, byte index)
    {
        comboItemList = menuManager.addComboItem(item, price, index, comboItemList);
    }

    /**
     * This method replaces the item and price Strings of a single item in the singles list
     * @param item
     * @param price
     * @param index
     */
    public void setSingleItem (String item, double price, byte index)
    {
        singleItemList[index-1][0] = item;
        singleItemList[index-1][1] = Double.toString(price);;
    }

    /**
     * This method replaces the item and price Strings of a single item in the combos list
     * @param item
     * @param price
     * @param index
     */
    public void setComboItem (String item, double price, byte index)
    {
        comboItemList[index-1][0] = item;
        comboItemList[index-1][1] = Double.toString(price);;
    }

    /**
     * This method sets a new value for the restaurant ID.
     * @param newID
     */
    public void setRestaurantID (String newID)
    {
        restaurantID = newID;
    }

    public void displayCompleteMenu()
    {
        menuManager.displayCompleteMenu(singleItemList, comboItemList);
    }
}
