package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DAOLoginRepository;
import dao.DaoUsuarioRepository;
import model.ModelLogin;

/* O chamdo controller são as servlets ou servletLoginController*/

@WebServlet(urlPatterns = { "/principal/ServletLogin", "/ServletLogin" })
public class ServletLogin extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private DAOLoginRepository daoLoginRepository = new DAOLoginRepository();
	private DaoUsuarioRepository daoUsuarioRepository = new DaoUsuarioRepository();

	public ServletLogin() {
		super();

	}

	/* Recebe os dados pela url em parametos */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String acao = request.getParameter("acao");
		
		if(acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("logout")) {
			request.getSession().invalidate();//invalida a sessão;
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
			requestDispatcher.forward(request, response);
		}else {
			doPost(request, response);
		}
		
		
		//System.out.println("sss");
	}

	/* Recebe os dados enviados por formulario */
	/* request tudo que vem da tela */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		String url = request.getParameter("url");

		Boolean isValidLogin = login != null && !login.isEmpty() && senha != null && !senha.isEmpty();

		try {
			if (isValidLogin) {
				ModelLogin modelLogin = new ModelLogin(login, senha);

				if (daoLoginRepository.validarAutenticacao(modelLogin)) {/* Simulando login */
					
					modelLogin = daoUsuarioRepository.consultaUsuarioLogado(login);
					
					request.getSession().setAttribute("usuario", modelLogin.getLogin());
					request.getSession().setAttribute("perfil", modelLogin.getPerfil());
					request.getSession().setAttribute("imagemUser", modelLogin.getFotoUser());

					if (url == null || url.equals("null")) {
						url = "principal/principal.jsp";
					}
					RequestDispatcher requestDispatcher = request.getRequestDispatcher(url);
					requestDispatcher.forward(request, response);

				} else {

					RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
					request.setAttribute("msg", "Informe o login e senha corretamente!");
					requestDispatcher.forward(request, response);
				}
			} else {
				RequestDispatcher redirecionar = request.getRequestDispatcher("index.jsp");
				request.setAttribute("msg", "Informe o login e senha corretamente!");
				redirecionar.forward(request, response);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("Erro.jsp");
			request.setAttribute("msg",e.getMessage());
			requestDispatcher.forward(request, response);
		}
	}
}
