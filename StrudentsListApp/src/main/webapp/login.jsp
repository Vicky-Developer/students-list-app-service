<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
</head>
<body>
	<h1>Login</h1>
	<form action="login" method="post">
		<table>
			<tr>
				<td>User :</td>
				<td><input type='text' name='username' value='' /></td>
			</tr>
			<tr>
				<td>Password :</td>
				<td><input type='password' name='password' value='' /></td>
			</tr>
			<tr>
				<td><input type='submit' name='submit' value='submit' /></td>
			</tr>
		</table>

	</form>
</body>
</html>