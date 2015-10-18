<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="../header.jsp" %>

<script type="text/javascript">
	function goToRanking() {
		var form = document.getElementById('formPrincipal');
		if(form.voterName.value != "" && form.voterEmail.value != "") {
			form.executeAction.value = "doSaveVoter";
			form.submit();
		} else {
			alert("Você deve preencher os campos nome e e-mail para visualizar o Ranking!");
		}
	}
</script>
	
<div class="container">
    <div class="row">
        <div class="col-md-4 col-md-offset-4">
            <p>Obrigado por votar!</p>
            <br>
            <p>Se você quiser ver o ranking, por favor nos informe seus dados.</p>
            <form id="formPrincipal" method="POST" action="/vote-no-restaurante/home">
          		<input type="hidden" name="executeAction" value="" />
                <div class="form-group">
                    <label for="nome">Nome</label>
                    <input id="nome" name="voterName" class="form-control" required="required"/>
                </div>
                <div class="form-group">
                    <label for="email">E-mail</label>
                    <input id="email" type="email" name="voterEmail" class="form-control" required="required"/>
                </div>
                <button type="button" class="btn btn-default pull-right" onclick="javascript:goToRanking()">Continuar</button>
            </form>
        </div>
    </div>
</div>

<%@ include file="../footer.jsp" %>