import javax.swing.*;

import javafx.scene.layout.Border;

import java.awt.*;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.*;
import java.awt.event.ActionEvent;
public class EmployeeGUI 
{
	Employee currentEmployee;
	int index = 0;
	public EmployeeGUI(int number) 
	{
		currentEmployee = UniversalManager.getEmployee(number);
		
		JFrame emp = new JFrame("Employee: " + number); 
		emp.setSize(400, 400);
		emp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel buttonPanel = new JPanel();
		JButton menuEditor = new JButton("Menu Editor");
		JButton orderDisplay = new JButton("Orders");
		JButton tableEditor = new JButton("Tables");
		JButton timeClocks = new JButton("Time Clocks");
		
		if(!currentEmployee.getIsSignedIn()) 
		{
			orderDisplay.setEnabled(false);
			menuEditor.setEnabled(false);
			tableEditor.setEnabled(false);
		}
		
		buttonPanel.setLayout(new GridLayout(2,2));
		buttonPanel.setBackground(Color.GRAY);
		
		menuEditor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) 
			{
				String pin = JOptionPane.showInputDialog(emp,"Enter Manager Pin: ");
				if(UniversalManager.checkManagerCode(pin)) 
				{
					JOptionPane.showMessageDialog(emp, "Managerial Function allowed", "Done",JOptionPane.WARNING_MESSAGE);
					new menuEditorGUI();
				}
				
			}
		});
		orderDisplay.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt) 
			{
				new orderDisplayGUI();
			}
		});
		tableEditor.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt) 
			{
				tableEditorGUI();
			}
		});
		timeClocks.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt) 
			{
				emp.setVisible(false);
				timeClocksGUI();
			}
		});
		
		
		buttonPanel.add(menuEditor);
		buttonPanel.add(orderDisplay);
		buttonPanel.add(tableEditor);
		buttonPanel.add(timeClocks);
		emp.add(buttonPanel);
		emp.setVisible(true);
	}
	
	/************************************Small GUI*******************************************************/
	public void tableEditorGUI() 
	{
		
	}
	private class menuEditorGUI
	{
		//Initialize the components outside to allow scope
				ArrayList<Item> menu = UniversalManager.getMenu().menu();
				JFrame menuFrame = new JFrame("MENU");
				//Listener listener = new Listener();
				JButton previous = new JButton("Previous");
				JButton next = new JButton("Next");
				JButton addItem = new JButton("Add Item");
				JButton removeItem = new JButton("Remove Item");
				JButton close = new JButton("Exit");
				JTextArea txt = new JTextArea();
				public menuEditorGUI() 
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
								new menuEditorGUI();
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
								new menuEditorGUI();
							}
						}
					});
					//TO-DO
					addItem.addActionListener(new ActionListener() 
					{
						public void actionPerformed(ActionEvent e) 
						{
							//Prompt the user to create the menu item
							menuFrame.setVisible(false);
							String name = JOptionPane.showInputDialog("Please type in the name of the item: ");
							String description = JOptionPane.showInputDialog("Please type in the description of the item: ");
							String stringPrice = JOptionPane.showInputDialog("Please type in the price of the item: ");
							Double price = Double.parseDouble(stringPrice);
							String imageLocation = JOptionPane.showInputDialog("Please type in the FULL path name of the image file:");
							Item i = new Item(name,description,price,imageLocation);
							UniversalManager.addToMenu(i, index);
							new menuEditorGUI();
						}
					});
					//TO DO
					removeItem.addActionListener(new ActionListener() 
					{
						public void actionPerformed(ActionEvent e) 
						{
							menuFrame.setVisible(false);
							UniversalManager.removeFromMenu(menu.get(index));
							new menuEditorGUI();
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
					buttonPanel.add(removeItem);
					buttonPanel.add(previous);
					buttonPanel.add(addItem);
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
  }			
	private class orderDisplayGUI
	{
		private PriorityQueue<Order> orders;
		HashMap<Tables,ArrayDeque<Order>> organizedOrders;
		JFrame orderFrame = new JFrame("Orders");
		JTextArea txt = new JTextArea();
		JButton completeOrder = new JButton("Finish First Order");
		JPanel buttons = new JPanel();
		JPanel p = new JPanel();
		JScrollPane p1 = new JScrollPane(txt);
		
		public orderDisplayGUI() 
		{
			orders = UniversalManager.getOrders();
			/*****************GUI Stuff**********************/
			orderFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
			orderFrame.setSize(300,500);
			
			p.setLayout(new BorderLayout());
			buttons.setLayout(new FlowLayout());
			
			completeOrder.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent evt) 
				{
					UniversalManager.removeOrder();
					orderFrame.setVisible(false);
					new orderDisplayGUI();
				}
			});
			
			//get the orders and display them
			String text = "";
			Iterator<Order> orderIT = orders.iterator();
			while(orderIT.hasNext()) 
			{
				Order o = orderIT.next();
				text += o.toString() + "\n";
			}
			txt.setText(text);
			txt.setEditable(false);
			txt.setFont(new Font("Times New Roman",Font.PLAIN,16));
			txt.setLineWrap(true);
			txt.setWrapStyleWord(true);
			
			p1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			p1.setPreferredSize(new Dimension(300,400));
			
			buttons.add(completeOrder);
			
			p.add(p1,BorderLayout.CENTER);
			p.add(buttons,BorderLayout.SOUTH);
			
			orderFrame.add(p);
			orderFrame.setVisible(true);
			
		}
		
	}
	public void timeClocksGUI() 
	{
		JFrame frame = new JFrame();
		JPanel buttonPanel = new JPanel();
		JButton clockIn = new JButton("Clock In");
		JButton clockOut = new JButton("Clock Out");
		JButton meal = new JButton("Meal Out");
		JButton checkTimes = new JButton("Check Times");
		
		frame.setSize(400,400);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		clockIn.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent evt)
			{
				if(!currentEmployee.getIsSignedIn()) 
				{
					currentEmployee.signIn();
					frame.dispose();
					new EmployeeGUI(currentEmployee.getEmployeeNumber());
				}
			}
		});
		clockOut.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent evt) 
			{
				if(currentEmployee.getIsSignedIn()) 
				{
					int n = JOptionPane.showConfirmDialog(frame, "Would you like to sign out for the day?", "Confirmation Message", JOptionPane.YES_NO_OPTION);
					if(n == 0) 
					{
						currentEmployee.signOut();
						JOptionPane.showMessageDialog(frame, "Confirmed you have signed out at:  " + currentEmployee.getSignOut().toString());
						//returns the data back to the OS and terminates cleanly
						frame.dispose();
						new EmployeeGUI(currentEmployee.getEmployeeNumber());
					}
				}
			}
		});
		//@TO DO
		meal.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent evt) 
			{
				if(meal.getText().equals("Meal In")) 
				{
					currentEmployee.mealIn();
					meal.setText("Meal Out");
				}
				else if(meal.getText().equals("Meal Out")) 
				{
					currentEmployee.mealOut();
					meal.setText("Meal In");
				}
			}
		});
		checkTimes.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent evt) 
			{
				timeGUI();
			}
		});
		buttonPanel.setLayout(new GridLayout(2,2));
		buttonPanel.add(clockIn);
		buttonPanel.add(meal);
		buttonPanel.add(checkTimes);
		buttonPanel.add(clockOut);
		
		frame.add(buttonPanel);
		frame.setVisible(true);
	}
	
	public void timeGUI() 
	{
		DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT);
		JFrame timeFrame = new JFrame();
		JTextArea txt = new JTextArea();
		JScrollPane sPanel = new JScrollPane(txt);
		
		//set up the text for the area
		String text = currentEmployee.toString();
		txt.setText(text);
		txt.setEditable(false);
		txt.setFont(new Font("Times New Roman",Font.PLAIN,16));
		txt.setLineWrap(true);
		txt.setWrapStyleWord(true);
		
		sPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		sPanel.setPreferredSize(new Dimension(300,400));
		
		timeFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		timeFrame.setSize(300,400);
		timeFrame.add(sPanel);
		timeFrame.setVisible(true);
	}
}

