<%@ page
	language="java"
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
%><!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Chifoumi - Faîtes votre choix</title>
		
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
	</head>
	<body><%
		String[] choix = {"Pierre", "Feuille", "Ciseaux"};
	%>
		<main class="container-fluid">
			<h1>TENTATIVE</h1>
			
			<form action="resultat" method="POST">
				<% for(int i = 0; i < choix.length; i++) { %>
					<input type="radio" class="btn-check" name="choix" id="<%= i %>" value="<%= i %>" onClick="this.form.submit()">
					<label class="btn btn-outline-primary" for="<%= i %>"><%= choix[i] %></label>
				<% } %>
			</form>
		</main>
	</body>
</html>