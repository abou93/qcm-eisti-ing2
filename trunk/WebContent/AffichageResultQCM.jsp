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
			boolean aRepondu = false;
			boolean correct = true;
			for (int i = 0; i < ((Modele)request.getSession().getAttribute("m")).getQCMCourant().getNbQuestions(); i++)
			{
				aRepondu = false;
				correct = true;
			%>
		<table>
			<p class="question"> <%= ((Modele)request.getSession().getAttribute("m")).getQCMCourant().getQuestion(i).getExpression() %> </p>
			<%
				for(int j = 0; j < ((Modele)request.getSession().getAttribute("m")).getQCMCourant().getQuestion(i).getNbBonnesReponses(); j++)
				{
					if(((Modele)request.getSession().getAttribute("m")).getResultat(((Modele)request.getSession().getAttribute("m")).getNomQCMCourant()).getQcmResultat().getQuestion(i).getReponse(j).isSelect() == true
					&& ((Modele)request.getSession().getAttribute("m")).getQCMCourant().getQuestion(i).getReponse(j).isTrue() == true)
						aRepondu = true;
					if(((Modele)request.getSession().getAttribute("m")).getResultat(((Modele)request.getSession().getAttribute("m")).getNomQCMCourant()).getQcmResultat().getQuestion(i).getReponse(j).isSelect() == false
					&& ((Modele)request.getSession().getAttribute("m")).getQCMCourant().getQuestion(i).getReponse(j).isTrue() == true)
						correct = false;
					if(((Modele)request.getSession().getAttribute("m")).getResultat(((Modele)request.getSession().getAttribute("m")).getNomQCMCourant()).getQcmResultat().getQuestion(i).getReponse(j).isSelect() == true
					&& ((Modele)request.getSession().getAttribute("m")).getQCMCourant().getQuestion(i).getReponse(j).isTrue() == false)
					{
						correct = false;
						aRepondu = true;
					}
					System.out.print(((Modele)request.getSession().getAttribute("m")).getResultat(((Modele)request.getSession().getAttribute("m")).getNomQCMCourant()).getQcmResultat().getQuestion(i).getReponse(j).getExpression());
					System.out.println(((Modele)request.getSession().getAttribute("m")).getResultat(((Modele)request.getSession().getAttribute("m")).getNomQCMCourant()).getQcmResultat().getQuestion(i).getReponse(j).isTrue());
				}
				System.out.println("correct: " + String.valueOf(correct));
				System.out.println("a repondu: " + String.valueOf(aRepondu)); 
				for(int j = 0; j < ((Modele)request.getSession().getAttribute("m")).getQCMCourant().getQuestion(i).getNbBonnesReponses(); j++)
				{
					if(correct == true && aRepondu == true)
					{
						if(((Modele)request.getSession().getAttribute("m")).getResultat(((Modele)request.getSession().getAttribute("m")).getNomQCMCourant()).getQcmResultat().getQuestion(i).getReponse(j).isTrue() == true)
						{
						%>
			<p class="vrai">
						<%= ((Modele)request.getSession().getAttribute("m")).getQCMCourant().getQuestion(i).getBonneReponse(j).getExpression() %>
			</p>
						<%
						}
					}
					if(correct == false)
					{
						if(((Modele)request.getSession().getAttribute("m")).getResultat(((Modele)request.getSession().getAttribute("m")).getNomQCMCourant()).getQcmResultat().getQuestion(i).getReponse(j).isTrue() == true)
						{
						%>
			<p class="faux"><%= ((Modele)request.getSession().getAttribute("m")).getQCMCourant().getQuestion(i).getBonneReponse(j).getExpression() %>
			</p>
						<%
						}
					}
				}
				if(correct == true && aRepondu == true)
				{	
					nbBonnesReponses++;
					%>
			Bravo, vous avez bien répondu !!<br/>
				<%
				}
				if(correct == false && aRepondu == true)
				{
				%>
			Vous vous êtes trompé, retournez réviser... <br/>
				<%
				}
				if(correct == true && aRepondu == false)
				{
				%>
			Vous n'avez pas répondu à cette question... <br/>
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