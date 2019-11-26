package secondUnitProjectTest;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Test;

import secondUnitProject.Evento;
import secondUnitProject.Login;
import secondUnitProject.User;
import secondUnitProject.UserNegocio;
import secondUnitProject.UserRepositorio;

public class EventoTest {
	
	UserRepositorio userRepo = new UserRepositorio();
	
	
    @Test 
	public void criarEventoComSucesso_CE_001() {
		User user =  new User("João Victor", "12345asd", "joao01@gmail.com", new GregorianCalendar(1999, Calendar.MAY, 31).getTime());
		UserNegocio un = new UserNegocio(userRepo); 
		un.addUser(user);
		Login lg = new Login(userRepo, user);
		lg.efetuarLogin(user);
		
		// criar User amigos
		User amigo1 =  new User("Boneco Josias", "12345asd", "josias@gmail.com", new GregorianCalendar(1999, Calendar.MAY, 31).getTime());
		un.addUser(amigo1); // add ao BD
		User amigo2 =  new User("Advogado Paloma", "12345asd", "paloma@gmail.com", new GregorianCalendar(1999, Calendar.MAY, 31).getTime());
		un.addUser(amigo2); // add ao BD
		

		un.addAmigo(user, amigo1);
		un.addAmigo(user, amigo2);
		
		ArrayList<User> escolhidos = new ArrayList<User>();
		escolhidos.add(amigo1);
		escolhidos.add(amigo2);
		escolhidos.add(user);
		
		Evento e = new Evento();
		e = e.abrirEvento().agendarEvento(31, 12, "20:00", user).convidarAmigos(escolhidos);
//		e.toString();

		assertEquals(e != null, true);
		assertEquals(e.getData() > 0 && e.getData() <= 31, true);
		assertEquals(e.getDia() > 0, true);
		assertEquals(e.getHora() != null, true);
		assertEquals(e.getConvidadosNaoConfirmados().size() > 0, true);
	}
	
    @Test 
	public void criarEventoSozinhoComSucesso_CE_002() {
		User user =  new User("João Victor", "12345asd", "joao01@gmail.com", new GregorianCalendar(1999, Calendar.MAY, 31).getTime());
		UserNegocio un = new UserNegocio(userRepo); 
		un.addUser(user);
		Login lg = new Login(userRepo, user);
		lg.efetuarLogin(user);
		
		// criar User amigos
		User amigo1 =  new User("Boneco Josias", "12345asd", "josias@gmail.com", new GregorianCalendar(1999, Calendar.MAY, 31).getTime());
		un.addUser(amigo1); // add ao BD
		User amigo2 =  new User("Advogado Paloma", "12345asd", "paloma@gmail.com", new GregorianCalendar(1999, Calendar.MAY, 31).getTime());
		un.addUser(amigo2); // add ao BD
		

		un.addAmigo(user, amigo1);
		un.addAmigo(user, amigo2);
		
		ArrayList<User> escolhidos = new ArrayList<User>();
		escolhidos.add(user);
		// lista de convidados com apenas o usuário logado
		
		Evento e = new Evento();
		e = e.abrirEvento().agendarEvento(31, 12, "20:00", user).convidarAmigos(escolhidos);
//		e.toString();

		assertEquals(e != null, true);
		assertEquals(e.getData() > 0 && e.getData() <= 31, true);
		assertEquals(e.getDia() > 0, true);
		assertEquals(e.getHora() != null, true);
		assertEquals(e.getConvidadosNaoConfirmados().size() > 0, true);
	}

