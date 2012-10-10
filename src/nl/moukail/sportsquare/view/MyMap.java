package nl.moukail.sportsquare.view;

import java.util.List;

import nl.moukail.sportsquare.maps.SportSquareOverlay;

import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;
import com.google.android.maps.Overlay;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MyMap extends MapActivity {
	private MapView mapView;
	private MapController controller;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map);
        initMapView();
        initMyLocation();
    }

	private void initMyLocation() {
		// TODO Auto-generated method stub
		final MyLocationOverlay overlay = new MyLocationOverlay(this, mapView);
		overlay.enableMyLocation();
		overlay.enableCompass();
		overlay.runOnFirstFix(new Runnable(){
			public void run(){
				controller.setZoom(13);
				controller.animateTo(overlay.getMyLocation());
			}
		});
		mapView.getOverlays().add(overlay);
	}

	private void initMapView() {
		// TODO Auto-generated method stub
		mapView = (MapView) findViewById(R.id.mapView);
		controller = mapView.getController();
		mapView.setSatellite(false);
		mapView.setBuiltInZoomControls(true);
		
		List<Overlay> mapOverlays = mapView.getOverlays();
        Drawable drawable = this.getResources().getDrawable(R.drawable.zalen_overlay);
        SportSquareOverlay itemizedoverlay = new SportSquareOverlay(drawable, mapView);
        mapOverlays.add(itemizedoverlay);
	}

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu_zaal_map, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_refresh:
			//loadData();
			return true;
		case R.id.menu_zaal_list:
			Intent i = new Intent(this, ZaalList.class);
			startActivity(i);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
}