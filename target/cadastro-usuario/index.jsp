<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">

	
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<title>Cadastro usuario</title>
<style type="text/css">
	form {
		position: absolute;
		top: 40%;
		left: 33%;
	}
	.msg{
		position: absolute;
		top: 30%;
		left: 33%;
		
	}
	h5{
		position: absolute;
		top: 10%;
		left: 33%;
		
		color: red;
		color: #664d03;
    	background-color: #fff3cd;
    	border-color: #ffecb5;
}
	}

</style>
</head>
<body>
	<h4 class="msg">Bem vido ao curso de JSP</h4>
		
		<input type="hidden" value=<%= request.getParameter("url")%> name="url">
		
		<form action="<%= request.getContextPath()%>/ServletLogin" method="post" class="row g-3 needs-validation" novalidate>
			
			<div class="md-3">
				<label for="inputLogin" class="form-label" >Login </label>
				<input   type="text" class="form-control" id="inputLogin" name="login" required >
				 <div class="invalid-feedback">
      				Obrigatorio.
    			</div>
    			 <div class="valid-feedback">
      				ok
    			</div>
			</div>
			
			<div class="md-3">
				<label for="inputSenha" class="form-label" >Senha </label>
				<input type="password" name="senha" class="form-control" id="inputLogin"  required >
				<div class="invalid-feedback">
      				Obrigatorio.
    			</div>
    			 <div class="valid-feedback">
      				ok
    			</div>
			</div>
			
			
				<input type="submit" value="Acessar"  class="btn btn-primary"/>
			
				
	
			
		</form>
	<h5>${msg}</h5>
	
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
	<script type="text/javascript">
	(function () {
		  'use strict'

		  // Fetch all the forms we want to apply custom Bootstrap validation styles to
		  var forms = document.querySelectorAll('.needs-validation')

		  // Loop over them and prevent submission
		  Array.prototype.slice.call(forms)
		    .forEach(function (form) {
		      form.addEventListener('submit', function (event) {
		        if (!form.checkValidity()) {
		          event.preventDefault()
		          event.stopPropagation()
		        }

		        form.classList.add('was-validated')
		      }, false)
		    })
		})()
	</script>
</body>
</html>