package secondUnitProject;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.Set;

public class User {
	String nome;
	String senha;
	String email;
	String picPath;
	ArrayList<User> amigos;
	Date dataNascimento;
	Boolean logado;
	ArrayList<Evento> eventos;
	ArrayList<Evento> ObserverEvents;
	Comanda comanda;
	
	
	public User() {	
	}


	public User(String nome, String senha, String email, Date dataNascimento) {
		super();
		this.nome = nome;
		this.senha = senha;
		this.email = email;
		this.dataNascimento = dataNascimento;
		this.amigos = new ArrayList<User>();
		this.eventos = new ArrayList<Evento>();
		
	}
	
	public void addEvent(Evento e) {
		eventos.add(e);
	}
	
	public void addEvents(ArrayList<Evento> eventos) {
		this.eventos.addAll(eventos);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}


	public String getPicPath() {
		return picPath;
	}


	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}


	public ArrayList<User> getAmigos() {
		return amigos;
	}


	public void setAmigos(ArrayList<User> amigos) {
		this.amigos = amigos;
	}


	public void addAmigo(User amigo) {
		if (amigo.getNome() != this.getNome() && amigo.getEmail() != this.getEmail()) {
			this.amigos.add(amigo);
		}		
	}


	public Boolean getLogado() {
		return logado;
	}


	public void setLogado(Boolean logado) {
		this.logado = logado;
	}


	public ArrayList<Evento> getEventos() {
		return eventos;
	}


	public void setEventos(ArrayList<Evento> eventos) {
		this.eventos = eventos;
	}


	public void update(Evento e) {
		// System.out.println("User.update();");
		// System.out.println("Convidado: " + this.getNome());
	}


	public void aceitarConvite(Evento e) {
		e.aceitarConvite(this);
	}


	public void naoAceitarConvite(Evento e) {
		e.naoAceitarConvite(this);
	}


	public Comanda getComanda() {
		return this.comanda;
	}


	public User incluirComanda() {
		this.comanda = new Comanda();
		comanda.gerente = this;
		return this;
	}


	public Item addItemNaComanda(String item, double preco) {
		return comanda.addItem(item, preco);
	}


	public Item addItemNaComanda(String item, int preco, boolean isShared) {
		return comanda.addItem(item, preco, isShared);	
	}


	public void addItemNaComanda(String itemName, double price, Map<User, Double> user_value, boolean isShared) {
		if (!isShared) {
			this.addItemNaComanda(itemName, price).setShared(false);
			return;
		} else if (isShared) {
			Set<User> usuarios = user_value.keySet();
			for (User u : usuarios) {
				if (u.getComanda() != null) {
					u.getComanda().addItem(itemName, price*user_value.get(u)).setShared(true);
					
				} else if (u.getComanda() == null) {
					u.incluirComanda().getComanda().addItem(itemName, price*user_value.get(u)).setShared(true);
				}
			}
			return;
		}
	}
}
