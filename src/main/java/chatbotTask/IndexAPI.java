package chatbotTask;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;
import org.json.JSONTokener;

public class IndexAPI implements IIndexAPI{
	
	private JSONObject response;
	
	private void connectAndGetResp(String index) {
		try {
			URL url = new URL(
					"https://www.pochta.ru/portal-portlet/delegate/postoffice-api/method/offices.find.byCode?postalCode=" + index);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			
			int status = con.getResponseCode();
			con.disconnect();
			JSONTokener tokener = new JSONTokener(url.openStream());
			response = new JSONObject(tokener);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public IndexAPI() {
		
	}
	
	@Override
	public Double[] getCoordinates(String index) {
		connectAndGetResp(index);
		
		if (response != null && response.has("office")) {
			JSONObject office = response.getJSONObject("office");
			if (office.has("latitude") && office.has("longitude")) {
				return new Double[] {office.getDouble("latitude"),
						office.getDouble("longitude")};
			}
		}
		return null;
		
	}
	
}
