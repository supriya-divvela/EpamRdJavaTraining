<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
<title>Quiz Application - Update Quiz</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<a class="navbar-brand" href="/adminhome">Admin Home</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarNav" aria-controls="navbarNav"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarNav">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item active"><a class="nav-link"
					href="/adminregister">Admin Register</a></li>
				<li class="nav-item active"><a class="nav-link"
					href="/questionpage">Question Library</a></li>
				<li class="nav-item active"><a class="nav-link"
					href="/quizpage">Quiz Library</a></li>
				<li class="nav-item active"><a class="nav-link"
					href="/createquestion">Create Question</a></li>
				<li class="nav-item active"><a class="nav-link"
					href="/createquiz">Create Quiz</a></li>
			</ul>
			<ul class="navbar-nav ml-auto">
				<li class="nav-item active"><a class="nav-link"
					href="/adminlogin">Logout</a></li>
			</ul>
		</div>
	</nav>

	<div class="container my-4">
		<h2 class="text-center my-4">Update Quiz</h2>
		<div class="row justify-content-center">
			<div class="col-md-8">
				<form action="/updatequizwithdetails" method="post">
					<div class="form-group">
						<label for="qNo">Quiz Number:</label> <input type="text"
							class="form-control" id="quizNo" name="quizNo"
							value="${quiz.getId()}" required readonly>
					</div>
					<div class="form-group">
						<label for="title">Quiz Title:</label> <input type="text"
							class="form-control" id="title" name="title"
							value="${quiz.getTitle()}" required>
					</div>
					<div class="dropdown">
						<button class="btn btn-secondary dropdown-toggle" type="button"
							id="optionsDropdown" data-toggle="dropdown" aria-haspopup="true"
							aria-expanded="false">Select Questions</button>
						<div class="dropdown-menu" aria-labelledby="optionsDropdown">
							<div class="form-check">
								<c:forEach var="question" items="${listOfQuestions}">
									<c:set var="contains" value="" />
									<c:forEach var="item" items="${questionnumbers}">
										<c:if test="${item eq question.getId()}">
											<c:set var="contains" value="checked" />
										</c:if>
									</c:forEach>
									<input class="form-check-input" type="checkbox"
										name="listOfQuestions" value="${question.getId()}"
										${contains} />
									<label class="form-check-label" for="option1">${question}</label>
								</c:forEach>
							</div>
						</div>
					</div>
					<br />
					<div class="form-group">
						<button class="btn btn-success ml-3">Update Quiz</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<br />
	<br />
	<br />
	<footer class="bg-dark text-white text-center py-1">
		<p>&copy; 2023 Quiz Application. All rights reserved.</p>
	</footer>
</body>
</html>
