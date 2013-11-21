package rtrevino.dtex;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.model.Marker;

public class DTexMapInfoWindowAdapter extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dtex_mapinfo_window);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.dtex_map_info_window_adapter, menu);
		return true;
	}
	
	
    // Use default InfoWindow frame
    //@Override
    public View getInfoWindow(Marker marker) {              
        return null;
    }           

    // Defines the contents of the InfoWindow
    //@Override
    public View getInfoContents(Marker marker) {

        // Getting view from the layout file info_window_layout
        View v = getLayoutInflater().inflate(R.layout.dtex_mapinfo_window, null);

        // Getting reference to the TextView to set title
        TextView name = (TextView) v.findViewById(R.id.map_info_window_name);
        TextView addr = (TextView) v.findViewById(R.id.map_info_window_addr);
        

        name.setText(marker.getTitle() );
        addr.setText(marker.getSnippet());

        // Returning the view containing InfoWindow contents
        return v;

    }

}
