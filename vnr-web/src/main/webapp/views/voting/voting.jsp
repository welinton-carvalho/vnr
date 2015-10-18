<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<%@ include file="../header.jsp"%>

<%
	Cookie firstName = new Cookie("haveVoted",	request.getParameter("first_name"));
%>
<script type="text/javascript">

	function doVote() {
		var form = document.getElementById('formPrincipal');
		if(form.idRestaurantFirstVote.value != "" && form.idRestaurantSecondVote.value != "") {
			form.executeAction.value = "doVote";
			form.submit();
		} else {
			alert("Você deve escolher os restaurantes antes de clicar em votar!");
		}
	}
	
	function showRanking() {
		var form = document.getElementById('formPrincipal');
		form.executeAction.value = "showRanking";
		form.submit();
	}
	
	function selectFirtsRestaurant(id) {
		var form = document.getElementById('formPrincipal');
		form.idRestaurantFirstVote.value = id;
		$("#panelRestaurantsTwo").show();
	}
	
	function selectSecondRestaurant(id) {
		var form = document.getElementById('formPrincipal');
		form.idRestaurantSecondVote.value = id;
	}
	
	$("#radioGroupTwoRestaurants").buttonset();
	$("#radioGroupAnotherRestaurants").buttonset();
	
</script>

<div class="container">
	<div class="col-md-12">
		<form id="formPrincipal" method="POST" action="/vnr-web/HomeController">
			
			<input type="hidden" name="executeAction" value="" />
			<input type="hidden" name="idRestaurantFirstVote" value="" />
			<input type="hidden" name="idRestaurantSecondVote" value="" />

			<h3>Vote no seu restaurante preferido:</h3>
			<div class="col-md-12">
				<div id="radioGroupTwoRestaurants">
					<c:forEach items="${firstTwoRestaurants}" var="restaurant" varStatus="loop">
						<input id="radioTwo${restaurant.id}" type="radio" name="firstPreference" value="${restaurant.id}" onclick="javascript:selectFirtsRestaurant('${restaurant.id}')" />
						<label for="radioTwo${restaurant.id}"><img src="<c:url value='/views/resources/img/restaurants/${restaurant.logo}'/>" alt="${restaurant.name}" class="img-responsive img-rounded"/></label>
					</c:forEach>
			  	</div>
			</div>

			<br>
			<br>
			
			<div id="panelRestaurantsTwo" style="display: none">
				<h3>E qual desses outros restaurantes você gosta mais?</h3>
				<div class="col-md-12">
					<div id="radioGroupAnotherRestaurants">
						<c:forEach items="${anothersRestaurants}" var="restaurant" varStatus="loop">
							<input id="radioAnother${restaurant.id}" type="radio" name="secondPreference" value="${restaurant.id}" onclick="javascript:selectSecondRestaurant('${restaurant.id}')"/> 
							<label for="radioAnother${restaurant.id}"><img src="<c:url value='/views/resources/img/restaurants/${restaurant.logo}'/>" alt="${restaurant.name}" class="img-responsive img-rounded"/></label>
						</c:forEach>
					</div>
				</div>
			</div>

			<br>
			<br>

			<div class="col-md-2 col-md-offset-5" style="text-align: center">
				<button type="button" class="btn btn-default btn-lg"
					onclick="javascript:doVote()">Votar</button>
			</div>
		</form>
	</div>
</div>

<%@ include file="../footer.jsp"%>