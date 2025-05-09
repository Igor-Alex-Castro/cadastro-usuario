package servlets;

import java.io.Serializable;
import java.sql.SQLException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import dao.DaoUsuarioRepository;
import model.ModelLogin;

public class ServletGenerecUtil extends HttpServlet	implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	private DaoUsuarioRepository daoUsuarioRepository =  new DaoUsuarioRepository();
	
	
	public Long getUserLogado(HttpServletRequest request) throws SQLException {
		
	
		HttpSession session = request.getSession();

		String usuarioLogado = (String) session.getAttribute("usuario");
		
		return daoUsuarioRepository.consultaUsuarioLogado(usuarioLogado).getId();
		
	}
	
	public ModelLogin getUserLogadoObjeto(HttpServletRequest request) throws Exception {
		
		
		HttpSession session = request.getSession();

		String usuarioLogado = (String) session.getAttribute("usuario");
		
		return daoUsuarioRepository.consultaUsuarioLogado(usuarioLogado);
		
	}
	
}
