<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Quiz Application - Admin Registration</title>
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
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarNav" aria-controls="navbarNav"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarNav">
			<ul class="navbar-nav">
				<li class="nav-item active"><a class="nav-link"
					href="/adminhome">Admin Home</a></li>
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

	<div class="container my-3">
		<div class="row justify-content-center">
			<div class="col-md-6">
				<div class="card">
					<div class="card-header">Admin Registration Form</div>
					<h3 style="color: red;">${passwordincorrect }
						${alreadyregistered } ${adminregistered}</h3>
					<div class="card-body">
						<form action="/addadmin" method="post">
							<div class="form-group">
								<label for="username">Username</label> <input type="text"
									class="form-control" id="username" name="username" required>
							</div>
							<div class="form-group">
								<label for="password">Password</label> <input type="password"
									class="form-control" id="password" name="password" required>
							</div>
							<div class="form-group">
								<label for="confirm_password">Confirm Password</label> <input
									type="password" class="form-control" id="confirm_password"
									name="confirmpassword" required>
							</div>
							<button type="submit" class="btn btn-primary">Register</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<br />
	<br />
	<footer class="bg-dark text-white text-center py-1">
		<p>&copy; 2023 Quiz Application. All rights reserved.</p>
	</footer>
</body>
</html>
