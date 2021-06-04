<%@ page
	language="java"
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
%><%@
	taglib
	prefix="c"
	uri="http://java.sun.com/jsp/jstl/core"
%><%@
	taglib
	prefix="fmt"
	uri="http://java.sun.com/jsp/jstl/fmt"
%><!DOCTYPE html>
<html>
	<c:if test="${!empty cookie.lang}">
		<fmt:setLocale value="${cookie.lang.value}"/>
	</c:if>
	<fmt:setBundle basename="fr.eni.chifoumi.translations" var="r" />
	<head>
		<meta charset="UTF-8">
		<fmt:message key="results" bundle="${r}" var="results"></fmt:message>
		<title>Chifoumi - ${results}</title>
		
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
	</head>
	<body>
		<main class="container-fluid pt-3">
			<h1 class="text-uppercase">${results}</h1>
			
			<p>
				<fmt:message key="you_played" bundle="${r}"></fmt:message> <strong><fmt:message key="${choixUtilisateur}" bundle="${r}"></fmt:message></strong>.<br>
				<fmt:message key="server_played" bundle="${r}"></fmt:message> <strong><fmt:message key="${choixServeur}" bundle="${r}"></fmt:message></strong>.
			</p>
			<p>
				<fmt:message key="${resultat}" bundle="${r}"></fmt:message><br>
				<a href="${pageContext.request.contextPath}/"><fmt:message key="replay" bundle="${r}"></fmt:message></a>
			</p>
		</main>
	</body>
</html>