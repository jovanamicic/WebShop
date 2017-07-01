package drools.spring.example.dto;

public class ItemDTO {
	
	private String id;
	private int quantity;
	private String name;
	private double price;
	private double total;
	
	public ItemDTO(){}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "ItemDTO [id=" + id + ", quantity=" + quantity + ", name=" + name + ", price=" + price + ", total="
				+ total + "]";
	}
	
}
