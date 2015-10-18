<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<%@ include file="../header.jsp"%>

<div class="container">
	<form id="formPrincipal" method="POST" action="/vote-no-restaurante/home">
		<input type="hidden" name="executeAction" value="" />
		<div class="col-md-12">
			<label> <span class="glyphicon glyphicon-envelope"></span>
			</label>
			<h2>Mensagem:</h2>
			<h4>${message}</h4>
		</div>
	</form>
</div>

<%@ include file="../footer.jsp"%>