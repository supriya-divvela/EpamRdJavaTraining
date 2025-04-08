<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Quiz Application - Create a New Question</title>
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
		<h2 class="text-center my-4">Create a New Question</h2>
		<h3 class="text-center my-4" style="color: red">${cannotcreatequestion}</h3>
		<h3 class="text-center my-4" style="color: green">${createquestion}</h3>
		<div class="row justify-content-center">
			<div class="col-md-8">
				<form action="/addquestion" method="post">
					<div class="form-group">
						<label for="title">Question Title:</label> <input type="text"
							class="form-control" id="title" name="title" required>
					</div>
					<div class="form-group">
						<label for="options">Options:</label> <input class="form-control"
							id="options" name="options" required>
					</div>
					<div class="form-group">
						<label for="difficulty">Difficulty:</label> <select
							class="form-control" id="difficulty" name="difficulty" required>
							<option value="easy">Easy</option>
							<option value="medium">Medium</option>
							<option value="difficult">Difficult</option>
						</select>
					</div>
					<div class="form-group">
						<label for="taggingTopic">Tagging Topic:</label> <input
							class="form-control" id="taggingTopic" name="taggingTopic"
							required>
					</div>
					<div class="form-group">
						<label for="answers">Answers:</label> <input class="form-control"
							id="answers" name="answers" required>
					</div>
					<div class="form-group">
						<label for="marks">Marks:</label> <input class="form-control"
							id="marks" name="marks" required>
					</div>
					<div class="form-group">
						<button class="btn btn-success ml-3">Create Question</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<footer class="bg-dark text-white text-center py-2">
		<p>&copy; 2023 Quiz Application. All rights reserved.</p>
	</footer>
</body>
</html>
