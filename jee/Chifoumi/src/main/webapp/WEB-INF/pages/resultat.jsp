<%@ page
	language="java"
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
%><!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Chifoumi - Résultat</title>
		
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
	</head>
	<body>
		<main class="container-fluid">
			<h1>RESULTAT</h1>
			
			<p>
				Vous avez joué <strong><%= request.getAttribute("choixUtilisateur") %></strong>.<br>
				Le serveur a joué <strong><%= request.getAttribute("choixServeur") %></strong>.
			</p>
			<p>
				<%= request.getAttribute("resultat") %><br>
				<a href="<%= request.getContextPath() %>">Rejouer</a>
			</p>
		</main>
	</body>
</html>