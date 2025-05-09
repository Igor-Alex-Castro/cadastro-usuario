package model;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

//Serializable serve par n dar erro de comp
public class ModelLogin implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	private String nome;
	private String email;
	private String login;
	private String senha;

	private Date dataNascimento;

	private Double rendaMensal;

	private boolean userAdmin;

	private String perfil;

	private String sexo;

	private String fotoUser;

	private String extensaoFotoUser;

	private String cep;

	private String logradouro;

	private String bairro;

	private String localidade;

	private String uf;

	private String numero;
	
	private List<ModelFone> listFone = new ArrayList<ModelFone>();
	
	public void setListFone(List<ModelFone> listFone) {
		this.listFone = listFone;
	}
	
	public List<ModelFone> getListFone() {
		return listFone;
	}

	public boolean isNovo() {
		if (this.id == null) {
			return true;// inserir novo;
		} else if (this.id != null && this.id > 0) {
			return false; // atualiza;
		}

		return id == null;

	}

	public ModelLogin() {

	}

	public ModelLogin(String login, String senha) {
		this.login = login;
		this.senha = senha;
	}

	public ModelLogin(Long id, String nome, String email, String login, String senha, String perfil, String sexo) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.login = login;
		this.senha = senha;
		this.perfil = perfil;
		this.sexo = sexo;
	}

	public ModelLogin(Long id, String nome, String email, String login, String senha, String perfil, String sexo,
			String cep, String logradouro, String bairro, String localidade, String uf, String numero) {

		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.login = login;
		this.senha = senha;
		this.perfil = perfil;
		this.sexo = sexo;

		this.cep = cep;
		this.logradouro = logradouro;
		this.bairro = bairro;
		this.localidade = localidade;
		this.uf = uf;
		this.numero = numero;

	}

	public Double getRendaMensal() {
		return rendaMensal;
	}

	public void setRendaMensal(Double rendaMensal) {
		this.rendaMensal = rendaMensal;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
	

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getFotoUser() {
		return fotoUser;
	}

	public void setFotoUser(String fotoUser) {
		this.fotoUser = fotoUser;
	}

	public String getExtensaoFotoUser() {
		return extensaoFotoUser;
	}

	public void setExtensaoFotoUser(String extensaoFotoUser) {
		this.extensaoFotoUser = extensaoFotoUser;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public boolean getUserAdmin() {
		return userAdmin;
	}

	public void setUserAdmin(boolean userAdmin) {
		this.userAdmin = userAdmin;
	}
	
	public String getMostraTelefoneRel() {
		String fone = "Telefones:\n\n";
		
		for (ModelFone modelFone : listFone) {
			
			fone += modelFone.getNumero() + "\n";
		}
		
		return fone;
	}

}
