import java.util.*;
import java.text.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class CustomerGUI
{
	//Initialize the components
	private JFrame frame;
	private int tableNumber;
	private String employeePin = "AaronsGreat";
	private JPanel panel;
	private JButton checkOut,orders,menu, currentTotal;
	private Tables table;
	//index used for Menu GUI
	private int index = 0; 
	//Decimal format of money to prevent run off decimals
	DecimalFormat money = new DecimalFormat("#,###,##0.00");
	//constructor that initializes the frame
	public CustomerGUI(int number) 
	{
		//set the values of the table 
		tableNumber = number;
		table = UniversalManager.getTable(tableNumber);
		
		//create the frame components
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
			//pulls up the menuGUI
			public void actionPerformed(ActionEvent e)
			{
				new menuGUI();
			}
		});
		orders.addActionListener(new ActionListener() 
		{
			//pulls up the ordersGUI to display the orders that people have
			public void actionPerformed(ActionEvent e)
			{
				ordersGUI();
			}
		});
		currentTotal.addActionListener(new ActionListener() 
		{
			//pulls up the totalGUI and shows the total amount that the customer owes
			public void actionPerformed(ActionEvent e)
			{
				totalGUI();
			}
		});
		checkOut.addActionListener(new ActionListener() 
		{
			//pulls up the outGUI and displays confirmation message
			public void actionPerformed(ActionEvent e)
			{
				outGUI();
			}
		});
		
		//add all the buttons the pane and the pane to the frame
		panel.add(menu);
		panel.add(orders);
		panel.add(currentTotal);
		panel.add(checkOut);
		//add the panel to the frame
		frame.add(panel);
		//makes frame visible
		frame.setVisible(true);
	}
/***************************CREATING THE SMALL GUIs*************************************************************************************************/

/*****************************************************MENU GUI*******************************************************************************/

	private class menuGUI
	{
		//Initialize the components outside to allow scope
		ArrayList<Item> menu = UniversalManager.getMenu().menu();
		JFrame menuFrame = new JFrame("MENU");
		//Listener listener = new Listener();
		JButton previous = new JButton("Previous");
		JButton next = new JButton("Next");
		JButton order = new JButton("Order");
		JButton remove = new JButton("Remove");
		JButton close = new JButton("Exit");
		JTextArea txt = new JTextArea();
		public menuGUI() 
		{
			//set up the menu frame
			JFrame menuFrame = new JFrame("MENU");
			menuFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
			menuFrame.setSize(500,500);
			//set up the panel that will contain the components
			JPanel panel = new JPanel();
			panel.setLayout(new BorderLayout());
			panel.setBackground(Color.GRAY);
			//set up the panel that will contain the buttons
			JPanel buttonPanel = new JPanel();
			buttonPanel.setLayout(new FlowLayout());
			buttonPanel.setBackground(Color.GRAY);
			//set up the title panel
			JPanel title = new JPanel();
			title.setLayout(new BorderLayout());
			title.setBackground(Color.GRAY);
			//set up the center panel that contains the image and text
			JPanel info = new JPanel();
			info.setLayout(new BorderLayout());
			info.setBackground(Color.GRAY);
			//set up the text field that will hold the information about the menu item
			String displayedText = menu.get(index).getDescription() + "\nPrice: $ " + menu.get(index).getPrice();
			txt.setText(displayedText);
			txt.setLineWrap(true);
			txt.setWrapStyleWord(true);
			txt.setEditable(false);
			//add the listeners to the buttons
			previous.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent evt) 
				{
					//only goes back if it can go back
					if(index - 1 >= 0)
					{
						//trick the user into thinking they flipped a page when really they just made a new gui
						menuFrame.setVisible(false);
						index--;
						new menuGUI();
					}
				}
			});
			next.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent evt) 
				{
					//only goes forward if it can go forward
					if(index+1 < menu.size()) 
					{
						menuFrame.setVisible(false);
						index++;
						new menuGUI();
					}
				}
			});
			order.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent e) 
				{
					//adds the order to the table array and displays a confirmation message
					table.addOrder(menu.get(index));
					UniversalManager.addOrder(new Order(menu.get(index),table));
					JOptionPane.showMessageDialog(menuFrame,"Order has been placed!", "Enjoy your meal!", JOptionPane.PLAIN_MESSAGE);
				}
			});
			remove.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent e) 
				{
					//checks the pin to ensure that the customer cannot change their order without the employee knowing
					String pin = JOptionPane.showInputDialog(menuFrame,"Enter Employee Pin: ");
					if(pin.equals(employeePin)) 
					{
						table.removeOrder(menu.get(index));
						JOptionPane.showMessageDialog(menuFrame, "Order has been removed successfully.", "Done",JOptionPane.WARNING_MESSAGE);
					}
					
				}
			});
			close.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent e) 
				{
					menuFrame.setVisible(false);
					
				}
			});
			
			//add buttons to the panel
			buttonPanel.add(remove);
			buttonPanel.add(previous);
			buttonPanel.add(order);
			buttonPanel.add(next);
			buttonPanel.add(close);
			//create necessary components
			ImageIcon icon = new ImageIcon(menu.get(index).getImage());
			JLabel name = new JLabel(menu.get(index).getName(),SwingConstants.CENTER);
			name.setFont(new Font("Seriff", Font.PLAIN,32));
			title.add(name,BorderLayout.CENTER);
			//add the information to the info panel
			info.add(new JLabel(icon), BorderLayout.CENTER);
			info.add(txt,BorderLayout.SOUTH);
			//add all three panels to the main panel
			panel.add(title,BorderLayout.NORTH);
			panel.add(info,BorderLayout.CENTER);
			panel.add(buttonPanel,BorderLayout.SOUTH);
			//set the frame visible
			menuFrame.add(panel);
			menuFrame.setVisible(true);
		}
	}//end of the menuGUI
	
