package secondUnitProject;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

public class Evento {
	
	private int data, mes;
	private String hora;
	private ArrayList<User> convidadosNaoConfirmados;
	private ArrayList<User> convidadosConfirmados;
	private Date dataDoEventoImediato;
	private boolean isImediato;
	ArrayList<Avaliacao> avaliacoes;
	boolean isFinished = false;
	ErroConvidados erro;
	User owner;
	ArrayList<User> pendentes;
	ArrayList<User> observers;
	ArrayList<User> observerbleUsers;

	public Evento abrirEvento() {
		return new Evento();
	}

	public Evento agendarEvento(int data, int mes, String hora, User owner) {
		
		if (!isDataValida(data, mes)) return null;
		if (isDateInThePast(data, mes)) return null;
		
		this.data = data;
		this.mes = mes;
		this.hora = hora;
		this.setImediato(false);
		this.avaliacoes = new ArrayList<Avaliacao>();
		this.erro = new ErroConvidados();
		this.owner = owner;
		this.pendentes = new ArrayList<User>();
		this.observers = new ArrayList<User>();
		this.convidadosConfirmados = new ArrayList<User>();
		return this;
	}
	
	public void addForNotify(User u) {
		// add to list of events to be notified
		this.observers.add(u);
	}
	
	public void remForNotify(User u) {
		// rem to list of events to be notified
		this.observers.remove(u);
	}
	
	public void myNotify() {
		// notify
		for (User observer : observers) {
			observer.update(this);
		}
	}

	private boolean isDataValida(int data, int mes) {
		
		if (mes < 1 || mes > 12) return false;
		
		if (mes == 1 && (data < 1 || data > 31)) {
			return false;
		} else if (mes == 2 && (data < 1 || data > 28)) {
			return false;
		} else if (mes == 3 && (data < 1 || data > 31)) {
			return false;
		} else if (mes == 4 && (data < 1 || data > 30)) {
			return false;
		} else if (mes == 5 && (data < 1 || data > 31)) {
			return false;
		} else if (mes == 6 && (data < 1 || data > 30)) {
			return false;
		} else if (mes == 7 && (data < 1 || data > 31)) {
			return false;
		} else if (mes == 8 && (data < 1 || data > 31)) {
			return false;
		} else if (mes == 9 && (data < 1 || data > 30)) {
			return false;
		} else if (mes == 10 && (data < 1 || data > 31)) {
			return false;
		} else if (mes == 11 && (data < 1 || data > 30)) {
			return false;
		} else if (mes == 12 && (data < 1 || data > 31)) {
			return false;
		}
		return true;
	}

	private boolean isDateInThePast(int data, int mes) {
		
		Date date = new Date();
		LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		int month = localDate.getMonthValue();
		int day = localDate.getDayOfMonth();
		if (month > mes || (month > mes && day > data)) {
			return true;
		}
		return false;
	}

	public Evento convidarAmigos(ArrayList<User> escolhidos) {
		
		this.convidadosNaoConfirmados = escolhidos;		
		
		for (User convidado : this.convidadosNaoConfirmados) {
			// convidar amigo
			if (!isYourFriend(convidado)) {
				erro.getUsers().add(convidado);
				erro.newMessage("alguns dos convidados não fazem parte da sua lista de amigos.");
				
			} else if (isYourFriend(convidado)) {
				convidado.getEventos().add(this);
			}
		}
		return this;
	}

	private boolean isYourFriend(User convidado) {
		if (owner == convidado) {
			return false;
		} else if (!owner.getAmigos().isEmpty()) 
			for (User amigo : owner.getAmigos()) {
				if (convidado == amigo) {
					return true;
				}
			}
		return true;
	}

	public int getData() {
		return data;
	}

	public void setData(int data) {
		this.data = data;
	}

	public int getDia() {
		return mes;
	}

	public void setDia(int dia) {
		this.mes = dia;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}
	
	public void setConvidados(ArrayList<User> convidados) {
		this.convidadosNaoConfirmados = convidados;
	}

	public Date getDataDoEventoImediato() {
		return dataDoEventoImediato;
	}

	public void setDataDoEventoImediato(Date dataDoEventoImediato) {
		this.dataDoEventoImediato = dataDoEventoImediato;
	}

	@Override
	public String toString() {
		
		System.out.println("###############");
		System.out.println("Evento:");
		System.out.println("-Data do Evento: " + this.getData());
		System.out.println("-Dia do Evento: " + this.getDia());
		System.out.println("-Hora do Evento: " + this.getHora());
		System.out.println("-Convidados(" + this.getConvidadosNaoConfirmados().size()+"):"); 
		for (User convidado : convidadosNaoConfirmados) {
			System.out.println("    " + convidado.getNome());
		}
		System.out.println("-Avaliações("+ this.getAvaliacoes().size() + "):");
		for (Avaliacao av : this.getAvaliacoes()) {
			System.out.println("    Comentário: " + av.getComentario() + ", Estrelas: " + av.getEstrelas() + ", Username: " + av.getUsername());
		}
		System.out.println("-ErroConvidados:");
		System.out.println("    " + this.erro.getMessage());
		for (User u : this.erro.getUsers()) {
			System.out.println("    Nome: " + u.getNome() + ", Email: " + u.getEmail() + ".");
		}
		
		return null;
	}

