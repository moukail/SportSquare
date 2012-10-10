package nl.moukail.sportsquare.controller;

import java.util.ArrayList;

import nl.moukail.sportsquare.model.Zaal;
import nl.moukail.sportsquare.view.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ZaalAdapter extends ArrayAdapter<Zaal> {

    private ArrayList<Zaal> items;
    Context mContext;
    public ZaalAdapter(Context context, int textViewResourceId, ArrayList<Zaal> items) {
            super(context, textViewResourceId, items);
            this.mContext = context;
            this.items = items;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
            View v = convertView;
            if (v == null) {
                LayoutInflater vi = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = vi.inflate(R.layout.zaal_list_item, null);
            }
            Zaal o = items.get(position);
            if (o != null) {
                    TextView tt = (TextView) v.findViewById(R.id.ZaalName);
                    TextView bt = (TextView) v.findViewById(R.id.ZaalAdress);
                    if (tt != null) {
                          tt.setText("Name: "+o.getZaalName());                            }
                    if(bt != null){
                          bt.setText("Status: "+ o.getZaalAdress());
                    }
            }
            return v;
    }
}
