import java.util.*;

public class Menu {
	ArrayList<Item> menu = new ArrayList<>(); //list of menu items
	
	//prints the tostring of each item in the menu
	public String printMenu() {
		for (Item i : menu) {
			return i.toString();
		}
		return "";
	}
	
	//creates and adds a new item to the menu
	public void addItem( String name, String description, double price) {
		Item a = new Item(name, description, price);
		menu.add(a);
	}
	
	//removes an item from the menu
	public void removeItem(Item i) {
		for (Item i2 : menu) {
			if (i == i2) {
				menu.remove(i2);
			}
		}
	}
	
	//adds the item to the tables order list
	public void orderItem(Item i, Tables t) {
		t.addOrder(i);
	}
}
