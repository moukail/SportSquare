package nl.moukail.sportsquare.controller;

import java.util.ArrayList;

import nl.moukail.sportsquare.model.Opdracht;
import nl.moukail.sportsquare.view.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class OpdrachtAdapter extends ArrayAdapter<Opdracht>{
	private ArrayList<Opdracht> items;
	private Context mContext;
    public OpdrachtAdapter(Context context, int textViewResourceId, ArrayList<Opdracht> items) {
            super(context, textViewResourceId, items);
            this.items = items;
            this.mContext = context;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
            View v = convertView;
            if (v == null) {
                LayoutInflater vi = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = vi.inflate(R.layout.opdracht_list_item, null);
            }
            Opdracht o = items.get(position);
            if (o != null) {
                    TextView tt = (TextView) v.findViewById(R.id.opdrachtId);
                    TextView bt = (TextView) v.findViewById(R.id.opdrachtName);
                    if (tt != null) {
                          tt.setText("Opdracht Id: "+o.getOpdrachtId());                            }
                    if(bt != null){
                          bt.setText("Name: "+ o.getOpdrachtName());
                    }
            }
            return v;
    }
}