package nl.moukail.sportsquare.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;

public class SportSquare extends Activity implements OnClickListener {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		View schemaButton = findViewById(R.id.schema_button);
		schemaButton.setOnClickListener(this);
		View mapButton = findViewById(R.id.list_button);
		mapButton.setOnClickListener(this);
		View vriendenButton = findViewById(R.id.vrienden_button);
		vriendenButton.setOnClickListener(this);
		View exitButton = findViewById(R.id.exit_button);
		exitButton.setOnClickListener(this);
	}

	public void onClick(View v) {
		Intent i;
		switch (v.getId()) {
		case R.id.schema_button:
			i = new Intent(this, MySchema.class);
			startActivity(i);
			break;
		case R.id.list_button:
			i = new Intent(this, ZaalList.class);
			startActivity(i);
			break;
		case R.id.vrienden_button:
			i = new Intent(this, MyVrienden.class);
			startActivity(i);
			break;
		case R.id.exit_button:
			finish();
			break;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_help:
			startActivity(new Intent(this, Help.class));
			return true;
		case R.id.menu_about:
			startActivity(new Intent(this, About.class));
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
}