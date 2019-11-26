package secondUnitProject;

import java.util.List;

public class Login {
	UserRepositorio userRepo;
	Facebook fb;
	User user;
	
	public Login(UserRepositorio userRepo) {
		this.userRepo = userRepo;
	}
	
	public Login(UserRepositorio userRepo, User user) {
		this.userRepo = userRepo;
		this.user = user;
	}
	
	public Login(UserRepositorio userRepo, Facebook fb) {
		this.userRepo = userRepo;
		this.fb = fb;
	}
	
	public boolean porRedeSocial(String login) {
		return fb.log(login);
	}

	public boolean efetuarLogin(String login, String senha) {
		if ( isUsuarioCadastrado(login, senha) && isEmailValid(login) && isEmailRegistered(login) ) {
			user.setLogado(true);
			return true;
		} else if ( login.isEmpty() || senha.isEmpty() || !isEmailValid(login) || !isEmailRegistered(login) ) {
			return false;
		}
		return false;
	}

	private boolean isEmailRegistered(String login) {
		for (User user : this.userRepo.getUsers()) {
			if (user.getEmail().equals(login)) {
				return true;
			}
		}
		return false;
	}

	private boolean isEmailValid(String login) {
		boolean result = UserNegocio.isValid(login);
		return result;
	}

	private boolean isUsuarioCadastrado(String login, String senha) {
		List<User> users = this.userRepo.getUsers();
		for (User user : users) {
			if ( login.equals(user.getEmail()) && senha.equals(user.getSenha())) {
				return true;
			}
		}
		return false;
	}

	public boolean efetuarLogin(User user) {
		this.user = user;
		return this.efetuarLogin(user.getEmail(), user.getSenha());
	}
}
