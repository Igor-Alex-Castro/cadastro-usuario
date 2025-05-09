<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@page import="model.ModelFone"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

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
														<h4 class="sub-title">Cad. Telefone</h4>
														<form class="form-material"
															action="<%=request.getContextPath()%>/ServletTelefone"
															method="post" id="formTelefone">


															<div class="form-group form-default form-static-label">
																<input type="text" name="id" id="id"
																	class="form-control" readonly="readonly"
																	value="${modelLogin.id }"> <span
																	class="form-bar"></span> <label class="float-label"
																	style="color: black";>ID: </label>
															</div>

															<div class="form-group form-default form-static-label">
																<input type="text" name="nome" id="nome"
																	class="form-control" readonly="readonly"
																	value="${modelLogin.nome }"> <span
																	class="form-bar"></span> <label class="float-label"
																	style="color: black";>Nome: </label>
															</div>
															<div class="form-group form-default form-static-label">
																<input type="text" name="numero" id="numero"
																	class="form-control" <span class="form-bar"></span>
																	<label
																	class="float-label" style="color: black";>Número:
																</label>
															</div>
															<button type="submit" class="btn btn-primary">Salvar</button>
														</form>
														<span id="" msg>${msg }</span>


														<div style="height: 300px; overflow-y: scroll">

															<table class="table" id="tabelaresultadosview">
																<thead>
																	<tr>
																		<th scope="col">ID</th>
																		<th scope="col">Número</th>
																		<th scope="col">Exluir</th>
																	</tr>
																</thead>
																<tbody>
																	<c:forEach items='${modelTelefones}' var='mt'>
																		<tr>
																			<td><c:out value="${mt.id}"></c:out></td>
																			<td><c:out value="${mt.numero}"></c:out></td>
																			<td><a class="btn btn-info"
																				href="<%= request.getContextPath() %>/ServletTelefone?acao=excluir&id=${mt.id}&userpaiid=${modelLogin.id}">Excluir
																					</button></td>

																		</tr>
																	</c:forEach>
																</tbody>
															</table>

														</div>
													</div>
												</div>
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
				
				
				<script type="text/javascript">
				
				$("#numero").keypress(function (event) {
					return /\d/.test(String.fromCharCode(event.keyCode));
				});
				</script>
</body>


</html>

