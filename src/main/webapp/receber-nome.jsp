<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%
		String name = request.getParameter("nome");
		out.println("Nome: " + name);
		
		String idade =  request.getParameter("idade");
		out.println("Idade: " + idade );
	%>
</body>
</html>