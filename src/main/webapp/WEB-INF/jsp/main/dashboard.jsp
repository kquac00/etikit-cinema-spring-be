<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
    href="/webjars/bootstrap/4.5.2/css/bootstrap.min.css" />
<script src="/webjars/jquery/3.5.1/jquery.min.js"></script>
<script src="/webjars/bootstrap/4.5.2/js/bootstrap.min.js"></script> 
<meta charset="ISO-8859-1">
<title>Admin access</title>
</head>
<body> 
	<h1>
		Welcome, <c:out value="${loggedInUser.userName}"/>
		
	</h1>
	<h3>Etikit Movie Library
	<a style="margin-left:600px" href="/create" class="btn btn-success">Add a Movie</a>
	</h3>
	<a href="/admin/logout" class="btn btn-warning">logout</a>
	<table class="table table-striped">
		<thead>
			<tr>
				<th>Id<th>
				<th>Title</th>
				<th>Duration</th>
				<th></th>
				<th></th>
			</tr>	
		</thead>
		<tbody>
			<c:forEach var="movie" items="${allMovie}">
				<tr>
					<td>${movie.id}</td>
					<td>${movie.title}</td>
					<td>${movie.duration}</td>
					<td><a class="btn btn-outline-warning" href="/edit/${movie.id}">Edit</a>
						<form action="/delete/${movie.id}" method="post">
							<input type="hidden" name="_method" value="delete"/>
							<input type="submit" value="Delete" class="btn btn-outline-danger btn-dark"/>
						</form>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<a href="/admin/logout" class="btn btn-warning">logout</a>
</body>
</html>