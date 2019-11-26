package secondUnitProject;

import java.util.ArrayList;

public class ErroConvidados extends Erro {

	ArrayList<User> users;
	
	public ErroConvidados() {
		this.users = new ArrayList<User>();
	}

	public ArrayList<User> getUsers() {
		return users != null ? users : new ArrayList<User>();
	}

	public void setUsers(ArrayList<User> users) {
		this.users = users;
	}
	
}