    @Test 
	public void criarEventoImediatamenteComSucesso_CE_003() {
		User user =  new User("João Victor", "12345asd", "joao01@gmail.com", new GregorianCalendar(1999, Calendar.MAY, 31).getTime());
		UserNegocio un = new UserNegocio(userRepo); 
		un.addUser(user);
		Login lg = new Login(userRepo, user);
		lg.efetuarLogin(user);
		
		// criar User amigos
		User amigo1 =  new User("Boneco Josias", "12345asd", "josias@gmail.com", new GregorianCalendar(1999, Calendar.MAY, 31).getTime());
		un.addUser(amigo1); // add ao BD
		User amigo2 =  new User("Advogado Paloma", "12345asd", "paloma@gmail.com", new GregorianCalendar(1999, Calendar.MAY, 31).getTime());
		un.addUser(amigo2); // add ao BD
		

		un.addAmigo(user, amigo1);
		un.addAmigo(user, amigo2);
		
		ArrayList<User> escolhidos = new ArrayList<User>();
		escolhidos.add(user);
		// lista de convidados com apenas o usuário logado
		
		Evento e = new Evento();
		e = e.abrirEvento().imediatamente(user).convidarAmigos(escolhidos);
		//e.toString();

		assertEquals(e != null, true);
		assertEquals(e.getDataDoEventoImediato().getTime() > new Date().getTime() + 10000 || e.getDataDoEventoImediato().getTime() > new Date().getTime() - 10000 , true);
	}
	
    @Test 
	public void criarEventoComDataNoPassadoSemSucesso_CE_005() {
		User user =  new User("João Victor", "12345asd", "joao01@gmail.com", new GregorianCalendar(1999, Calendar.MAY, 31).getTime());
		UserNegocio un = new UserNegocio(userRepo); 
		un.addUser(user);
		Login lg = new Login(userRepo, user);
		lg.efetuarLogin(user);
		
		// criar User amigos
		User amigo1 =  new User("Boneco Josias", "12345asd", "josias@gmail.com", new GregorianCalendar(1999, Calendar.MAY, 31).getTime());
		un.addUser(amigo1); // add ao BD
		User amigo2 =  new User("Advogado Paloma", "12345asd", "paloma@gmail.com", new GregorianCalendar(1999, Calendar.MAY, 31).getTime());
		un.addUser(amigo2); // add ao BD
		

		un.addAmigo(user, amigo1);
		un.addAmigo(user, amigo2);
		
		ArrayList<User> escolhidos = new ArrayList<User>();
		escolhidos.add(amigo1);
		escolhidos.add(amigo2);
		escolhidos.add(user);
		
		Evento e = new Evento();
		e = e.abrirEvento().agendarEvento(10, 2, "20:00", user);

		assertEquals(e == null, true);
	}
	
    @Test 
	public void criarEventoComDataInvalida_CE_006() {
		User user =  new User("João Victor", "12345asd", "joao01@gmail.com", new GregorianCalendar(1999, Calendar.MAY, 31).getTime());
		UserNegocio un = new UserNegocio(userRepo); 
		un.addUser(user);
		Login lg = new Login(userRepo, user);
		lg.efetuarLogin(user);
		
		// criar User amigos
		User amigo1 =  new User("Boneco Josias", "12345asd", "josias@gmail.com", new GregorianCalendar(1999, Calendar.MAY, 31).getTime());
		un.addUser(amigo1); // add ao BD
		User amigo2 =  new User("Advogado Paloma", "12345asd", "paloma@gmail.com", new GregorianCalendar(1999, Calendar.MAY, 31).getTime());
		un.addUser(amigo2); // add ao BD
		

		un.addAmigo(user, amigo1);
		un.addAmigo(user, amigo2);
		
		ArrayList<User> escolhidos = new ArrayList<User>();
		escolhidos.add(amigo1);
		escolhidos.add(amigo2);
		escolhidos.add(user);
		
		Evento e = new Evento();
		e = e.abrirEvento().agendarEvento(80, 6, "20:00", user);

		assertEquals(e == null, true);
	}

