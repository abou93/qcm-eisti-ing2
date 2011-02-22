<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link href="style.css" rel="stylesheet" type="text/css" />
		<title>Page d'accueil du module QCM d'entrainnement</title>
	</head>
	<body class="body">
		<%@ page import="Modele.*" %>
		<h1>Test QCM pour le projet AREL (Client EISTI)</h1>
		<p>Pour vous entraîner avant le partiel, veuillez choisir un QCM :</p>
		<form method="get" action="ControleurListeQCMs">
			<table align="center" width="500px">
			<%
			Modele m = (Modele)request.getSession().getAttribute("m");
			for (int i = 0; i < m.getLesQCMs().size(); i++)
			{
			%>
				<tr>
					<td>
						<input class="bouton" type="submit" name="QCM" value= <%= m.getLesQCMs().get(i) %> />
					</td>
					<td class="difficulte">
						Difficulté :  <%= m.getLesQCMs().get(i).getLvl() %>
					</td>
				</tr>
			<%
			}
			%>
			</table>
		</form>
		<form method="get" action="ControleurListeQCMs">
			<table>
				<tr>
					<td align="center">
						<input class="bouton" type="submit" name="Rafraichir" value="Rafraichir"/>
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>