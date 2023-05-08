<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>  
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="/webjars/bootstrap/4.5.2/css/bootstrap.min.css" />
<script src="/webjars/jquery/3.5.1/jquery.min.js"></script>
<script src="/webjars/bootstrap/4.5.2/js/bootstrap.min.js"></script> 
<meta charset="ISO-8859-1">
<title>Add a movie</title>
</head>
<body>
	<h1>
		Add a Movie
		
	</h1>
	<form:form action="/process/create" method="post" modelAttribute="movie">
	    
	    <div class="form-group">
	        <form:label path="title">Title: </form:label>
	        <form:input type="text" path="title" class="form-control"/>
	        <form:errors path="title" style="color:red"/>
	    </div>
	    <div class="form-group">
	        <form:label path="duration">Duration: </form:label>
	        <form:input type="number" path="duration" class="form-control" />
	        <form:errors path="duration" style="color:red"/>
	    </div>
	    <div class="form-group">
	        <form:label path="price">Price: </form:label>
	        <form:input type="number" path="price" class="form-control" />
	        <form:errors path="price" style="color:red"/>
	    </div>
	    <div class="form-group">
	        <form:label path="room">Room: </form:label>
	        <form:input type="number" path="room" class="form-control" />
	        <form:errors path="room" style="color:red"/>
	    </div>
	    <div class="form-group">
	        <form:label path="seat">Seat: </form:label>
	        <form:input type="text" path="seat" class="form-control" />
	        <form:errors path="seat" style="color:red"/>
	    </div>
	    <div class="form-group">
	        <form:label path="posterImage">Image URL: </form:label>
	        <form:input type="text" path="posterImage" class="form-control" />
	        <form:errors path="posterImage" style="color:red"/>
	    </div>
	    <input type="submit" value="Add a Movie" class="btn btn-primary" />
	    <a href="/" class="btn btn-warning">Back to Library</a>
	</form:form>
</body>
</html>