package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.SingleConnetionBanco;
import model.ModelLogin;

public class DAOLoginRepository {
	private Connection connection;
	
	public DAOLoginRepository() {
		connection = SingleConnetionBanco.getConnection();
	}
	
	public boolean  validarAutenticacao(ModelLogin modelLogin) throws SQLException {
		
		String sql = "SELECT * FROM LOGIN WHERE UPPER(LOGIN) = UPPER(?) AND UPPER(SENHA) = UPPER(?) ";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		
		statement.setString(1, modelLogin.getLogin());
		statement.setString(2,  modelLogin.getSenha());
		
		ResultSet resultSet = statement.executeQuery();
		if(resultSet.next()) {
			return true;/*autenticado*/
		}
		return false;
		
	}
}
