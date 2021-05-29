<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.util.Hashtable"%>
<%@page import="fr.eni.suiviDesRepas.bo.Repas"%>
<%@page import="java.util.Map"%>
<%@ page
	language="java"
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
%><!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Suivi des repas - Visualisation des repas</title>
		
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
	</head>
	<body>
		<main class="container-fluid">
			<table class="table">
				<thead>
					<tr>
						<th>Date</th>
						<th>Heure</th>
						<th>Aliments</th>
					</tr>
				</thead>
				<tbody>
				<%
					@SuppressWarnings("unchecked")
					Map<Integer, Repas> repasMap = (Map<Integer, Repas>) request.getAttribute("repasMap");
				
					for(Repas r : repasMap.values()) {
				%>
					<tr>
						<td><%= r.getDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) %></td>
						<td><%= r.getDateTime().format(DateTimeFormatter.ofPattern("HH:mm:ss")) %></td>
						<td><%= r.getAliments() %></td>
					</tr>
				<%
					}
				%>
				</tbody>
			</table>
			
			<nav>
				<div class="container">
					<div class="row">
						<div class="col-lg-6 mb-3">
							<a href="<%= request.getContextPath() %>/add" class="btn btn-outline-primary btn-lg d-block">Ajouter un nouveau repas</a>
						</div><div class="col-lg-6 mb-3">
							<a href="<%= request.getContextPath() %>/" class="btn btn-outline-primary btn-lg d-block">Retour à l'accueil</a>
						</div>
					</div>
				</div>
			</nav>
		</main>
	</body>
</html>