    @Test 
	public void deixarComentarioEAvaliacao_AC_001() {
		User user =  new User("João Victor", "12345asd", "joao01@gmail.com", new GregorianCalendar(1999, Calendar.MAY, 31).getTime());
		UserNegocio un = new UserNegocio(userRepo); 
		un.addUser(user);
		Login lg = new Login(userRepo, user);
		lg.efetuarLogin(user);
		
		// criar User amigos
		User amigo1 =  new User("Boneco Josias", "12345asd", "josias@gmail.com", new GregorianCalendar(1999, Calendar.MAY, 31).getTime());
		un.addUser(amigo1); // add ao BD
		User amigo2 =  new User("Advogado Paloma", "12345asd", "paloma@gmail.com", new GregorianCalendar(1999, Calendar.MAY, 31).getTime());
		un.addUser(amigo2); // add ao BD
		

		un.addAmigo(user, amigo1);
		un.addAmigo(user, amigo2);
		
		ArrayList<User> escolhidos = new ArrayList<User>();
		escolhidos.add(amigo1);
		escolhidos.add(amigo2);
		escolhidos.add(user);
		
		Evento e = new Evento();
		e = e.abrirEvento().agendarEvento(31, 12, "20:00", user).convidarAmigos(escolhidos).terminarEvento(true);
		e = e.comentarEAvaliar("Lugar ótimo demais", 5, user.getNome());
		// e.toString();

		assertEquals(e != null, true);
		assertEquals(e.getAvaliacoes().size(), 1);
	}
	
    @Test 
	public void deixarAvaliacaoComEstrelas_AC_003() {
		User user =  new User("João Victor", "12345asd", "joao01@gmail.com", new GregorianCalendar(1999, Calendar.MAY, 31).getTime());
		UserNegocio un = new UserNegocio(userRepo); 
		un.addUser(user);
		Login lg = new Login(userRepo, user);
		lg.efetuarLogin(user);
		
		// criar User amigos
		User amigo1 =  new User("Boneco Josias", "12345asd", "josias@gmail.com", new GregorianCalendar(1999, Calendar.MAY, 31).getTime());
		un.addUser(amigo1); // add ao BD
		User amigo2 =  new User("Advogado Paloma", "12345asd", "paloma@gmail.com", new GregorianCalendar(1999, Calendar.MAY, 31).getTime());
		un.addUser(amigo2); // add ao BD
		

		un.addAmigo(user, amigo1);
		un.addAmigo(user, amigo2);
		
		ArrayList<User> escolhidos = new ArrayList<User>();
		escolhidos.add(amigo1);
		escolhidos.add(amigo2);
		escolhidos.add(user);
		
		Evento e = new Evento();
		e = e.abrirEvento().agendarEvento(31, 12, "20:00", user).convidarAmigos(escolhidos).terminarEvento(true).estrelas(3, user.getNome());
		// e.toString();

		assertEquals(e != null, true);
		assertEquals(e.getAvaliacoes().size(), 1);
		assertEquals(e.getAvaliacoes().get(0).getEstrelas(), 3);
	}
	
	
    @Test 
	public void deixarAvaliacaoComComentario_AC_004() {
		User user =  new User("João Victor", "12345asd", "joao01@gmail.com", new GregorianCalendar(1999, Calendar.MAY, 31).getTime());
		UserNegocio un = new UserNegocio(userRepo); 
		un.addUser(user);
		Login lg = new Login(userRepo, user);
		lg.efetuarLogin(user);
		
		// criar User amigos
		User amigo1 =  new User("Boneco Josias", "12345asd", "josias@gmail.com", new GregorianCalendar(1999, Calendar.MAY, 31).getTime());
		un.addUser(amigo1); // add ao BD
		User amigo2 =  new User("Advogado Paloma", "12345asd", "paloma@gmail.com", new GregorianCalendar(1999, Calendar.MAY, 31).getTime());
		un.addUser(amigo2); // add ao BD
		

		un.addAmigo(user, amigo1);
		un.addAmigo(user, amigo2);
		
		ArrayList<User> escolhidos = new ArrayList<User>();
		escolhidos.add(amigo1);
		escolhidos.add(amigo2);
		escolhidos.add(user);
		
		Evento e = new Evento();
		e = e.abrirEvento().agendarEvento(31, 12, "20:00", user).convidarAmigos(escolhidos).terminarEvento(true).comentar("Lugar bonito de se visitar", user.getNome());
		// e.toString();

		assertEquals(e != null, true);
		assertEquals(e.getAvaliacoes().size(), 1);
		assertEquals(e.getAvaliacoes().get(0).getComentario() != null, true);
	}
	
