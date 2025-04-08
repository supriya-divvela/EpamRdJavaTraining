<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<a href="adminhome">Admin Home Page</a> ${passwordincorrect }
	${alreadyregistered } ${adminregistered}
	<br />
	<form action="/addadmin" method="post">
		<input type="text" placeholder="Enter Username" name="username"
			required><br /> <input type="password"
			placeholder="Enter Password" name="password" required><br />
		<input type="password" placeholder="Enter Confirm Password"
			name="confirmpassword" required><br />
		<button type="submit">Register</button>
	</form>
</body>
</html>