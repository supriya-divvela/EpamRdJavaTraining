<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Quiz Application - User Login</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<!-- Popper JS -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<!-- Bootstrap JS -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<a class="navbar-brand" href="/">Quiz Application</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarNav" aria-controls="navbarNav"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarNav">
			<ul class="navbar-nav ml-auto">
				<li class="nav-item active"><a class="nav-link" href="adminlogin">Admin
						Login Page</a></li>
			</ul>
		</div>
	</nav>

	<div class="container my-5">
		<div class="row justify-content-center">
			<div class="col-lg-6">
				<h1 class="text-center mb-4">User Login</h1>
				<h3 style="color: red;">${invaliduser}${alreadyregistered }
					${userregisterd }</h3>
				<form action="/verifyuser">
					<div class="form-group">
						<label for="username">Username</label> <input type="text"
							class="form-control" id="username" name="username" required>
					</div>
					<div class="form-group">
						<label for="password">Password</label> <input type="password"
							class="form-control" id="password" name="password" required>
					</div>
					<div class="form-group">
						<input type="text" value="user" name="usertype" hidden>
					</div>
					<div class="form-group">
						<button type="submit" class="btn btn-primary">Login</button>
						<a href="userregister" class="btn btn-success ml-3">Register</a>
					</div>
				</form>
			</div>
		</div>
	</div>
	<br />
	<br />
	<br />
	<footer class="bg-dark text-white text-center py-2">
		<p>&copy; 2023 Quiz Application. All rights reserved.</p>
	</footer>
</body>
</html>
