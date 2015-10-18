
<div class="navbar navbar-default">
	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar" aria-expanded="false"
					aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="${linkTo[HomeController].votar}">
					Vote no Restaurante <span class="glyphicon glyphicon-cutlery"></span>
				</a>
			</div>
			<div id="navbar" class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li><a onclick="javascript:showRestaurantsToVote()">Votar</a></li>
					<c:if test="${haveVoted}">
						<li><a onclick="javascript:showRanking()">Ranking</a></li>
					</c:if>
				</ul>
			</div>
		</div>
	</nav>
</div>