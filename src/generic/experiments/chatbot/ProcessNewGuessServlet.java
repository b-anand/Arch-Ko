package generic.experiments.chatbot;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ProcessNewGuessServlet extends HttpServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		HttpSession session = req.getSession(true);
		String guess = req.getParameter("guess");
		if (guess == null || guess.length() != 4
				|| StartNewGameServlet.checkForDuplicateDigits(guess)) {
			System.err.println("Guess is not right length " + guess);
			session.setAttribute("Error", "Wrong number input. Guess Invalid!!");
			resp.sendRedirect("/");
			return;
		}

		session.setAttribute("Error", "");
		
		String guessList = (String) session.getAttribute("Guesses");
		if (guessList == null)
			guessList = "";
		
		String secretNumber = (String) session.getAttribute("SecretNumber");
		if (secretNumber == null || secretNumber.isEmpty()) {
			secretNumber = StartNewGameServlet.generateSecretNumber();
			session.setAttribute("SecretNumber", secretNumber);
		}

		int cows = 0;
		int bulls = 0;

		for (int i = 0; i < secretNumber.length(); i++) {
			if (guess.charAt(i) == secretNumber.charAt(i))
				bulls++;
		}

		if (bulls == 4) {
			session.setAttribute("Success", "true");
		}

		for (int i = 0; i < secretNumber.length(); i++) {
			for (int j = 0; j < secretNumber.length(); j++) {
				if (i != j && guess.charAt(i) == secretNumber.charAt(j))
					cows++;
			}
		}

		String finalGuessString = String.format("%s C:%d B:%d\n", guess, cows,
				bulls);
		guessList += finalGuessString;
		session.setAttribute("Guesses", guessList);
		resp.sendRedirect("/");
	}
}
