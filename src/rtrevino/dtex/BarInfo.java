package rtrevino.dtex;

import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class BarInfo extends Activity {
	
	
	public static final String AUSTIN_TEXAS = "Austin, Texas";
	
	public static final String TAG = "BarInfo";
	
	private SharedPreferences mPrefs;	
	
	public TextView BarSelection;
	public TextView BarAddress;
	public TextView BarCity;
	public TextView BarArea;
	public TextView BarPhone;
	public TextView BarWebsite;
	
	public String BarChoice;
	public long ForeignKey;
	
	public Button ListDrinkSpecials;
	public Button ListTodayDrinkSpecials;
	
	private GoogleMap DTex_Bar_Map;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bar_info);
		
		mPrefs = getSharedPreferences("ttt_prefs", MODE_PRIVATE);
		//BarChoice = mPrefs.getString("BarChoice", "");
		
		BarSelection = (TextView) findViewById(R.id.bar_info);
		BarAddress = (TextView) findViewById(R.id.bar_addr);
		BarCity = (TextView) findViewById(R.id.bar_city);
		BarArea = (TextView) findViewById(R.id.bar_area);
		BarPhone = (TextView) findViewById(R.id.bar_phone);
		BarWebsite = (TextView)	findViewById(R.id.bar_website);
		
		// Getting reference to the SupportMapFragment of activity_dtex_map.xml
        MapFragment fm = (MapFragment) getFragmentManager().findFragmentById(R.id.bar_info_map);
        // Getting GoogleMap object from the fragment
        DTex_Bar_Map = fm.getMap();
        // Enabling MyLocation Layer of Google Map
        DTex_Bar_Map.setMyLocationEnabled(true);
        // Enable myLocationButton
        DTex_Bar_Map.getUiSettings().setMyLocationButtonEnabled(true);
        //DTex_Bar_Map.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 13));
        
        //DTex_Bar_Map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
		
		DTex.DTexDatasource.open();
		
		Bundle extras = getIntent().getExtras();
		String name = extras.getString("name");
		long fkey = extras.getLong("fkey");
		Log.d(TAG, "@@@@@@@@@@@@@@@@@@@@@@@@BAR info: key = " + fkey);
		BarSelection.setText(name);
		Log.d(TAG, "BARSELECTION = " + name);
		Log.d(TAG, "BAR ADDRESS = " + DTex.DTexDatasource.getBarAddress(fkey));
		
		BarSelection.setText(DTex.DTexDatasource.getBarName(fkey));
		BarAddress.setText(DTex.DTexDatasource.getBarAddress(fkey));
		BarCity.setText(AUSTIN_TEXAS);
		BarArea.setText(DTex.DTexDatasource.getBarArea(fkey));
		BarPhone.setText(DTex.DTexDatasource.getBarPhone(fkey));
		BarWebsite.setText(DTex.DTexDatasource.getBarWebsite(fkey));
		
        Geocoder coder = new Geocoder(this);
        //List<Address> address;

       try {
            //address = coder.getFromLocationName("" + BarAddress.getText(), 5);
//            if (address == null) 
//                return null;
            //Address loc = address.get(0);     
       		//LatLng myotherPosition = new LatLng(loc.getLatitude(), loc.getLongitude());
    	   
       		DTexBar bar_onmap = DTex.DTexDatasource.getBarFromKey(fkey);
       		Log.d( TAG, "BAR MAP LAT = " + bar_onmap.getLatitude());
       		Log.d( TAG, "BAR MAP LONG = " + bar_onmap.getLongitude());
       		LatLng map_pos = new LatLng(bar_onmap.getLatitude(), bar_onmap.getLongitude());
       		
       		DTex_Bar_Map.moveCamera(CameraUpdateFactory.newLatLngZoom(map_pos, 14));
       		DTex_Bar_Map.addMarker(new MarkerOptions().position(map_pos).title("" + BarSelection.getText())).showInfoWindow();     		
       		
            //GeoPoint p1 = new GeoPoint((int) (location.getLatitude() * 1E6), (int) (location.getLongitude() * 1E6));
       }
       catch(Exception e) {
    	   System.out.println( "Geo exception: " );
       }
		
		BarChoice = name;
		ForeignKey = fkey;
		
		ListDrinkSpecials = (Button) findViewById(R.id.list_drinks);
		ListTodayDrinkSpecials = (Button) findViewById(R.id.list_todays_drinks);
		ListDrinkSpecials.setOnClickListener(new ButtonClickListener(0));
		ListTodayDrinkSpecials.setOnClickListener(new ButtonClickListener(0));
		
	}

//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.bar_info, menu);
//		return true;
//	}
	
	private class ButtonClickListener implements View.OnClickListener { 
		
		int location;
		
		public ButtonClickListener(int location) { 
			this.location = location;
		}
		
		public void onClick(View view) {
			
			switch(view.getId()) {
			
			case R.id.list_drinks :
				Log.d(TAG, "list_drinks from button");
				setContentView(R.layout.activity_drinklist);
				Intent intent = new Intent(BarInfo.this, DrinkList.class);
				intent.putExtra("query_type", "bar_query");
				intent.putExtra("query_args", BarChoice);
				intent.putExtra("fkey", ForeignKey);
				BarInfo.this.startActivity(intent);
				break;
			case R.id.list_todays_drinks :
				Log.d(TAG, "list todays specials from button");
				setContentView(R.layout.activity_drinklist);
				intent = new Intent(BarInfo.this, DrinkList.class);
				intent.putExtra("query_type", "bar_day_query");
				intent.putExtra("query_args", BarChoice);
				intent.putExtra("fkey", ForeignKey);
				BarInfo.this.startActivity(intent);
				break;
			}
		} 
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.dtex, menu);
		super.onCreateOptionsMenu(menu);
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.options_menu, menu);
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {		
		switch (item.getItemId()) {	
		case R.id.home :
			//startNewGame();
			Intent intent = new Intent(this, DTexHome.class);
			startActivity(intent);
			return true;
		case android.R.id.home :
			intent = new Intent(this, BarList.class);
			startActivity(intent);
			return true;
		case R.id.favorite :
			showDialog(DTex.DIALOG_DIFFICULTY_ID);
			return true;
		case R.id.quit :
			showDialog(DTex.DIALOG_QUIT_ID);
			return true;
		}
		return false;
	}
	protected Dialog onCreateDialog(int id) {
		Dialog dialog = null;
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		switch (id) {
		case DTex.DIALOG_QUIT_ID :
			// Create the quit information dialog		
			builder.setMessage(R.string.quit_question)
					.setCancelable(false)
					.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {		
						@Override
						public void onClick(DialogInterface dialog, int id) {
							// TODO Auto-generated method stub
							BarInfo.this.finish();
						}			
					})
					.setNegativeButton(R.string.no, null);
			dialog = builder.create();
			break;
		}
		return dialog;
	}
	
	@Override 
	public void onBackPressed() {
		Intent intent = new Intent(this, BarList.class);
		intent.putExtra("query_type", "");
		intent.putExtra("query_args", "");
		startActivity(intent);
	}
	
	@Override
	protected void onStop() { 
		super.onStop();
		SharedPreferences.Editor ed = mPrefs.edit(); 
		ed.putString("BarChoice", BarChoice); 
		ed.commit();
	}

}
