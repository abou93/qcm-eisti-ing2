<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="Modele.*, java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Page d'accueil du module QCM d'entrainnement</title>
		<link rel="stylesheet" type="text/css" href="css/reset.css"/>
		<link rel="stylesheet" type="text/css" href="css/type.css" />
		<link rel="stylesheet" type="text/css" href="css/tables.css" />
		<link rel="stylesheet" type="text/css" href="css/forms.css" />
		<link rel="stylesheet" type="text/css" href="css/helpers.css" />
		<link rel="stylesheet" type="text/css" href="css/ux.css" />
		<link rel="stylesheet" type="text/css" href="css/style.css"/>
	</head>
	<body class="body">
		<div id="header"></div>
		<h1>Test QCM pour le projet AREL (Client EISTI)</h1>
		<%
		if(request.getSession().getAttribute("msgErr") != null) {
			%><p class="faux"><%= request.getSession().getAttribute("msgErr")%></p><%
		}
		%>
		<p>Pour vous entraîner avant le partiel, veuillez choisir un QCM</p>
		<p>Attention ! Tous les QCMs se font en temps limité, le compte à rebours est lancé dés que vous selectionnez un QCM !</p>
		
		<form class="horizontal_form" method="get" action="ControleurListeQCMs">
		<% List<Cours> lc = (List<Cours>)request.getSession().getAttribute("ListCours"); %>
		<fieldset>
	      <legend>Liste des cours</legend>
	      <ul>
		<%
		for (int i = 0; i < lc.size(); i++)
		{
		%>
	         <li>
	           <h1 class="cours">  <%= lc.get(i).getNom() %> </h1>
	         
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
			</li>
		<%
		}
		%>
			</ul>
	  	 </fieldset>
		</form>
		
		<table>
		<tr>
			<td>
				<form method="get" action="ControleurListeQCMs">
					<fieldset class="fieldsetbuttons">
				      <div>
				         <button class="edit_button black styled_button" type="submit" name="ordre">Raffraichir</button>
				      </div>
				   </fieldset> 
				</form>
				<form <% if((Boolean) request.getSession().getAttribute("estProf")){%>method="get"
				<%}else {%> method="post" <%}%> action="ControleurResultats">
					<fieldset class="fieldsetbuttons">
				      <div>
				         <button class="view_button black styled_button" type="submit" name="VoirResultats">Voir les Résultats enregistrés</button>
				      </div>
				   </fieldset> 
				</form>
				<form method="post" action="ControleurListeQCMs">
					<fieldset class="fieldsetbuttons">
				      <div>
				         <button class="cancel_button black styled_button" type="submit" name="logout">Logout</button>
				      </div>
				   </fieldset> 
				</form>
			</td>
			<td>
				<% if((Boolean) request.getSession().getAttribute("estProf"))
					{%>
				<form class="vertical_form" method="post" enctype="multipart/form-data" action="ControleurUploadQCM">
					<fieldset>
						<legend>Upload</legend>
						<label for="mon_fichier">Fichier (format .xml | max. 10 Mo) :</label><br />
							<input type="hidden" name="MAX_FILE_SIZE" value="10485760" />
							<input type="file" name="mon_fichier" id="mon_fichier" /><br />
							<label for="titre">Titre du fichier (max. 50 caractères) :</label><br />
							<input type="text" name="titre" value="Titre du fichier" id="titre" /><br />
							<input type="submit" name="submit" value="Upload" />
					</fieldset>
				</form>
				<%
				}
				%>
			</td>
		</tr>
	</table>
	</body>
</html>