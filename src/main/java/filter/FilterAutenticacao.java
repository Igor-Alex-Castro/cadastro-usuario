package filter;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import connection.SingleConnetionBanco;
import dao.DaoVersionadorBanco;

@WebFilter(urlPatterns = { "/principal/*" })
public class FilterAutenticacao extends HttpFilter implements Filter {

	private static Connection connection;

	public FilterAutenticacao() {
		super();

	}

	/* Encerra os processo quando o servido é parado */

	/* Mataria os processos de conexão com o banco */
	public void destroy() {
		try {
			connection.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}
	// Intercepta as reqsuisicoes e as respostas no sistmema*/
	/* Tudo que fizer no sistema vai fazer por aqui */
	/* Validação de autencicacao */
	/* Dar commit e rolback de transações do banco */
	/* Validar e fazer redirecionamento de paginas */

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		try {
			HttpServletRequest req = (HttpServletRequest) request;
			HttpSession session = req.getSession();

			String usuarioLogado = (String) session.getAttribute("usuario");
			String urlParaAuntenticar = req.getServletPath();// Url que esta sendo acessada*/

			// validar se usuario esta logado
			if (usuarioLogado == null || (usuarioLogado != null && usuarioLogado.isEmpty())
					&& !urlParaAuntenticar.contains("/principal/ServletLogin")) {

				RequestDispatcher requestDispatcher = request
						.getRequestDispatcher("/index.jsp?url=" + urlParaAuntenticar);
				request.setAttribute("msg", "Por favor realize o login!");
				requestDispatcher.forward(request, response);
				return;

			} else {
				chain.doFilter(request, response);
			}

			connection.commit();/* Deu tudo certo commita as alterações no banco de dados */

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();

			RequestDispatcher requestDispatcher = request.getRequestDispatcher("Erro.jsp");
			request.setAttribute("msg", e.getMessage());
			requestDispatcher.forward(request, response);
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	/* Inicia os processos ou recursos quando o servido sobe o projeto */
	// iniciar conexão com o banco
	public void init(FilterConfig fConfig) throws ServletException {
		connection = SingleConnetionBanco.getConnection();

		DaoVersionadorBanco daoVersionadorBanco = new DaoVersionadorBanco();

		String caminhoPastaSQL = fConfig.getServletContext().getRealPath("versionamentoBancoSql") + File.separator;

		File[] filesSQL = new File(caminhoPastaSQL).listFiles();

		try {
			for (File file : filesSQL) {
				boolean arquivoJavaRodado = daoVersionadorBanco.arquivoSqlRodado(file.getName());
				
				if(!arquivoJavaRodado) {
					FileInputStream entradaArquivo = new FileInputStream(file);
					Scanner lerArquivo = new Scanner(entradaArquivo, "UTF-8");
					
					StringBuilder sql = new StringBuilder();
					while(lerArquivo.hasNext()) {
						sql.append(lerArquivo.nextLine());
						sql.append("\n");
					}
					
					connection.prepareStatement(sql.toString()).execute();
					
					daoVersionadorBanco.gravaArquivoSqlRodado(file.getName());
					connection.commit();
					lerArquivo.close();
				}
			}
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}

}
