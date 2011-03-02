<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="Modele.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link href="style.css" type="text/css" rel="stylesheet" />
		<title>QCM Arel de <%= ((Modele)request.getSession().getAttribute("m")).getNomQCMCourant() %></title>
		
		<!-- Script pour le compte à rebours -->
		<script type="text/javascript">
			var valeur_origine = 10;
			
			
			var valeur = valeur_origine;
			var x;
			function Init()	{
				window.document.getElementById('compteur').value=valeur;
				x = window.setInterval('Decompte()', 1000);
			}
			
			function Decompte()	{
				//((valeur > 0)&&( ! window.document.getElementById('MaCheck').checked)) ? (window.document.getElementById('compteur').value = --valeur) : (window.clearInterval(x));
				//if ((valeur > 0)&&(!window.document.getElementById('MaCheck').checked)) {
				if (valeur > 0) {
					window.document.getElementById('compteur').value = --valeur;
				}
				else if (valeur == 0) {
					//window.location.href = "http://www.nouveaulien.com";
					//window.open('plop','_blank','toolbar=0, location=0, directories=0, status=0, scrollbars=0, resizable=0, copyhistory=0, menuBar=0, width=50, height=50');
					window.clearInterval(x);
					//request.getSession().setAttribute("temps", -1);
					alert ('Temps écoulé !!');
					window.location.href = "ControleurListeQCMs?temps=ecoule";
				}
			}
			
			function Relance(elem)	{
				if( ! elem.checked )		x= window.setInterval('Decompte()', 1000);
			}
			
			function ResetCompteur()	{
				valeur = valeur_origine;
				window.document.getElementById('MaCheck').checked = false;
				window.clearInterval(x);
				Init();
			}
			window.onload = Init;
		</script>

	</head>
	
	<body>
		<h1 align="center">Répondez aux questions !!! =)</h1>
		<br/>
		<form method="post" action="ControleurSelectQCM">
			<table>
				<tr><td>Temps restant : </td>
				<td><input type="text" name="temps" id="compteur" size="2" readonly="readonly"></td></tr>
			</table>
		<br/>
		<br/>
		<%
			for (int i = 0; i < ((Modele)request.getSession().getAttribute("m")).getQCMCourant().getNbQuestions(); i++)
			{
			%>
				<p class="question"> <%= ((Modele)request.getSession().getAttribute("m")).getQCMCourant().getQuestion(i).getExpression() %> </p>
				<%
					for (int j = 0; j < ((Modele)request.getSession().getAttribute("m")).getQCMCourant().getQuestion(i).getNbReponses(); j++)
					{
					%>
			<table>
				<tr>
					<td class="choix">
						<input type="checkbox" name= "<%= "q" +  i + "r" + j%>" value= "<%= ((Modele)request.getSession().getAttribute("m")).getQCMCourant().getQuestion(i).getReponse(j).getExpression() %>" >
						<%= ((Modele)request.getSession().getAttribute("m")).getQCMCourant().getQuestion(i).getReponse(j).getExpression() %>
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
			<table>
				<tr>
					<td align="center">
						<input class="bouton" type="submit" name="ordre" value= "<%= ("Valider ").concat(((Modele)request.getSession().getAttribute("m")).getNomQCMCourant()) %>" />
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>