    @Test 
	public void convidarAmigoComSucesso_CA_001() {
		User user =  new User("João Victor", "12345asd", "joao01@gmail.com", new GregorianCalendar(1999, Calendar.MAY, 31).getTime());
		UserNegocio un = new UserNegocio(userRepo); 
		un.addUser(user);
		Login lg = new Login(userRepo, user);
		lg.efetuarLogin(user);
		
		// criar User amigos
		User amigo1 =  new User("João Paulo", "12345asd", "josias@gmail.com", new GregorianCalendar(1999, Calendar.MAY, 31).getTime());
		un.addUser(amigo1); // add ao BD
		

		un.addAmigo(user, amigo1);
		
		ArrayList<User> escolhidos = new ArrayList<User>();
		escolhidos.add(amigo1);
		escolhidos.add(user);
		
		Evento e = new Evento();
		e = e.abrirEvento().agendarEvento(31, 12, "20:00", user).convidarAmigos(escolhidos);
		// e.toString();

		assertEquals(e != null, true);
		assertEquals(!e.getConvidadosNaoConfirmados().isEmpty(), true);
		
		String nomeConvidado = null;
		for (User convidado : e.getConvidadosNaoConfirmados()) {
			if (convidado.getNome().equals("João Paulo")) {
				nomeConvidado = convidado.getNome(); 
			}
		}
		
		assertEquals(nomeConvidado, "João Paulo");
	}
	

	
    @Test 
	public void convidarAmigosComSucesso_CA_002() {
		User user =  new User("João Victor", "12345asd", "joao01@gmail.com", new GregorianCalendar(1999, Calendar.MAY, 31).getTime());
		UserNegocio un = new UserNegocio(userRepo); 
		un.addUser(user);
		Login lg = new Login(userRepo, user);
		lg.efetuarLogin(user);
		
		// criar User amigos
		User amigo1 =  new User("Boneco Josias", "12345asd", "josias@gmail.com", new GregorianCalendar(1999, Calendar.MAY, 31).getTime());
		un.addUser(amigo1); // add ao BD
		User amigo2 =  new User("Advogado Paloma", "12345asd", "paloma@gmail.com", new GregorianCalendar(1999, Calendar.MAY, 31).getTime());
		un.addUser(amigo2); // add ao BD
		

		un.addAmigo(user, amigo1);
		un.addAmigo(user, amigo2);
		
		ArrayList<User> escolhidos = new ArrayList<User>();
		escolhidos.add(amigo1);
		escolhidos.add(amigo2);
		escolhidos.add(user);
		
		Evento e = new Evento();
		e = e.abrirEvento().agendarEvento(31, 12, "20:00", user).convidarAmigos(escolhidos);
		// e.toString();

		assertEquals(e != null, true);
		assertEquals(e.getConvidadosNaoConfirmados().size() > 2, true);
	}
	
	
    @Test 
	public void convidarAmigoInexistente_CA_003() {
		User user =  new User("João Victor", "12345asd", "joao01@gmail.com", new GregorianCalendar(1999, Calendar.MAY, 31).getTime());
		UserNegocio un = new UserNegocio(userRepo); 
		un.addUser(user);
		Login lg = new Login(userRepo, user);
		lg.efetuarLogin(user);
		
		// criar User amigos
		User amigo1 =  new User("João Henrique", "12345asd", "josias@gmail.com", new GregorianCalendar(1999, Calendar.MAY, 31).getTime());
		un.addUser(amigo1); // add ao BD
		
		
		ArrayList<User> escolhidos = new ArrayList<User>();
		escolhidos.add(amigo1);
		escolhidos.add(user);
		
		Evento e = new Evento();
		e = e.abrirEvento().agendarEvento(31, 12, "20:00", user).convidarAmigos(escolhidos);
//		e.toString();

		assertEquals(e != null, true);
		assertEquals(e.getErro().getMessage(), "Ocorreu o seguinte erro: alguns dos convidados não fazem parte da sua lista de amigos.");
		assertEquals(e.getErro().getUsers().size() > 0, true);
		assertEquals(e.getErro().getUsers().get(0).getNome(), "João Victor");
	}
	