	public Evento imediatamente(User owner) {
		this.dataDoEventoImediato = new Date();
		this.setImediato(true);
		this.owner = owner;
		this.erro = new ErroConvidados();
		this.pendentes = new ArrayList<User>();
		return this;
	}

	public boolean isImediato() {
		return isImediato;
	}

	public void setImediato(boolean isImediato) {
		this.isImediato = isImediato;
	}

	public Evento comentar(String comment, String username) {
		
		if (this.isFinished) {	
			Avaliacao avaliacao = new Avaliacao();
			avaliacao.setComentario(comment);
			avaliacao.setUsername(username);
			this.avaliacoes.add(avaliacao);
		}
		return this;
		
	}

	public Evento estrelas(int numOfStars, String username) {
		
		if (this.isFinished) {
			Avaliacao avaliacao = new Avaliacao();
			avaliacao.setEstrelas(numOfStars);
			avaliacao.setUsername(username);
			this.avaliacoes.add(avaliacao);
		}
		return this;
	}

	public Evento comentarEAvaliar(String comment, int numOfStars, String username) {
		
		if (this.isFinished) {
			Avaliacao avaliacao = new Avaliacao();
			avaliacao.setEstrelas(numOfStars);
			avaliacao.setComentario(comment);
			avaliacao.setUsername(username);
			this.avaliacoes.add(avaliacao);
		}
		return this;
	}

	public int getMes() {
		return mes;
	}

	public void setMes(int mes) {
		this.mes = mes;
	}

	public ArrayList<Avaliacao> getAvaliacoes() {
		return avaliacoes;
	}

	public void setAvaliacoes(ArrayList<Avaliacao> avaliacoes) {
		this.avaliacoes = avaliacoes;
	}

	public boolean isFinished() {
		return isFinished;
	}

	public void setFinished(boolean isFinished) {
		this.isFinished = isFinished;
	}
	
	public Evento terminarEvento(boolean b) {
		this.isFinished = b;
		return this;
	}

	public ErroConvidados getErro() {
		return erro;
	}

	public void setErro(ErroConvidados erro) {
		this.erro = erro;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public Evento enviarLinkDoConvite(User amigo) {
		// Abstração
		convidadosNaoConfirmados.add(amigo);
		return this;
	}

	public ArrayList<User> getConvidadosNaoConfirmados() {
		return convidadosNaoConfirmados;
	}

	public void setConvidadosNaoConfirmados(ArrayList<User> convidadosNaoConfirmados) {
		this.convidadosNaoConfirmados = convidadosNaoConfirmados;
	}

	public ArrayList<User> getConvidadosConfirmados() {
		return convidadosConfirmados;
	}

	public void setConvidadosConfirmados(ArrayList<User> convidadosConfirmados) {
		this.convidadosConfirmados = convidadosConfirmados;
	}

	public ArrayList<User> getPendentes() {
		return pendentes;
	}

	public void setPendentes(ArrayList<User> pendentes) {
		this.pendentes = pendentes;
	}

	public ArrayList<User> getObservers() {
		return observers;
	}

	public void setObservers(ArrayList<User> observers) {
		this.observers = observers;
	}

	public ArrayList<User> getObserverbleUsers() {
		return observerbleUsers;
	}

	public void setObserverbleUsers(ArrayList<User> observerbleUsers) {
		this.observerbleUsers = observerbleUsers;
	}

	public Evento desconvidarAmigo(User amigoChato) {
		convidadosNaoConfirmados.remove(amigoChato);
		return this;
	}

	public Evento aceitarConvite(User u) {
		convidadosConfirmados.add(u);
		return this;
	}

	public Evento naoAceitarConvite(User user) {
		convidadosNaoConfirmados.remove(user);
		return this;
	}

	public Evento incluirComanda(User convidado) {
		if (convidadosConfirmados.contains(convidado) || convidado == this.owner) {
			convidado.incluirComanda();
		}
		return this;
	}

	public Evento adicionarAmigoNaComanda(User gerente, User gerenciado) {
		for (User convidadosConfirmados : this.getConvidadosConfirmados()) {
			if (convidadosConfirmados.getComanda() != null && convidadosConfirmados.getComanda().getGerenciados().contains(gerenciado)) {
				return this;
			}
		}
		gerente.getComanda().getGerenciados().add(gerenciado);
		return this;
	}
	
	
}
