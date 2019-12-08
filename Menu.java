import java.io.File;
import java.util.*;

public class Menu {
	ArrayList<Item> menu = new ArrayList<>(); //list of menu items
	
	public Menu() 
	{
		//initialize the menu
		Item pizza = new Item("Pizza","A cheesy delight that comes with different toppings at no extra costs!" + 
		"\nToppings:Peppperoni,Pineapple,Pepper, Olives, and Anchovies",15.99,"Images/pizza.jpg");
		
		Item burger = new Item("Burger","A juicy beef patty on top of fresh lettuce and tomatoes and melted cheese to make your mouth water",10.99,"Images/burger.jpg");
		
		Item eggroll = new Item("Egg Roll","A crispy fried dish filled with chicken and vegetables to give your appetite a quick boost",3.99,"Images/eggroll.jpg");
		
		Item soup = new Item("Chicken Corn Soup","A soup filled with the flavor of chicken and a good way to start a meal. ",4.99,"Images/soup.jpg");
		
		Item soda = new Item("Soda","A delicious fizzy drink to quench your thirst."
				+ "\nAvailable flavors: Coca Cola, Orange, Sprite, Root Beer, and Grape ",3.99,"Images/soda.jpg");
		
		Item shake = new Item("Milk Shake","A cold beverage made of ice cream and milk" + 
				"\nAvailable flavors: Vanilla, Chocolate, Strawberry, and Cookies and Cream",5.99,"Images/shake.jpg");
		//add all the items into the menu
		menu.add(eggroll);
		menu.add(soup);
		menu.add(pizza);
		menu.add(burger);
		menu.add(soda);
		menu.add(shake);
		
	}
	//prints the tostring of each item in the menu
	public String printMenu() {
		String result = "";
		for (Item i : menu) {
			result += i.toString() + " ";
		}
		return result;
	}
	
	//creates and adds a new item to the menu
	public void addItem( String name, String description, double price, String file) {
		Item a = new Item(name, description, price,file);
		menu.add(a);
	}
	public void addItem(Item i, int index) 
	{
		menu.add(index, i);
	}
	
	//removes an item from the menu
	public boolean removeItem(Item i) 
	{
		for (Item i2 : menu) {
			if (i == i2) {
				menu.remove(i2);
				return true;
			}
		}
		return false;
	}
	
	//adds the item to the tables order list
	public void orderItem(Item i, Tables t) {
		t.addOrder(i);
	}
	
	public ArrayList<Item> menu()
	{
		return this.menu;
	}
}