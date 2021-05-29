<%@ page
	language="java"
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
%><!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Suivi des repas - Ajouter un nouveau repas</title>
		
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
	</head>
	<body>
		<main class="container">
			<h1 class="text-center">AJOUT</h1>
			<%
				String date = null;
				String heure = null;
				String repas = null;
			
				if(request.getAttribute("error") != null) {
					date = request.getAttribute("date") != "" ? request.getAttribute("date").toString() : null;
					heure = request.getAttribute("heure") != "" ? request.getAttribute("heure").toString() : null;
					repas = request.getAttribute("repas") != "" ? request.getAttribute("repas").toString() : null;
			%>
			<div class="alert alert-danger" role="alert">
				<%= request.getAttribute("error") %>
			</div>
			<%
				}
			%>
			<form action="<%= request.getContextPath() %>/add" method="POST">
				<div class="mb-3">
					<label for="inputDate" class="form-label">Date</label>
					<input type="date" class="form-control" name="date" id="inputDate"<%= date != null ? " value=\""+request.getAttribute("date")+"\"" : "" %>>
				</div>
				<div class="mb-3">
					<label for="inputTime" class="form-label">Heure</label>
					<input type="time" class="form-control" name="heure" id="inputTime"<%= heure != null ? " value=\""+request.getAttribute("heure")+"\"" : "" %>>
				</div>
				<div class="mb-3">
					<label for="textRepas" class="form-label">Repas</label>
					<textarea class="form-control" name="repas" id="textRepas" rows="2" aria-describedby="repasHelp"><%= repas != null ? request.getAttribute("repas") : "" %></textarea>
					<div id="repasHelp" class="form-text">Aliments séparés par une virgule.</div>
				</div>
				<div class="container-fluid">
					<div class="row">
						<div class="col-6 text-center mb-3">
							<button type="submit" class="btn btn-primary w-75">Valider</button>
						</div>
						<div class="col-6 text-center mb-3">
							<a href="<%= request.getContextPath() %>/" class="btn btn-danger w-75">Annuler</a>
						</div>
					</div>
				</div>
			</form>
		</main>
	</body>
</html>