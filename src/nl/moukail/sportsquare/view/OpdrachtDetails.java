package nl.moukail.sportsquare.view;

import nl.moukail.sportsquare.model.Opdracht;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class OpdrachtDetails extends Activity {
   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.opdracht_details);
      Intent i = getIntent();
      Opdracht opdracht = (Opdracht) i.getSerializableExtra("OPDRACHT");
      TextView tv1 = (TextView) findViewById(R.id.opdrachtId);
      tv1.setText(""+opdracht.getOpdrachtId());
      TextView tv2 = (TextView) findViewById(R.id.opdrachtName);
      tv2.setText(opdracht.getOpdrachtName());
      TextView tv3 = (TextView) findViewById(R.id.opdrachtSerie);
      tv3.setText(""+opdracht.getOpdrachtSerie());
      TextView tv4 = (TextView) findViewById(R.id.opdrachtHerhaal);
      tv4.setText(""+opdracht.getOpdrachtHerhaal());
      
   }
}