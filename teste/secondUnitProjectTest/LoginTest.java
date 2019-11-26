package secondUnitProjectTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import secondUnitProject.Facebook;
import secondUnitProject.Login;
import secondUnitProject.User;
import secondUnitProject.UserNegocio;
import secondUnitProject.UserRepositorio;

@RunWith(MockitoJUnitRunner.class)
public class LoginTest {
	
	UserRepositorio userRepo = new UserRepositorio();
	
	@Mock
	Facebook fb;
	
	@Test
	public void LoginComDadosCorretos_RL_001() {
		// Adicionar usuário válido
		User user =  new User("João Victor", "12345asd", "joao01@gmail.com", new GregorianCalendar(1999, Calendar.MAY, 31).getTime());
		new UserNegocio(userRepo).addUser(user);
		
		// Validar login
		Login lg = new Login(userRepo, user);
		boolean result = lg.efetuarLogin("joao01@gmail.com", "12345asd");
		
		assertTrue(result);
	}
	
	@Test
	public void LoginSemDados_RL_002() {
		Login lg = new Login(userRepo);
		boolean result = lg.efetuarLogin("", "");
		
		assertFalse(result);
	}
	
	@Test
	public void LoginComEmailInvalido_RL_003() {
		// Adicionar usuário válido
		User user =  new User("João Victor", "12345asd", "joao01@gmail.com", new GregorianCalendar(1999, Calendar.MAY, 31).getTime());
		new UserNegocio(userRepo).addUser(user);
		
		// Validar login
		Login lg = new Login(userRepo, user);
		boolean result = lg.efetuarLogin("joao", "12345asd");
		
		assertFalse(result);
	}
	
	@Test
	public void LoginComEmailNaoCadastrado_RL_004() {
		// Adicionar usuário válido
		User user =  new User("João Victor", "12345asd", "joao01@gmail.com", new GregorianCalendar(1999, Calendar.MAY, 31).getTime());
		new UserNegocio(userRepo).addUser(user);
		
		// Validar login
		Login lg = new Login(userRepo, user);
		boolean result = lg.efetuarLogin("test321@gmail.com", "12345asd");
		
		assertFalse(result);
	}
	
	@Test
	public void LoginComRedeSocial_RL_005() {		
		// Validar login
		Login lg = new Login(userRepo, fb);
		when(fb.log("joao01@gmail.com")).thenReturn(true); // REFACTOR: poderia ter como retorno um credenciais. Se existir no repo loga, senão cria.
		
		boolean result = lg.porRedeSocial("joao01@gmail.com");
		
		assertTrue(result);
	}
	
	
	

}
