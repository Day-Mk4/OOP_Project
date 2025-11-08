public class MenuManager
{
    public String[][] addSingleItem (String item, double price, byte index, String[][] singleItemList)
    {
        String[][] newList = new String[singleItemList.length+1][2];
        int newListCounter = 0;
        for (int x = 0; x < singleItemList.length; x++)
        {
            if (newListCounter == index-1)
            {
                newList[newListCounter][0] = item;
                newList[newListCounter][1] = Double.toString(price);
                newListCounter++;
            }
            newList[newListCounter][0] = singleItemList[x][0];
            newList[newListCounter][1] = singleItemList[x][1];
            newListCounter++;
        }
        return newList;
    }

    public String[][] addComboItem (String item, double price, byte index, String[][] comboItemList)
    {
        String[][] newList = new String[comboItemList.length+1][2];
        int newListCounter = 0;
        for (int x = 0; x < comboItemList.length; x++)
        {
            if (newListCounter == index-1)
            {
                newList[newListCounter][0] = item;
                newList[newListCounter][1] = Double.toString(price);
                newListCounter++;
            }
            newList[newListCounter][0] = comboItemList[x][0];
            newList[newListCounter][1] = comboItemList[x][1];
            newListCounter++;
        }
        return newList;
    }
}
