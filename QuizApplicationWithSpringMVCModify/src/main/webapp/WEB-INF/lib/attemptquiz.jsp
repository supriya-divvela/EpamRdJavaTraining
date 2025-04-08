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
					<c:forEach var="question" items="${questions}">
						<tr>
							<td>${ question}<c:forEach var="option"
									items="${question.getOptions()}" varStatus="loop">
									<div class="form-check">
										<input class="form-check-input" type="checkbox" name="answers"
											value="${loop.index+1}" id="answers"> <label
											class="form-check-label" for="answers">${option}</label>
									</div>
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