    @Test 
	public void convidarAmigosViaLink_CA_005() {
		User user =  new User("João Victor", "12345asd", "joao01@gmail.com", new GregorianCalendar(1999, Calendar.MAY, 31).getTime());
		UserNegocio un = new UserNegocio(userRepo); 
		un.addUser(user);
		Login lg = new Login(userRepo, user);
		lg.efetuarLogin(user);
		
		// criar User amigos
		User amigo1 =  new User("Boneco Josias", "12345asd", "josias@gmail.com", new GregorianCalendar(1999, Calendar.MAY, 31).getTime());
		un.addUser(amigo1); // add ao BD
		User amigo2 =  new User("Advogado Paloma", "12345asd", "paloma@gmail.com", new GregorianCalendar(1999, Calendar.MAY, 31).getTime());
		un.addUser(amigo2); // add ao BD
		

		un.addAmigo(user, amigo1);
		un.addAmigo(user, amigo2);
		
		ArrayList<User> escolhidos = new ArrayList<User>();
		escolhidos.add(amigo1);
		escolhidos.add(user);
		
		Evento e = new Evento();
		e = e.abrirEvento().agendarEvento(31, 12, "20:00", user).convidarAmigos(escolhidos);
		e = e.enviarLinkDoConvite(amigo2);

		assertEquals(e != null, true);
		assertEquals(e.getConvidadosNaoConfirmados().size() > 2, true);
	}
	
	
    @Test 
	public void desconvidarAmigoComSucesso_DA_001() {
		User user =  new User("João Victor", "12345asd", "joao01@gmail.com", new GregorianCalendar(1999, Calendar.MAY, 31).getTime());
		UserNegocio un = new UserNegocio(userRepo); 
		un.addUser(user);
		Login lg = new Login(userRepo, user);
		lg.efetuarLogin(user);
		
		// criar User amigos
		User amigo1 =  new User("João Paulo", "12345asd", "josias@gmail.com", new GregorianCalendar(1999, Calendar.MAY, 31).getTime());
		un.addUser(amigo1); // add ao BD
		

		un.addAmigo(user, amigo1);
		
		ArrayList<User> escolhidos = new ArrayList<User>();
		escolhidos.add(amigo1);
		escolhidos.add(user);
		
		
		Evento e = new Evento();
		e = e.abrirEvento().agendarEvento(31, 12, "20:00", user).convidarAmigos(escolhidos);
		//e.toString();

		assertEquals(e != null, true);
		assertEquals(e.getConvidadosNaoConfirmados().size(), 2);
		assertEquals(!e.getConvidadosNaoConfirmados().isEmpty(), true);
		
		User amigoChato = null;
		String nomeConvidado = null;
		for (User convidado : e.getConvidadosNaoConfirmados()) {
			if (convidado.getNome().equals("João Paulo")) {
				amigoChato = convidado;
				nomeConvidado = convidado.getNome(); 
			}
		}
		
		assertEquals(nomeConvidado, "João Paulo");
		
		e = e.desconvidarAmigo(amigoChato);
		assertEquals(e != null, true);
		assertEquals(e.getConvidadosNaoConfirmados().size(), 1);
		
	}
	
	
    @Test 
	public void desconvidarAmigoAntesDaConfirmacao_DA_003() {
		User user =  new User("João Victor", "12345asd", "joao01@gmail.com", new GregorianCalendar(1999, Calendar.MAY, 31).getTime());
		UserNegocio un = new UserNegocio(userRepo); 
		un.addUser(user);
		Login lg = new Login(userRepo, user);
		lg.efetuarLogin(user);
		
		// criar User amigos
		User amigo1 =  new User("João Paulo", "12345asd", "josias@gmail.com", new GregorianCalendar(1999, Calendar.MAY, 31).getTime());
		un.addUser(amigo1); // add ao BD
		User amigo2 =  new User("Paulo Abadie", "12345asd", "josias@gmail.com", new GregorianCalendar(1999, Calendar.MAY, 31).getTime());
		un.addUser(amigo2); // add ao BD
		

		un.addAmigo(user, amigo1);
		un.addAmigo(user, amigo2);
		
		ArrayList<User> escolhidos = new ArrayList<User>();
		escolhidos.add(amigo1);
		escolhidos.add(amigo2);
		escolhidos.add(user);
		
		Evento e = new Evento();
		e = e.abrirEvento().agendarEvento(31, 12, "20:00", user).convidarAmigos(escolhidos);
		e.addForNotify(amigo1);
		e.addForNotify(amigo2);
		e.myNotify();
		
		
		ArrayList<User> escolhidos2 = new ArrayList<User>();
		escolhidos2.add(amigo1);
		escolhidos2.add(user);
		Evento e2 = new Evento();
		e2 = e2.abrirEvento().agendarEvento(31, 12, "20:00", user).convidarAmigos(escolhidos2);
		
		
		e2.addForNotify(amigo1);
		e2.myNotify();
		
		//e.toString();

		assertEquals(e != null, true);
		assertEquals(e.getConvidadosNaoConfirmados().size(), 3);
		assertEquals(!e.getConvidadosNaoConfirmados().isEmpty(), true);
		
		User amigoChato = null;
		String nomeConvidado = null;
		for (User convidado : e.getConvidadosNaoConfirmados()) {
			if (convidado.getNome().equals("João Paulo")) {
				amigoChato = convidado;
				nomeConvidado = convidado.getNome(); 
			}
		}
		
		assertEquals(nomeConvidado, "João Paulo");
		
		e = e.desconvidarAmigo(amigoChato);
		assertEquals(e != null, true);
		assertEquals(e.getConvidadosNaoConfirmados().size(), 2);
	}
	
