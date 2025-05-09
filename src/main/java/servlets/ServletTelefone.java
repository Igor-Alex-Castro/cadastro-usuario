package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoFoneRepository;
import dao.DaoUsuarioRepository;
import model.ModelFone;
import model.ModelLogin;

@WebServlet("/ServletTelefone")
public class ServletTelefone extends ServletGenerecUtil {

	private static final long serialVersionUID = 1L;

	private DaoUsuarioRepository daoUsuarioRepository = new DaoUsuarioRepository();
	private DaoFoneRepository daoFoneRepository = new DaoFoneRepository();

	public ServletTelefone() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {

			String acao = request.getParameter("acao");

			if (acao != null && !acao.isEmpty() && acao.equals("excluir")) {
				String idFone = request.getParameter("id");

				String userpai = request.getParameter("userpaiid");

				daoFoneRepository.deleteFone(Long.parseLong(idFone));

				ModelLogin modelLogin = daoUsuarioRepository.consultaUsuarioId(Long.parseLong(userpai));

				List<ModelFone> modelFones = daoFoneRepository.listFone(modelLogin.getId());

				request.setAttribute("modelTelefones", modelFones);
				request.setAttribute("modelLogin", modelLogin);
				request.setAttribute("msg", "Telefone excluido");
				request.getRequestDispatcher("principal/telefone.jsp").forward(request, response);

				return;

			}

			String idUser = request.getParameter("idUser");

			if (idUser != null && !idUser.isEmpty()) {

				ModelLogin modelLogin = daoUsuarioRepository.consultaUsuarioId(Long.parseLong(idUser));

				List<ModelFone> modelFones = daoFoneRepository.listFone(modelLogin.getId());

				request.setAttribute("modelTelefones", modelFones);
				request.setAttribute("modelLogin", modelLogin);
				request.getRequestDispatcher("principal/telefone.jsp").forward(request, response);

			} else {

				List<ModelLogin> modelLogins = daoUsuarioRepository.consultaUsuarioList(super.getUserLogado(request));
				request.setAttribute("modelLogins", modelLogins);
				request.setAttribute("totalPagin", daoUsuarioRepository.totalPagina(this.getUserLogado(request)));
				request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String IdUserPai = request.getParameter("id");
			String numero = request.getParameter("numero");

			if (!daoFoneRepository.existeFone(numero, Long.parseLong(IdUserPai))) {

				ModelFone modelFone = new ModelFone();

				modelFone.setNumero(numero);
				modelFone.setLoginPaiId(daoUsuarioRepository.consultaUsuarioId(Long.parseLong(IdUserPai)));
				modelFone.setLoginCadId(super.getUserLogadoObjeto(request));

				daoFoneRepository.gravaFone(modelFone);

			
				request.setAttribute("msg", "Salvo com sucesso");
				
			}else {
				request.setAttribute("msg", "Telefone já existe");
			}
			
			List<ModelFone> modelFones = daoFoneRepository.listFone(Long.parseLong(IdUserPai));

			ModelLogin modelLogin = daoUsuarioRepository.consultaUsuarioId(Long.parseLong(IdUserPai));

			request.setAttribute("modelLogin", modelLogin);
			request.setAttribute("modelTelefones", modelFones);
			request.getRequestDispatcher("principal/telefone.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("post");
	}

}
