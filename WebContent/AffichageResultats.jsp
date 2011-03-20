<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.ArrayList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Affichage des résultats</title>
	<link rel="stylesheet" type="text/css" href="css/style.css"/>
</head>
<body>
<div id="header"></div>

<%@ page import="Modele.*" %>
<h1>Choisissez les résultats que vous voulez afficher</h1>
	
<form <%if ((Boolean)request.getSession().getAttribute("estProf")) 
		{ %>method="get"<%} 
		else { %>method="post"<%} %> 
action="ControleurResultats">
	<%if ((Boolean)request.getSession().getAttribute("estProf")) 
		{ %> <h2> Liste des utilisateurs : </h2>
	<%}
	 else { %> <h2> Liste des Résultats : </h2> 
	<% }%>
	<%for (int i = 0; i < ((ArrayList<String>) request.getSession().getAttribute("listeUsers")).size(); i++) 
	{ %>
		<p class="more-user">
			<button class="plus" type="submit" name="choixUser" value="<%=((ArrayList<String>) request.getSession().getAttribute("listeUsers")).get(i) %>"> &nbsp; </button>
			<%=((ArrayList<String>) request.getSession().getAttribute("listeUsers")).get(i) %>
		</p>
		
		<%if ( request.getSession().getAttribute("choixUser")!=null && ((String)request.getSession().getAttribute("choixUser")).matches(((ArrayList<String>) request.getSession().getAttribute("listeUsers")).get(i)))
		{%>
			<%if( ((ArrayList<String>) request.getSession().getAttribute("liste")).size() == 0)
			{%>
				<p class="more-qcm">
					Aucun résultat.
				</p>
			<%} %>
			<%for(int j=0; j<((ArrayList<String>) request.getSession().getAttribute("liste")).size(); j++)
			{%>
				<p class="more-qcm">
					<button class="plus" type="submit" name="choix" value="<%=((ArrayList<Integer>) request.getSession().getAttribute("listeId")).get(j) %>"> &nbsp; </button>
					<%=((ArrayList<String>) request.getSession().getAttribute("liste")).get(j) %>
				</p>
				<% if ( request.getSession().getAttribute("choix")!=null 
						&& ( ((Integer) request.getSession().getAttribute("choix")) == ((ArrayList<Integer>) request.getSession().getAttribute("listeId")).get(j)) )
						
				{%>
					<p class="more-result">
						<b>Score =	<%=((QCM)request.getSession().getAttribute("qcm_select")).getScore() %></b>
						<br/>
						<br/>
						<%for(int k=0 ; k<((QCM)request.getSession().getAttribute("qcm_select")).getNbQuestions() ; k++) 
						{ %>	
										
								Question n°<%=k+1%> - 
								<%=((QCM)request.getSession().getAttribute("qcm_select")).getQuestion(k).getExpression() %>
								<br/>
								<%for(int l=0 ; l<((QCM)request.getSession().getAttribute("qcm_select")).getQuestion(k).getLesReponses().size() ; l++ )
									{%>
										<result class="more-and-more">
										<%=((QCM)request.getSession().getAttribute("qcm_select")).getQuestion(k).getReponse(l).getExpression() %>
										<% if (((QCM)request.getSession().getAttribute("qcm_select")).getQuestion(k).getReponse(l).isSelect()
												&& ((QCM)request.getSession().getAttribute("qcm_select")).getQuestion(k).getReponse(l).isTrue())
										   {%>
												<correct>&nbsp;</correct>
										<% }%>
										<% if (((QCM)request.getSession().getAttribute("qcm_select")).getQuestion(k).getReponse(l).isSelect()
												&& !((QCM)request.getSession().getAttribute("qcm_select")).getQuestion(k).getReponse(l).isTrue())
										   {%>
												<incorrect>&nbsp;</incorrect>
										<% }%>
										</result>
										<br/>
								<%  }%>
						<%} %>
					</p>
					<br/><br/><br/>
				<%} %>
			<%} %>
		<%} %>
	<%} %>
</form>

<br/><br/>
<form method="get" action="ControleurResultQCM">
	<button class="back_button blue styled_button" type="submit" name="ordre" value="Retour">Retour</button>
</form>
</body>
</html>