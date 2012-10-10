/**
 * 
 */
package nl.moukail.sportsquare.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;

/**
 * @author MOUKAIL
 *
 */
public class Splash extends Activity {
	long m_dwSplashTime = 1000;
	boolean m_bPaused = false;
	boolean m_bSplashActive = true;
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		Thread splashTimer = new Thread(){
			public void run(){
				try{
					long ms = 0;
					while(m_bSplashActive && ms < m_dwSplashTime){
						sleep(300);
						if(!m_bPaused)
							ms += 100;
					}
					startActivity(new Intent("com.google.app.splashy.CLEARSPLASH"));
				}catch(Exception e){
					Log.e("Splash", e.toString());
				}
				finally{
					finish();
				}
			}
		};
		splashTimer.start();
	}
	
	@Override
	protected void onPause(){
		super.onPause();
		m_bPaused = true;
	}
	
	@Override
	protected void onResume(){
		super.onResume();
		m_bPaused = false;
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event){
		super.onKeyDown(keyCode, event);
		m_bSplashActive = false;
		return true;
	}
	
	@Override
	protected void onStop()
    {
    	super.onStop();
    }
	
	@Override
	protected void onDestroy()
    {
    	super.onDestroy();
    }
}
