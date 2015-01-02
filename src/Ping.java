import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.util.logging.Logger;

public class Ping {
	protected URL url;
	char[] cs = new char[2000];
	private boolean available;

	public Logger LOG = Logger.getLogger(getClass().getName());

	public Ping(String arg) throws IOException {
		File f = new File(System.getProperty("user.home") + File.separator
				+ ".ping.config");

		if (!f.exists()) {
			System.err.println("Fichier ~/ping.config manquant");
		}
		InputStreamReader fr = null;
		fr = new InputStreamReader(new FileInputStream(f));
		fr.read(cs);
		fr.close();
		String urlSTR = new String(cs);
		System.out.println(urlSTR);
		URL url = null;
		url = new URL(urlSTR);

			final HttpURLConnection connection = (HttpURLConnection) new URL(
					urlSTR).openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("from", "My computer thinks I\'m Gay");
			connection.connect();
			int responseCode = connection.getResponseCode();
			System.out.println("Connected ["+connection.toString()+"]Response: " + responseCode);
	}

	public static void main(String[] args) {
		try {
			Ping p = new Ping(args[0]);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
