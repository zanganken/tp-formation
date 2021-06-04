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
%><%@
	taglib
	prefix="fn"
	uri="http://java.sun.com/jsp/jstl/functions"
%><!DOCTYPE html>
<html>
	<c:if test="${!empty cookie.lang}">
		<fmt:setLocale value="${cookie.lang.value}"/>
	</c:if>
	<fmt:setBundle basename="fr.eni.chifoumi.translations" var="r" />
	<head>
		<meta charset="UTF-8">
		<title>Chifoumi - <fmt:message key="make_your_choice" bundle="${r}"></fmt:message></title>
		
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
	</head>
	<body>
		<main class="container-fluid pt-3">
			<div class="row g-3">
				<form class="col-sm-6" action="setLanguage" method="POST" onchange="this.submit()">
					<label class="form-label fw-bold" for="language"><fmt:message key="choose_language" bundle="${r}"></fmt:message></label>
					<select class="form-select" id="language" name="lang">
						<option value="default"><fmt:message key="default" bundle="${r}"></fmt:message></option>
						<option value="fr"${ cookie.lang.value == 'fr' ? ' selected': '' }>Français</option>
						<option value="en"${ cookie.lang.value == 'en' ? ' selected': '' }>English</option>
					</select>
				</form>
				<div class="col-12">
					<fmt:message key="attempt" bundle="${r}" var="attempt"></fmt:message>
					<h1 class="text-uppercase"><fmt:message key="attempt" bundle="${r}"></fmt:message></h1>
					
					<form action="resultat" method="POST">
						<div class="row g-3">
						<c:set var="choices" value="${['rock', 'paper', 'scissors']}"></c:set>
						<c:forEach var="i" begin="0" end="${fn:length(choices) - 1}">
							<div class="col-sm-4">
								<input type="radio" class="btn-check" name="choix" id="${i}" value="${i}" onClick="this.form.submit()">
								<label class="btn btn-outline-primary w-100" for="${i}">
									<fmt:message key="${choices[i]}" bundle="${r}"></fmt:message>
								</label>
							</div>
						</c:forEach>
						</div>
					</form>
				</div>
			</div>
		</main>
	</body>
</html>