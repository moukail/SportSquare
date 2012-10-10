package nl.moukail.sportsquare.view;

import java.util.ArrayList;


import nl.moukail.sportsquare.controller.TrainingAdapter;
import nl.moukail.sportsquare.model.Dag;
import nl.moukail.sportsquare.model.Training;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class TrainingOverzicht extends ListActivity {
	private ArrayList<Training> m_trainingen;
	private TrainingAdapter m_adapter;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.training_overzicht_list);
		
		/*sportSquareDb = new SportSquareData(this);
		db = sportSquareDb.getWritableDatabase();*/
		Intent i = getIntent();
		Dag dag = (Dag) i.getSerializableExtra("DAG");
		m_trainingen = (ArrayList<Training>) dag.getTrainingen();
		//Log.d("Test456", "" + dag.getTrainingen().size());
		//showData();
		m_adapter = new TrainingAdapter(this, R.layout.training_overzicht_list, m_trainingen);
		setListAdapter(m_adapter);
		
		ListView lv = getListView();
		lv.setTextFilterEnabled(true);
		lv.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				Intent i = new Intent(parent.getContext(), OpdrachtOverzicht.class);
				i.putExtra("TRAINING", m_trainingen.get(position));
				startActivity(i);
			}
		});
	}
	
	@Override
	public void onDestroy(){
		super.onDestroy();
	}

	/*private void addTraining(Training training) {
		db = sportSquareDb.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("_ID", training.getTrainingId());
		values.put("DAGNUMMER", training.getDagNummer());
		db.insertOrThrow("TRAINING", null, values);
	}*/


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
			//loadData();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
}
