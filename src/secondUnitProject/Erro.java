package secondUnitProject;

public class Erro {
	
	String message = "";
	
	public Erro(String msg) {
		this.message = msg;
	}
	
	public Erro() {
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message += message;
	}
	
	public void newMessage(String message) {
		this.message = "Ocorreu o seguinte erro: ";
		this.message += "alguns dos convidados não fazem parte da sua lista de amigos.";
	}
	

}
