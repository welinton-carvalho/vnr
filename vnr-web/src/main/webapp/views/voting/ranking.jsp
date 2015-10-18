<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ include file="../header.jsp"%>

<div class="container ranking">
 	<form id="formPrincipal" method="POST" action="/vote-no-restaurante/home">
  		<input type="hidden" name="executeAction" value="" />
		<div class="row geral">
			<h3>Ranking de Preferência</h3>
			<h5>Total de votos: ${ranking[0].qttTotalOfVotes}</h5>
			<ul class="list-inline">
				<c:forEach items="${ranking}" var="position">
					<li class="col-md-12">
						<img src="<c:url value='/views/resources/img/restaurants/${position.restaurant.logo}'/>" alt="${position.restaurant.name}" title="${position.restaurant.name}" /> 
						<span>${position.qttOfVotes} - <fmt:formatNumber type="percent" maxIntegerDigits="2" maxFractionDigits="2" value="${position.percentualOfVotes}" /></span> 
					</li>
				</c:forEach>
			</ul>
		</div>
	</form>
</div>

<%@ include file="../footer.jsp"%>
