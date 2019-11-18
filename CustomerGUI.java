import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class CustomerGUI
{
	private JFrame frame;
	private int tableNumber;
	private JPanel panel;
	private JButton checkOut,orders,menu, currentTotal;
	
	public CustomerGUI(int number) 
	{
		tableNumber = number;
		
		frame = new JFrame("Table " + tableNumber);
		panel = new JPanel();
		checkOut = new JButton("Check Out");
		orders = new JButton("Current Orders");
		menu = new JButton("Menu");
		currentTotal = new JButton("Current Total");
		
		//sets up the frame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400,400);
		
		//set up panel layout and background
		panel.setLayout(new GridLayout(2,2));
		panel.setBackground(Color.BLUE);
		
		//set up buttons with actionlisteners
		menu.addActionListener(new ActionListener() 
		{
			//pulls up the menuGUI and sets the current GUI invisible
			public void actionPerformed(ActionEvent e)
			{
				frame.setVisible(false);
				//menuGUI();
			}
		});
		orders.addActionListener(new ActionListener() 
		{
			//pulls up the ordersGUI to display the orders that people have
			public void actionPerformed(ActionEvent e)
			{
				frame.setVisible(false);
				//ordersGUI();
			}
		});
		currentTotal.addActionListener(new ActionListener() 
		{
			//pulls up the totalGUI and sets the current GUI invisible
			public void actionPerformed(ActionEvent e)
			{
				frame.setVisible(false);
				//totalGUI();
			}
		});
		checkOut.addActionListener(new ActionListener() 
		{
			//pulls up the outGUI and sets the current GUI invisible
			public void actionPerformed(ActionEvent e)
			{
				frame.setVisible(false);
				//outGUI();
			}
		});
		
		//add all the buttons the pane and the pane to the frame
		panel.add(menu);
		panel.add(orders);
		panel.add(currentTotal);
		panel.add(checkOut);
		
		frame.add(panel);
		//makes frame visible
		frame.setVisible(true);
	}

}
