import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class managerEditorGUI
{	
		//init components
		private JFrame frame;
		private JPanel panel;
		private JButton close, remove, seats, add;
		
		public managerEditorGUI()
		{
			//init frame
			frame = new JFrame("Restaurant Manager");
			frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
			frame.setSize(400,400);
			panel = new JPanel();
			panel.setLayout(new GridLayout(2,2));
			panel.setBackground(Color.GRAY);
			close = new JButton("Exit");
			remove = new JButton("Remove Employee");
			seats = new JButton("Edit Number of Seats");
			add = new JButton("Add Employee");
			
			//closes window
			close.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					frame.dispose();
				}
			});
			//reomves employee
			remove.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					String pin = JOptionPane.showInputDialog(frame, "Enter Employee Pin: ");
					if (UniversalManager.isNumber(pin))
					{
						if (UniversalManager.isEmployee(Integer.parseInt(pin)))
						{
							UniversalManager.removeEmployee(Integer.parseInt(pin));
							JOptionPane.showMessageDialog(frame, "Successfully removed empolyee!", "Success",JOptionPane.WARNING_MESSAGE);
						}
						else
						{
							JOptionPane.showMessageDialog(frame, "Not a valid Employee Pin!", "Error",JOptionPane.ERROR_MESSAGE);
						}
					}
					else
					{
						JOptionPane.showMessageDialog(frame, "Not a valid pin!", "Error",JOptionPane.ERROR_MESSAGE);						
					}
				}
			});
			//changes seating for desired table
			seats.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					String num = JOptionPane.showInputDialog(frame, "Enter Table Number: ");
					if (UniversalManager.isNumber(num))
					{
						if (UniversalManager.checkTable(Integer.parseInt(num)))
						{
							String seats = JOptionPane.showInputDialog(frame, "Enter Number of Seats: ");
							if (UniversalManager.isNumber(seats))
							{
								UniversalManager.getTable(Integer.parseInt(num)).setNumSeats(Integer.parseInt(seats));;
								JOptionPane.showMessageDialog(frame, "Successfully changed seating!", "Success",JOptionPane.WARNING_MESSAGE);
							}
							else
							{
								JOptionPane.showMessageDialog(frame, "Not a valid number!", "Error",JOptionPane.ERROR_MESSAGE);								
							}
						}
						else
						{
							JOptionPane.showMessageDialog(frame, "Not a valid table number!", "Error",JOptionPane.ERROR_MESSAGE);			
						}
					}
					else
					{
						JOptionPane.showMessageDialog(frame, "Not a valid table number!", "Error",JOptionPane.ERROR_MESSAGE);
					}
				}
			});
			//add employee
			add.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					String pin = JOptionPane.showInputDialog(frame, "Enter New Employee Pin: ");
					if (UniversalManager.isNumber(pin) && Integer.parseInt(pin) >= 100)
					{
						if (UniversalManager.isEmployee(Integer.parseInt(pin)))
						{
							JOptionPane.showMessageDialog(frame, "Already active employee!", "Error",JOptionPane.ERROR_MESSAGE);
							
						}
						else
						{
							Employee emp = new Employee(Integer.parseInt(pin));
							UniversalManager.addEmployee(emp);
							JOptionPane.showMessageDialog(frame, "Successfully added employee!", "Success",JOptionPane.WARNING_MESSAGE);
						}
					}
					else
					{
						JOptionPane.showMessageDialog(frame, "Not a valid pin!", "Error",JOptionPane.ERROR_MESSAGE);
					}
					
				}
			});
			panel.add(add);
			panel.add(remove);
			panel.add(seats);
			panel.add(close);
			frame.add(panel);
			frame.setVisible(true);
			
	}
}
