<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false" import="java.util.*"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Update Question Page</title>
</head>
<body>
	<a href="createquestion">create question</a>
	</br>
	<a href="questionpage">Questions Page</a>
	<br />
	<a href="logout">Logout</a>
	<br />Update Question Page
	<form action="/updatequestionwithdetails" method="post">
		<input type="text" placeholder="Enter Question Number" name="qNo"
			value="${question.getQNo()}" required readonly><br /> <input
			type="text" placeholder="Enter title" name="title"
			value="${question.getTitle()}" required><br /> <input
			type="text" placeholder="Enter options seperated by comma.."
			name="options" value="${options }" required><br /> <input
			type="text" placeholder="Enter difficulty" name="difficulty"
			value="${question.getDifficulty() }" required><br /> <input
			type="text" placeholder="Enter tagging topic" name="taggingTopic"
			value="${question.getTaggingTopic() }" required><br /> <input
			type="text" placeholder="Enter answers seperated by comma"
			name="answers" value="${answers}" required><br /> <input
			type="number" placeholder="Enter marks"
			value="${question.getMarks() }" name="marks" required><br />
		<button id="submit" type="submit">update question</button>
	</form>
</body>
</html>