import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
public class Employee 
{
	private int employeeNumber;
	private LocalTime signIn, signOut, mealIn, mealOut;
	private boolean isSignedIn, isMealOut;
	
	public Employee(int number) 
	{
		this.employeeNumber = number;
		this.isSignedIn = false;
		this.isMealOut = false;
	}
	
	//sets the time the employee started their shift
	public void signIn() 
	{
		if (!isSignedIn) 
		{
			this.signIn = LocalTime.now();
			this.isSignedIn = true;
		}
		
	}
	//sets the time the employee left
	public void signOut() 
	{
		this.signOut = LocalTime.now();
		this.isSignedIn = false;
	}
	
	public void mealOut() 
	{
		if(isMealOut == false) 
		{
			this.mealOut = LocalTime.now();
			this.isMealOut = true;
		}
		
	}
	
	public void mealIn() 
	{
		if(isMealOut == true) 
		{
			this.mealIn = LocalTime.now();
			this.isMealOut = false;
		}
	}
	
	/****************GETTERS AND SETTERS*****************/
	public int getEmployeeNumber() 
	{
		return this.employeeNumber;
	}
	public void setEmployeeNumber(int n) 
	{
		this.employeeNumber = n;
	}
	
	public LocalTime getSignIn() 
	{
		return this.signIn;
	}
	public LocalTime getSignOut() 
	{
		return this.signOut;
	}
	
	public boolean getIsSignedIn() 
	{
		return this.isSignedIn;
	}
	
	public LocalTime getMealIn() 
	{
		return this.mealIn;
	}
	
	public LocalTime getMealOut() 
	{
		return this.mealOut;
	}
	
	
	//toString method
	public String toString() 
	{
		String result = "";
		if(this.signIn != null) 
		{
			result += "Clocked In: " + DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT).format(this.signIn) + "\n";
		}
		else
		{
			result += "Clocked In: N/A" + "\n";
		}
		
		if(this.mealOut != null) 
		{
			result += "Meal Out: " + DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT).format(this.mealOut) + "\n";
		}
		else 
		{
			result += "Meal Out: N/A" + "\n";
		}
		
		if(this.mealIn != null) 
		{
			result += "Meal In: " + DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT).format(this.mealIn) + "\n";
		}
		else 
		{
			result += "Meal In: N/A" + "\n";
		}
		
		if(this.signOut != null) 
		{
			result += "Clocked Out: " + DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT).format(this.signOut) + "\n";
		}
		else 
		{
			result += "Clocked Out: N/A" + "\n";
		}
		return result;
	}
	
}
