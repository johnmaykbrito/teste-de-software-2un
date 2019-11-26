package secondUnitProjectTest;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Test;

import secondUnitProject.Login;
import secondUnitProject.User;
import secondUnitProject.UserNegocio;
import secondUnitProject.UserRepositorio;

public class AmigoTest {
	
	UserRepositorio userRepo = new UserRepositorio();
	
	@Test
	public void VisualisarListaDeAmigos_VLA_001() {
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
		
		// add a lista de amigos
		un.addAmigo(user, amigo1);
		// add a lista de amigos
		un.addAmigo(user, amigo2);


		assertEquals(user.getLogado(), true);
		assertEquals(user.getAmigos().size(), 2);
		assertEquals(user.getAmigos().get(0).getNome(), "Boneco Josias");
		assertEquals(user.getAmigos().get(1).getNome(), "Advogado Paloma");
	}
	
	@Test
	public void visualisarListaDeAmigosVazia_VLA_002() {
		User user =  new User("João Victor", "12345asd", "joao01@gmail.com", new GregorianCalendar(1999, Calendar.MAY, 31).getTime());
		UserNegocio un = new UserNegocio(userRepo); 
		un.addUser(user);
		Login lg = new Login(userRepo, user);
		lg.efetuarLogin(user);
		
		assertEquals(user.getLogado(), true);
		assertEquals(user.getAmigos().size(), 0);
	}
	
	@Test
	public void adicionarAmigoComSuceso_AA_001() {
		User user =  new User("João Victor", "12345asd", "joao01@gmail.com", new GregorianCalendar(1999, Calendar.MAY, 31).getTime());
		UserNegocio un = new UserNegocio(userRepo); 
		un.addUser(user);
		Login lg = new Login(userRepo, user);
		lg.efetuarLogin(user);
		
		
		
		// criar User amigos
		User amigo =  new User("Boneco Josias", "12345asd", "jp@gmail.com", new GregorianCalendar(1999, Calendar.MAY, 31).getTime());
		un.addUser(amigo); // add ao BD
		// add a lista de amigos
		un.addAmigo(user, amigo);
		
		assertEquals(user.getLogado(), true);
		assertEquals(user.getAmigos().size() > 0, true);
	}
	
	@Test
	public void adicionarAmigoComEmailNaoCadastrado_AA_002() {
		User user =  new User("João Victor", "12345asd", "joao01@gmail.com", new GregorianCalendar(1999, Calendar.MAY, 31).getTime());
		UserNegocio un = new UserNegocio(userRepo); 
		un.addUser(user);
		Login lg = new Login(userRepo, user);
		lg.efetuarLogin(user);
		
		un.addAmigoComEmail(user, "testeee@mail.com");
		
		assertEquals(user.getLogado(), true);
		assertEquals(user.getAmigos().size() == 0, true);
	}
	
	@Test
	public void adicionarAmigoComEmailInvalido_AA_003() {
		User user =  new User("João Victor", "12345asd", "joao01@gmail.com", new GregorianCalendar(1999, Calendar.MAY, 31).getTime());
		UserNegocio un = new UserNegocio(userRepo); 
		un.addUser(user);
		Login lg = new Login(userRepo, user);
		lg.efetuarLogin(user);
		
		un.addAmigoComEmail(user, "testejp");
		
		assertEquals(user.getLogado(), true);
		assertEquals(user.getAmigos().size() == 0, true);
	}
	
	@Test
	public void excluirAmigoComSucesso_EA_001() {
		User user =  new User("João Victor", "12345asd", "joao01@gmail.com", new GregorianCalendar(1999, Calendar.MAY, 31).getTime());
		UserNegocio un = new UserNegocio(userRepo); 
		un.addUser(user);
		Login lg = new Login(userRepo, user);
		lg.efetuarLogin(user);
		
		User amigo1 =  new User("Boneco Josias", "12345asd", "josias@gmail.com", new GregorianCalendar(1999, Calendar.MAY, 31).getTime());
		un.addUser(amigo1);
		User amigo2 =  new User("Advogado Paloma", "12345asd", "paloma@gmail.com", new GregorianCalendar(1999, Calendar.MAY, 31).getTime());
		un.addUser(amigo2);
		
		un.addAmigo(user, amigo1);
		un.addAmigo(user, amigo2);
		
		un.excluirAmigo(user, amigo1);


		assertEquals(user.getLogado(), true);
		assertEquals(user.getAmigos().size(), 1);
		assertEquals(user.getAmigos().get(0).getNome(), "Advogado Paloma");
	}
	
	

}
