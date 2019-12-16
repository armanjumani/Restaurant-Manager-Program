
import java.util.*;
public class UniversalManager 
{
	private static ArrayList<Tables> tablesArray;
	private static ArrayList<Employee> employeeArray;
	private static PriorityQueue<Order> orders;
	private static String managerCode = "124578";
	static Menu menu = new Menu();
	
	public static void init(ArrayList<Tables> tables, ArrayList<Employee> employees) 
	{
		tablesArray = tables;
		employeeArray = employees;
		orders = new PriorityQueue<Order>();
	}
	
	public static void addOrder(Order o) 
	{
		orders.add(o);
	}
	public static void removeOrder() 
	{
		if(!orders.isEmpty()) 
		{
			orders.remove();
		}
		
	}
	public static PriorityQueue<Order> getOrders() 
	{
		return orders;
	}
	
	/**
	 * @param tableNumber is the number of the table being requested 
	 * @return the table that the number is indicating
	 * */
	public static Tables getTable(int tableNumber) 
	{
		Tables desiredTable = null;
		for(Tables t : tablesArray) 
		{
			if(t.getTableNum() == tableNumber) 
			{
				desiredTable = t;
				
			}
		}
		return desiredTable;
	}
	
	public static ArrayList<Tables> getTables() //getter for table array
	{
		return tablesArray;
	}
	
	public static int getNumOfTables() {
		return tablesArray.size();
	}
	/**
	 * @return false if table num is not present in array
	 */
	public static boolean checkTable(int x) 
	{
		for (Tables t : tablesArray)
		{
			if (t.getTableNum() == x)
			{
				return true;
			}
		}
		return false;
	}
	
	public static boolean addTable(Tables table) 
	{
		//check if there is a table with the same table number
		int newTableNumber = table.getTableNum();
		for(Tables t : tablesArray) 
		{
			if (t.getTableNum() == newTableNumber) 
			{
				return false;
			}
		}
		tablesArray.add(table);
		return true;
	}
	
	public static boolean removeTable(Tables table) 
	{
		if(tablesArray.contains(table)) 
		{
			tablesArray.remove(table);
			return true;
		}
		return false;
	}
	/**
	 * @return The menu object that was pre generated
	 */
	public static Menu getMenu() 
	{
		return menu;
	}
	public static void setMenu(Menu m) 
	{
		menu = m;
	}
	
	public static void addToMenu(Item i, int index) 
	{
		menu.addItem(i, index);
	}
	public static boolean removeFromMenu(Item i) 
	{
		return(menu.removeItem(i));
	}
	
	
	public static boolean isEmployee(int employeeNumber) 
	{
		for(Employee e : employeeArray) 
		{
			if (e.getEmployeeNumber() == employeeNumber) 
			{
				return true;
			}
			
		}
		return false;
	}
	public static Employee getEmployee(int employeeNumber) 
	{
		for(Employee e : employeeArray) 
		{
			if(e.getEmployeeNumber() == employeeNumber) 
			{
				return e;
			}
		}
		return null;
	}
	public static void addEmployee(Employee e) 
	{
		employeeArray.add(e);
	}
	public static boolean removeEmployee(int employeeNumber) 
	{
		for(Employee e : employeeArray) 
		{
			if(e.getEmployeeNumber() == employeeNumber) 
			{
				employeeArray.remove(e);
				return true;
			}
			
		}
		return false;
	}
	
	public static boolean checkManagerCode(String code) 
	{
		if(code.equals(managerCode)) 
		{
			return true;
		}
		return false;
	}
	public static boolean isNumber(String text) //moved to manager from restaurant for easier use across files
	{
		try 
		{
			Integer i = Integer.parseInt(text);
			
		}
		//checks if the exception is a nullpointer or not a number
		catch(NumberFormatException | NullPointerException nf) 
		{
			return false;
		}
		return true;
	}
}
