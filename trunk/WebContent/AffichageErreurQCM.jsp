<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.io.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<style type="text/css">	<%@ include file="style.css" %>	</style>
		<title>Page d'erreur</title>
	</head>
	<body>
		<h1 align="center">Vous n'avez pas répondu à l'ensemble des questions !!!</h1>
		<br/>
		<br/>
		<br/>
		<form method="get" action="ControleurListeQCMs">
			<table>
				<tr>
					<td align="center">
						<input class="bouton" type="submit" name="ordre" value="Retour"/>
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>