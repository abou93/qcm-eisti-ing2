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
		<title>Page d'erreur</title>
	</head>
	<body>
		<h1 align="center">Vous n'avez pas r�pondu � l'ensemble des questions !!!</h1>
		<br/>
		<br/>
		<br/>
		<form method="get" action="ControleurListeQCMs">
			<table>
				<tr>
					<td align="center">
						<input class="bouton" type="submit" name="ordre" value="Retour"/>
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>