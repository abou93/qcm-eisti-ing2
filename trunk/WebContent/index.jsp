<%@page import="java.util.List"%>
<%@page import="DAO.DAOBase"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/>
	<title>Authentification</title>
	<link rel="stylesheet" type="text/css" href="css/style.css"/>
</head>
<body class="body">
<div id="header"></div>
	<form class="horizontal_form" method="post" action="ControleurListeQCMs">
	   <fieldset>
	      <legend>Bienvenue sur Arel</legend>
	      <ul>
	         <li>
	            <label for="login">Nom d'utilisateur</label>
	            <input name="login" type="text" class="requiredfield" id="login" size="35">
	         </li>
	         <li>
	            <label for="pwd">Mot de passe</label>
	            <input name="pwd" type="password" class="requiredfield" id="pwd" size="35">
	         </li>
	      </ul>
	   </fieldset>
	   <fieldset class="fieldsetbuttons">
	      <div>
	         <button type="submit" name="Login" class="confirm_button blue styled_button">Se Connecter</button>
	      </div>
	   </fieldset> 
	</form>
	<%
		String msg = (String) request.getSession().getAttribute("msgErr");
		if (msg != null) {
			%>
			<div class="error">
			   <div></div>
			   <p><%= msg %></p> 
			</div>
			
			<%
		}
	%>	
		

</body>
</html>