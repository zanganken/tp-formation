<%@ page
	language="java"
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
%><%@ taglib
	prefix="c"
	uri="http://java.sun.com/jsp/jstl/core"
 %><!DOCTYPE html>
<html class="h-100">
	<head>
		<meta charset="UTF-8">
		<title>Votre panier</title>
		
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
	</head>
	<body class="h-100 d-flex flex-column">
		<header class="navbar navbar-dark bg-dark sticky-top">
			<div class="container">
				<a href="./" class="navbar-brand">Courses</a>
				<span class="navbar-text">Votre panier</span>
			</div>
		</header>
		
		<main class="container py-3">
		<c:if test="${not empty param.id}">
			<h3 class="mb-0"></h3>
			
			<ul data-id="${param.id}" class="list-group list-group-flush mb-3"></ul>
		</c:if>
		</main>
		
		<footer class="navbar mt-auto border-top border-dark p-3">
			<div class="container">
				<a href="./" class="btn btn-dark btn-lg rounded-circle">
					<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-arrow-return-left" viewBox="0 0 16 16">
						<path fill-rule="evenodd" d="M14.5 1.5a.5.5 0 0 1 .5.5v4.8a2.5 2.5 0 0 1-2.5 2.5H2.707l3.347 3.346a.5.5 0 0 1-.708.708l-4.2-4.2a.5.5 0 0 1 0-.708l4-4a.5.5 0 1 1 .708.708L2.707 8.3H12.5A1.5 1.5 0 0 0 14 6.8V2a.5.5 0 0 1 .5-.5z"/>
					</svg>
				</a>
				<button id="uncheck" type="button" class="btn btn-dark btn-lg rounded-circle">
					<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-eraser-fill" viewBox="0 0 16 16">
						<path d="M8.086 2.207a2 2 0 0 1 2.828 0l3.879 3.879a2 2 0 0 1 0 2.828l-5.5 5.5A2 2 0 0 1 7.879 15H5.12a2 2 0 0 1-1.414-.586l-2.5-2.5a2 2 0 0 1 0-2.828l6.879-6.879zm.66 11.34L3.453 8.254 1.914 9.793a1 1 0 0 0 0 1.414l2.5 2.5a1 1 0 0 0 .707.293H7.88a1 1 0 0 0 .707-.293l.16-.16z"/>
					</svg>
				</button>
			</div>
		</footer>
		
		<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
		<script src="./assets/js/view.js"></script>
	</body>
</html>