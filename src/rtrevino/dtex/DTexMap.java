package rtrevino.dtex;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


public class DTexMap extends Activity {
	
	private GoogleMap DTex_Map;
	
	public LatLng myPosition;
	
	
	private static final String DOBIE_ADDRESS = "2021 Guadalupe St, Austin, TX 78705";
	
	public static final int DIALOG_DIFFICULTY_ID = 0;
	public static final int DIALOG_QUIT_ID = 1;
	
	private static final String TAG = "DTexMap";
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dtex_map);
		
		// Getting reference to the SupportMapFragment of activity_dtex_map.xml
        MapFragment fm = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        // Getting GoogleMap object from the fragment
        DTex_Map = fm.getMap();
        // Enabling MyLocation Layer of Google Map
        DTex_Map.setMyLocationEnabled(true);
        // Enable myLocationButton
        DTex_Map.getUiSettings().setMyLocationButtonEnabled(true);
        
        // Getting LocationManager object from System Service LOCATION_SERVICE
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        // Creating a criteria object to retrieve provider
        Criteria criteria = new Criteria();
        // Getting the name of the best provider
        String provider = locationManager.getBestProvider(criteria, true);
        // Getting Current Location
        Location location = locationManager.getLastKnownLocation(provider);
        
        //DTex_Map.getUiSettings().setMyLocationButtonEnabled(true);

        if (location != null) {
        	// Getting latitude of the current location
        	double latitude = location.getLatitude();
        	// Getting longitude of the current location
        	double longitude = location.getLongitude();
        	// Creating a LatLng object for the current location
        	LatLng latLng = new LatLng(latitude, longitude);
        	myPosition = new LatLng(latitude, longitude);
        	//DTex_Map.addMarker(new MarkerOptions().position(myPosition).title("My location"));
        }
        
        //DTex_Map.moveCamera(CameraUpdateFactory.newLatLngZoom(myPosition, 13));
        
        String map_addr = "400 East 6th Street Austin, TX 78701";
        String chupa = "Chupacabra";
        
