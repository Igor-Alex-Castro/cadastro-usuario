package dao;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import beanDTO.BeanDTOGraficoSalarioUser;
import connection.SingleConnetionBanco;
import model.ModelFone;
import model.ModelLogin;

public class DaoUsuarioRepository {

	private Connection connection;
	
	

	public DaoUsuarioRepository() {
		// TODO Auto-generated constructor stub
		connection = SingleConnetionBanco.getConnection();
	}
	
	public BeanDTOGraficoSalarioUser montarGraficoMediaSalario(Long userLogado, String dataInicial, String dataFinal) throws Exception {
		String sql = "SELECT AVG(RENDA_MENSAL) AS MEDIA_SALARIAL, PERFIL FROM LOGIN WHERE USUARIO_ID =  ? AND DATA_NASCIMENTO >= TO_DATE(?) AND DATA_NASCIMENTO <= TO_DATE(?) GROUP BY PERFIL";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		
		preparedStatement.setLong(1, userLogado);
		preparedStatement.setDate(2, Date.valueOf(new SimpleDateFormat("yyyy-mm-dd").format(new SimpleDateFormat("dd/mm/yy").parse(dataInicial))));
		preparedStatement.setDate(3, Date.valueOf(new SimpleDateFormat("yyyy-mm-dd").format(new SimpleDateFormat("dd/mm/yy").parse(dataFinal))));
		
		ResultSet resultSet = preparedStatement.executeQuery();
		
		List<String> perfils = new ArrayList<String>();
		List<Double> salarios = new ArrayList<Double>();
		
		BeanDTOGraficoSalarioUser beanDTOGraficoSalarioUser = new BeanDTOGraficoSalarioUser();
		
		while(resultSet.next()) {
			Double mediaSalarial = resultSet.getDouble("MEDIA_SALARIAL");
			String perfil =  resultSet.getString("PERFIL") ;
			
			salarios.add(mediaSalarial);
			perfils.add(perfil);
			
		}
		
		beanDTOGraficoSalarioUser.setPerfils(perfils);
		beanDTOGraficoSalarioUser.setSalarios(salarios);
		
		return beanDTOGraficoSalarioUser;
	}

	
	public BeanDTOGraficoSalarioUser montarGraficoMediaSalario(Long usuarioLogado) throws Exception {
		String sql = "SELECT AVG(RENDA_MENSAL) AS MEDIA_SALARIAL, PERFIL FROM LOGIN WHERE USUARIO_ID =  ? GROUP BY PERFIL";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		
		preparedStatement.setLong(1, usuarioLogado);
		
		ResultSet resultSet = preparedStatement.executeQuery();
		
		List<String> perfils = new ArrayList<String>();
		List<Double> salarios = new ArrayList<Double>();
		
		BeanDTOGraficoSalarioUser beanDTOGraficoSalarioUser = new BeanDTOGraficoSalarioUser();
		
		while(resultSet.next()) {
			
			Double mediaSalarial = resultSet.getDouble("MEDIA_SALARIAL");
			String perfil =  resultSet.getString("PERFIL") ;
			
			salarios.add(mediaSalarial);
			perfils.add(perfil);
			
		}
		
		beanDTOGraficoSalarioUser.setPerfils(perfils);
		beanDTOGraficoSalarioUser.setSalarios(salarios);
		
		return beanDTOGraficoSalarioUser;
		
	}

	public int getMaxId() throws Exception {

		String sql = "SELECT id_login_seq.nextval FROM LOGIN";

		PreparedStatement statement = connection.prepareStatement(sql);

		ResultSet resultSet = statement.executeQuery();
		if (resultSet.next()) {
			return (int) resultSet.getInt(1);/* autenticado */
		}
		return 0;

	}

