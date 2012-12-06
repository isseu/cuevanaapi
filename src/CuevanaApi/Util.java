package CuevanaApi;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.HttpURLConnection;
import java.net.URL;

public class Util {

	public static String getWebContent(String url) throws Exception {
		return getWebContent(url, null, null);
	}
	
	public static String getWebContent(String url, String postdata) throws Exception {
		return getWebContent(url, postdata, null);
	}	
	
	public static String getWebContent(String url, String postdata, String header) throws Exception {
		URL _url;
		HttpURLConnection conn;
		BufferedReader rd;
		String line;
		String result = "";
		CookieManager manager = new CookieManager();
		manager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
		CookieHandler.setDefault(manager);
		try {

			_url = new URL(url);
			conn = (HttpURLConnection) _url.openConnection();
			HttpURLConnection.setFollowRedirects(false);

			if (postdata != null) {
				conn.setRequestMethod("POST");
				conn.setRequestProperty("Content-Length", "" + Integer.toString(postdata.getBytes().length));
			}

			conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2");

			if (postdata != null) {
				conn.setDoOutput(true);
				DataOutputStream wr = new DataOutputStream(
						conn.getOutputStream());
				wr.writeBytes(postdata);
				wr.flush();
				wr.close();
			}
			
			if (header != null) {
				// Solo queremos un header
				if (conn.getHeaderField(header) != null) {
					return conn.getHeaderField(header);
				}
				// Sino
				throw new NotValidCaptcha("Captcha incorrecto");
			}
			
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			while ((line = rd.readLine()) != null) {
				result += line;
			}
			rd.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	

	public static boolean validUrl(String url) {
		if (url.matches("^http:\\/\\/(www\\.)?cuevana\\.(co|tv|me).*")) {
			return true;
		}
		return false;

	}
}
