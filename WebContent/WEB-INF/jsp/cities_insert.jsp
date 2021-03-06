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
	<h1>Cities insert page.</h1>
	</div>
	<h4>
		Insert cities here. <br />
		You can insert multiple cities.
		Use comma, semicolon or new line as a delimiter.
	</h4>
	<form action="" method="POST">
		<textarea rows="10" cols="70" name="new_cities"></textarea><br /><br />
		<input type="submit" value="Add New Cities" />
	</form>
	<p>
		<a href="../j_spring_security_logout">Logout</a>
	</p>
	</div>
</body>
</html>