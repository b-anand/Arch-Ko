package generic.experiments.chatbot;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class StartNewGameServlet extends HttpServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		HttpSession session = req.getSession(true);
		session.setAttribute("SecretNumber", generateSecretNumber());
		session.setAttribute("Guesses", "");
		session.setAttribute("Success", "");
		session.setAttribute("Error", "");
		resp.sendRedirect("/");
	}

	public static String generateSecretNumber() {
		String number = "";
		do {
			number = String.format("%04d", new Integer(
					((int) (Math.random() * 100000)) % 10000));
		} while (checkForDuplicateDigits(number));
		return number;
	}

	public static boolean checkForDuplicateDigits(String number) {
		int bitmask = 0;
		for (int i = 0; i < number.length(); i++) {
			int targetbit = (1 << (number.charAt(i) - '0'));
			int bitvalue = bitmask & targetbit;
			if (bitvalue == 0) {
				bitmask |= targetbit;
			} else {
				return true;
			}
		}

		return false;
	}
}
