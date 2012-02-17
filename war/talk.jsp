<%@page import="generic.experiments.chatbot.NameListRetriever"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome to ArchKo</title>
</head>
<body>
	<%
		NameListRetriever nameRetriever = new NameListRetriever();
		String nameList = nameRetriever.getNames();
	%>

	<h3>Current list of names in database</h3>
	<label align="center"> <%
 	out.println(nameList);
 %>
	</label>
	</p>
	<form action="/updateName" method="post">
		<div>
			<label> Enter name: </label> <input type="text" name="name"
				id="nameField"></input> <input type="submit" value="Submit Name" />
		</div>
	</form>

	<script type="text/javascript">
		var txtBox = document.getElementById('nameField');
		txtBox.focus();
	</script>
</body>
</html>