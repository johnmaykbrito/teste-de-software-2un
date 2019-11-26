package secondUnitProject;

import java.util.ArrayList;

public class Comanda {
	
	User gerente;
	ArrayList<User> gerenciados;
	ArrayList<Item> items;
	
	public User getGerente() {
		return gerente;
	}
	
	public Comanda() {
		this.gerenciados = new ArrayList<User>();
		this.items = new ArrayList<Item>();
	}
	
	
	public void setGerente(User gerente) {
		this.gerente = gerente;
	}
	public ArrayList<User> getGerenciados() {
		return gerenciados;
	}
	public void setGerenciados(ArrayList<User> gerenciados) {
		this.gerenciados = gerenciados;
	}

	public Item addItem(String itemName, double preco) {
		Item item = new Item(itemName, preco);
		items.add(item);
		return item;
	}

	public ArrayList<Item> getItems() {
		return this.items;
	}

	public Item addItem(String itemName, int preco, boolean isShared) {
		Item item = new Item(itemName, preco, isShared);
		items.add(item);
		return item;
	}
	
	

}
