package nl.moukail.sportsquare.view;

import nl.moukail.sportsquare.model.Person;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;


public class PersonDetails extends Activity {
	@Override
	   protected void onCreate(Bundle savedInstanceState) {
	      super.onCreate(savedInstanceState);
	      setContentView(R.layout.person_details);
	      Intent i = getIntent();
	      Person person = (Person) i.getSerializableExtra("VRIEND");
	      TextView tv1 = (TextView) findViewById(R.id.personFirstName);
	      tv1.setText(person.getPersonFirstName());
	      TextView tv2 = (TextView) findViewById(R.id.personLastName);
	      tv2.setText(person.getPersonLastName());
	      TextView tv3 = (TextView) findViewById(R.id.personLastVisit);
	      tv3.setText(person.getLastVisit());
	      TextView tv4 = (TextView) findViewById(R.id.personSportZaal);
	      tv4.setText(person.getPersonZaal());
	      
	   }
}
