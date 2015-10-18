<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<%@ include file="../header.jsp"%>

<div class="container">
	<form id="formPrincipal" method="POST" action="/vote-no-restaurante/home">
		<input type="hidden" name="executeAction" value="" />
		<div class="col-md-12">
			<label> <span class="glyphicon glyphicon-remove-circle"></span>
			</label>
			<h2>Ocorreu um erro!</h2>
			<h4>${internalException}</h4>
		</div>
	</form>
</div>

<%@ include file="../footer.jsp"%>