<%@ page
	language="java"
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
%><!DOCTYPE html>
<html class="h-100">
	<head>
		<meta charset="UTF-8">
		<title>Listes de courses prédéfinies</title>
		
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
	</head>
	<body class="h-100 d-flex flex-column">
		<header class="navbar navbar-dark bg-dark sticky-top">
			<div class="container">
				<a href="./" class="navbar-brand">Courses</a>
				<span class="navbar-text">${not empty param.id ? 'Éditer la liste' : 'Créer une nouvelle liste'}</span>
			</div>
		</header>
		
		<main class="container py-3">
			<form${not empty param.id ? ' data-id="'.concat(param.id).concat('"') : null} action="./api/listesCourses${not empty param.id ? '/'.concat(param.id) : null}" method="post" autocomplete="off">
				<div class="form-floating">
					<input type="text" class="form-control" name="nom" id="nomListe" required>
					<label for="nomListe">Nom de la liste</label>
				</div>
				
				<ul class="list-group list-group-flush mb-3">
					<li id="addItem" class="list-group-item list-group-item-dark d-flex mt-3">
						<div class="row g-2 w-75">
							<div class="col-9 col-md-10">
								<input type="text" class="form-control" name="article" placeholder="Article" required>
							</div>
							<div class="col-3 col-md-2">
								<input type="number" min="1" max="255" value="1" class="form-control" name="qte" placeholder="Quantité" required>
							</div>
						</div>
						<button class="btn btn-dark btn-lg rounded-circle ms-auto" type="submit">+</button>
					</li>
				</ul>
			</form>
		</main>
		
		<footer class="navbar mt-auto border-top border-dark p-3">
			<div class="container justify-content-end">
				<a href="./" class="btn btn-dark btn-lg rounded-circle">
					<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-arrow-return-right" viewBox="0 0 16 16">
						<path fill-rule="evenodd" d="M1.5 1.5A.5.5 0 0 0 1 2v4.8a2.5 2.5 0 0 0 2.5 2.5h9.793l-3.347 3.346a.5.5 0 0 0 .708.708l4.2-4.2a.5.5 0 0 0 0-.708l-4-4a.5.5 0 0 0-.708.708L13.293 8.3H3.5A1.5 1.5 0 0 1 2 6.8V2a.5.5 0 0 0-.5-.5z"/>
					</svg>
				</a>
			</div>
		</footer>
		
		<div class="modal fade" id="delete" tabindex="-1">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title">Supprimer l'article</h5>
						<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
					</div>
					<div class="modal-body">
						<p>Êtes-vous sûr de vouloir supprimer l'article ?</p>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-primary" data-bs-dismiss="modal">Annuler</button>
						<button id="deleteBtn" type="button" class="btn btn-danger">Supprimer</button>
					</div>
				</div>
			</div>
		</div>
		
		<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.min.js" integrity="sha384-Atwg2Pkwv9vp0ygtn1JAojH0nYbwNJLPhwyoVbhoPwBhjQPR5VtM2+xf0Uwh9KtT" crossorigin="anonymous"></script>
		<script src="./assets/js/edit.js"></script>
	</body>
</html>