import java.util.*;

public class Tables {
	private boolean available;
	private int numSeats;
	private boolean ready;
	private int tableNum;

	ArrayList<Item> orders = new ArrayList<>(); //list of the tables orders
	
	public Tables(boolean available, int numSeats, boolean ready, int tableNum) {
		this.available = available;
		this.numSeats = numSeats;
		this.ready = ready;
		this.tableNum = tableNum;
	}
	
	//adds an item to the list of orders for this table
	public void addOrder(Item i) {
		orders.add(i);
	}
	
	//remove an item from the orders list
	public void removeOrder(Item i) {
		for (Item x : orders) {
			if (i == x) {
				orders.remove(x);
			}
		}
	}
	
	//returns the tables total
	public double tallyTotal() {
		double x = 0;
		for (Item i : orders) {
			x += i.getPrice();
		}
		return x;
	}
	
	///////Setters and Getters
	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public int getNumSeats() {
		return numSeats;
	}

	public void setNumSeats(int numSeats) {
		this.numSeats = numSeats;
	}

	public boolean isReady() {
		return ready;
	}

	public void setReady(boolean ready) {
		this.ready = ready;
	}

	public int getTableNum() {
		return tableNum;
	}

	public void setTableNum(int tableNum) {
		this.tableNum = tableNum;
	}
	
	

}
