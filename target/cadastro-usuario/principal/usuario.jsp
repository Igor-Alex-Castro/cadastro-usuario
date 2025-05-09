
<%@page import="model.ModelLogin"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>



<!DOCTYPE html>
<html lang="en">




<jsp:include page="head.jsp"></jsp:include>


<body>
	<!-- Pre-loader start -->

	<jsp:include page="theme-loader.jsp"></jsp:include>

	<!-- Pre-loader end -->
	<div id="pcoded" class="pcoded">
		<div class="pcoded-overlay-box"></div>
		<div class="pcoded-container navbar-wrapper">

			<jsp:include page="navbar.jsp"></jsp:include>


			<div class="pcoded-main-container">
				<div class="pcoded-wrapper">

					<jsp:include page="navbarmainmenu.jsp"></jsp:include>

					<div class="pcoded-content">
						<!-- Page-header start -->

						<jsp:include page="page-header.jsp"></jsp:include>

						<!-- Page-header end -->
						<div class="pcoded-inner-content">
							<!-- Main-body start -->
							<div class="main-body">
								<div class="page-wrapper">
									<!-- Page-body start -->
									<div class="page-body">




										<div class="row">
											<div class="col-sm-12">
												<!-- Basic Form Inputs card start -->
												<div class="card">

													<div class="card-block">
														<h4 class="sub-title">Cad. Usuário</h4>
														<form class="form-material" enctype="multipart/form-data"
															action="<%=request.getContextPath()%>/ServletUsuarioController"
															method="post" id="formUser">
															<input type="hidden" name="acao" id="acao" value="">
															<div class="form-group form-default form-static-label">
																<input type="text" name="id" id="id"
																	class="form-control" readonly="readonly"
																	value="${modelLogin.id }"> <span
																	class="form-bar"></span> <label class="float-label"
																	style="color: black";>ID: </label>
															</div>

															<div class="form-group form-default form-static-label">
																<c:if
																	test="${modelLogin.fotoUser != '' && modelLogin.fotoUser != null}">
																	<a
																		href="<%= request.getContextPath()%>/ServletUsuarioController?acao=downloadFoto&id=${modelLogin.id}">
																		<img alt="Imagem user" id="fotoEmBase64"
																		src="${modelLogin.fotoUser}" width="70px">
																</c:if>
																<c:if
																	test="${modelLogin.fotoUser == '' || modelLogin.fotoUser == null}">
																	<img alt="Imagem user" id="fotoEmBase64"
																		src="<%=request.getContextPath()%>/assets/images/avatar-3.jpg"
																		width="70px">
																</c:if>

																<div class="mb-3">

																	<input class="form-control" type="file" id="fileFoto"
																		name="fileFoto" accept="image/*"
																		onchange="visualisarImg('fotoEmBase64', 'fileFoto');"
																		id="formFile"> <span class="form-bar"></span>
																	<label class="float-label" style="color: black";>Foto:
																	</label>
																</div>



															</div>
															<div class="form-group form-default form-static-label">
																<input type="email" name="email" id="email"
																	class="form-control" value="${modelLogin.email }">
																<span class="form-bar"></span> <label
																	class="float-label" style="color: black";>Email:
																</label>
															</div>

															<div class="form-group form-default form-static-label">
																<input type="text" name="nome" id="nome"
																	class="form-control" value="${modelLogin.nome }">
																<span class="form-bar"></span> <label
																	class="float-label" style="color: black";>Nome:
																</label>
															</div>

															<div class="form-group form-default form-static-label">
																<input type="text" name="dataNascimento"
																	id="dataNascimento" class="form-control"
																	value="${modelLogin.dataNascimento }"> <span
																	class="form-bar"></span> <label class="float-label"
																	style="color: black";>Dat. Nascimento: </label>
															</div>
															
															<div class="form-group form-default form-static-label">
																<input type="text" name="rendaMensal" id="rendaMensal"
																	class="form-control" value="${modelLogin.rendaMensal }">
																<span class="form-bar"></span> <label
																	class="float-label" style="color: black";>Renda Mensal:
																</label>
															</div>
															<div class="form-group form-default form-static-label">
																<select class="form-control"
																	aria-label="Default select example" name="perfil">
																	<option disabled="disabled">[Selecione o
																		perfil]</option>
																	<option value="ADMIN"
																		<%ModelLogin modelLogin = (ModelLogin) request.getAttribute("modelLogin");

