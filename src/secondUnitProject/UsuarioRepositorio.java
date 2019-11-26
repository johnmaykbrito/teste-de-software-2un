package secondUnitProject;

import java.util.ArrayList;
import java.util.List;

public class UsuarioRepositorio {

	private List<Usuario> usuarios;
	
	public UsuarioRepositorio() {
		this.usuarios = new ArrayList<Usuario>();
	}
	
	public boolean addUsuario(Usuario u) {
		try {
			this.usuarios.add(u);
		} catch (Exception ex) {
			return false;
		}
		return true;
	}
	
	public Usuario buscarPorCPF(String cpf) {
		Usuario u = null;
		for (Usuario user : this.usuarios) {
			if (user.getCpf().equals(cpf)) {
				u = user;
			}
		}		
		return u;
	}
	
	public List<Usuario> buscarTodos() {
		return this.usuarios;
	}
	
	public boolean deletarPorCPF(String cpf) {
		Usuario u = this.buscarPorCPF(cpf);
		try {
			return this.usuarios.remove(u);
		} catch (Exception ex) {
			return false;
		}
	}

	public boolean atualizarUsuario(Usuario u) {
		Usuario uRepo = this.buscarPorCPF(u.getCpf());
		if (uRepo != null) {
			u = uRepo;
			return true;
		}
		return false;
	}

	public List<Usuario> getUsuariosPorNomeParcial(String nomeParcial) {
		List<Usuario> usuariosARetornar = new ArrayList<Usuario>();
		for (Usuario u : this.usuarios) {
			if (u.getNome().contains(nomeParcial)) 
				usuariosARetornar.add(u);
		}
		return usuariosARetornar;
	}
}
