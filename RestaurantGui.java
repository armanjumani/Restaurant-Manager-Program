import javax.swing.*;

import javafx.scene.layout.Border;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class RestaurantGui implements ActionListener
{
	private JFrame frameOfStartup;
	private JPanel panel;
	private JTextField tf;
	
	public RestaurantGui() 
	{
		//Initiates the JFrames
		frameOfStartup = new JFrame("Aaron's Average Restaurant");
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
		if(isNumber(text)) 
		{
			//check if its a table number or employee ID
			if(Integer.parseInt(text) <= 6) 
			{
				frameOfStartup.setVisible(false);
				new CustomerGUI(Integer.parseInt(text));
			}
			else if(UniversalManager.checkEmployee(Integer.parseInt(text))) 
			{
				frameOfStartup.setVisible(false);
				//new EmployeeGUI(Integer.parseInt(text));
			}
			
		}
		else 
		{
			//sets up error message to appear and for people to re enter number
			JFrame pop = new JFrame("ERROR");
			JLabel errorMessage = new JLabel("This is an incorrect value!");
			JPanel popPanel = new JPanel();
			
			pop.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			pop.setSize(350,250);
			popPanel.setBackground(Color.RED);
			
			popPanel.add(errorMessage,BorderLayout.CENTER);
			pop.add(popPanel,BorderLayout.CENTER);
			
			pop.setVisible(true);
		}
	}

	private boolean isNumber(String text) 
	{
		try 
		{
			Integer i = Integer.parseInt(text);
		}
		catch(NumberFormatException | NullPointerException nf) 
		{
			return false;
		}
		return true;
	}
	
	public static void main(String[] args) 
	{
		RestaurantGui test = new RestaurantGui();
	}
	
}