// method refactor
        
        Geocoder coder = new Geocoder(this);
        List<Address> address;
        List<Address> dobie_addr;

       try {
            address = coder.getFromLocationName(map_addr,5);
            dobie_addr = coder.getFromLocationName(DOBIE_ADDRESS, 1);
//            if (address == null) {
//                return null;
//            }
            Address loc = address.get(0);
            Address dobie_loc = dobie_addr.get(0);
            
//       		LatLng myotherPosition = new LatLng(loc.getLatitude(), loc.getLongitude());
//       		DTex_Map.addMarker(new MarkerOptions().position(myotherPosition).title("addr"));
            
            Log.d(TAG, " dobie lat = " + dobie_loc.getLatitude());
            Log.d(TAG, " dobie long = " + dobie_loc.getLongitude());
       		
       		LatLng dobiePos = new LatLng(loc.getLatitude(), loc.getLongitude());
       		DTex_Map.moveCamera(CameraUpdateFactory.newLatLngZoom(dobiePos, 14));
       		
       		//DTex_Map.setInfoWindowAdapter(new DTexMapInfoWindowAdapter());

            //GeoPoint p1 = new GeoPoint((int) (location.getLatitude() * 1E6), (int) (location.getLongitude() * 1E6));
       }
       catch(Exception e) {
    	   System.out.println( "Geo exception: " );
       }
       
       //setBarMarkOverlay(this);
       DTex.DTexDatasource.open();
       List<DTexBar> result = DTex.DTexDatasource.getAllBars();
       for (int i = 0; i < result.size(); i++) {
    	   LatLng pos = new LatLng(result.get(i).getLatitude(), result.get(i).getLongitude());
    	   DTex_Map.addMarker(new MarkerOptions().position(pos).title(result.get(i).getName()).snippet("" + result.get(i).getAddress()));
       }
       
       //DTex_Map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
 
       /*
       DTex_Map.setOnMarkerClickListener(new OnMarkerClickListener()
       {

           @Override
           public boolean onMarkerClick(Marker arg0) {
               
        	   Intent intent = new Intent(DTexMap.this, BarInfo.class);
        	   
       	    	intent.putExtra("name", arg0.getTitle());
       	    	intent.putExtra("fkey", Long.valueOf(arg0.getSnippet()));
       	    	
       	    	startActivity(intent);
        	   
               return true;
           }

       });
       */
       
       DTex_Map.setOnInfoWindowClickListener(new OnInfoWindowClickListener() {
           @Override
           public void onInfoWindowClick(Marker marker) {
              Intent intent = new Intent(DTexMap.this, BarInfo.class);
              intent.putExtra("name", marker.getTitle());
              intent.putExtra("fkey", Long.valueOf(marker.getSnippet()));
              startActivity(intent);

           }
       });
       
        
   		//LatLng myotherPosition = new LatLng(loc.getLatitude(), loc.getLongitude());
   		//DTex_Map.addMarker(new MarkerOptions().position(myotherPosition).title("addr"));
        
	}
	
	public static Address searchLocationByName(Context context, String locationName){
	    Geocoder geoCoder = new Geocoder(context, Locale.getDefault());
	    LatLng gp = null;
	    Address ad = null;
	    try {
	        List<Address> addresses = geoCoder.getFromLocationName(locationName, 1);
	        for(Address address : addresses){
	            gp = new LatLng((int)(address.getLatitude() * 1E6), (int)(address.getLongitude() * 1E6));
	            address.getAddressLine(1);
	            ad = address;
	            System.out.println( "*****************" + ad.toString());
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    return ad;
	}

	
	public void setBarMarkOverlay(Context context) {
		
		DTex.DTexDatasource.open();
		
		List<DTexBar> map_addresses = DTex.DTexDatasource.getAllBars();
		
	    Geocoder geoCoder = new Geocoder(context, Locale.getDefault());
	    LatLng gp = null;
	    Address ad = null;
	    try {
	    	
	    	for (DTexBar bar: map_addresses) {
	    		
	    		List<Address> addresses = geoCoder.getFromLocationName(bar.getAddress(), 1);
	    		int i = 0;
	    		
	    		for(Address address: addresses) {
	    			gp = new LatLng((int)(address.getLatitude() * 1E6), (int)(address.getLongitude() * 1E6));
	    			address.getAddressLine(1);
	    			ad = address;
	    			//System.out.println( "################# ad address = " + ad.toString());
	    			LatLngBounds bounds = this.DTex_Map.getProjection().getVisibleRegion().latLngBounds;
	    			
	           		LatLng markerPoint = new LatLng(ad.getLatitude(), ad.getLongitude());
	           		System.out.println( "****************** LATLNG ******** = " + "i - " + i++ + " - "+ bar.getName() + " ---- " + markerPoint);
	           		//if(bounds.contains(markerPoint))
	           			DTex_Map.addMarker(new MarkerOptions().position(markerPoint).title(bar.getName()).snippet("" + bar.getId()));
	           		i++;
	    		}

	    	}
	    } 
	    catch (IOException e) {
	    	System.out.println( " IO Exception");
	        e.printStackTrace();
	    }
		
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.android_tic_tac_toe, menu);
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
			Intent intent = new Intent(this, DTex.class);
			startActivity(intent);
			return true;
			
		case R.id.search :
			showDialog(DIALOG_DIFFICULTY_ID);
			intent = new Intent(this, BarList.class);
			return true;
		case R.id.quit :
			showDialog(DIALOG_QUIT_ID);
			return true;
		}
		return false;
	}
	
	protected Dialog onCreateDialog(int id) {
		
		Dialog dialog = null;
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		
		switch (id) {

		case DIALOG_QUIT_ID :
			// Create the quit information dialog		
			builder.setMessage(R.string.quit_question)
					.setCancelable(false)
					.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int id) {
							// TODO Auto-generated method stub
							//DTex.this.finish();
						}			
					})
					.setNegativeButton(R.string.no, null);
			dialog = builder.create();
			break;
		}
		return dialog;
	}

}