	public ModelLogin gravarUsuario(ModelLogin modelLogin, Long userLogado) throws Exception {

		if (modelLogin.isNovo()) {


			String sql = "INSERT INTO LOGIN(id, NOME, EMAIL, LOGIN, SENHA, USUARIO_ID, PERFIL, SEXO, CEP, LOGRADOURO, BAIRRO, LOCALIDADE, UF, NUMERO, DATA_NASCIMENTO, RENDA_MENSAL ) " + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?, ?)";

			PreparedStatement preparedStatement = connection.prepareStatement(sql);

			int maxId = getMaxId();

			preparedStatement.setInt(1, maxId);
			preparedStatement.setString(2, modelLogin.getNome());
			preparedStatement.setString(3, modelLogin.getEmail());
			preparedStatement.setString(4, modelLogin.getLogin());
			preparedStatement.setString(5, modelLogin.getSenha());
			preparedStatement.setLong(6, userLogado);
			preparedStatement.setString(7, modelLogin.getPerfil());
			preparedStatement.setString(8, modelLogin.getSexo());
			preparedStatement.setString(9, modelLogin.getCep());
			preparedStatement.setString(10, modelLogin.getLogradouro());
			preparedStatement.setString(11, modelLogin.getBairro());
			preparedStatement.setString(12, modelLogin.getLocalidade());
			preparedStatement.setString(13, modelLogin.getUf());
			preparedStatement.setString(14, modelLogin.getNumero());
			preparedStatement.setDate(15, modelLogin.getDataNascimento());
			preparedStatement.setDouble(16, modelLogin.getRendaMensal());

			preparedStatement.execute();

			connection.commit();
			
			if(modelLogin.getFotoUser() != null && !modelLogin.getFotoUser().isEmpty())  {
				sql = "UPDATE LOGIN SET FOTOUSER =?, EXTENSAOFOTOUSER=? WHERE ID =  ?"; 
				preparedStatement =  connection.prepareStatement(sql);
				
				preparedStatement.setString(1, modelLogin.getFotoUser());
				preparedStatement.setString(2, modelLogin.getExtensaoFotoUser());
				preparedStatement.setLong(3,  maxId);
				
				preparedStatement.execute();
				
				connection.commit();
				
				
				
			}
			
		}else {
			String sql = "UPDATE LOGIN SET NOME=?, EMAIL=?, LOGIN=?, SENHA=?, PERFIL=?, SEXO = ?, CEP = ?, LOGRADOURO = ?, BAIRRO =?, LOCALIDADE=?, UF=?, NUMERO=?, DATA_NASCIMENTO=?, RENDA_MENSAL=? WHERE ID = "+ modelLogin.getId() + "";
			
		    PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
		    preparedStatement.setString(1, modelLogin.getNome());
			preparedStatement.setString(2, modelLogin.getEmail());
			preparedStatement.setString(3, modelLogin.getLogin());
			preparedStatement.setString(4, modelLogin.getSenha());
			preparedStatement.setString(5, modelLogin.getPerfil());
			preparedStatement.setString(6, modelLogin.getSexo());
			preparedStatement.setString(7, modelLogin.getCep());
			preparedStatement.setString(8, modelLogin.getLogradouro());
			preparedStatement.setString(9, modelLogin.getBairro());
			preparedStatement.setString(10, modelLogin.getLocalidade());
			preparedStatement.setString(11, modelLogin.getUf());
			preparedStatement.setString(12, modelLogin.getNumero());
			preparedStatement.setDate(13, modelLogin.getDataNascimento());
			preparedStatement.setDouble(14, modelLogin.getRendaMensal() == null ? 0 : modelLogin.getRendaMensal() );
			
			preparedStatement.executeUpdate();
			
			connection.commit();
			
		}

