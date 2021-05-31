<%@page import="java.util.Map"%>
<%@ page
	language="java"
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
%><!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Accueil - TP Préférences d'usage de l'application</title>
		
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
	</head>
	<body class="<%= session.getAttribute("color") %>">
		<main class="container-fluid pt-3">
			<h1>Accueil</h1>
			
			<form action="<%= request.getContextPath() %>/" method="POST" class="mt-3">
				<div class="container-fluid">
					<div class="row">
						<div class="col mb-3">
							<select name="color" id="colorTheme" class="form-select" aria-label="Sélection de la couleur d'affichage">
								<%
									@SuppressWarnings("unchecked")
									Map<String, String> colors = (Map<String, String>) application.getAttribute("colors");
								
									for(Map.Entry<String, String> entry : colors.entrySet()) {
								%>
								<option value="<%= entry.getKey() %>" class="<%= entry.getValue() %>"<%= entry.getValue() == session.getAttribute("color") ? " selected" : "" %>>
									<%= entry.getKey().toUpperCase().charAt(0) + entry.getKey().substring(1) %>
								</option>
								<% } %>
							</select>
						</div>
						
						<div class="col mb-3">
							<input type="submit" class="btn btn-primary" value="Valider">
						</div>
					</div>
				</div>
			</form>
			
			<p>
				Vous êtes venu <strong><%= session.getAttribute("counter") %></strong> fois.
			</p>
		</main>
	</body>
</html>