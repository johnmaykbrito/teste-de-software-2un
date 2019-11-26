package secondUnitProjectTest;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import secondUnitProject.Evento;
import secondUnitProject.Login;
import secondUnitProject.User;
import secondUnitProject.UserNegocio;
import secondUnitProject.UserRepositorio;

public class ComandaTest {
	
	UserRepositorio userRepo = new UserRepositorio();
	
	@Test 
	public void inicluirComanda_IC_001() {
		User user =  new User("João Victor", "12345asd", "joao01@gmail.com", new GregorianCalendar(1999, Calendar.MAY, 31).getTime());
		UserNegocio un = new UserNegocio(userRepo); 
		un.addUser(user);
		Login lg = new Login(userRepo, user);
		lg.efetuarLogin(user);
		
		
		ArrayList<User> escolhidos = new ArrayList<User>();

		escolhidos.add(user);
		
		Evento e = new Evento();
		e = e.abrirEvento().agendarEvento(31, 12, "20:00", user).convidarAmigos(escolhidos);
		for (User convidadoNaoConfirmado : e.getConvidadosNaoConfirmados()) {
			e.addForNotify(convidadoNaoConfirmado);
		}
		
		e.myNotify();
				

		e = e.incluirComanda(user);
		//e.toString();

		assertEquals(e != null, true);
		assertEquals(user.getComanda() != null, true);	
	}
	
	@Test 
	public void capacidadeMaximaDeIncuirComanda_IC_003() {
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
		escolhidos.add(user);
		
		Evento e = new Evento();
		e = e.abrirEvento().agendarEvento(31, 12, "20:00", user).convidarAmigos(escolhidos);
		for (User convidadoNaoConfirmado : e.getConvidadosNaoConfirmados()) {
			e.addForNotify(convidadoNaoConfirmado);
		}
		
		e.myNotify();
				
		user.aceitarConvite(e);
		amigo1.aceitarConvite(e);
		amigo2.aceitarConvite(e);
		
		e = e.incluirComanda(user);
		e = e.adicionarAmigoNaComanda(user, amigo1);
		
		e = e.incluirComanda(amigo2);
		e = e.adicionarAmigoNaComanda(amigo2, amigo1);
		
		//e.toString();

		assertEquals(user.getComanda() != null, true);
		assertEquals(user.getComanda().getGerenciados().size() == 1, true);
		assertEquals(amigo2.getComanda() != null, true);
		assertEquals(amigo2.getComanda().getGerenciados().size() == 0, true);
	}
	
	@Test
	public void adicionarItensNaComanda_GC_001() {
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
		escolhidos.add(user);
		
		Evento e = new Evento();
		e = e.abrirEvento().agendarEvento(31, 12, "20:00", user).convidarAmigos(escolhidos);
		for (User convidadoNaoConfirmado : e.getConvidadosNaoConfirmados()) {
			e.addForNotify(convidadoNaoConfirmado);
		}
		
		e.myNotify();
				
		user.aceitarConvite(e);
		amigo1.aceitarConvite(e);
		amigo2.aceitarConvite(e);
		
		e = e.incluirComanda(user);
		e = e.adicionarAmigoNaComanda(user, amigo1);
		e = e.adicionarAmigoNaComanda(user, amigo2);
		
		// Adicionar item na comanda
		user.addItemNaComanda("Cerveja", 5.20);
		
		//e.toString();

		assertEquals(user.getComanda() != null, true);
		assertEquals(user.getComanda().getGerenciados().size() == 2, true);
		
		assertEquals(!user.getComanda().getItems().isEmpty(), true);
		assertEquals(user.getComanda().getItems().size() == 1, true);
		assertEquals(user.getComanda().getItems().get(0).getItemName(), "Cerveja");
		assertEquals(user.getComanda().getItems().get(0).getItemPrice() == 5.20, true);
		
	}
	
