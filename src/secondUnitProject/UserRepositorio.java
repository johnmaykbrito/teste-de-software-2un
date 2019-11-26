package secondUnitProject;

import java.util.ArrayList;
import java.util.List;

public class UserRepositorio {
	private List<User> users;
	
	public UserRepositorio() {
		this.users = new ArrayList<User>();
	}
	
	public boolean addUser(User user) {
		try {
			this.users.add(user);		
		} catch (Exception ex) {
			return false;
		}
		return true;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
	
	

}
