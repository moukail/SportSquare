package nl.moukail.sportsquare.controller;

import java.util.ArrayList;

import nl.moukail.sportsquare.model.Person;
import nl.moukail.sportsquare.view.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class PersonAdapter extends ArrayAdapter<Person> {

	private ArrayList<Person> items;
	private Context mContext;
    public PersonAdapter(Context context, int textViewResourceId, ArrayList<Person> items) {
            super(context, textViewResourceId, items);
            this.items = items;
            this.mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
            View v = convertView;
            if (v == null) {
                LayoutInflater vi = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = vi.inflate(R.layout.friend_list_item, null);
            }
            Person p = items.get(position);
            if (p != null) {
                    TextView tt = (TextView) v.findViewById(R.id.person_firstname);
                    TextView bt = (TextView) v.findViewById(R.id.person_last_visit);
                    if (tt != null) {
                          tt.setText("First name: "+p.getPersonFirstName());                            }
                    if(bt != null){
                          bt.setText("Last Visit: "+ p.getLastVisit());
                    }
            }
            return v;
    }

}
