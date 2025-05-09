package connection;

import java.sql.Connection;
import java.sql.DriverManager;

//so pode ter uma conexao com o banco de dados
//o são abertas e fechadas são sessões e transações
public class SingleConnetionBanco {
	
	private static String url = 
				"jdbc:oracle:thin:@localhost:1521:XE";
	//"jdbc:oracle:thin//@localhost:1521/admin"+

	private static Connection connection = null;
	
	
	
	static {
		conectar();
	}
	
	public SingleConnetionBanco(){ //quando tiver uma instancia vai conectar
		conectar();
	}
	
	public static Connection getConnection() {
		return connection;
	}
	
	private static void conectar() {
		try {
			if(connection == null) {
				//Class.forName()
				Class.forName("oracle.jdbc.driver.OracleDriver");
				connection = DriverManager.getConnection(url,"system", "admin123");
				connection.setAutoCommit(false);//para nao efetuar alteracoes no banco sem o nosso comando
				
			}
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	
}
