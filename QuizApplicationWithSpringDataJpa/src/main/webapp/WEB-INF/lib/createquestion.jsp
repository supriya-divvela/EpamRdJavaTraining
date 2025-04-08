<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Create Question Page</title>
</head>
<body>
	<a href="adminhome">Admin Home Page</a>
	<a href="questionpage">Questions Page</a>
	<br />
	<a href="createquestion">create question</a>
	<br />
	<a href="logout">Logout</a>
	<br />Create a new Question Page
	<br />
	<br />
	<br /> ${duplicatequestion} ${createquestion}
	<form action="/addquestion" method="post">
		<input type="text" placeholder="Enter Question Number" name="qNo"
			required><br /> <input type="text" placeholder="Enter title"
			name="title" required><br /> <input type="text"
			placeholder="Enter options seperated by comma.." name="options"
			required><br /> <input type="text"
			placeholder="Enter difficulty" name="difficulty" required><br />
		<input type="text" placeholder="Enter tagging topic"
			name="taggingTopic" required><br /> <input type="text"
			placeholder="Enter answers seperated by comma" name="answers"
			required><br /> <input type="number"
			placeholder="Enter marks" name="marks" required><br />
		<button id="submit" type="submit">create question</button>
	</form>
</body>
</html>
