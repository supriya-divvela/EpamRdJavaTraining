<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>User Page</title>
<!-- jQuery -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.0.2/css/bootstrap.min.css">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.0.2/js/bootstrap.min.js"></script>
<!-- Bootstrap -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.6.0/css/bootstrap.min.css"
	integrity="sha512-qWPJqVC3xEp1LcSDZwNKjZro+wH2JshqwRft/Wf/HvTwXj+YGKY6BzCJAZ5U2Z5U6Z4UOqlzzJ4HXR+AYyOg6A=="
	crossorigin="anonymous" />
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.6.0/js/bootstrap.min.js"
	integrity="sha512-DQcVtRjEAbJYgn8WUE3qT3j+e1CJNl9Nn/XrOZrJzV7+LZdWgdVHhKpylzFzVJl2y+HJ9l0+vJtR+jRtMwLyZw=="
	crossorigin="anonymous"></script>

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<a class="navbar-brand" href="/user">User Home</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarNav" aria-controls="navbarNav"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="navbar-nav ml-auto">
			<ul class="navbar-nav ml-auto">
				<li class="nav-item active"><a class="nav-link"
					href="/userlogin">Logout</a></li>
			</ul>

		</div>
	</nav>
	<div class="container mt-4">
		<h3 class="text-center my-4">Quiz Attempt Page</h3>
		<h3 style="color: red;">${emptyquizlibrary }</h3>
		<form action="/marks" method="get">
			<input type="text" name="quizNo" value="${quizNo}" hidden>
			<table class="table table-striped">
				<caption>Questions in the quiz</caption>
				<thead>
					<tr>
						<th scope="col">Question</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="question" items="${questions}" varStatus="status">
						<tr>
							<td>${ question}<br/><c:forEach var="option"
									items="${question.getOptions()}" varStatus="loop">
									<input class="form-check-input" type="checkbox"
										name="${question.getId() }" value="${loop.index+1}" style="margin-left:30px;">
									<label class="form-check-label" for="option1" style="margin-left:60px;">${option}</label>
									<br />
								</c:forEach>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div class="form-group">
				<button class="btn btn-success ml-3">Submit Quiz</button>
			</div>
		</form>
	</div>
	<footer class="bg-dark text-white text-center py-2">
		<p>&copy; 2023 Quiz Application. All rights reserved.</p>
	</footer>
</body>
</html>
