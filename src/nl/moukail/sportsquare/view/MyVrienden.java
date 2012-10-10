package nl.moukail.sportsquare.view;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import nl.moukail.sportsquare.controller.PersonAdapter;
import nl.moukail.sportsquare.model.Person;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class MyVrienden extends ListActivity {
	private ArrayList<Person> m_friends;
	private PersonAdapter m_adapter;
	private Runnable viewVrienden;
	private ProgressDialog m_ProgressDialog = null;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.vrienden_list);
        m_friends = new ArrayList<Person>();
		showData();

		ListView lv = getListView();
		lv.setTextFilterEnabled(true);
		lv.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent i = new Intent(parent.getContext(), PersonDetails.class);
				i.putExtra("VRIEND", m_friends.get(position));
				startActivity(i);
			}
		});
    }
	private void showData() {
		
		if (m_friends.size() > 0) {
			m_adapter = new PersonAdapter(this, R.layout.friend_list_item, m_friends);
			setListAdapter(m_adapter);
		} else {
			loadData();
		}
	}
	private void loadData() {
		boolean connected = isConnection();
		if (connected) {
			showData();
		} else {
			Toast toast = Toast.makeText(this, "server is not connected", 1000);
			toast.show();
		}
	}
	private boolean isConnection() {
		URL url = null;
		JSONObject jArray = null;
		URLConnection cn;
		InputStream stream = null;

		try {
			url = new URL("http://sportsquare.phpfogapp.com/vrienden/1");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		try {
			cn = url.openConnection();
			cn.connect();
			stream = cn.getInputStream();

			DataInputStream data = new DataInputStream(stream);

			if (data == null) {
				return false;
			}
			String result = data.readLine();

			if (result.length() == 0) {
				return false;
			} else {
				// /////////////////////////////////////////////
				/*viewVrienden = new Runnable() {
					@Override
					public void run() {
						//getZalen();
					}
				};
				Thread thread = new Thread(null, viewVrienden, "MagentoBackground");
				thread.start();
				m_ProgressDialog = ProgressDialog.show(MyVrienden.this,
						"Please wait...", "Retrieving data ...", true);*/
				try {

					jArray = new JSONObject(result);
					JSONArray sportsquare = jArray.getJSONArray("sportsquare");
					for (int i = 0; i < sportsquare.length(); i++) {
						// HashMap<String, String> map = new HashMap<String,
						// String>();
						JSONObject e = sportsquare.getJSONObject(i);
						Person person = new Person();
						person.setPersonId(e.getInt("id"));
						person.setPersonFirstName(e.getString("firstname"));
						person.setPersonLastName(e.getString("lastname"));
						person.setPersonLastVisit(e.getString("last_visit"));
						person.setPersonZaal(e.getString("sportzaal"));
						m_friends.add(person);
						Log.d("Name", e.getString("firstname"));
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}// ////////////////////////////////////////

				// Log.d("data3", jArray.toString());
				return true;
			}
		} catch (IOException e) {
			return false;
		}
	}
}
