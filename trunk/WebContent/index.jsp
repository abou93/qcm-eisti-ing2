<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.Vector"
    import="modele.Personne"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>TD3 JEE - MVC</title>
</head>
<body>
<h1>Veuillez remplir le formulaire suivant</h1>
Les champs marqués d'une * sont obligatoires.
<form method="post" action="http://localhost:8080/TD3_MVC/MyServlet">
<%
Personne p = (Personne) request.getAttribute("personne");
Vector<String> erreurs = (Vector<String>) request.getAttribute("erreur");
boolean remplir = false;%>
	<h2>Vous avez mal saisi les informations</h2>
	<ul>
	<%
		for (String s : erreurs) {
			%><li>
			<%= s %></li>
			<%
		}
	%>
	</ul>
<table>
	<tr><td>Transport : </td><td><select name="transport" size="4" multiple="multiple">
			<option value="Vélo">Vélo</option>
			<option value="Voiture">Voiture</option>
			<option value="A pied">A pied</option>
			<option value="Avion">Avion</option>
			<option value="Camion">Camion</option>
		</select></td></tr>
	<tr><td>* Code postal : </td><td><input type="text" name="cp" maxlength="5"
	<%if (remplir) out.println ("value=\"" + p.getCodePostal() + "\""); %>/></td></tr>
</table>
	<br/><br/>
	<input type="submit" name="submit" value="Submit" />
<%
request.removeAttribute("personne");
request.removeAttribute("erreur");
%>
</form>
<!-- <a href="index.jsp">Retour au formulaire</a> -->
</body>
</html>