if (modelLogin != null && modelLogin.getPerfil().equals("ADMIN")) {
	out.print("");
	out.print("selected=\"selected\"");
	out.print("");
}%>>Admin</option>

																	<option value="SECRETARIA"
																		<%modelLogin = (ModelLogin) request.getAttribute("modelLogin");

if (modelLogin != null && modelLogin.getPerfil().equals("SECRETARIA")) {
	out.print("");
	out.print("selected=\"selected\"");
	out.print("");
}%>>Secretária</option>
																	<option value="AUXILIAR"
																		<%modelLogin = (ModelLogin) request.getAttribute("modelLogin");

if (modelLogin != null && modelLogin.getPerfil().equals("AUXILIAR")) {
	out.print("");
	out.print("selected=\"selected\"");
	out.print("");
}%>>Auxiliar</option>
																</select> <span class="form-bar"></span> <label
																	class="float-label" style="color: black";>Perfil:
																</label>
															</div>


															<div>
																<div class="form-group form-default form-static-label">
																	<input onblur="pesquisaCep();" type="text" name="cep"
																		id="cep" class="form-control"
																		value="${modelLogin.cep }"> <span
																		class="form-bar"></span> <label class="float-label"
																		style="color: black";>Cep: </label>
																</div>
																<div class="form-group form-default form-static-label">
																	<input type="text" name="logradouro" id="logradouro"
																		class="form-control" value="${modelLogin.logradouro }">
																	<span class="form-bar"></span> <label
																		class="float-label" style="color: black";>Logradouro:
																	</label>
																</div>

																<div class="form-group form-default form-static-label">
																	<input type="text" name="bairro" id="bairro"
																		class="form-control" value="${modelLogin.bairro }">
																	<span class="form-bar"></span> <label
																		class="float-label" style="color: black";>Bairro:
																	</label>
																</div>

																<div class="form-group form-default form-static-label">
																	<input type="text" name="localidade" id="localidade"
																		class="form-control" value="${modelLogin.localidade }">
																	<span class="form-bar"></span> <label
																		class="float-label" style="color: black";>Localidade:
																	</label>
																</div>

																<div class="form-group form-default form-static-label">
																	<input type="text" name="uf" id="uf"
																		class="form-control" value="${modelLogin.uf }">
																	<span class="form-bar"></span> <label
																		class="float-label" style="color: black";>Estado:
																	</label>
																</div>

																<div class="form-group form-default form-static-label">
																	<input type="text" name="numero" id="numero"
																		class="form-control" value="${modelLogin.numero }">
																	<span class="form-bar"></span> <label
																		class="float-label" style="color: black";>Numero:
																	</label>
																</div>


																<div class="form-group form-default form-static-label">
																	<input type="text" name="login" id="login"
																		class="form-control" value="${modelLogin.login }">
																	<span class="form-bar"></span> <label
																		class="float-label" style="color: black";>Login:
																	</label>
																</div>

																<div class="form-group form-default form-static-label">
																	<input type="password" name="senha" id:="senha"
																		value="${modelLogin.senha }" required="required"
																		autocomplete="off" class="form-control" required=""
																		<span class="form-bar"></span>
																		<label
																	class="float-label" style="color:black";>Senha</label>
																</div>

																<div class="form-group form-default form-static-label">
 
																	<input type="radio" name="sexo" value="MASCULINO"
																		<%
																		
																		modelLogin = (ModelLogin) request.getAttribute("modelLogin");
if (modelLogin != null && modelLogin.getSexo().equals("MASCULINO")) {
	out.print("");
	out.print("checked=\"checked\"");
	out.print("");
}
%>>Masculino</>


																	<input type="radio" name="sexo" value="FEMININO"
																		<%
																		modelLogin = (ModelLogin) request.getAttribute("modelLogin");

