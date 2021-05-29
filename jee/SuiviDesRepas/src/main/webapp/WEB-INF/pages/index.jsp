<%@ page
	language="java"
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
%><!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Suivi des repas - Accueil</title>
		
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
	</head>
	<body>
		<header class="container-fluid">
			<h1 class="text-center">ACCUEIL</h1>
			<nav>
				<div class="container">
					<div class="row">
						<div class="col-lg-6 mb-3">
							<a href="<%= request.getContextPath() %>/add" class="btn btn-outline-primary btn-lg d-block">Ajouter un nouveau repas</a>
						</div><div class="col-lg-6 mb-3">
							<a href="<%= request.getContextPath() %>/view" class="btn btn-outline-primary btn-lg d-block">Visualiser les repas</a>
						</div>
					</div>
				</div>
			</nav>
		</header>
	</body>
</html>