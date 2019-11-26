package secondUnitProjectTest;

import secondUnitProject.*;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class UserTest {
	
	UserRepositorio userRepo = new UserRepositorio();
	
	@Test
	public void cadastrarUsuario_CU_001() {
		User user =  new User("João Victor", "12345asd", "joao01@gmail.com", new GregorianCalendar(1999, Calendar.MAY, 31).getTime());
		UserNegocio un = new UserNegocio(userRepo);
		boolean result = un.addUser(user);
		assertTrue(result);
	}
	
	@Test
	public void cadastrarUsuarioComDataDeNascimentoInvalida_CU_002() {
		User user =  new User("João Victor", "12345asd", "joao01@gmail.com", new GregorianCalendar(2500, Calendar.AUGUST, 20).getTime());
		UserNegocio un = new UserNegocio(userRepo);
		boolean result = un.addUser(user);
		assertFalse(result);
	}
	
	@Test
	public void cadastrarUsuarioComPadraoDeEmailInvalido_CU_003() {
		User user =  new User("João Victor", "12345asd", "joao", new GregorianCalendar(1997, Calendar.APRIL, 06).getTime());
		UserNegocio un = new UserNegocio(userRepo);
		boolean result = un.addUser(user);
		assertFalse(result);
	}
	
	@Test
	public void editarNomeDoUsuario_EU_001	() {
		User user =  new User("João Victor", "12345asd", "joao01@gmail.com", new GregorianCalendar(1999, Calendar.MAY, 31).getTime());
		UserNegocio un = new UserNegocio(userRepo); 
		un.addUser(user);
		Login lg = new Login(userRepo);
		boolean logado = lg.efetuarLogin(user);
		
		User newUserInfo =  new User("João Paulo", "12345asd", "joao01@gmail.com", new GregorianCalendar(1999, Calendar.MAY, 31).getTime());
		
		User updatedUser = un.updateUsuario(newUserInfo, user, logado);
		
		assertEquals(updatedUser.getNome(), newUserInfo.getNome());
	}
	
	@Test
	public void editarEmailDoUsuario_EU_002	() {
		User user =  new User("João Victor", "12345asd", "joao01@gmail.com", new GregorianCalendar(1999, Calendar.MAY, 31).getTime());
		UserNegocio un = new UserNegocio(userRepo); 
		un.addUser(user);
		Login lg = new Login(userRepo);
		boolean logado = lg.efetuarLogin(user);
		
		User newUserInfo =  new User("João Victor", "12345asd", "teste@gmail.com", new GregorianCalendar(1999, Calendar.MAY, 31).getTime());
		
		User updatedUser = un.updateUsuario(newUserInfo, user, logado);
		
		assertEquals(updatedUser.getEmail(), newUserInfo.getEmail());
	}
	
	@Test
	public void editarSenhaDoUsuario_EU_003	() {
		User user =  new User("João Victor", "12345asd", "joao01@gmail.com", new GregorianCalendar(1999, Calendar.MAY, 31).getTime());
		UserNegocio un = new UserNegocio(userRepo); 
		un.addUser(user);
		Login lg = new Login(userRepo);
		boolean logado = lg.efetuarLogin(user);
		
		User newUserInfo =  new User("João Victor", "54321qwe", "joao01@gmail.com", new GregorianCalendar(1999, Calendar.MAY, 31).getTime());
		
		User updatedUser = un.updateUsuario(newUserInfo, user, logado);
		
		assertEquals(updatedUser.getSenha(), newUserInfo.getSenha());
	}
	
	@Test
	public void editarFotoDoUsuario_EU_004	() {
		User user =  new User("João Victor", "12345asd", "joao01@gmail.com", new GregorianCalendar(1999, Calendar.MAY, 31).getTime());
		UserNegocio un = new UserNegocio(userRepo); 
		un.addUser(user);
		Login lg = new Login(userRepo);
		boolean logado = lg.efetuarLogin(user);
		
		User newUserInfo =  new User("João Victor", "54321qwe", "joao01@gmail.com", new GregorianCalendar(1999, Calendar.MAY, 31).getTime());
		newUserInfo.setPicPath("https://www.ibahia.com/fileadmin/user_upload/ibahia/2019/abril/11/mussum.jpg");
		
		User updatedUser = un.updateUsuario(newUserInfo, user, logado);
		
		assertEquals(updatedUser.getPicPath(), newUserInfo.getPicPath());
	}
	
	@Test
	public void editarEmailDoUsuarioComEmailInvaido_EU_005	() {
		User user =  new User("João Victor", "12345asd", "joao01@gmail.com", new GregorianCalendar(1999, Calendar.MAY, 31).getTime());
		UserNegocio un = new UserNegocio(userRepo); 
		un.addUser(user);
		Login lg = new Login(userRepo);
		boolean logado = lg.efetuarLogin(user);
		
		User newUserInfo =  new User("João Victor", "12345asd", "teste", new GregorianCalendar(1999, Calendar.MAY, 31).getTime());
		
		User updatedUser = un.updateUsuario(newUserInfo, user, logado);
		
		assertNotEquals(updatedUser.getEmail(), newUserInfo.getEmail());
	}
	
	
}
