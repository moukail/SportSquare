package nl.moukail.sportsquare.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import nl.moukail.sportsquare.view.MyVrienden;

import org.json.JSONException;
import org.json.JSONObject;
import android.util.Log;

public class MyTask implements Runnable {
	private final MyVrienden myVrienden;
	
	public MyTask(MyVrienden myVrienden) {
		this.myVrienden = myVrienden;
	}

	public void run() {
		Log.d("MyTask", "MyTask running");
		String result = getVrienden();
		//myVrienden.setVrienden(result);
	}

	private String getVrienden() {
		
		String result = null;
		HttpURLConnection con = null;
		try{
			if(Thread.interrupted()){
				throw new InterruptedException();
			}
			URL url = new URL("http://sportsquare.dandytest.nl/api/gym.json");
			con = (HttpURLConnection) url.openConnection();
			con.setReadTimeout(10000);
			con.setConnectTimeout(15000);
			con.setRequestMethod("GET");
			con.addRequestProperty("referer", "www.moukail.nl");
			con.setDoInput(true);
			
			con.connect();
			
			if(Thread.interrupted()){
				throw new InterruptedException();
			}
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
			String payload = reader.readLine();
			reader.close();
			
			/*String encodedTitle = Uri.encode(title);
	        String expandClause = expandTemplates ? WIKTIONARY_EXPAND_TEMPLATES : "";

			String content = getUrlContent(String.format("http://sportsquare.dandytest.nl/api/gym.json",
	                encodedTitle, expandClause));*/

			JSONObject response = new JSONObject(payload);
			Log.d("MyTask", "jsonObject");
			result = response.getString("visitor_count");
			
			if(Thread.interrupted()){
				throw new InterruptedException();
			}
		}catch(IOException e){
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

}
