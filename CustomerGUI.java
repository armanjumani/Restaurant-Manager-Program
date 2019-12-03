import java.util.*;
import java.text.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class CustomerGUI
{
	private JFrame frame;
	private int tableNumber;
	private String employeePin = "AaronsGreat";
	private JPanel panel;
	private JButton checkOut,orders,menu, currentTotal;
	private Tables table;
	
	private int index = 0; 
	
	DecimalFormat money = new DecimalFormat("#,###,##0.00");
	
	public CustomerGUI(int number) 
	{
		tableNumber = number;
		table = UniversalManager.getTable(tableNumber);
		
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
			//pulls up the totalGUI and sets the current GUI invisible
			public void actionPerformed(ActionEvent e)
			{
				totalGUI();
			}
		});
		checkOut.addActionListener(new ActionListener() 
		{
			//pulls up the outGUI and sets the current GUI invisible
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
		
		frame.add(panel);
		//makes frame visible
		frame.setVisible(true);
	}
/***************************CREATING THE SMALL GUIs*************************************************************************************************/
	private class menuGUI
	{
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
			title.setBackground(Color.GRAY);
			//set up the center panel that contains the image and text
			JPanel info = new JPanel();
			info.setLayout(new BorderLayout());
			info.setBackground(Color.GRAY);
			//set up the text field that will hold the information about the menu item
			String displayedText = menu.get(index).getDescription() + "\n$ " + menu.get(index).getPrice();
			txt.setText(displayedText);
			txt.setLineWrap(true);
			txt.setWrapStyleWord(true);
			txt.setEditable(false);
			//add the listeners to the buttons
			previous.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent evt) 
				{
					if(index - 1 >= 0)
					{
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
					table.addOrder(menu.get(index));
					JOptionPane.showMessageDialog(menuFrame,"Order has been placed!", "Enjoy your meal!", JOptionPane.PLAIN_MESSAGE);
				}
			});
			remove.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent e) 
				{
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
			JLabel name = new JLabel(menu.get(index).getName());
			name.setFont(new Font("Times New Roman", 24, Font.PLAIN));
			
			info.add(new JLabel(icon), BorderLayout.CENTER);
			info.add(txt,BorderLayout.SOUTH);
			
			panel.add(title,BorderLayout.NORTH);
			panel.add(info,BorderLayout.CENTER);
			panel.add(buttonPanel,BorderLayout.SOUTH);
			
			menuFrame.add(panel);
			menuFrame.setVisible(true);
		}
		/*
		//class within a class
		private class Listener implements ActionListener 
		{
			public void actionPerformed(ActionEvent evt) 
			{
				
				if(evt.getSource() == previous) 
				{
					if(index > 0) 
					{
						setInvisible();
						index--;
						new menuGUI();
					}
					else if (index == 0) 
					{
						setInvisible();
					}
					
				}
				else if(evt.getSource() == next) 
				{
					if(index < menu.size()) 
					{
						setInvisible();
						index++;
						//new menuGUI();
					}
				}
				else if(evt.getSource() == close) 
				{
					setInvisible();
				}
			}
		}*/
	}
	
	//Displays a message and disables all other functions for the user to use
	public void outGUI() 
	{
		int n = JOptionPane.showConfirmDialog(frame, "Would you really like to leave?", "Confirmation Message", JOptionPane.YES_NO_OPTION);
		if(n == 0) 
		{
			JOptionPane.showMessageDialog(frame, "We hope you enjoyed eating at Aaron's Average Restaurant!" +
					"\n A server has been notified and will be with you shortly!", "See You Later!", JOptionPane.PLAIN_MESSAGE);
			frame.dispose();
		}
	}
	
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
		result += "Tax: $" + tax + "\n" + "Total Amount: $" + (totalCost + tax);
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
	
	public void ordersGUI() 
	{
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
		while(it.hasNext()) 
		{
			Item i = it.next();
			result += i.getName() + " x" + orderQuantity.get(i);
			result += "\n";
		}
		
		JFrame orderFrame = new JFrame("Orders");
		orderFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		orderFrame.setSize(300,400);
		JTextArea txt = new JTextArea(result);
		JScrollPane p = new JScrollPane(txt);
		txt.setEditable(false);
		txt.setFont(new Font("Times New Roman",Font.PLAIN,16));
		txt.setLineWrap(true);
		txt.setWrapStyleWord(true);
		
		p.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		p.setPreferredSize(new Dimension(300,400));
		
		orderFrame.add(p);
		orderFrame.setVisible(true);
	}

}