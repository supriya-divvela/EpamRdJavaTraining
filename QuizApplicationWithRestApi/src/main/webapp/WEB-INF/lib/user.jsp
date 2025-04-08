<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>User Page</title>
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
		<h3 class="text-center my-4">Welcome to User Page</h3>
		<h3 style="color: red;">${emptyquizlibrary }</h3>
		<table class="table table-striped">
			<caption>List Of Available Quizes</caption>
			<thead>
				<tr>
					<th scope="col">Quiz Number</th>
					<th scope="col">Title</th>
					<th scope="col">List Of Questions</th>
					<th scope="col">Attempt Quiz</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="quiz" items="${listOfQuizs}">
					<tr>
						<td>${ quiz.getId()}</td>
						<td>${ quiz.getTitle()}</td>
						<td>${ quiz.getListOfQuestions()}</td>
						<td><div class="form-group">
								<a href="attemptquiz?quizNo=${quiz.getId()}"
									class="btn btn-success ml-3">Attempt Quiz</a>
							</div></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<footer class="bg-dark text-white text-center py-2">
		<p>&copy; 2023 Quiz Application. All rights reserved.</p>
	</footer>
</body>
</html>
