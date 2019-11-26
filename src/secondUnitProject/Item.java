package secondUnitProject;

public class Item {
	String itemName;
	double itemPrice;
	boolean isShared;
	
	public Item(String itemName, double preco) {
		this.itemName = itemName;
		this.itemPrice = preco;
	}
	public Item(String itemName, int preco, boolean isShared) {
		this.itemName = itemName;
		this.itemPrice = preco;
		this.isShared = isShared;
				
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public double getItemPrice() {
		return itemPrice;
	}
	public void setItemPrice(double price) {
		this.itemPrice = price;
	}
	public boolean isShared() {
		return isShared;
	}
	public void setShared(boolean isShared) {
		this.isShared = isShared;
	}	
}
