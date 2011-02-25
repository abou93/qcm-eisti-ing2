<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/>
	<title>Authentification</title>
	<style type="text/css">	<%@ include file="style.css" %>	</style>
</head>
<body class="body">
	<h1>Login</h1>
	
	<p>Veuillez vous connecter pour accéder aux QCMs</p>
	<%
		String msg = (String) request.getSession().getAttribute("msgErr");
		if (msg != null) {
			%>
			<p><%= msg %></p>
			<%
		}
	%>
	<br/>
	<form method="post" action="ControleurListeQCMs">
		<table>
			<tr><td>Login</td><td><input type="text" name="login" /></td></tr>
			<tr><td>Password</td><td><input type="password" name="pwd" /></td></tr>
		</table>
		<input type="submit" name="Login" value="Login" />
	</form>
</body>
</html>