<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<style type="text/css">	<%@ include file="style.css" %>	</style>
		<title>Résultat du QCM Arel de <%= ((Modele)request.getSession().getAttribute("m")).getNomQCMCourant() %></title>
	</head>
	<body>
		<%@ page import="Modele.*" %>
		<h1 align="center">Réponse aux questions !!! =)</h1>
		<br/>
		<br/>
		<br/>
		<%
			String reponse;
			int nbBonnesReponses = 0;
			for (int i = 0; i < ((Modele)request.getSession().getAttribute("m")).getQCMCourant().getNbQuestions(); i++)
			{
			%>
		<table>
			<p class=question> <%= ((Modele)request.getSession().getAttribute("m")).getQCMCourant().getQuestion(i).getExpression() %> </p>
			<%
				reponse = request.getParameter("q" + i);
				if (((Modele)request.getSession().getAttribute("m")).getQCMCourant().getQuestion(i).getBonneReponse().getExpression().matches(reponse))
				{
				%>
			<p class=vrai> <%= ((Modele)request.getSession().getAttribute("m")).getQCMCourant().getQuestion(i).getBonneReponse().getExpression() %> </p>
			Bravo, vous avez bien répondu !!<br/>
				<%	
					nbBonnesReponses++;
				}
				else
				{
				%>
			<p class=faux> <%=((Modele)request.getSession().getAttribute("m")).getQCMCourant().getQuestion(i).getBonneReponse().getExpression() %> </p>
			Vous vous êtes trompé, retournez réviser...<br />
				<%
				}
			}
			%>
		</table>
		<br/>
		<br/>
			<%= "Vous avez réalisé ce QCM avec un total de " + nbBonnesReponses + " bonnes réponses sur " + ((Modele)request.getSession().getAttribute("m")).getQCMCourant().getNbQuestions() + "." %>
		<br/>
		<br/>
		<form method="get" action="ControleurResultQCM">
			<table>
				<tr>
					<td align="center">
						<input class="bouton" type="submit" name="ordre" value="Enregistrer"/>
					</td>
				</tr>
				<tr>
					<td align="center">
						<input class="bouton" type="submit" name="ordre" value="Retour"/>
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<%@ page import="java.io.*" %>
	<% 	
		ServletContext context = getServletContext(); 
		String urlFile = context.getRealPath("style.css");
		File file = new File(urlFile);
		InputStream ips=new FileInputStream(file); 
		InputStreamReader ipsr=new InputStreamReader(ips);
		BufferedReader br=new BufferedReader(ipsr);
		String ligne, style="";
		while ((ligne=br.readLine())!=null){
			style+=ligne+"\n";
		}
		br.close(); 
	%>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<style><%=style %></style>
		<title>Résultat du QCM Arel de <%= ((Modele)request.getSession().getAttribute("m")).getNomQCMCourant() %></title>
	</head>
	<body>
		<%@ page import="Modele.*" %>
		<h1 align="center">Réponse aux questions !!! =)</h1>
		<br/>
		<br/>
		<br/>
		<%
			String reponse;
			int nbBonnesReponses = 0;
			for (int i = 0; i < ((Modele)request.getSession().getAttribute("m")).getQCMCourant().getNbQuestions(); i++)
			{
			%>
		<table>
			<p class=question> <%= ((Modele)request.getSession().getAttribute("m")).getQCMCourant().getQuestion(i).getExpression() %> </p>
			<%
				reponse = request.getParameter("q" + i);
				if (((Modele)request.getSession().getAttribute("m")).getQCMCourant().getQuestion(i).getBonneReponse().getExpression().matches(reponse))
				{
				%>
			<p class=vrai> <%= ((Modele)request.getSession().getAttribute("m")).getQCMCourant().getQuestion(i).getBonneReponse().getExpression() %> </p>
			Bravo, vous avez bien répondu !!<br/>
				<%	
					nbBonnesReponses++;
				}
				else
				{
				%>
			<p class=faux> <%=((Modele)request.getSession().getAttribute("m")).getQCMCourant().getQuestion(i).getBonneReponse().getExpression() %> </p>
			Vous vous êtes trompé, retournez réviser...<br />
				<%
				}
			}
			%>
		</table>
		<br/>
		<br/>
			<%= "Vous avez réalisé ce QCM avec un total de " + nbBonnesReponses + " bonnes réponses sur " + ((Modele)request.getSession().getAttribute("m")).getQCMCourant().getNbQuestions() + "." %>
		<br/>
		<br/>
		<form method="get" action="ControleurResultQCM">
			<table>
				<tr>
					<td align="center">
						<input class="bouton" type="submit" name="ordre" value="Enregistrer"/>
					</td>
				</tr>
				<tr>
					<td align="center">
						<input class="bouton" type="submit" name="ordre" value="Retour"/>
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>