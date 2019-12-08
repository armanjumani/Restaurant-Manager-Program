import javax.swing.*;

import javafx.scene.layout.Border;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
public class test
{
	public static void main(String[] args) 
	{
		ArrayList<Tables> initialTables = new ArrayList<Tables>();
		ArrayList<Employee> initialEmployees = new ArrayList<Employee>();
		//Initialize 6 tables with 6 seats
		for(int x = 1; x <= 6; x++) 
		{
			initialTables.add(new Tables(true,6,false,x));
		}
		for(int x = 0; x < 3; x++) 
		{
			initialEmployees.add(new Employee(147+x));
		}
		UniversalManager.init(initialTables,initialEmployees);
		RestaurantGui test = new RestaurantGui();		
	}
}
