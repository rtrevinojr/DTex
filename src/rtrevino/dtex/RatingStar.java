package rtrevino.dtex;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;

public class RatingStar extends Activity implements OnClickListener {

	private boolean isRated = false;
	ImageButton rated;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        rated = (ImageButton) findViewById(R.id.ratingStar);
        
        rated.setOnClickListener(new OnClickListener(){
        	@Override
        	public void onClick(View v) {
        		isRated = !isRated;
        		rated.setImageDrawable(RatingStar.this.getResources().getDrawable(R.drawable.rated));
        	}
        });
    }

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
}
