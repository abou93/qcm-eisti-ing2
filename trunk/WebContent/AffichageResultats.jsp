<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.ArrayList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Affichage des résultats</title>
<style type="text/css">	<%@ include file="style.css" %>	</style>
</head>
<body>

<%@ page import="Modele.*" %>
<h1>Choisissez les résultats que vous voulez afficher</h1>
	
<form <%if ((Boolean)request.getSession().getAttribute("estProf")) 
		{ %>method="get"<%} 
		else { %>method="post"<%} %> 
action="ControleurResultats">
	<h2> Liste des utilisateurs : </h2>
	<%for (int i = 0; i < ((ArrayList<String>) request.getSession().getAttribute("listeUsers")).size(); i++) 
	{ %>
		<p class="more-user">
			<button type="submit" name="choixUser" value="<%=((ArrayList<String>) request.getSession().getAttribute("listeUsers")).get(i) %>">+</button>
			<%=((ArrayList<String>) request.getSession().getAttribute("listeUsers")).get(i) %>
		</p>
		
		<%if ( request.getSession().getAttribute("choixUser")!=null && ((String)request.getSession().getAttribute("choixUser")).matches(((ArrayList<String>) request.getSession().getAttribute("listeUsers")).get(i)))
		{%>
			<%if( ((ArrayList<String>) request.getSession().getAttribute("liste")).size() == 0)
			{%>
				<p class="more-qcm">
					Aucun QCM.
				</p>
			<%} %>
			<%for(int j=0; j<((ArrayList<String>) request.getSession().getAttribute("liste")).size(); j++)
			{%>
				<p class="more-qcm">
					<button type="submit" name="choix" value="<%=((ArrayList<String>) request.getSession().getAttribute("liste")).get(j) %>">+</button>
					<%=((ArrayList<String>) request.getSession().getAttribute("liste")).get(j) %>
				</p>
				<% if ( request.getSession().getAttribute("choix")!=null && ((String)request.getSession().getAttribute("choix")).matches(((ArrayList<String>) request.getSession().getAttribute("liste")).get(j)) )
				{%>
					<p class="more-result">
						<b>Score =	<%=((Modele)request.getSession().getAttribute("m")).getResultatCourant().getScore() %></b>
						<br/>
						<%for(int k=0 ; k<((Modele)request.getSession().getAttribute("m")).getResultatCourant().getNbQuestions() ; k++) 
						{ %>			
								Question n°<%=k+1%> - 
								<%=((Modele)request.getSession().getAttribute("m")).getResultatCourant().getQuestion(k).getExpression() %>
								<br/>
								<%=((Modele)request.getSession().getAttribute("m")).getResultatCourant().getQuestion(k).getReponse(0).getExpression() %>
								<br/>
						<%} %>
					</p>
				<%} %>
			<%} %>
		<%} %>
	<%} %>
</form>

<br/><br/>
<a href="accueil.jsp">Accueil</a>
</body>
</html>