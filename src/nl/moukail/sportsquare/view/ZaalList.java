package nl.moukail.sportsquare.view;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import nl.moukail.sportsquare.data.SportSquareData;
import nl.moukail.sportsquare.model.Zaal;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import static android.provider.BaseColumns._ID;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class ZaalList extends ListActivity {

	private ArrayList<Zaal> m_zalen;
	private SimpleCursorAdapter m_adapter;
	private Runnable viewZalen;
	private ProgressDialog m_ProgressDialog = null;
	private SportSquareData sportSquareDb;
	SQLiteDatabase db;
	private static String[] FROM = { _ID, "VISITORS", "NAME", "ADRESS", "CITY"};
	private static int[] TO = { R.id.zaalId, R.id.ZaalName, R.id.ZaalAdress, };

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.zaal_list);

		sportSquareDb = new SportSquareData(this);
		db = sportSquareDb.getWritableDatabase();
		m_zalen = new ArrayList<Zaal>();
		showData();

		ListView lv = getListView();
		lv.setTextFilterEnabled(true);
		lv.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Details Activity weergeven
				//Log.d("con", mContext.toString());
				sportSquareDb.close();
				Intent i = new Intent(parent.getContext(), ZaalDetails.class);
				i.putExtra("ZAAL", m_zalen.get(position));
				/*i.putExtra("NAME", m_zalen.get(position).getZaalName());
				i.putExtra("ADRESS", m_zalen.get(position).getZaalAdress());*/
				startActivity(i);
				// --------> // TextView tx = (TextView) view;
				/*Toast.makeText(getApplicationContext(), "pos: " + position,
						Toast.LENGTH_SHORT).show();*/
			}
		});
	}

	@Override
	public void onDestroy(){
		super.onDestroy();
		sportSquareDb.close();
	}
	
	@Override
	public void onPause(){
		super.onPause();
		sportSquareDb.close();
	}
	
	@Override
	public void onResume(){
		super.onResume();
		sportSquareDb = new SportSquareData(this);
		db = sportSquareDb.getWritableDatabase();
	}
	private void showData() {
		Cursor cursor = db.query("ZAAL", FROM, null, null, null, null,
				"NAME DESC");
		startManagingCursor(cursor);
		if (cursor.getCount() > 0) {
			while(cursor.moveToNext()){
				Zaal zaal = new Zaal();
				zaal.setZaalId(cursor.getInt(cursor.getColumnIndex(_ID)));
				Log.d("VISITORS", "" + cursor.getInt(cursor.getColumnIndex("VISITORS")));
				zaal.setZaalName(cursor.getString(cursor.getColumnIndex("NAME")));
				zaal.setZaalVisitors(cursor.getInt(cursor.getColumnIndex("VISITORS")));
				zaal.setZaalAdress(cursor.getString(cursor.getColumnIndex("ADRESS")));
				zaal.setZaalCity(cursor.getString(cursor.getColumnIndex("CITY")));
				m_zalen.add(zaal);
			}
			m_adapter = new SimpleCursorAdapter(this, R.layout.zaal_list_item,
					cursor, FROM, TO);
			setListAdapter(m_adapter);
			//cursor.close();
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
			url = new URL("http://sportsquare.phpfogapp.com/sportzalen");
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
				viewZalen = new Runnable() {
					public void run() {
						getZalen();
					}
				};
				Thread thread = new Thread(null, viewZalen, "MagentoBackground");
				thread.start();
				m_ProgressDialog = ProgressDialog.show(ZaalList.this,
						"Please wait...", "Retrieving data ...", true);
				try {

					jArray = new JSONObject(result);
					JSONArray sportsquare = jArray.getJSONArray("sportsquare");
					db.delete("ZAAL", null, null);
					for (int i = 0; i < sportsquare.length(); i++) {
						// HashMap<String, String> map = new HashMap<String,
						// String>();
						JSONObject e = sportsquare.getJSONObject(i);
						Zaal zaal = new Zaal();
						zaal.setZaalId(e.getInt("id"));
						zaal.setZaalName(e.getString("name"));
						zaal.setZaalVisitors(e.getInt("visitor_count"));
						zaal.setZaalAdress(e.getString("adress"));
						zaal.setZaalCity(e.getString("city"));
						zaal.setZaalLatitude(e.getString("latitude"));
						zaal.setZaalLongitude(e.getString("longitude"));
						addZaal(zaal);
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

	private void addZaal(Zaal zaal) {
		db = sportSquareDb.getWritableDatabase();
		ContentValues values = new ContentValues();
		//values.put("_ID", zaal.getZaalId());
		values.put("NAME", zaal.getZaalName());
		values.put("VISITORS", zaal.getZaalVisitors());
		values.put("ADRESS", zaal.getZaalAdress());
		values.put("CITY", zaal.getZaalCity());
		values.put("LATITUDE", zaal.getZaalLatitude());
		values.put("LONGITUDE", zaal.getZaalLongitude());
		db.insertOrThrow("ZAAL", null, values);
	}

	private void getZalen() {
		try {
			for (int i = 0; i < 9; i++) {
				Zaal zaal = new Zaal();
				zaal.setZaalName("Zaal" + i);
				zaal.setZaalAdress("Adress" + i);
				m_zalen.add(zaal);
			}

			Thread.sleep(1000);
			Log.i("ARRAY", "" + m_zalen.size());
		} catch (Exception e) {
			Log.e("BACKGROUND_PROC", e.getMessage());
		}
		runOnUiThread(returnRes);
	}

	private Runnable returnRes = new Runnable() {

		public void run() {
			if (m_zalen != null && m_zalen.size() > 0) {
				m_adapter.notifyDataSetChanged();
				// for (int i = 0; i < m_zalen.size(); i++)
				// m_adapter.add(m_zalen.get(i));
			}
			m_ProgressDialog.dismiss();
			m_adapter.notifyDataSetChanged();
		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu_zaal_list, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_refresh:
			loadData();
			return true;
		case R.id.menu_zaal_map:
			
			Intent i = new Intent(this, MyMap.class);
			startActivity(i);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
}
