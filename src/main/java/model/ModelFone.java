package model;

import java.io.Serializable;
import java.util.Objects;

public class ModelFone  implements Serializable{

	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String numero;
	
	private ModelLogin loginPaiId;
	
	private ModelLogin loginCadId;
	
	
	public ModelFone() {
		
	}

	public ModelFone(Long id, String numero, ModelLogin loginPaiId, ModelLogin loginCadId) {
		super();
		this.id = id;
		this.numero = numero;
		this.loginPaiId = loginPaiId;
		this.loginCadId = loginCadId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public ModelLogin getLoginPaiId() {
		return loginPaiId;
	}

	public void setLoginPaiId(ModelLogin loginPaiId) {
		this.loginPaiId = loginPaiId;
	}

	public ModelLogin getLoginCadId() {
		return loginCadId;
	}

	public void setLoginCadId(ModelLogin loginCadId) {
		this.loginCadId = loginCadId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ModelFone other = (ModelFone) obj;
		return Objects.equals(id, other.id);
	}
	
	
	
	
	
}
