<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Quiz Application</title>
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
		<a class="navbar-brand" href="/">Quiz Application</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarNav" aria-controls="navbarNav"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarNav">
			<ul class="navbar-nav">
				<li class="nav-item active"><a class="nav-link" href="/">Home
						<span class="sr-only">(current)</span>
				</a></li>
			</ul>
		</div>

		<div class="collapse navbar-collapse" id="navbarNav">
			<ul class="navbar-nav ml-auto">
				<li class="nav-item active"><a class="nav-link"
					href="adminlogin">Admin</a></li>
				<li class="nav-item active"><a class="nav-link"
					href="userlogin">User</a></li>
			</ul>
		</div>
	</nav>
	<br />
	<br />
	<br />
	<div class="container my-5">
		<div class="row justify-content-center">
			<div class="col-lg-6">
				<h1 class="text-center">Welcome to Quiz Application</h1>
				<p class="text-center">This is a quiz application where you can
					test your knowledge on various topics. Please select one of the
					following options:</p>
				<div class="d-flex justify-content-center my-3">
					<a href="adminlogin" class="btn btn-primary mx-3">Admin</a> <a
						href="userlogin" class="btn btn-primary mx-3">User</a>
				</div>
			</div>
		</div>
	</div>
	<br />
	<br />
	<br />
	<br /></br>
	<footer class="bg-dark text-white text-center py-2">
		<p>&copy; 2023 Quiz Application. All rights reserved.</p>
	</footer>
</body>
</html>