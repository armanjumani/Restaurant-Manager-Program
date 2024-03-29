import javax.swing.*;

import javafx.scene.layout.Border;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class RestaurantGui implements ActionListener
{
	private JFrame frameOfStartup;
	private JPanel panel;
	private JTextField tf;
	public RestaurantGui() 
	{
		
		//Initiates the JFrames
		frameOfStartup = new JFrame("Aaron's Restaurant");
		frameOfStartup.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameOfStartup.setSize(300,200);
		
		//initialize the panel and label message for the person
		panel = new JPanel();
		JLabel instructions = new JLabel();
		instructions.setText("Please input the table number you are seated at!");
		
		//sets the textfield length to allow up to 6 characters
		tf = new JTextField(6);
		tf.addActionListener(this);
		tf.setSize(150, 350);
		//add components to the pane and the pane to the frame
		panel.add(instructions,BorderLayout.CENTER);		
		panel.add(tf,BorderLayout.CENTER);
		
		frameOfStartup.add(panel);

		frameOfStartup.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) 
	{
		String text = tf.getText();
		//check if its a logical number
		if(UniversalManager.isNumber(text)) 
		{
			int x = Integer.parseInt(text);
			//checks if within or range of tables
			if (x > 0 && x <= UniversalManager.getNumOfTables()) 
			{	
				if (UniversalManager.checkTable(x))
				{
					//if table is available
					if (UniversalManager.getTable(x).isAvailable()) 
					{
						//frameOfStartup.setVisible(false);
						//sets new table to false
						UniversalManager.getTable(x).setAvailable(false);
						new CustomerGUI(x);
					}
					//displays error if not
					else 
					{
						JOptionPane.showMessageDialog(frameOfStartup, "That is not an available table! \nPlease Try Again!", "ERROR", JOptionPane.ERROR_MESSAGE);
						
					}
				}
				else 
				{
					JOptionPane.showMessageDialog(frameOfStartup, "This is not a valid table number! \nPlease Try Again!", "ERROR", JOptionPane.ERROR_MESSAGE);
					
				}
				
			}
			//checks for employee
			else if(UniversalManager.isEmployee(x))
			{
				//frameOfStartup.setVisible(false);
				new EmployeeGUI(x);
			}
			//checks for manager
			else if (UniversalManager.checkManagerCode(text))
			{
				new managerEditorGUI();
			}
			else 
			{
				JOptionPane.showMessageDialog(frameOfStartup, "This is not a valid table number! \nPlease Try Again!", "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		}
		else 
		{
			JOptionPane.showMessageDialog(frameOfStartup, "This is not a valid table number! \nPlease Try Again!", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
	}

}
