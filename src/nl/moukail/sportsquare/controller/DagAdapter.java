package nl.moukail.sportsquare.controller;

import java.util.ArrayList;

import nl.moukail.sportsquare.model.Dag;
import nl.moukail.sportsquare.view.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class DagAdapter extends ArrayAdapter<Dag> {

	private ArrayList<Dag> items;
	private Context mContext;
    public DagAdapter(Context context, int textViewResourceId, ArrayList<Dag> items) {
            super(context, textViewResourceId, items);
            this.items = items;
            this.mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
            View v = convertView;
            if (v == null) {
                LayoutInflater vi = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = vi.inflate(R.layout.schema_list_item, null);
            }
            Dag d = items.get(position);
            if (d != null) {
                    TextView tt = (TextView) v.findViewById(R.id.dagId);
                    TextView bt = (TextView) v.findViewById(R.id.dagName);
                    if (tt != null) {
                          tt.setText("Dag: "+d.getDagId());                            }
                    if(bt != null){
                          bt.setText("Dagnaam: "+ d.getDagName());
                    }
            }
            return v;
    }

}
