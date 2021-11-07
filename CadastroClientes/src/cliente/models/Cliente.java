package cliente.models;

public class Cliente {
	private String id;
	private String nome;
	private String apelido;
	private String email;
	private String telefone;
	
	
	
	public Cliente(String nome, String apelido) {
		this.nome = nome;
		this.apelido = apelido;
	}
	
	public String getId() {
		return id;
	}	
	public void setId(String id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getApelido() {
		return apelido;
	}
	public void setApelido(String apelido) {
		this.apelido = apelido;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public String getNomeCompleto() {
		return nome + " " + apelido;
	}
	

}
