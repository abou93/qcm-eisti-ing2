<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
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
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Page d'accueil du module QCM d'entrainnement</title>
		<style><%=style %></style>
	</head>
	<body class="body">
		<%@ page import="Modele.*" %>
		<h1>Test QCM pour le projet AREL (Client EISTI)</h1>
		<p>Pour vous entraîner avant le partiel, veuillez choisir un QCM :</p>
		<form method="get" action="ControleurListeQCMs">
			<table align="center" width="500px">
		<%
		for (int i = 0; i < ((Modele)request.getSession().getAttribute("m")).getLesCours().size(); i++)
		{
		%>
			<tr>
				<p class=cours><%= ((Modele)request.getSession().getAttribute("m")).getLesCours().get(i).getNom() %></p>
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
							Difficulté :  <%= String.valueOf(((Modele)request.getSession().getAttribute("m")).getLesCours().get(i).getLesQCMs().get(j).getDifficulte()) %>
						</td>
					</tr>
			<%
			}
			%>
				</table>
				<br/>
			</tr>
		<%
		}
		%>
			</table>
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