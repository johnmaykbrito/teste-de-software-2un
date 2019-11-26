package secondUnitProject;

import java.util.ArrayList;
import java.util.Date;

public class UserNegocio {
	
	private UserRepositorio userRepo;
	
	public UserNegocio(UserRepositorio userRepo) {
		this.userRepo = userRepo;
	}
	
	public boolean  addAmigoComEmail(User user, String email) {
		if  (!UserNegocio.isValid(email)) {
			return false;
		}
		
		for (User u : userRepo.getUsers()) {
			if (email.equals(u.getEmail()) && user.getLogado()) {
				return this.addAmigo(user, u);
			}
		}
		return false;
	}
	
	public boolean addAmigo(User user, User amigo) {
		User u = this.getUserByEmail(user);
		User friend = this.getUserByEmail(amigo);
		
		if (u != null && friend != null) {
			u.addAmigo(amigo);
			return true;
		} else if (u == null) {
			System.out.println("Usuário não existe no BD");
			return false;
		} else if (friend == null) {
			System.out.println("Amigo não existe no BD");
			return false;
		}
		return false;
	}

	private User getUserByEmail(User user) {
		for (User u : userRepo.getUsers()) {
			if (user.getEmail().equals(u.getEmail())) {
				return u;
			}
		}
		return null;
	}

	public boolean addUser(User user) {
		boolean result = false;

		Date today = new Date();
		if ( today.after(user.getDataNascimento()) && UserNegocio.isValid(user.getEmail()) ) { 
			return this.userRepo.addUser(user);
		} else if ( today.before(user.getDataNascimento()) || !UserNegocio.isValid(user.getEmail())) {
			return false;
		}
		return result;
	}
	
	static boolean isValid(String email) {
      String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
      return email.matches(regex);
   }
	
	public User updateUsuario(User newUserInfo, User u, boolean logado) {
		Date today = new Date();
		// Refactor: fazer um método que pegue user por um identificador: email
		for (User user : userRepo.getUsers()) {
			if (logado && user == u) {
				if (newUserInfo.getNome() != null && !newUserInfo.getNome().isEmpty() && !newUserInfo.getNome().equals(user.getNome())) {
					user.setNome(newUserInfo.getNome());
					System.out.println(">>>" + user.getNome());
				}
				if (newUserInfo.getSenha() != null && !newUserInfo.getSenha().isEmpty()&& !newUserInfo.getSenha().equals(user.getSenha())) {
					user.setSenha(newUserInfo.getSenha());
					System.out.println(">>>" + user.getSenha());
				}
				if (newUserInfo.getEmail() != null && !newUserInfo.getEmail().isEmpty() && UserNegocio.isValid(newUserInfo.getEmail()) && !newUserInfo.getEmail().equals(user.getEmail())) {
					user.setEmail(newUserInfo.getEmail());
					System.out.println(">>>" + user.getEmail());
				}
				if (newUserInfo.getPicPath() != null && !newUserInfo.getPicPath().isEmpty() && !newUserInfo.getPicPath().equals(user.getPicPath())) {
					user.setPicPath(newUserInfo.getPicPath());
					System.out.println(">>>" + user.getPicPath());
				}
				if (newUserInfo.getDataNascimento() != null && today.after(user.getDataNascimento()) && !newUserInfo.getDataNascimento().equals(user.getDataNascimento())) {
					user.setDataNascimento(newUserInfo.getDataNascimento());
					System.out.println(">>>" + user.getDataNascimento());
				}
				return user;
			}
		}
		return u;
	}

	public void excluirAmigo(User user, User amigo) {
		if (!user.getLogado()) {
		}
		User loggedUser = getUserByEmail(user);
		 ArrayList<User> amigos = loggedUser.getAmigos();
		 amigos.remove(amigos.indexOf(amigo));	
	}
}
