<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<fmt:setLocale value="pt_BR"/> 
		
		<title>Vote no Restaurante</title>
		
		<link href="/vote-no-restaurante/views/resources/css/bootstrap/css/bootstrap.min.css" type="text/css"	rel="stylesheet" />
		<link href="/vote-no-restaurante/views/resources/css/vnr.css" type="text/css" rel="stylesheet" />
		
		<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
		<!--[if lt IE 9]>
		    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
		    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
		    <![endif]-->
	</head>
	<script type="text/javascript">
		function showRestaurantsToVote() {
			var form = document.getElementById('formPrincipal');
			form.executeAction.value = null;
			form.submit();
		}

		//      TODO: Desabilitado para forcar o usuario a cadastrar seus dados para ver o ranking. 
		// 		function showRanking() {
		// 			var form = document.getElementById('formPrincipal');
		// 			form.executeAction.value = "showRanking";
		// 			form.submit();
		// 		}
		
	</script>
	
	<body style="background: rgb(79, 70, 136) none repeat scroll 0% 0%;">
		<%@ include file="menu.jsp"%>