	@Test 
	public void aceitarConvite_AE_001() {
		User user =  new User("João Victor", "12345asd", "joao01@gmail.com", new GregorianCalendar(1999, Calendar.MAY, 31).getTime());
		UserNegocio un = new UserNegocio(userRepo); 
		un.addUser(user);
		Login lg = new Login(userRepo, user);
		lg.efetuarLogin(user);
		
		// criar User amigos
		User amigo1 =  new User("João Paulo", "12345asd", "josias@gmail.com", new GregorianCalendar(1999, Calendar.MAY, 31).getTime());
		un.addUser(amigo1); // add ao BD
		User amigo2 =  new User("Paulo Abadie", "12345asd", "josias@gmail.com", new GregorianCalendar(1999, Calendar.MAY, 31).getTime());
		un.addUser(amigo2); // add ao BD
		

		un.addAmigo(user, amigo1);
		un.addAmigo(user, amigo2);
		
		ArrayList<User> escolhidos = new ArrayList<User>();
		escolhidos.add(amigo1);
		escolhidos.add(amigo2);
		escolhidos.add(user);
		
		Evento e = new Evento();
		e = e.abrirEvento().agendarEvento(31, 12, "20:00", user).convidarAmigos(escolhidos);
		for (User convidadoNaoConfirmado : e.getConvidadosNaoConfirmados()) {
			e.addForNotify(convidadoNaoConfirmado);
		}
		
		e.myNotify();
				
		amigo1.aceitarConvite(e);
		amigo2.aceitarConvite(e);
		//e.toString();

		assertEquals(e != null, true);
		assertEquals(e.getConvidadosNaoConfirmados().size(), 3);
		assertEquals(!e.getConvidadosNaoConfirmados().isEmpty(), true);
		
		User amigoChato = null;
		String nomeConvidado = null;
		for (User convidado : e.getConvidadosNaoConfirmados()) {
			if (convidado.getNome().equals("João Paulo")) {
				amigoChato = convidado;
				nomeConvidado = convidado.getNome(); 
			}
		}
		
		assertEquals(nomeConvidado, "João Paulo");
		
		e = e.desconvidarAmigo(amigoChato);
		assertEquals(e != null, true);
		assertEquals(e.getConvidadosNaoConfirmados().size(), 2);
		
		assertEquals(e.getConvidadosConfirmados().size(), 2);
		assertEquals(e.getConvidadosConfirmados().get(0).getNome(), "João Paulo");
		assertEquals(e.getConvidadosConfirmados().get(1).getNome(), "Paulo Abadie");
	}
	
