
import java.util.*;
public class UniversalManager 
{
	private static Tables[] tablesArray = new Tables[6];
	static Menu menu = new Menu();
	
	public static void init() 
	{
		for(int x = 0; x < tablesArray.length; x++) 
		{
			tablesArray[x] = new Tables(true,6,false);
		}
	}
	
	public static Tables getTable(int tableNumber) 
	{
		return(tablesArray[tableNumber-1]);
	}
	
	public static Menu getMenu() 
	{
		return menu;
	}
}