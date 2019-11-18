import java.util.HashMap;

public class UniversalManager 
{
	private Tables[] tablesArray = new Tables[6];
	private HashMap<Integer,Employee> eMap = new HashMap<Integer, Employee>();
	
	public static boolean checkEmployee(int employeeNumber) 
	{
		if(eMap.containsKey(employeeNumber)) 
		{
			return true;
		}
		return false;
	}
}