/*****************************************************CheckOut GUI*******************************************************************************/	
	//Displays a message and disables all other functions for the user to use
	public void outGUI() 
	{
		//if the user clicks yes then n = 0 else n = 1 to ensure that customers want to leave and not misclick
		int n = JOptionPane.showConfirmDialog(frame, "Would you really like to leave?", "Confirmation Message", JOptionPane.YES_NO_OPTION);
		if(n == 0) 
		{
			JOptionPane.showMessageDialog(frame, "We hope you enjoyed eating at Aaron's Average Restaurant!" +
					"\n A server has been notified and will be with you shortly!", "See You Later!", JOptionPane.PLAIN_MESSAGE);
			//returns the data back to the OS and terminates cleanly
			frame.dispose();
		}
	}
/*****************************************************TOTAL GUI*******************************************************************************/
	public void totalGUI() 
	{
		/***************************GETTING THE TOTAL NUMBER AND FORMATTING******************************************************/
		//organize the orders on the table with the quantity of the order
		HashMap<Item, Integer> orderQuantity = new HashMap<Item,Integer>();
		for (Item i : table.orders) 
		{
			if (orderQuantity.containsKey(i)) 
			{
				int oldQ = orderQuantity.get(i);
				orderQuantity.put(i,oldQ+1);
			}
			else 
			{
				orderQuantity.put(i, 1);
			}
		}
		String result = "";
		Double totalCost = 0.0;
		Iterator<Item> it = orderQuantity.keySet().iterator();
		while(it.hasNext()) 
		{
			Item i = it.next();
			result += i.getName() + ": $" + (i.getPrice() * orderQuantity.get(i));
			totalCost += i.getPrice() * orderQuantity.get(i);
			result += "\n";
		}
		totalCost = Double.parseDouble(money.format(totalCost));
		result += "SubTotal: $" + totalCost + "\n";
		Double tax = Double.parseDouble(money.format(0.056 * totalCost));
		result += "Tax: $" + tax + "\n" + "Total Amount: $" + money.format(totalCost + tax);
		/*******************************CREATING THE DISPLAY*****************************************************/
		JFrame totalFrame = new JFrame("Total");
		totalFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		totalFrame.setSize(300,400);
		JTextArea txt = new JTextArea(result);
		JScrollPane p = new JScrollPane(txt);
		txt.setEditable(false);
		txt.setFont(new Font("Times New Roman",Font.PLAIN,16));
		txt.setLineWrap(true);
		txt.setWrapStyleWord(true);
		
		p.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		p.setPreferredSize(new Dimension(300,400));
		
		totalFrame.add(p);
		totalFrame.setVisible(true);
	}
	
/*****************************************************Orders GUI*******************************************************************************/	
	public void ordersGUI() 
	{
		//map of the items that are in the table: Key = Item Value = Quantity
		HashMap<Item, Integer> orderQuantity = new HashMap<Item,Integer>();
		for (Item i : table.orders) 
		{
			if (orderQuantity.containsKey(i)) 
			{
				int oldQ = orderQuantity.get(i);
				orderQuantity.put(i,oldQ+1);
			}
			else 
			{
				orderQuantity.put(i, 1);
			}
		}
		String result = "";
		Iterator<Item> it = orderQuantity.keySet().iterator();
		//iterate through and concatenate to the result
		while(it.hasNext()) 
		{
			Item i = it.next();
			result += i.getName() + " x" + orderQuantity.get(i);
			result += "\n";
		}
		//create the GUI frame
		JFrame orderFrame = new JFrame("Orders");
		orderFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		orderFrame.setSize(300,400);
		//create the Text Area and Scroll Pane 
		JTextArea txt = new JTextArea(result);
		JScrollPane p = new JScrollPane(txt);
		txt.setEditable(false);
		txt.setFont(new Font("Times New Roman",Font.PLAIN,16));
		txt.setLineWrap(true);
		txt.setWrapStyleWord(true);
		//set the scroll bar to always be available and function when necessary
		p.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		p.setPreferredSize(new Dimension(300,400));
		//make the frame visible
		orderFrame.add(p);
		orderFrame.setVisible(true);
	}

}