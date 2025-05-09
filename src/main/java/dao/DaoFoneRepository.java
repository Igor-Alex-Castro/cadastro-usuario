package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.SingleConnetionBanco;
import model.ModelFone;

public class DaoFoneRepository {
	
	private Connection connection;
	
	private DaoUsuarioRepository daoUsuarioRepository = new DaoUsuarioRepository();
	
	 public DaoFoneRepository() {
		connection = SingleConnetionBanco.getConnection();
	}
	
	public Long getMaxId() throws Exception {

		String sql = "SELECT seq_fone.nextval FROM fone";

		PreparedStatement statement = connection.prepareStatement(sql);

		ResultSet resultSet = statement.executeQuery();
		if (resultSet.next()) {
			return  (long) resultSet.getInt(1);/* autenticado */
		}
		return 0L;

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
			modelFone.setLoginPaiId(daoUsuarioRepository.consultaUsuarioId(rs.getLong("LOGIN_PAI_ID")));
			modelFone.setLoginCadId(daoUsuarioRepository.consultaUsuarioId(rs.getLong("LOGIN_CAD_ID")));
			
			retorno.add(modelFone);
		}
		
		return retorno;
		
		
		
	}
	
	public void gravaFone(ModelFone modelFone) throws Exception {
		
		Long maxId = getMaxId();
		
		String sql = "INSERT INTO FONE(ID_FONE, NUMERO, LOGIN_PAI_ID, LOGIN_CAD_ID) VALUES (?,?,?,?)";
		
		
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		
		
		preparedStatement.setLong(1, maxId );
		preparedStatement.setString(2, modelFone.getNumero());
		preparedStatement.setLong(3, modelFone.getLoginPaiId().getId());
		preparedStatement.setLong(4, modelFone.getLoginCadId().getId());
		
		preparedStatement.execute();
		
		connection.commit();
		
	}
	
	public void deleteFone(Long id) throws SQLException {
		String sql = "delete from fone where id_fone = ?";
		

		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		
		
		preparedStatement.setLong(1, id );

		
		preparedStatement.executeUpdate();
		
		connection.commit();
		
	}
	
	
	public boolean existeFone(String fone, Long idUser) throws Exception {
		String sql = "SELECT COUNT(ID_FONE) AS QUANT FROM FONE WHERE LOGIN_PAI_ID = ? AND NUMERO = ?";

		PreparedStatement statement = connection.prepareStatement(sql);

		statement.setLong(1, idUser);
		statement.setString(2, fone);
		
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
}
