<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<br>
	<br>
	<div align="center">
	<table border="1" align="center" width="80%" height="60vh"
			cellspacing="0" cellpadding="3">
		<tr>My Page</tr></table>


		<form action="/chat/main" method="POST">
			<p>Name</p>
			<input type="text" name="myName" value="${name}">
			<p>Profile</p>
			<input type="text" name="myProfile" value="${profile}">
			<p></p>
			<br> <input type="submit" value="Update" name="newProfile">
		</form>
		<form action="/chat/main" method="POST">
			<input type="submit" value="Back">
		</form>
	</div>

</body>
</html>