package nl.moukail.sportsquare.view;

import java.util.ArrayList;

import nl.moukail.sportsquare.controller.OpdrachtAdapter;
import nl.moukail.sportsquare.model.Opdracht;
import nl.moukail.sportsquare.model.Training;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class OpdrachtOverzicht extends ListActivity {
	private ArrayList<Opdracht> m_opdrachten;
	private OpdrachtAdapter m_adapter;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.opdrachten_list);
		
		/*sportSquareDb = new SportSquareData(this);
		db = sportSquareDb.getWritableDatabase();*/
		Intent i = getIntent();
		Training training = (Training) i.getSerializableExtra("TRAINING");
		m_opdrachten = (ArrayList<Opdracht>) training.getOpdrachten();
		Log.d("Test456", "" + training.getOpdrachten().size());
		//showData();
		m_adapter = new OpdrachtAdapter(this, R.layout.opdrachten_list, m_opdrachten);
		setListAdapter(m_adapter);
		
		ListView lv = getListView();
		lv.setTextFilterEnabled(true);
		lv.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				Intent i = new Intent(parent.getContext(), OpdrachtDetails.class);
				i.putExtra("OPDRACHT", m_opdrachten.get(position));
				startActivity(i);
			}
		});
	}
	
	@Override
	public void onDestroy(){
		super.onDestroy();
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
			//loadData();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
}