		return this.consultaUsuario(modelLogin.getLogin(), userLogado);

	}
	
	public int consultaUsuarioListTotalPaginacao(String nome, Long userLogado ) throws Exception{
		
		
		String sql = "SELECT COUNT(1) AS TOTAL FROM LOGIN WHERE UPPER(NOME) LIKE UPPER(?) AND USER_ADMIN = 0 AND USUARIO_ID = ?" ;
		
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
	
		preparedStatement.setString(1, "%" + nome + "%");
		preparedStatement.setLong(2,  userLogado);
		
		ResultSet resultSet = preparedStatement.executeQuery();
		
		resultSet.next();
		
		Double  cadastros = resultSet.getDouble("TOTAL");
		
		Double porpagina = 5.0;
		
		Double pagina = cadastros/ porpagina;
		
		Double resto = pagina % 2;
		
		if(resto > 0) {
			pagina++;
		}
		
		return pagina.intValue();
		
	}
	
	
	public List<ModelLogin> consultaUsuarioList(String nome, Long userLogado ) throws Exception{
		List<ModelLogin> retorno = new ArrayList<ModelLogin>();
		
		String sql = "SELECT * FROM LOGIN WHERE UPPER(NOME) LIKE UPPER(?) AND USER_ADMIN = 0 AND USUARIO_ID = ? AND ROWNUM <= 5 ORDER BY ID  " ;
		
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
	
		preparedStatement.setString(1, "%" + nome + "%");
		preparedStatement.setLong(2,  userLogado);
		
		ResultSet resultSet = preparedStatement.executeQuery();
		
		while(resultSet.next()) {//percorrer as linhas de resultado do SQL;
			
			ModelLogin modelLogin =  new ModelLogin();
			
			modelLogin.setEmail(resultSet.getString("email"));
			modelLogin.setId(resultSet.getLong("id"));
			modelLogin.setLogin(resultSet.getString("login"));
			modelLogin.setNome(resultSet.getString("nome"));
			//modelLogin.setSenha(resultSet.getString("senha"));
			modelLogin.setPerfil(resultSet.getString("perfil"));
			modelLogin.setSexo(resultSet.getString("sexo"));
			modelLogin.setFotoUser(resultSet.getString("FOTOUSER"));
			modelLogin.setExtensaoFotoUser(resultSet.getString("EXTENSAOFOTOUSER"));
			modelLogin.setCep(resultSet.getString("CEP"));
			modelLogin.setLogradouro(resultSet.getString("LOGRADOURO"));
			modelLogin.setBairro(resultSet.getString("BAIRRO"));
			modelLogin.setLocalidade(resultSet.getString("LOCALIDADE"));
			modelLogin.setUf(resultSet.getString("UF"));
			modelLogin.setNumero(resultSet.getString("NUMERO"));
			modelLogin.setDataNascimento(resultSet.getDate("DATA_NASCIMENTO"));
			modelLogin.setRendaMensal(resultSet.getDouble("RENDA_MENSAL"));
			
			
			retorno.add(modelLogin);
		}
		
		return retorno;
	}
	
	public List<ModelLogin> consultaUsuarioListOffiset(String nome, Long userLogado, String Offiset ) throws Exception{
		List<ModelLogin> retorno = new ArrayList<ModelLogin>();
		
		String sql = "SELECT * FROM LOGIN WHERE UPPER(NOME) LIKE UPPER(?) AND USER_ADMIN = 0 AND USUARIO_ID = ?  ORDER BY ID OFFSET ? ROWS FETCH NEXT 5 ROWS ONLY" ;
		
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
	
		preparedStatement.setString(1, "%" + nome + "%");
		preparedStatement.setLong(2,  userLogado);
		preparedStatement.setString(3,  Offiset);
		
		ResultSet resultSet = preparedStatement.executeQuery();
		
		while(resultSet.next()) {//percorrer as linhas de resultado do SQL;
			
			ModelLogin modelLogin =  new ModelLogin();
			
			modelLogin.setEmail(resultSet.getString("email"));
			modelLogin.setId(resultSet.getLong("id"));
			modelLogin.setLogin(resultSet.getString("login"));
			modelLogin.setNome(resultSet.getString("nome"));
			//modelLogin.setSenha(resultSet.getString("senha"));
			modelLogin.setPerfil(resultSet.getString("perfil"));
			modelLogin.setSexo(resultSet.getString("sexo"));
			modelLogin.setFotoUser(resultSet.getString("FOTOUSER"));
			modelLogin.setExtensaoFotoUser(resultSet.getString("EXTENSAOFOTOUSER"));
			modelLogin.setCep(resultSet.getString("CEP"));
			modelLogin.setLogradouro(resultSet.getString("LOGRADOURO"));
			modelLogin.setBairro(resultSet.getString("BAIRRO"));
			modelLogin.setLocalidade(resultSet.getString("LOCALIDADE"));
			modelLogin.setUf(resultSet.getString("UF"));
			modelLogin.setNumero(resultSet.getString("NUMERO"));
			modelLogin.setDataNascimento(resultSet.getDate("DATA_NASCIMENTO"));
			modelLogin.setRendaMensal(resultSet.getDouble("RENDA_MENSAL"));
			
			
			retorno.add(modelLogin);
		}
		
		return retorno;
	}
	
	public int totalPagina(Long userLogado) throws SQLException {
		
		String sql = "SELECT COUNT(1) AS CADASTROS FROM LOGIN WHERE USUARIO_ID = " + userLogado ;
		PreparedStatement statement = connection.prepareStatement(sql);
		
		ResultSet resultSet = statement.executeQuery();
		
		resultSet.next();
		
		Double  cadastros = resultSet.getDouble("CADASTROS");
		
		Double porpagina = 5.0;
		
		Double pagina = cadastros/ porpagina;
		
		Double resto = pagina % 2;
		
		if(resto > 0) {
			pagina++;
		}
		
		return pagina.intValue();
		
	}
	public List<ModelLogin> consultaUsuarioList(Long userLogado) throws Exception{
		List<ModelLogin> retorno = new ArrayList<ModelLogin>();
		
		String sql = "SELECT * FROM LOGIN WHERE USER_ADMIN = 0 AND USUARIO_ID =  " + userLogado + " AND ROWNUM <= 5 ORDER BY ID ";
		
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
	
		
		ResultSet resultSet = preparedStatement.executeQuery();
		
		while(resultSet.next()) {//percorrer as linhas de resultado do SQL;
			
			ModelLogin modelLogin =  new ModelLogin();
			
			modelLogin.setEmail(resultSet.getString("email"));
			modelLogin.setId(resultSet.getLong("id"));
			modelLogin.setLogin(resultSet.getString("login"));
			modelLogin.setNome(resultSet.getString("nome"));
			//modelLogin.setSenha(resultSet.getString("senha"));
			modelLogin.setPerfil(resultSet.getString("perfil"));
			modelLogin.setSexo(resultSet.getString("sexo"));
			modelLogin.setFotoUser(resultSet.getString("FOTOUSER"));
			modelLogin.setExtensaoFotoUser(resultSet.getString("EXTENSAOFOTOUSER"));
			modelLogin.setCep(resultSet.getString("CEP"));
			modelLogin.setLogradouro(resultSet.getString("LOGRADOURO"));
			modelLogin.setBairro(resultSet.getString("BAIRRO"));
			modelLogin.setLocalidade(resultSet.getString("LOCALIDADE"));
			modelLogin.setUf(resultSet.getString("UF"));
			modelLogin.setNumero(resultSet.getString("NUMERO"));
			modelLogin.setDataNascimento(resultSet.getDate("DATA_NASCIMENTO"));
			modelLogin.setRendaMensal(resultSet.getDouble("RENDA_MENSAL"));
			
			
			retorno.add(modelLogin);
		}
		
		return retorno;
	}
	
	public List<ModelLogin> consultaUsuarioListRel(Long userLogado) throws Exception{
		List<ModelLogin> retorno = new ArrayList<ModelLogin>();
		
		String sql = "SELECT * FROM LOGIN WHERE USER_ADMIN = 0 AND USUARIO_ID =  " + userLogado;
		
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
	
		
		ResultSet resultSet = preparedStatement.executeQuery();
		
		while(resultSet.next()) {//percorrer as linhas de resultado do SQL;
			
			ModelLogin modelLogin =  new ModelLogin();
			
			modelLogin.setEmail(resultSet.getString("email"));
			modelLogin.setId(resultSet.getLong("id"));
			modelLogin.setLogin(resultSet.getString("login"));
			modelLogin.setNome(resultSet.getString("nome"));
			//modelLogin.setSenha(resultSet.getString("senha"));
			modelLogin.setPerfil(resultSet.getString("perfil"));
			modelLogin.setSexo(resultSet.getString("sexo"));
			modelLogin.setFotoUser(resultSet.getString("FOTOUSER"));
			modelLogin.setExtensaoFotoUser(resultSet.getString("EXTENSAOFOTOUSER"));
			modelLogin.setCep(resultSet.getString("CEP"));
			modelLogin.setLogradouro(resultSet.getString("LOGRADOURO"));
			modelLogin.setBairro(resultSet.getString("BAIRRO"));
			modelLogin.setLocalidade(resultSet.getString("LOCALIDADE"));
			modelLogin.setUf(resultSet.getString("UF"));
			modelLogin.setNumero(resultSet.getString("NUMERO"));
			modelLogin.setDataNascimento(resultSet.getDate("DATA_NASCIMENTO"));
			modelLogin.setRendaMensal(resultSet.getDouble("RENDA_MENSAL"));
			
			modelLogin.setListFone(this.listFone(modelLogin.getId()));
			
			
			retorno.add(modelLogin);
		}
		
		return retorno;
	}
	
	
	public List<ModelLogin> consultaUsuarioListRel(Long userLogado, String dataInicial, String dataFinal) throws Exception{
		List<ModelLogin> retorno = new ArrayList<ModelLogin>();
		
		String sql = "SELECT * FROM LOGIN WHERE USER_ADMIN = 0 AND DATA_NASCIMENTO >= TO_DATE(?) AND DATA_NASCIMENTO <= TO_DATE(?) AND USUARIO_ID =  " + userLogado;
		
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setDate(1, Date.valueOf(new SimpleDateFormat("yyyy-mm-dd").format(new SimpleDateFormat("dd/mm/yy").parse(dataInicial))));
		preparedStatement.setDate(2, Date.valueOf(new SimpleDateFormat("yyyy-mm-dd").format(new SimpleDateFormat("dd/mm/yy").parse(dataFinal))));
		
		ResultSet resultSet = preparedStatement.executeQuery();
		
		while(resultSet.next()) {//percorrer as linhas de resultado do SQL;
			
			ModelLogin modelLogin =  new ModelLogin();
			
			modelLogin.setEmail(resultSet.getString("email"));
			modelLogin.setId(resultSet.getLong("id"));
			modelLogin.setLogin(resultSet.getString("login"));
			modelLogin.setNome(resultSet.getString("nome"));
			//modelLogin.setSenha(resultSet.getString("senha"));
			modelLogin.setPerfil(resultSet.getString("perfil"));
			modelLogin.setSexo(resultSet.getString("sexo"));
			modelLogin.setFotoUser(resultSet.getString("FOTOUSER"));
			modelLogin.setExtensaoFotoUser(resultSet.getString("EXTENSAOFOTOUSER"));
			modelLogin.setCep(resultSet.getString("CEP"));
			modelLogin.setLogradouro(resultSet.getString("LOGRADOURO"));
			modelLogin.setBairro(resultSet.getString("BAIRRO"));
			modelLogin.setLocalidade(resultSet.getString("LOCALIDADE"));
			modelLogin.setUf(resultSet.getString("UF"));
			modelLogin.setNumero(resultSet.getString("NUMERO"));
			modelLogin.setDataNascimento(resultSet.getDate("DATA_NASCIMENTO"));
			modelLogin.setRendaMensal(resultSet.getDouble("RENDA_MENSAL"));
			
			
			modelLogin.setListFone(this.listFone(modelLogin.getId()));
			
			
			retorno.add(modelLogin);
		}
		
		return retorno;
	}

	public List<ModelFone> listFone(Long idUserPai) throws Exception{
		
		List<ModelFone> retorno = new ArrayList<ModelFone>();
		String sql = "SELECT * FROM FONE WHERE LOGIN_PAI_ID = ?";
		
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setLong(1, idUserPai);
		
		ResultSet rs = preparedStatement.executeQuery();
		
		while(rs.next()) {
			ModelFone modelFone = new ModelFone();
			
			modelFone.setId(rs.getLong("ID_FONE"));
			modelFone.setNumero(rs.getString("NUMERO"));
			modelFone.setLoginPaiId(this.consultaUsuarioId(rs.getLong("LOGIN_PAI_ID")));
			modelFone.setLoginCadId(this.consultaUsuarioId(rs.getLong("LOGIN_CAD_ID")));
			
			retorno.add(modelFone);
		}
		
		return retorno;
		
		
		
	}
	public List<ModelLogin> consultaUsuarioList(Long userLogado, Integer offset) throws Exception{
		List<ModelLogin> retorno = new ArrayList<ModelLogin>();
		
		String sql = "SELECT * FROM LOGIN WHERE USER_ADMIN = 0 AND USUARIO_ID =  " + userLogado + " ORDER BY ID OFFSET " + offset  + " ROWS FETCH NEXT 5 ROWS ONLY";
		
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
	
		
		ResultSet resultSet = preparedStatement.executeQuery();
		
		while(resultSet.next()) {//percorrer as linhas de resultado do SQL;
			
			ModelLogin modelLogin =  new ModelLogin();
			
			modelLogin.setEmail(resultSet.getString("email"));
			modelLogin.setId(resultSet.getLong("id"));
			modelLogin.setLogin(resultSet.getString("login"));
			modelLogin.setNome(resultSet.getString("nome"));
			//modelLogin.setSenha(resultSet.getString("senha"));
			modelLogin.setPerfil(resultSet.getString("perfil"));
			modelLogin.setSexo(resultSet.getString("sexo"));
			modelLogin.setFotoUser(resultSet.getString("FOTOUSER"));
			modelLogin.setExtensaoFotoUser(resultSet.getString("EXTENSAOFOTOUSER"));
			modelLogin.setCep(resultSet.getString("CEP"));
			modelLogin.setLogradouro(resultSet.getString("LOGRADOURO"));
			modelLogin.setBairro(resultSet.getString("BAIRRO"));
			modelLogin.setLocalidade(resultSet.getString("LOCALIDADE"));
			modelLogin.setUf(resultSet.getString("UF"));
			modelLogin.setNumero(resultSet.getString("NUMERO"));
			modelLogin.setDataNascimento(resultSet.getDate("DATA_NASCIMENTO"));
			modelLogin.setRendaMensal(resultSet.getDouble("RENDA_MENSAL"));
			
			
			retorno.add(modelLogin);
		}
		
		return retorno;
	}
	
	public ModelLogin consultaUsuarioLogado(String login) throws SQLException {
		ModelLogin modelLogin = new ModelLogin();

		String sql = "SELECT  * FROM LOGIN WHERE UPPER(LOGIN) = UPPER('" + login + "')";

		PreparedStatement statement = connection.prepareStatement(sql);

		ResultSet result = statement.executeQuery();

		while (result.next()) {
			modelLogin.setId(result.getLong("id"));
			modelLogin.setEmail(result.getString("email"));
			modelLogin.setNome(result.getString("nome"));
			modelLogin.setLogin(result.getString("login"));
			modelLogin.setSenha(result.getString("senha"));
			modelLogin.setUserAdmin(result.getBoolean("user_admin"));
			modelLogin.setPerfil(result.getString("perfil"));
			modelLogin.setSexo(result.getString("sexo"));
			modelLogin.setFotoUser(result.getString("FOTOUSER"));
			modelLogin.setExtensaoFotoUser(result.getString("EXTENSAOFOTOUSER"));
			modelLogin.setCep(result.getString("CEP"));
			modelLogin.setLogradouro(result.getString("LOGRADOURO"));
			modelLogin.setBairro(result.getString("BAIRRO"));
			modelLogin.setLocalidade(result.getString("LOCALIDADE"));
			modelLogin.setUf(result.getString("UF"));
			modelLogin.setNumero(result.getString("NUMERO"));
			modelLogin.setDataNascimento(result.getDate("DATA_NASCIMENTO"));
			modelLogin.setRendaMensal(result.getDouble("RENDA_MENSAL"));
			
			
		}

		return modelLogin;

	}
	
	public ModelLogin consultaUsuario(String login) throws SQLException {
		ModelLogin modelLogin = new ModelLogin();

		String sql = "SELECT  * FROM LOGIN WHERE UPPER(LOGIN) = UPPER('" + login + "') AND USER_ADMIN = 0";

		PreparedStatement statement = connection.prepareStatement(sql);

		ResultSet result = statement.executeQuery();

		while (result.next()) {
			modelLogin.setId(result.getLong("id"));
			modelLogin.setEmail(result.getString("email"));
			modelLogin.setNome(result.getString("nome"));
			modelLogin.setLogin(result.getString("login"));
			modelLogin.setSenha(result.getString("senha"));
			modelLogin.setUserAdmin(result.getBoolean("user_admin"));
			modelLogin.setPerfil(result.getString("perfil"));
			modelLogin.setSexo(result.getString("sexo"));
			modelLogin.setFotoUser(result.getString("FOTOUSER"));
			modelLogin.setExtensaoFotoUser(result.getString("EXTENSAOFOTOUSER"));
			modelLogin.setCep(result.getString("CEP"));
			modelLogin.setLogradouro(result.getString("LOGRADOURO"));
			modelLogin.setBairro(result.getString(" BAIRRO"));
			modelLogin.setLocalidade(result.getString("LOCALIDADE"));
			modelLogin.setUf(result.getString("UF"));
			modelLogin.setNumero(result.getString("NUMERO"));
			modelLogin.setDataNascimento(result.getDate("DATA_NASCIMENTO"));
			modelLogin.setRendaMensal(result.getDouble("RENDA_MENSAL"));
		}

		return modelLogin;

	}
	

	public ModelLogin consultaUsuario(String login, Long userLogado) throws SQLException {
		ModelLogin modelLogin = new ModelLogin();

		String sql = "SELECT  * FROM LOGIN WHERE UPPER(LOGIN) = UPPER('" + login + "') AND USER_ADMIN = 0 AND USUARIO_ID = " + userLogado;

		PreparedStatement statement = connection.prepareStatement(sql);

		ResultSet result = statement.executeQuery();

		while (result.next()) {
			modelLogin.setId(result.getLong("id"));
			modelLogin.setEmail(result.getString("email"));
			modelLogin.setNome(result.getString("nome"));
			modelLogin.setLogin(result.getString("login"));
			modelLogin.setSenha(result.getString("senha"));
			modelLogin.setPerfil(result.getString("perfil"));
			modelLogin.setSexo(result.getString("sexo"));
			modelLogin.setFotoUser(result.getString("FOTOUSER"));
			modelLogin.setExtensaoFotoUser(result.getString("EXTENSAOFOTOUSER"));
			modelLogin.setCep(result.getString("CEP"));
			modelLogin.setLogradouro(result.getString("LOGRADOURO"));
			modelLogin.setBairro(result.getString("BAIRRO"));
			modelLogin.setLocalidade(result.getString("LOCALIDADE"));
			modelLogin.setUf(result.getString("UF"));
			modelLogin.setNumero(result.getString("NUMERO"));
			modelLogin.setDataNascimento(result.getDate("DATA_NASCIMENTO"));
			modelLogin.setRendaMensal(result.getDouble("RENDA_MENSAL"));
			
		}

		return modelLogin;

	}
	
	public ModelLogin consultaUsuarioId(String id, Long userLogin) throws SQLException {
		ModelLogin modelLogin = new ModelLogin();
		
		String sql = "SELECT  * FROM LOGIN WHERE ID = ? AND USER_ADMIN = 0 AND USUARIO_ID = ? ";
		System.out.println(userLogin);
		
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setLong(1, Long.parseLong(id));
		preparedStatement.setLong(2,  userLogin);
		
		ResultSet result = preparedStatement.executeQuery();
		
		while (result.next()) {
			modelLogin.setId(result.getLong("id"));
			modelLogin.setEmail(result.getString("email"));
			modelLogin.setNome(result.getString("nome"));
			modelLogin.setLogin(result.getString("login"));
			modelLogin.setSenha(result.getString("senha"));
			modelLogin.setPerfil(result.getString("perfil"));
			modelLogin.setSexo(result.getString("sexo"));
			modelLogin.setFotoUser(result.getString("FOTOUSER"));
			modelLogin.setExtensaoFotoUser(result.getString("EXTENSAOFOTOUSER"));
			modelLogin.setCep(result.getString("CEP"));
			modelLogin.setLogradouro(result.getString("LOGRADOURO"));
			modelLogin.setBairro(result.getString("uf"));
			modelLogin.setLocalidade(result.getString("LOCALIDADE"));
			modelLogin.setUf(result.getString("UF"));
			modelLogin.setNumero(result.getString("NUMERO"));
			modelLogin.setDataNascimento(result.getDate("DATA_NASCIMENTO"));
			modelLogin.setRendaMensal(result.getDouble("RENDA_MENSAL"));
		}

		return modelLogin;
		
	}
	
	public ModelLogin consultaUsuarioId(Long id) throws SQLException {
		ModelLogin modelLogin = new ModelLogin();
		
		String sql = "SELECT  * FROM LOGIN WHERE ID = ? AND USER_ADMIN = 0";
		
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setLong(1, (id));
		
		
		ResultSet result = preparedStatement.executeQuery();
		
		while (result.next()) {
			modelLogin.setId(result.getLong("id"));
			modelLogin.setEmail(result.getString("email"));
			modelLogin.setNome(result.getString("nome"));
			modelLogin.setLogin(result.getString("login"));
			modelLogin.setSenha(result.getString("senha"));
			modelLogin.setPerfil(result.getString("perfil"));
			modelLogin.setSexo(result.getString("sexo"));
			modelLogin.setFotoUser(result.getString("FOTOUSER"));
			modelLogin.setExtensaoFotoUser(result.getString("EXTENSAOFOTOUSER"));
			modelLogin.setCep(result.getString("CEP"));
			modelLogin.setLogradouro(result.getString("LOGRADOURO"));
			modelLogin.setBairro(result.getString("uf"));
			modelLogin.setLocalidade(result.getString("LOCALIDADE"));
			modelLogin.setUf(result.getString("UF"));
			modelLogin.setNumero(result.getString("NUMERO"));
			modelLogin.setDataNascimento(result.getDate("DATA_NASCIMENTO"));
			modelLogin.setRendaMensal(result.getDouble("RENDA_MENSAL"));
		}

		return modelLogin;
		
	}

	public boolean validarLogin(String login) throws Exception {
		String sql = "SELECT COUNT(LOGIN) AS QUANT FROM LOGIN WHERE LOGIN = '" + login + "'";

		PreparedStatement statement = connection.prepareStatement(sql);

		ResultSet resultSet = statement.executeQuery();

		int quant = 0;
		
		if(resultSet.next()) {
			quant = resultSet.getInt("QUANT");
		}
		
		
		if(quant > 0) {
			return true;
		}else {
			return false;
		}
		

	}
	
	public void deletarUser(String idUser) throws SQLException {
		String sql = "DELETE FROM LOGIN WHERE ID = ? AND USER_ADMIN = 0";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		
		preparedStatement.setLong(1, Long.parseLong(idUser));
		
		preparedStatement.executeUpdate();
		
		connection.commit();
	}

	
}
