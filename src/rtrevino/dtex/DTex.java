package rtrevino.dtex;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Calendar;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class DTex extends Activity {
	
	public static DTexDataSource DTexDatasource;
	
	public Button TodaySpecials;
	public Button SearchDay;
	public Button SearchArea;
	public Button SearchBar;
	public Button SearchDTexMap;
	
	public Button Monday;
	public Button Tuesday;
	public Button Wednesday;
	public Button Thursday;
	public Button Friday;
	public Button Saturday;
	public Button Sunday;
	
	public Button DirtySixthArea;
	public Button WestSixthArea;
	//public Button RaineyStArea;
	public Button TheDomainArea;
	public Button WestCampusArea;
	public Button NorthAustinArea;
	// public Button CampusArea;
	
	public Button DTexMap;
	
	public static final int DIALOG_DIFFICULTY_ID = 0;
	public static final int DIALOG_QUIT_ID = 1;

	public static final String TAG = "DTex";	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home);
		
		InputStream bars_istream = getResources().openRawResource(R.raw.dtexbars_table);
		InputStreamReader bars_istreamreader = new InputStreamReader(bars_istream);
		InputStream drinks_istream = getResources().openRawResource(R.raw.dtexdrinks_table);
		InputStreamReader drinks_istreamreader = new InputStreamReader(drinks_istream);		
		DTexDatasource = new DTexDataSource(this, bars_istreamreader, drinks_istreamreader);
		DTexDatasource.open();
		
		DTexDatasource.clearTable(MySQLiteHelper.TABLE_BARS);
		DTexDatasource.clearTable(MySQLiteHelper.TABLE_DRINKS);
	
		boolean isEmptyBars = DTexDatasource.isEmptyTable(MySQLiteHelper.TABLE_BARS);
		boolean isEmptyDrinks = DTexDatasource.isEmptyTable(MySQLiteHelper.TABLE_DRINKS);

		if (isEmptyBars)
			DTexDatasource.insertBarLst(DTexDatasource.getDTexDatabase().getDTex_BarsLst());
		if (isEmptyDrinks)
			DTexDatasource.insertDrinkLst(DTexDatasource.getDTexDatabase().getDTex_DrinksLst());
		
		//setHomeButtons();
		
		DTexDatasource.close();
		
		Intent intent = new Intent(this, DTexHome.class);
		startActivity(intent);
		
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
							DTex.this.finish();
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
	   Log.d("CDA", "onBackPressed Called");
	   setContentView(R.layout.home);
	   //setHomeButtons();
	}
	
	
	
	private static String getToday() {
		String [] weekdays = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
		Calendar cal = Calendar.getInstance();
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
		return weekdays[dayOfWeek -1];
		}
	
	@Override
	protected void onResume() {
		//DTexDatasource.open();
		super.onResume();
	}
	@Override
	protected void onPause() {
		//DTexDatasource.close();
		super.onPause();
	}

}