	@Test
	public void adicionarItemJaExistenteNaComanda_GC_002() {
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
		escolhidos.add(user);
		
		Evento e = new Evento();
		e = e.abrirEvento().agendarEvento(31, 12, "20:00", user).convidarAmigos(escolhidos);
		for (User convidadoNaoConfirmado : e.getConvidadosNaoConfirmados()) {
			e.addForNotify(convidadoNaoConfirmado);
		}
		
		e.myNotify();
				
		user.aceitarConvite(e);
		amigo1.aceitarConvite(e);
		amigo2.aceitarConvite(e);
		
		e = e.incluirComanda(user);
		e = e.adicionarAmigoNaComanda(user, amigo1);
		e = e.adicionarAmigoNaComanda(user, amigo2);
		
		// Adicionar itens na comanda
		user.addItemNaComanda("Cerveja", 5.20);
		user.addItemNaComanda("Cerveja", 5.20);
		
		//e.toString();

		assertEquals(user.getComanda() != null, true);
		assertEquals(user.getComanda().getGerenciados().size() == 2, true);
		
		assertEquals(!user.getComanda().getItems().isEmpty(), true);
		assertEquals(user.getComanda().getItems().size() == 2, true);
		assertEquals(user.getComanda().getItems().get(0).getItemName(), "Cerveja");
		assertEquals(user.getComanda().getItems().get(0).getItemPrice() == 5.20, true);
		assertEquals(user.getComanda().getItems().get(1).getItemName(), "Cerveja");
		assertEquals(user.getComanda().getItems().get(1).getItemPrice() == 5.20, true);
		
	}
	
	@Test
	public void adicionarItemCompartilhadoNaComanda_GC_003() {
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
		escolhidos.add(user);
		
		Evento e = new Evento();
		e = e.abrirEvento().agendarEvento(31, 12, "20:00", user).convidarAmigos(escolhidos);
		for (User convidadoNaoConfirmado : e.getConvidadosNaoConfirmados()) {
			e.addForNotify(convidadoNaoConfirmado);
		}
		
		e.myNotify();
				
		user.aceitarConvite(e);
		amigo1.aceitarConvite(e);
		amigo2.aceitarConvite(e);
		
		e = e.incluirComanda(user);
		e = e.adicionarAmigoNaComanda(user, amigo1);
		e = e.adicionarAmigoNaComanda(user, amigo2);
		
		// Adicionar itens na comanda
		user.addItemNaComanda("Cerveja", 5.20);
		user.addItemNaComanda("Cerveja", 5.20);
		
		// Adicioinar item compartilhado
		double ribPrice = 42;
		String rib = "Costela de Porco";
		Map<User, Double> compartilhadoresPorcentagens = new HashMap<User, Double>();
		compartilhadoresPorcentagens.put(user, 0.4);    // 40%
		compartilhadoresPorcentagens.put(amigo1, 0.3);  // 30%
		compartilhadoresPorcentagens.put(amigo2, 0.3);  // 30%
		user.addItemNaComanda(rib, ribPrice , compartilhadoresPorcentagens, true);
		
		//e.toString();

		assertEquals(user.getComanda() != null, true);
		assertEquals(user.getComanda().getGerenciados().size() == 2, true);
		
		assertEquals(!user.getComanda().getItems().isEmpty(), true);
		assertEquals(user.getComanda().getItems().size() == 3, true);
		assertEquals(user.getComanda().getItems().get(0).getItemName(), "Cerveja");
		assertEquals(user.getComanda().getItems().get(0).getItemPrice() == 5.20, true);
		assertEquals(user.getComanda().getItems().get(1).getItemName(), "Cerveja");
		assertEquals(user.getComanda().getItems().get(1).getItemPrice() == 5.20, true);
		
		// Teste compartilhado
		assertEquals(user.getComanda().getItems().size() == 3, true);
		assertEquals(user.getComanda().getItems().get(2).getItemName(), "Costela de Porco");
		assertEquals(user.getComanda().getItems().get(2).isShared(), true);
		assertEquals(user.getComanda().getItems().get(2).getItemPrice() == ribPrice*0.4, true);
		
		assertEquals(amigo1.getComanda().getItems().size() == 1, true);
		assertEquals(amigo1.getComanda().getItems().get(0).getItemName(), "Costela de Porco");
		assertEquals(amigo1.getComanda().getItems().get(0).isShared(), true);
		assertEquals(amigo1.getComanda().getItems().get(0).getItemPrice() == ribPrice*0.3, true);
		
		assertEquals(amigo2.getComanda().getItems().size() == 1, true);
		assertEquals(amigo2.getComanda().getItems().get(0).getItemName(), "Costela de Porco");
		assertEquals(amigo2.getComanda().getItems().get(0).isShared(), true);
		assertEquals(amigo2.getComanda().getItems().get(0).getItemPrice() == ribPrice*0.3, true);
		
		
		
	}
}