	@Test 
	public void naoAceitarConvite_AE_002() {
		User user =  new User("João Victor", "12345asd", "joao01@gmail.com", new GregorianCalendar(1999, Calendar.MAY, 31).getTime());
		UserNegocio un = new UserNegocio(userRepo); 
		un.addUser(user);
		Login lg = new Login(userRepo, user);
		lg.efetuarLogin(user);
		
		// criar User amigos
		User amigo1 =  new User("João Paulo", "12345asd", "josias@gmail.com", new GregorianCalendar(1999, Calendar.MAY, 31).getTime());
		un.addUser(amigo1); // add ao BD
		User amigo2 =  new User("Paulo Abadie", "12345asd", "josias@gmail.com", new GregorianCalendar(1999, Calendar.MAY, 31).getTime());
		un.addUser(amigo2); // add ao BD
		

		un.addAmigo(user, amigo1);
		un.addAmigo(user, amigo2);
		
		ArrayList<User> escolhidos = new ArrayList<User>();
		escolhidos.add(amigo1);
		escolhidos.add(amigo2);
		escolhidos.add(user);
		
		Evento e = new Evento();
		e = e.abrirEvento().agendarEvento(31, 12, "20:00", user).convidarAmigos(escolhidos);
		for (User convidadoNaoConfirmado : e.getConvidadosNaoConfirmados()) {
			e.addForNotify(convidadoNaoConfirmado);
		}
		
		e.myNotify();
				
		amigo1.naoAceitarConvite(e);
		amigo2.aceitarConvite(e);
		//e.toString();

		assertEquals(e != null, true);
		assertEquals(e.getConvidadosNaoConfirmados().size(), 2);
		assertEquals(!e.getConvidadosNaoConfirmados().isEmpty(), true);
		
		User amigoChato = null;
		String nomeConvidado = null;
		for (User convidado : e.getConvidadosNaoConfirmados()) {
			if (convidado.getNome().equals("Paulo Abadie")) {
				amigoChato = convidado;
				nomeConvidado = convidado.getNome(); 
			}
		}
		
		assertEquals(nomeConvidado, "Paulo Abadie");
		
		e = e.desconvidarAmigo(amigoChato);
		assertEquals(e != null, true);
		assertEquals(e.getConvidadosNaoConfirmados().size(), 1);
		
		assertEquals(e.getConvidadosConfirmados().size(), 1);
		assertEquals(e.getConvidadosConfirmados().get(0).getNome(), "Paulo Abadie");

	}
	
}
