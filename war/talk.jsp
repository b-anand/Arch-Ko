<%@page import="generic.experiments.chatbot.ProcessNewGuessServlet"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome to ArchKo</title>
</head>
<body>
	<h1>Cows And Bulls</h1>
	<form action="/startNewGame" method="post">
		<input type="submit" value="Start New Game" />
	</form>

	</p>

	<form action="/updateGuess" method="post">
		<div>
			<label> Enter your Guess: </label> <input type="text" name="guess"
				id="guessField"></input> <input type="submit" value="Submit" />
		</div>
	</form>
	</p>

	<label align="center"> <%
 	String guesses = (String) session.getAttribute("Guesses");
 	if (guesses != null)
 		out.println(guesses.replaceAll("\n", "<br>"));
 	else
 		out.println("<br>");

 	String error = (String) session.getAttribute("Error");
 	if (error != null && !error.isEmpty()) {
 		out.println("Last Guess was incorrect. Please try again.<br>");
 	}
 	
 	String success = (String) session.getAttribute("Success");
 	if (success != null && !success.isEmpty()) {
 		out.println("Bulls Eye!!! Start a new Game...<br>");
 	}
 %>
	</label>
	<script type="text/javascript">
		var txtBox = document.getElementById('guessField');
		txtBox.focus();
	</script>
</body>
</html>