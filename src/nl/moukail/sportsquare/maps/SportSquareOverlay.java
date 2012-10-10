package nl.moukail.sportsquare.maps;

import static android.provider.BaseColumns._ID;
import java.util.ArrayList;

import nl.moukail.sportsquare.data.SportSquareData;
import nl.moukail.sportsquare.model.Zaal;
import nl.moukail.sportsquare.view.ZaalDetails;


import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.util.Log;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;

public class SportSquareOverlay extends BalloonItemizedOverlay<OverlayItem> {

	private ArrayList<OverlayItem> mOverlays = new ArrayList<OverlayItem>();
	private ArrayList<Zaal> m_zalen;
	Context mContext;
	SQLiteDatabase db;
	private SportSquareData sportSquareDb;
	private static String[] FROM = { _ID, "NAME", "VISITORS", "ADRESS", "CITY", "LATITUDE",
			"LONGITUDE" };


	public SportSquareOverlay(Drawable defaultMarker, MapView mapView) {
		super(boundCenter(defaultMarker), mapView);
		mContext = mapView.getContext();
		sportSquareDb = new SportSquareData(mContext);
		db = sportSquareDb.getReadableDatabase();
		m_zalen = new ArrayList<Zaal>();
		
		Cursor cursor = db.query("ZAAL", FROM, null, null, null, null,
				"NAME DESC");
		if (cursor.getCount() > 0) {
			while (cursor.moveToNext()) {
				
				Zaal zaal = new Zaal();
				zaal.setZaalId(cursor.getInt(cursor.getColumnIndex(_ID)));
				zaal.setZaalName(cursor.getString(cursor.getColumnIndex("NAME")));
				zaal.setZaalVisitors(cursor.getInt(cursor.getColumnIndex("VISITORS")));
				zaal.setZaalAdress(cursor.getString(cursor.getColumnIndex("ADRESS")));
				zaal.setZaalCity(cursor.getString(cursor.getColumnIndex("CITY")));
				m_zalen.add(zaal);
				
				GeoPoint point = new GeoPoint((int) (cursor.getDouble(cursor
						.getColumnIndex("LATITUDE")) * 1000000),
						(int) (cursor.getDouble(cursor
								.getColumnIndex("LONGITUDE")) * 1000000));
				OverlayItem overlayitem = new OverlayItem(point,
						cursor.getString(cursor.getColumnIndex("NAME")),
						"Adress: " + cursor.getString(cursor.getColumnIndex("ADRESS")) + "\n Aantal bezoekers: "+ cursor.getInt(cursor.getColumnIndex("VISITORS")));
				addOverlay(overlayitem);
			}
		}
	}

	public void addOverlay(OverlayItem overlay) {
		mOverlays.add(overlay);
		populate();
	}

	@Override
	protected OverlayItem createItem(int i) {
		return mOverlays.get(i);
	}

	@Override
	public int size() {
		return mOverlays.size();
	}

	@Override
	protected boolean onBalloonTap(int index, OverlayItem item) {
		//OverlayItem item = mOverlays.get(index);
		/*Toast.makeText(mContext, "onBalloonTap for overlay index " + index,
				Toast.LENGTH_LONG).show();*/
		Intent i = new Intent(mContext, ZaalDetails.class);
		i.putExtra("ZAAL", m_zalen.get(index));
		Log.d("test333", m_zalen.get(index).getZaalAdress());
		mContext.startActivity(i);
		
		
		/*AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
		builder.setMessage(item.getSnippet())
				.setTitle(item.getTitle())
				.setCancelable(true)
				.setPositiveButton("View Details",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								Intent i = new Intent(mContext,
										ZaalDetails.class);
								i.putExtra("NAME", m_zalen.get(index)
										.getZaalName());
								i.putExtra("ADRESS", m_zalen.get(index)
										.getZaalAdress());
								Log.d("test333", m_zalen.get(index).getZaalAdress());
								mContext.startActivity(i);
							}
						})
				.setNegativeButton("Close window",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								dialog.dismiss();
							}
						});
		AlertDialog alert = builder.create();
		alert.show();*/

		return true;
	}
}
