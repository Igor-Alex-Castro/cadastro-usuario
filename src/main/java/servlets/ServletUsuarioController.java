package servlets;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.fileupload2.javax.JavaxServletFileUpload;
import org.apache.commons.io.IOUtils;
import org.apache.tomcat.util.codec.binary.Base64;

import com.fasterxml.jackson.databind.ObjectMapper;

import beanDTO.BeanDTOGraficoSalarioUser;
import dao.DaoUsuarioRepository;
import model.ModelLogin;
import util.ReportUtil;

@SuppressWarnings("deprecation")
@MultipartConfig
@WebServlet(urlPatterns = { "/ServletUsuarioController" })
public class ServletUsuarioController extends ServletGenerecUtil {
	private static final long serialVersionUID = 1L;

	private DaoUsuarioRepository daoUsuarioRepository = new DaoUsuarioRepository();

	public ServletUsuarioController() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {

			String acao = request.getParameter("acao");

			if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("deletar")) {
				String idUser = request.getParameter("id");
				daoUsuarioRepository.deletarUser(idUser);

				List<ModelLogin> modelLogins = daoUsuarioRepository.consultaUsuarioList(super.getUserLogado(request));
				request.setAttribute("modelLogins", modelLogins);

				request.setAttribute("msg", "Excluido com sucesso!");
				request.setAttribute("totalPagin", daoUsuarioRepository.totalPagina(this.getUserLogado(request)));
				request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);

			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("deletaraAjax")) {
				String idUser = request.getParameter("id");
				daoUsuarioRepository.deletarUser(idUser);

				// request.setAttribute("msg", "Excluido com sucesso!" );

				response.getWriter().write("Excluido com sucesso!");

			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarUserAjax")) {
				String nomeBusca = request.getParameter("nomeBusca");
				// System.out.println(nomeBusca);

				List<ModelLogin> dadosJsonUser = daoUsuarioRepository.consultaUsuarioList(nomeBusca,
						super.getUserLogado(request));

				ObjectMapper mapper = new ObjectMapper();

				String json = mapper.writeValueAsString(dadosJsonUser);

				response.addHeader("totalPagina", "" + daoUsuarioRepository.consultaUsuarioListTotalPaginacao(nomeBusca,
						super.getUserLogado(request)));

				response.getWriter().write(json);
			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarUserAjaxPage")) {
				String nomeBusca = request.getParameter("nomeBusca");
				String pagina = request.getParameter("pagina");
				System.out.println(nomeBusca);
				System.out.println(pagina);
				List<ModelLogin> dadosJsonUser = daoUsuarioRepository.consultaUsuarioListOffiset(nomeBusca,
						super.getUserLogado(request), pagina);

				ObjectMapper mapper = new ObjectMapper();

				String json = mapper.writeValueAsString(dadosJsonUser);

				response.addHeader("totalPagina", "" + daoUsuarioRepository.consultaUsuarioListTotalPaginacao(nomeBusca,
						super.getUserLogado(request)));

				response.getWriter().write(json);

			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarEditar")) {

				String id = request.getParameter("id");

				ModelLogin ModelLogin = daoUsuarioRepository.consultaUsuarioId(id, super.getUserLogado(request));

				List<ModelLogin> modelLogins = daoUsuarioRepository.consultaUsuarioList(super.getUserLogado(request));
				request.setAttribute("modelLogins", modelLogins);

				request.setAttribute("msg", "Usuário em edição");
				request.setAttribute("modelLogin", ModelLogin);
				request.setAttribute("totalPagin", daoUsuarioRepository.totalPagina(this.getUserLogado(request)));
				request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);

			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("listarUsuario")) {
				List<ModelLogin> modelLogins = daoUsuarioRepository.consultaUsuarioList(super.getUserLogado(request));

				request.setAttribute("msg", "Usuários carregados");
				request.setAttribute("modelLogins", modelLogins);
				request.setAttribute("totalPagin", daoUsuarioRepository.totalPagina(this.getUserLogado(request)));
				request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("downloadFoto")) {
				String idUser = request.getParameter("id");

				ModelLogin modelLogin = daoUsuarioRepository.consultaUsuarioId(idUser, super.getUserLogado(request));

				if (modelLogin.getFotoUser() != null && !modelLogin.getFotoUser().isEmpty()) {
					response.setHeader("Content-Disposition",
							"attachment; filemane=arquivo. " + modelLogin.getExtensaoFotoUser());
					response.getOutputStream()
							.write(new Base64().decodeBase64(modelLogin.getFotoUser().split("\\,")[1]));
				}
			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("paginar")) {
				Integer offset = Integer.parseInt(request.getParameter("pagina"));

				List<ModelLogin> modelLogins = daoUsuarioRepository.consultaUsuarioList(this.getUserLogado(request),
						offset);

				request.setAttribute("modelLogins", modelLogins);
				request.setAttribute("totalPagin", daoUsuarioRepository.totalPagina(this.getUserLogado(request)));
				request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("imprimirRelatorioUser")) {

				String dataInicial = request.getParameter("dataInicial");
				String dataFinal = request.getParameter("dataFinal");

				System.out.println(dataFinal);
				System.out.println(dataInicial);

				if (dataInicial == null || dataInicial.isEmpty() && dataFinal == null || dataFinal.isEmpty()) {

					request.setAttribute("listaUser",
							daoUsuarioRepository.consultaUsuarioListRel(super.getUserLogado(request)));
				} else {
					request.setAttribute("listaUser", daoUsuarioRepository
							.consultaUsuarioListRel(super.getUserLogado(request), dataInicial, dataFinal));
				}

				request.setAttribute("dataInicial", dataInicial);
				request.setAttribute("dataFinal", dataFinal);
				request.getRequestDispatcher("principal/reluser.jsp").forward(request, response);
			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("imprimirRelatorioPDF")
					|| acao.equalsIgnoreCase("imprimirRelatorioExcel")) {

				String dataInicial = request.getParameter("dataInicial");
				String dataFinal = request.getParameter("dataFinal");

				List<ModelLogin> lisModelLogins = null;

				if (dataInicial == null || dataInicial.isEmpty() && dataFinal == null || dataFinal.isEmpty()) {

					lisModelLogins = daoUsuarioRepository.consultaUsuarioListRel(super.getUserLogado(request));
				} else {
					lisModelLogins = daoUsuarioRepository.consultaUsuarioListRel(super.getUserLogado(request),
							dataInicial, dataFinal);

				}

				HashMap<String, Object> params = new HashMap<String, Object>();
				params.put("PARAM_SUB_REPORT", request.getServletContext().getRealPath("relatorio") + File.separator);

				byte[] relatorio = null;

				String extencao = "";

				if (acao.equalsIgnoreCase("imprimirRelatorioPDF")) {

					relatorio = new ReportUtil().geraRelatorioPDF(lisModelLogins, "rel-user-jsp", params,
							request.getServletContext());
					extencao = "pdf";
				} else {
					if (acao.equalsIgnoreCase("imprimirRelatorioExcel")) {
						System.out.println("..");
						relatorio = new ReportUtil().geraRelatorioExcelSemSubLista(lisModelLogins, "rel-user-jsp2",
								request.getServletContext());
						extencao = "xls";
					}

				}

				response.setHeader("Content-Disposition", "attachment; filemane=arquivo." + extencao);
				response.getOutputStream().write(relatorio);

			}

			else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("imprimirExcel")) {
				String dataInicial = request.getParameter("dataInicial");
				String dataFinal = request.getParameter("dataFinal");
				List<ModelLogin> lisModelLogins = daoUsuarioRepository
						.consultaUsuarioListRel(super.getUserLogado(request), dataInicial, dataFinal);
				// new ReportUtil().geraExcel(lisModelLogins);
			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("graficoSalario")) {
				String dataInicial = request.getParameter("dataInicial");
				String dataFinal = request.getParameter("dataFinal");

				System.out.println(dataFinal);
				if (dataInicial == null || dataInicial.isEmpty() && dataFinal == null || dataFinal.isEmpty()) {
					BeanDTOGraficoSalarioUser beanDTOGraficoSalarioUser = daoUsuarioRepository
							.montarGraficoMediaSalario(super.getUserLogado(request));

					ObjectMapper mapper = new ObjectMapper();
					String json = mapper.writeValueAsString(beanDTOGraficoSalarioUser);

					response.getWriter().write(json);

				} else {
					
					BeanDTOGraficoSalarioUser beanDTOGraficoSalarioUser = daoUsuarioRepository
							.montarGraficoMediaSalario(super.getUserLogado(request), dataInicial, dataFinal);

					ObjectMapper mapper = new ObjectMapper();
					String json = mapper.writeValueAsString(beanDTOGraficoSalarioUser);

					response.getWriter().write(json);

				}
			}

			else {
				List<ModelLogin> modelLogins = daoUsuarioRepository.consultaUsuarioList(super.getUserLogado(request));
				request.setAttribute("modelLogins", modelLogins);
				request.setAttribute("totalPagin", daoUsuarioRepository.totalPagina(this.getUserLogado(request)));
				request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher redirecionar = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			redirecionar.forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {

			String msg = "Operação realizada com sucesso";

			String id = request.getParameter("id");
			String nome = request.getParameter("nome");
			String email = request.getParameter("email");
			String login = request.getParameter("login");
			String senha = request.getParameter("senha");
			String perfil = request.getParameter("perfil");
			String sexo = request.getParameter("sexo");

			String cep = request.getParameter("cep");
			String logradouro = request.getParameter("logradouro");
			String bairro = request.getParameter("bairro");
			String localidade = request.getParameter("localidade");
			String uf = request.getParameter("uf");
			String numero = request.getParameter("numero");
			String dataNascimento = request.getParameter("dataNascimento");
			String rendaMensal = request.getParameter("rendaMensal");
			
			
			
			System.out.println(rendaMensal);
			System.out.println(rendaMensal);

			Long idLong = id != null && !id.isEmpty() ? Long.parseLong(id) : null;
			ModelLogin modelLogin = new ModelLogin(idLong, nome, email, login, senha, perfil, sexo, cep, logradouro,
					bairro, localidade, uf, numero);

			if(dataNascimento != null && !dataNascimento.isEmpty()) {
				modelLogin.setDataNascimento(Date.valueOf(
						new SimpleDateFormat("yyyy-mm-dd").format(new SimpleDateFormat("dd/mm/yy").parse(dataNascimento))));
				
			}
			if(rendaMensal != null && !rendaMensal.isEmpty()) {
				rendaMensal = rendaMensal.split("\\ ")[1].replaceAll("\\.", "").replaceAll("\\,", ".");
				modelLogin.setRendaMensal(Double.parseDouble(rendaMensal));
			}
			
			

			if (JavaxServletFileUpload.isMultipartContent(request)) {
				Part part = request.getPart("fileFoto");
				if (part.getSize() > 0) {

					byte[] foto = IOUtils.toByteArray(part.getInputStream()); // converte a imagem para byte;
					String imagemBase64 = "data:image/" + part.getContentType().split("\\/")[1] + ";base64,"
							+ new Base64().encodeBase64String(foto);

					System.out.println(imagemBase64);
					modelLogin.setFotoUser(imagemBase64);
					modelLogin.setExtensaoFotoUser(part.getContentType().split("\\/")[1]);

				}

			}

			if (daoUsuarioRepository.validarLogin(modelLogin.getLogin()) && modelLogin.getId() == null) {
				msg = "Já existe usuário com o mesmo login, informe outro login;";
			} else {
				if (modelLogin.isNovo()) {
					msg = "Gravado com sucesso";
				} else {
					msg = "Atualizado com sucesso";
				}
				modelLogin = daoUsuarioRepository.gravarUsuario(modelLogin, super.getUserLogado(request));

			}

			List<ModelLogin> modelLogins = daoUsuarioRepository.consultaUsuarioList(super.getUserLogado(request));
			request.setAttribute("modelLogins", modelLogins);
			request.setAttribute("msg", msg);
			request.setAttribute("modelLogin", modelLogin);
			request.setAttribute("totalPagin", daoUsuarioRepository.totalPagina(this.getUserLogado(request)));
			request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
			// request.getRequestDispatcher("principal/usuario.jsp").forward(request,
			// response);
			//

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("Erro.jsp");
			request.setAttribute("msg", e.getMessage());
			requestDispatcher.forward(request, response);
		}

	}

}
