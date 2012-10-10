package nl.moukail.sportsquare.view;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import nl.moukail.sportsquare.controller.DagAdapter;
import nl.moukail.sportsquare.data.SportSquareData;
import nl.moukail.sportsquare.model.Dag;
import nl.moukail.sportsquare.model.Opdracht;
import nl.moukail.sportsquare.model.Training;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class MySchema extends ListActivity {
	private ArrayList<Dag> m_dagen;
	
	private DagAdapter m_adapter;
	private Runnable viewDagen;
	private ProgressDialog m_ProgressDialog = null;
	private SportSquareData sportSquareDb;
	private SQLiteDatabase db;
	//private int dagnummer;
	private static String[] FROM_DAG = { "ID", "DAGNAAM"};
	private static String[] FROM_TRAINING = { "ID", "DAGNUMMER"};
	private static String[] FROM_OPDRACHT = { "ID", "NAME", "SERIE", "HERHAAL", "TRAINING_ID"};
	private static int[] TO = { R.id.dagId, R.id.dagName};
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.schema);
		
		sportSquareDb = new SportSquareData(this);
		db = sportSquareDb.getWritableDatabase();
		m_dagen = new ArrayList<Dag>();
		showData();

		ListView lv = getListView();
		lv.setTextFilterEnabled(true);
		lv.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Log.d("test599", "" + m_dagen.get(position).getTrainingen().size());
				Intent i = new Intent(parent.getContext(), TrainingOverzicht.class);
				i.putExtra("DAG", m_dagen.get(position));
				startActivity(i);
			}
		});
	}

	@Override
	public void onDestroy(){
		super.onDestroy();
		db.close();
	}
	private void showData() {
		m_dagen.clear();
		Cursor cursor1 = db.query("DAG", FROM_DAG, null, null, null, null, "ID ASC");
		startManagingCursor(cursor1);
		if (cursor1.getCount() > 0) {
			while(cursor1.moveToNext()){
				Dag dag = new Dag();
				dag.setDagId(cursor1.getInt(cursor1.getColumnIndex("ID")));
				dag.setDagName(cursor1.getString(cursor1.getColumnIndex("DAGNAAM")));
				Cursor cursor2 = db.query("TRAINING", FROM_TRAINING, "DAGNUMMER ="+cursor1.getInt(cursor1.getColumnIndex("ID")) , null, null, null, "ID ASC");
				startManagingCursor(cursor2);
				if (cursor2.getCount() > 0) {
					while(cursor2.moveToNext()){
						Training training = new Training();
						training.setTrainingId(cursor2.getInt(cursor2.getColumnIndex("ID")));
						training.setDagNummer(cursor2.getInt(cursor2.getColumnIndex("DAGNUMMER")));
						
						Cursor cursor3 = db.query("OPDRACHT", FROM_OPDRACHT, "TRAINING_ID ="+cursor2.getInt(cursor2.getColumnIndex("ID")) , null, null, null, "ID ASC");
						startManagingCursor(cursor3);
						if (cursor3.getCount() > 0) {
							while(cursor3.moveToNext()){
								Opdracht opdracht = new Opdracht();
								opdracht.setOpdrachtId(cursor3.getInt(cursor3.getColumnIndex("ID")));
								opdracht.setOpdrachtName(cursor3.getString(cursor3.getColumnIndex("NAME")));
								opdracht.setOpdrachtSerie(cursor3.getInt(cursor3.getColumnIndex("SERIE")));
								opdracht.setOpdrachtHerhaal(cursor3.getInt(cursor3.getColumnIndex("HERHAAL")));
								
								training.addOpdracht(opdracht);
							}
						}
						dag.addTraining(training);
					}
				}
				m_dagen.add(dag);
			}
			m_adapter = new DagAdapter(this, R.layout.schema_list_item, m_dagen);
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
			url = new URL("http://sportsquare.phpfogapp.com/schema/1");
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
				try {

					jArray = new JSONObject(result);
					JSONArray sportsquare = jArray.getJSONArray("sportsquare");
					db.delete("DAG", null, null);
					db.delete("TRAINING", null, null);
					db.delete("OPDRACHT", null, null);
					for (int i = 0; i < sportsquare.length(); i++) {
						// HashMap<String, String> map = new HashMap<String,
						// String>();
						JSONObject e = sportsquare.getJSONObject(i);
						Dag dag = new Dag();
						dag.setDagId(e.getInt("dagnummer"));
						dag.setDagName(e.getString("dagnaam"));
						
						JSONArray trainingen = e.getJSONArray("trainingen");
						for (int j = 0; j < trainingen.length(); j++) {
							JSONObject t = trainingen.getJSONObject(j);
							Training training = new Training();
							training.setTrainingId(t.getInt("trainingId"));
							training.setDagNummer(e.getInt("dagnummer"));
							JSONArray opdrachten = t.getJSONArray("opdrachten");
							for(int z = 0; z < opdrachten.length(); z++){
								JSONObject o = opdrachten.getJSONObject(z);
								Opdracht opdracht = new Opdracht();
								opdracht.setOpdrachtId(o.getInt("opdrachtId"));
								opdracht.setOpdrachtTrainingId(t.getInt("trainingId"));
								opdracht.setOpdrachtName(o.getString("name"));
								opdracht.setOpdrachtSerie(o.getInt("serie"));
								opdracht.setOpdrachtHerhaal(o.getInt("herhalen"));
								
								//training.addOpdracht(opdracht);
								addOpdracht(opdracht);
							}
							//dag.addTraining(training);
							addTraining(training);
						}
						//m_dagen.add(dag);
						addDag(dag);
						Log.d("Name", e.getString("dagnaam"));
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

	private void addDag(Dag dag) {
		db = sportSquareDb.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("ID", dag.getDagId());
		values.put("DAGNAAM", dag.getDagName());
		db.insertOrThrow("DAG", null, values);
	}
	
	private void addTraining(Training training) {
		db = sportSquareDb.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("ID", training.getTrainingId());
		values.put("DAGNUMMER", training.getDagNummer());
		db.insertOrThrow("TRAINING", null, values);
	}
	private void addOpdracht(Opdracht opdracht) {
		db = sportSquareDb.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("ID", opdracht.getOpdrachtId());
		values.put("NAME", opdracht.getOpdrachtName());
		values.put("SERIE", opdracht.getOpdrachtSerie());
		values.put("HERHAAL", opdracht.getOpdrachtHerhaal());
		values.put("TRAINING_ID", opdracht.getOpdrachtTrainingId());
		db.insertOrThrow("OPDRACHT", null, values);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu_dag_list, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_refresh:
			loadData();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
}
