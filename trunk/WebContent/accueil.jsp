<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="Modele.*"%>
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
		<p>Pour vous entra�ner avant le partiel, veuillez choisir un QCM</p>
		<p>Attention ! Tous les QCMs se font en temps limit�, le compte � rebours est lanc� d�s que vous selectionnez un QCM !</p>
		<form method="get" action="ControleurListeQCMs">
		<%
		for (int i = 0; i < ((Modele)request.getSession().getAttribute("m")).getLesCours().size(); i++)
		{
		%>
				<p class="cours"><%= ((Modele)request.getSession().getAttribute("m")).getLesCours().get(i).getNom() %></p>
				<table align="center" width="500px">
			<%
			for (int j = 0; j < ((Modele)request.getSession().getAttribute("m")).getLesCours().get(i).getLesQCMs().size(); j++)
			{
			%>
					<tr>
						<td>
							<input class="bouton" type="submit" name="QCM" value= <%= ((Modele)request.getSession().getAttribute("m")).getLesCours().get(i).getLesQCMs().get(j).getNom() %> />
						</td>
						<td class="difficulte">
							Difficult� :  <%= String.valueOf(((Modele)request.getSession().getAttribute("m")).getLesCours().get(i).getLesQCMs().get(j).getDifficulte()) %>
						</td>
					</tr>
			<%
			}
			%>
				</table>
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
						<input class="bouton" type="submit" name="VoirResultats" value="Voir les R�sultats enregistr�s"/>
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
     					<label for="titre">Titre du fichier (max. 50 caract�res) :</label><br />
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