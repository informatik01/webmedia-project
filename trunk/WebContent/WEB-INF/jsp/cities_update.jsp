<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Home Page</title>
<link type="text/css" rel="stylesheet" href="<c:url value="/css/main.css" />" />
</head>
<body>
	<div id="header">
		<img src="<c:url value="/images/header.png" />" alt="Header" />
	</div>
	<div id="mainContent">
	<div id="navMenu">
		<ul>
			<li><a href="../home">Home</a></li>
			<li><a href="../cities">Manage Cities Information</a>
				<ul>
					<li><a href="new">Insert City Data</a></li>
					<li><a href="update">Update City Data</a></li>
				</ul>
			</li>
			<li><a href="#">Manage Weather Information</a>
				<ul>
					<li><a href="../weather/new">Insert Weather Data</a></li>
					<li><a href="../weather/update">Update Weather Data</a></li>
				</ul>
			</li>
			
		</ul>
		
		<br class="clearFloat" />
	</div>
	<div style="min-height: 30px; padding-top: 25px;  font-stretch: expanded;">
	<h1>
	Cities update page.
	</h1>
	</div>
	<h2>Edit list of cities, currently stored in the database.</h2>
	
	<form:form modelAttribute="cityForm" method="POST" acceptCharset="UTF-8" >
		
		<ol>
		<c:forEach items="${cityForm.cities}" var="city" varStatus="status">
				<li>
					<input type="checkbox" name="checkedCities" value="${city.id}" />
					<form:input path="cities[${status.index }].name" size="40"/> (id = ${city.id})
				</li>
		</c:forEach>
		</ol>
		<input type="submit" name="update" value="Update Selected Fields" />
		<input type="submit" name="delete" value="Delete Selected Fields" />
	</form:form>
	<p>
		<a href="../j_spring_security_logout">Logout</a>
	</p>
	</div>
</body>
</html>