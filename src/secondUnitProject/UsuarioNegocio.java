package secondUnitProject;

import java.util.List;

public class UsuarioNegocio {

	private UsuarioRepositorio userRepo;
	
	public UsuarioNegocio(UsuarioRepositorio userRepo) {
		this.userRepo = userRepo;
	}
	
	public boolean adicionarUsuario(Usuario u){
		boolean adicionado = false;
		if (u.getNome() != null && u.getIdade() >= 0 && 
				u.getCpf() != null &&
				this.userRepo.buscarPorCPF(u.getCpf()) == null) {
			adicionado = this.userRepo.addUsuario(u);
		}
		return adicionado;
	}
	
	public List<Usuario> relatorioUsuariosDados() {
		return this.userRepo.buscarTodos();
	}
	
	public boolean deletarUsuario(String cpf) {
		return this.userRepo.deletarPorCPF(cpf);
	}
	
	public boolean atualizarUsuario(Usuario u){		
		return this.userRepo.atualizarUsuario(u);
	}
	
	public List<Usuario> relatorioUsuariosPorNomeParcial(String nomeParcial) {
		return this.userRepo.getUsuariosPorNomeParcial(nomeParcial);
	}
}
