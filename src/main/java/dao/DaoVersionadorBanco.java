package dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import connection.SingleConnetionBanco;

public class DaoVersionadorBanco implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Connection connection;
	
	public DaoVersionadorBanco() {
		connection = SingleConnetionBanco.getConnection();
	}
	
	public int getMaxId() throws Exception {

		String sql = "SELECT VERSIONADORBANCO_SEQ.NEXTVAL FROM VERSIONADORBANCO";

		PreparedStatement statement = connection.prepareStatement(sql);

		ResultSet resultSet = statement.executeQuery();
		if (resultSet.next()) {
			return (int) resultSet.getInt(1);/* autenticado */
		}
		return 0;

	}
	
	public void gravaArquivoSqlRodado(String nomeFile) throws Exception {
		String sql = "INSERT INTO VERSIONADORBANCO (ID, ARQUIVO_SQL) VALUES (?, ?)";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		
		int maxId = getMaxId();

		preparedStatement.setInt(1, maxId);
		preparedStatement.setString(2, nomeFile);
		
		preparedStatement.execute();
		
	}
	
	public boolean arquivoSqlRodado(String nomeArquivo) throws Exception {
		String sql = "SELECT COUNT( ARQUIVO_SQL) AS RODADO FROM VERSIONADORBANCO WHERE ARQUIVO_SQL = ?" ;
		
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		
		preparedStatement.setString(1, nomeArquivo);
		
		ResultSet resultSet = preparedStatement.executeQuery();
		
		
		int quant = 0;
		if(resultSet.next()) {
			quant = resultSet.getInt("RODADO");
		}
		
		
		if(quant > 0) {
			return true;
		}else {
			return false;
		}
		
	
	}

}
