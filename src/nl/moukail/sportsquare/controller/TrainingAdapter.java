package nl.moukail.sportsquare.controller;

import java.util.ArrayList;
import nl.moukail.sportsquare.model.Training;
import nl.moukail.sportsquare.view.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class TrainingAdapter extends ArrayAdapter<Training>{
	private ArrayList<Training> items;
	private Context mContext;
    public TrainingAdapter(Context context, int textViewResourceId, ArrayList<Training> items) {
            super(context, textViewResourceId, items);
            this.items = items;
            this.mContext = context;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
            View v = convertView;
            if (v == null) {
                LayoutInflater vi = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = vi.inflate(R.layout.training_list_item, null);
            }
            Training t = items.get(position);
            if (t != null) {
                    TextView tt = (TextView) v.findViewById(R.id.trainingId);
                    TextView bt = (TextView) v.findViewById(R.id.dagNummer);
                    if (tt != null) {
                          tt.setText("Training Id: "+t.getTrainingId());                            }
                    if(bt != null){
                          bt.setText("Dag: "+ t.getDagNummer());
                    }
            }
            return v;
    }
}
