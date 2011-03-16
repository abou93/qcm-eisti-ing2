<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="Modele.*, java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Page d'accueil du module QCM d'entrainnement</title>
		<style type="text/css">	<%@ include file="style.css" %>	</style>
	</head>
	<body class="body">
		<h1>Test QCM pour le projet AREL (Client EISTI)</h1>
		<%
		if(request.getSession().getAttribute("msgErr") != null) {
			%><p class="faux"><%= request.getSession().getAttribute("msgErr")%></p><%
		}
		%>
		<p>Pour vous entraîner avant le partiel, veuillez choisir un QCM</p>
		<p>Attention ! Tous les QCMs se font en temps limité, le compte à rebours est lancé dés que vous selectionnez un QCM !</p>
		
		<form method="get" action="ControleurListeQCMs">
		<% List<Cours> lc = (List<Cours>)request.getSession().getAttribute("ListCours"); %>
		<%
		for (int i = 0; i < lc.size(); i++)
		{
		%>
			<p class="cours"><%= lc.get(i).getNom() %></p>
			<%
			for (int j = 0; j < lc.get(i).getLesQCMs().size(); j++)
			{
			%>
				<button type="submit" name="QCM" value="<%=lc.get(i).getLesQCMs().get(j).getId()%>">
					Difficulté :  <%= String.valueOf(lc.get(i).getLesQCMs().get(j).getDifficulte()) %>
				</button>
			<%
			}
			%>
			<br/>
		<%
		}
		%>
		</form>
		<form method="get" action="ControleurListeQCMs">
			<table>
				<tr>
					<td align="center">
						<input class="bouton" type="submit" name="ordre" value="Rafraichir"/>
					</td>
				</tr>
			</table>
		</form>
		<form <% if((Boolean) request.getSession().getAttribute("estProf")){%>method="get"
		<%}else {%> method="post" <%}%> action="ControleurResultats">
			<table>
				<tr>
					<td align="center">
						<input class="bouton" type="submit" name="VoirResultats" value="Voir les Résultats enregistrés"/>
					</td>
				</tr>
			</table>
		</form>
		<% if((Boolean) request.getSession().getAttribute("estProf"))
		{%>
		<form method="post" enctype="multipart/form-data" action="ControleurUploadQCM">
			<table>
				<tr>
					<td align="center">
						<label for="mon_fichier">Fichier (format .xml | max. 10 Mo) :</label><br />
     					<input type="hidden" name="MAX_FILE_SIZE" value="10485760" />
     					<input type="file" name="mon_fichier" id="mon_fichier" /><br />
     					<label for="titre">Titre du fichier (max. 50 caractères) :</label><br />
     					<input type="text" name="titre" value="Titre du fichier" id="titre" /><br />
     					<input type="submit" name="submit" value="Upload" />
					</td>
				</tr>
			</table>
		</form>
		<%
		}
		%>
		<form method="post" action="ControleurListeQCMs">
			<table>
				<tr>
					<td align="center">
						<input class="bouton" type="submit" name="logout" value="Logout"/>
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>