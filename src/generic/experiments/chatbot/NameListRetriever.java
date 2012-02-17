package generic.experiments.chatbot;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Query;

public class NameListRetriever extends HttpServlet {

	private static List<String> names = new ArrayList<String>();

	public void update(String name) {
		if (name != null && !name.isEmpty()) {
			DatastoreService datastore = DatastoreServiceFactory
					.getDatastoreService();
			Entity entity = new Entity("Name");
			entity.setProperty("name", name);
			datastore.put(entity);
		}
	}

	public String getNames() {
		String namesList = "";
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Query query = new Query("Name").addSort("name");
		for (Entity entity : datastore.prepare(query).asIterable()) {
			namesList += entity.getProperty("name") + "<br>";
		}

		return namesList;
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		String name = req.getParameter("name");
		update(name);
		resp.sendRedirect("/");
	}
}