if (modelLogin != null && modelLogin.getSexo().equals("FEMININO")) {
	out.print("");
	out.print("checked=\"checked\"");
	out.print("");
} %>>Feminino</>

																</div>

																<button type="button" class="btn btn-primary"
																	onClick="limparForm();">Novo</button>
																<button type="submit" class="btn btn-secondary">Salvar</button>

																<button type="button" class="btn btn-danger"
																	onclick="criarDelete();">Excluir</button>
																<button type="button" class="btn btn-danger"
																	onclick="criarDeleteComAjax();">Excluir Ajax</button>

																<c:if test="${modelLogin.id > 0}">
																	<a
																		href="<%= request.getContextPath()%>/ServletTelefone?idUser=${modelLogin.id}"
																		class="btn btn-info">Telefone</a>
																</c:if>
																<!-- Button trigger modal -->
																<button type="button" class="btn btn-secondary"
																	data-toggle="modal" data-target="#exampleModalUsuario">
																	Pesquisar</button>
														</form>

													</div>
													<span id="" msg>${msg }</span>

													<div style="height: 300px; overflow-y: scroll">

														<table class="table" id="tabelaresultadosview">
															<thead>
																<tr>
																	<th scope="col">ID</th>
																	<th scope="col">Nome</th>
																	<th scope="col">Ver</th>
																</tr>
															</thead>
															<tbody>
																<c:forEach items='${modelLogins}' var='ml'>
																	<tr>
																		<td><c:out value="${ml.id}"></c:out></td>
																		<td><c:out value="${ml.nome}"></c:out></td>
																		<td><a class="btn btn-info"
																			href="<%= request.getContextPath() %>/ServletUsuarioController?acao=buscarEditar&id=${ml.id}">Ver
																				</button></td>

																	</tr>
																</c:forEach>
															</tbody>
														</table>

													</div>

													<nav aria-label="Page navigation example">
														<ul class="pagination">
															<%
															int totalPagina = (int) request.getAttribute("totalPagin");
															for (int p = 0; p < totalPagina; p++) {
																String url = request.getContextPath() + "/ServletUsuarioController?acao=paginar&pagina=" + (p * 5);
																out.print("<li class=\"page-item\"><a class=\"page-link\" href=\"" + url + "\">" + (p + 1) + "</a></li>");
															}
															%>




														</ul>
													</nav>

													<!-- Page-body end -->
												</div>
												<div id="styleSelector"></div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>


					<jsp:include page="javascripfile.jsp"></jsp:include>



					<!-- Modal -->
					<div class="modal fade" id="exampleModalUsuario" tabindex="-1"
						aria-labelledby="exampleModalLabel" aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<h5 class="modal-title" id="exampleModalLabel">Pesquisa de
										Usuário</h5>
									<button type="button" class="close" data-dismiss="modal"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
								</div>
								<div class="modal-body">

									<div class="input-group mb-3">
										<input type="text" class="form-control" placeholder="Nome"
											aria-label="nome" id="nomeBusca"
											aria-describedby="basic-addon2">
										<div class="input-group-append">
											<button class="btn btn-success" type="button"
												onclick="buscarUsuario();">Buscar</button>
										</div>
									</div>
									<div style="height: 300px; overflow-y: scroll">

										<table class="table" id="tabelaresultados">
											<thead>
												<tr>
													<th scope="col">ID</th>
													<th scope="col">Nome</th>
													<th scope="col">Ver</th>
												</tr>
											</thead>
											<tbody>

											</tbody>
										</table>

									</div>
									<nav aria-label="Page navigation example">
										<ul class="pagination" id="ulPaginacaoUserAjax">

										</ul>
									</nav>
									<span id="totalResultados"></span>
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-secondary"
										data-dismiss="modal">Fechar</button>

								</div>
							</div>
						</div>
					</div>

					<script type="text/javascript">
					
					
						$("#rendaMensal").maskMoney({showSymbol:true, prefix:'R$ ', decimal: ",", thousands:"."});
						
						const formatter = new Intl.NumberFormat('pt-BR', {
							currency:'BRL',
							minimumFractionDigits: 2 
						});
						
						$("#rendaMensal").val(formatter.format($("#rendaMensal").val()));
						
						$("#rendaMensal").focus();
						
						var dataNascimento = $("#dataNascimento").val();
						
						if(dataNascimento != null &&  dataNascimento !=''){
							var dateFormat = new Date(dataNascimento);
							
							$("#dataNascimento").val(dateFormat.toLocaleDateString('pt-BR', {timeZone: 'UTC'} ));
						}
						
						
						
						$("#nome").focus();
					
						$(function() {

							$("#dataNascimento").datepicker(
									{
										dateFormat : 'dd/mm/yy',
										dayNames : [ 'Domingo', 'Segunda',
												'Terça', 'Quarta', 'Quinta',
												'Sexta', 'Sábado' ],
										dayNamesMin : [ 'D', 'S', 'T', 'Q',
												'Q', 'S', 'S', 'D' ],
										dayNamesShort : [ 'Dom', 'Seg', 'Ter',
												'Qua', 'Qui', 'Sex', 'Sáb',
												'Dom' ],
										monthNames : [ 'Janeiro', 'Fevereiro',
												'Março', 'Abril', 'Maio',
												'Junho', 'Julho', 'Agosto',
												'Setembro', 'Outubro',
												'Novembro', 'Dezembro' ],
										monthNamesShort : [ 'Jan', 'Fev',
												'Mar', 'Abr', 'Mai', 'Jun',
												'Jul', 'Ago', 'Set', 'Out',
												'Nov', 'Dez' ],
										nextText : 'Próximo',
										prevText : 'Anterior'
									});
						});

						$("#numero").keypress(
								function(event) {
									return /\d/.test(String
											.fromCharCode(event.keyCode));
								});

						$("#cep").keypress(
								function(event) {
									return /\d/.test(String
											.fromCharCode(event.keyCode));
								});

						function buscarUserPagAjax(url) {
							alert(url);
							var urlAction = document.getElementById('formUser').action;

							var nomeBusca = document
									.getElementById('nomeBusca').value;

							if (nomeBusca != null && nomeBusca != ''
									&& nomeBusca.trim != '') {

								$
										.ajax(
												{
													method : "get",
													url : urlAction,
													data : url,
													success : function(
															response,
															textStatus, xhr) {
														var json = JSON
																.parse(response);

														$(
																'#tabelaresultados > tbody > tr')
																.remove();

														$(
																"#ulPaginacaoUserAjax > li")
																.remove();

														for (var p = 0; p < json.length; p++) {
															$(
																	'#tabelaresultados > tbody')
																	.append(
																			'<tr><td>'
																					+ json[p].id
																					+ '</td><td>'
																					+ json[p].nome
																					+ '</td><td><button type="button" onclick="verEditar('
																					+ json[p].id
																					+ ')" class="btn btn-info" >Ver</button></td></tr>');
														}

														document
																.getElementById('totalResultados').textContent = 'Resultados: '
																+ json.length;

														var totalPagina = xhr
																.getResponseHeader("totalPagina");

														for (var p = 0; p < totalPagina; p++) {

															var url = 'nomeBusca='
																	+ nomeBusca
																	+ '&acao=buscarUserAjaxPage&pagina='
																	+ (p * 5);
															$(
																	"#ulPaginacaoUserAjax")
																	.append(
																			'<li class="page-item"><a class="page-link" href="#" onclick="buscarUserPagAjax(\''
																					+ url
																					+ '\')">'
																					+ (p + 1)
																					+ '</a></li>');
														}
													}
												})
										.fail(
												function(xhr, status,
														errorThrow) {
													alert('Erro ao buscar usuário por nome: '
															+ xhr.response);
												});
							}
						}

						function pesquisaCep() {
							var cep = $('#cep').val();

							$.getJSON("https://viacep.com.br/ws/" + cep
									+ "/json/?callback=?", function(dados) {
								if (!("erro" in dados)) {
									$("#cep").val(dados.cep);
									$("#logradouro").val(dados.logradouro);
									$("#bairro").val(dados.bairro);
									$("#localidade").val(dados.localidade);
									$("#uf").val(dados.uf);

								}
							});

						}
						function visualisarImg(fotoEmBase64, fileFoto) {

							var preview = document.getElementById(fotoEmBase64); //campo img hmtl
							var fileUser = document.getElementById(fileFoto).files[0];

							alert(preview + '/' + fileUser);
							var reader = new FileReader();

							reader.onloadend = function() {
								preview.src = reader.result; //carrega a foto na tela;
							};

							if (fileUser) {
								reader.readAsDataURL(fileUser);//*

							} else {
								preview.src = '';
							}

						}
						function verEditar(id) {
							var urlAction = document.getElementById('formUser').action;

							window.location.href = urlAction
									+ '?acao=buscarEditar&id=' + id;
						}

						function buscarUsuario() {
							var nomeBusca = document
									.getElementById('nomeBusca').value;
							var urlAction = document.getElementById('formUser').action;

							if (nomeBusca != null && nomeBusca != ''
									&& nomeBusca.trim != '') {

								$
										.ajax(
												{
													method : "get",
													url : urlAction,
													data : "nomeBusca="
															+ nomeBusca
															+ "&acao=buscarUserAjax",
													success : function(
															response,
															textStatus, xhr) {
														var json = JSON
																.parse(response);

														$(
																'#tabelaresultados > tbody > tr')
																.remove();

														$(
																"#ulPaginacaoUserAjax > li")
																.remove();

														for (var p = 0; p < json.length; p++) {
															$(
																	'#tabelaresultados > tbody')
																	.append(
																			'<tr><td>'
																					+ json[p].id
																					+ '</td><td>'
																					+ json[p].nome
																					+ '</td><td><button type="button" onclick="verEditar('
																					+ json[p].id
																					+ ')" class="btn btn-info" >Ver</button></td></tr>');
														}

														document
																.getElementById('totalResultados').textContent = 'Resultados: '
																+ json.length;

														var totalPagina = xhr
																.getResponseHeader("totalPagina");

														for (var p = 0; p < totalPagina; p++) {
															var url = 'nomeBusca='
																	+ nomeBusca
																	+ '&acao=buscarUserAjaxPage&pagina='
																	+ (p * 5);
															$(
																	"#ulPaginacaoUserAjax")
																	.append(
																			'<li class="page-item"><a class="page-link" href="#" onclick="buscarUserPagAjax(\''
																					+ url
																					+ '\')">'
																					+ (p + 1)
																					+ '</a></li>')
														}
													}
												})
										.fail(
												function(xhr, status,
														errorThrow) {
													alert('Erro ao buscar usuário por nome: '
															+ xhr.response);
												});
							}
						}

						function criarDeleteComAjax() {
							if (confirm('Desenja realmente excluir os dados?')) {
								var urlAction = document
										.getElementById('formUser').action;
								var idUser = document.getElementById('id').value;

								$
										.ajax(
												{

													method : "get",
													url : urlAction,
													data : "id="
															+ idUser
															+ "&acao=deletaraAjax",
													success : function(response) {
														limparForm();
														alert(response);
														document
																.getElementById('msg').textContent = response;
													}

												})
										.fail(
												function(xhr, status,
														errorThrown) {
													alert('Erro ao deletar usuário por id: '
															+ xhr.responseText);
												});

							}
						}

						function criarDelete() {

							if (confirm('Desenja realmente excluir os dados?')) {
								document.getElementById("formUser").method = 'get';
								document.getElementById("acao").value = 'deletar';

								document.getElementById("formUser").submit();
							}

						}

						function limparForm() {

							//
							var elementos = document.getElementById("formUser").elements;/*Retornas os elementos html dentro do form*/

							for (p = 0; p < elementos.length; p++) {
								elementos[p].value = '';
							}

						}
					</script>
</body>


</html>

