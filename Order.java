import java.time.LocalTime;

public class Order implements Comparable
{
	private Item it;
	private Tables tableOfOrigin;
	private LocalTime timeOfOrder;
	
	public Order(Item i,Tables t) 
	{
		this.it = i;
		this.tableOfOrigin = t;
		this.timeOfOrder = LocalTime.now();
	}
	
	public Item getItem() 
	{
		return this.it;
	}
	public void setItem(Item it) 
	{
		this.it = it;
	}
	
	public Tables getTable() 
	{
		return this.tableOfOrigin;
	}
	public void setTable(Tables t) 
	{
		this.tableOfOrigin = t;
	}
	
	public LocalTime getTime() 
	{
		return this.timeOfOrder;
	}
	public void setTime(LocalTime time) 
	{
		this.timeOfOrder = time;
	}
	
	public String toString() 
	{
		String result = "" + it.getName() + " : Table " + tableOfOrigin.getTableNum();
		return result;
	}	

	@Override
	public int compareTo(Object o) 
	{
		if (o instanceof Order) 
		{
			return(this.timeOfOrder.compareTo(((Order) o).getTime()));
		}
		return -1;
	}
}
