import java.io.File;

public class Item {
	
	private String name;
	private String description;
	private double price;
	private String imageFile;
	
	
	public Item(String name, String description, double price, String image) {
		
		this.name = name;
		this.description = description;
		this.price = price;
		this.imageFile = image;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	public String getImage() 
	{
		return this.imageFile;
	}
	public void setImage(String name) 
	{
		this.imageFile = name;
	}
	
	@Override
	public String toString() {
		return (name+"\t$"+price+"\n"+description);
	}
	
}