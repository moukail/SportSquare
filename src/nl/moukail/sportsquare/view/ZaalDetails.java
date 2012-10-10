package nl.moukail.sportsquare.view;

import nl.moukail.sportsquare.model.Zaal;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class ZaalDetails extends Activity {
   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.zaal_details);
      Intent i = getIntent();
      Zaal zaal = (Zaal) i.getSerializableExtra("ZAAL");
      Log.d("Details",""+ zaal.getZaalVisitors());
      TextView tv1 = (TextView) findViewById(R.id.zaalDetailsName);
      tv1.setText(zaal.getZaalName());
      TextView tv2 = (TextView) findViewById(R.id.zaalDetailsVisitors);
      tv2.setText("" + zaal.getZaalVisitors());
      TextView tv3 = (TextView) findViewById(R.id.zaalDetailsAdress);
      tv3.setText(zaal.getZaalAdress());
      TextView tv4 = (TextView) findViewById(R.id.zaalDetailsCity);
      tv4.setText(zaal.getZaalCity());
      
   }
}