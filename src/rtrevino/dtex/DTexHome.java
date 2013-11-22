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

public class DTexHome extends Activity {
	
	//public static DTexDataSource DTexDatasource;
	
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

	public static final String TAG = "DTexHome";	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dtex_home);
		
//		InputStream bars_istream = getResources().openRawResource(R.raw.dtexbars_table);
//		InputStreamReader bars_istreamreader = new InputStreamReader(bars_istream);
//		InputStream drinks_istream = getResources().openRawResource(R.raw.dtexdrinks_table);
//		InputStreamReader drinks_istreamreader = new InputStreamReader(drinks_istream);		
//		DTexDatasource = new DTexDataSource(this, bars_istreamreader, drinks_istreamreader);
//		DTexDatasource.open();
//		
//		DTexDatasource.clearTable(MySQLiteHelper.TABLE_BARS);
//		DTexDatasource.clearTable(MySQLiteHelper.TABLE_DRINKS);
//	
//		boolean isEmptyBars = DTexDatasource.isEmptyTable(MySQLiteHelper.TABLE_BARS);
//		boolean isEmptyDrinks = DTexDatasource.isEmptyTable(MySQLiteHelper.TABLE_DRINKS);
//
//		if (isEmptyBars)
//			DTexDatasource.insertBarLst(DTexDatasource.getDTexDatabase().getDTex_BarsLst());
//		if (isEmptyDrinks)
//			DTexDatasource.insertDrinkLst(DTexDatasource.getDTexDatabase().getDTex_DrinksLst());
		
		setHomeButtons();
		
		//DTexDatasource.close();

		
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
			Intent intent = new Intent(this, DTexHome.class);
			startActivity(intent);
			return true;
		case R.id.favorite :
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
							DTexHome.this.finish();
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
	   setContentView(R.layout.activity_dtex_home);
	   setHomeButtons();
	}
	
	
	private class ButtonClickListener implements View.OnClickListener {
		
		int location;
		public ButtonClickListener(int location) {
			this.location = location;
		}
		
		public void onClick(View v) {
			
			switch(v.getId()) {
			
			case R.id.todays_specials :
				Log.d(TAG, "todays_specials");
				DTexHome.this.setContentView(R.layout.activity_bar_drinklist);
				Intent intent = new Intent(DTexHome.this, DrinkList.class);
				String today = getToday();
				intent.putExtra("query_type", "day_query");
				intent.putExtra("query_args", today);
				startActivity(intent);
				
				break;
			case R.id.search_day :
				Log.d(TAG, "search_day");
				DTexHome.this.setContentView(R.layout.day_choice);
				setDayButtons();
				break;
			case R.id.search_area :
				Log.d(TAG, "search_area");
				DTexHome.this.setContentView(R.layout.area_choice);
				setAreaButtons();
				break;
			case R.id.search_bar :
				Log.d(TAG, "search_bar"); 
				//startActivity(intent);
				setContentView(R.layout.activity_barlist);
			    intent = new Intent(DTexHome.this, BarList.class);
			    intent.putExtra("query_type", "bar_query");
			    intent.putExtra("query_args", "all");
			    DTexHome.this.startActivity(intent);
			    break;
			    
			case R.id.search_map :
				Log.d(TAG, "search_map");
				intent = new Intent(DTexHome.this, DTexMap.class);
				DTexHome.this.startActivity(intent);
				break;
			case R.id.monday :
				Log.d(TAG, "monday press");
				setContentView(R.layout.activity_bar_drinklist);
				intent = new Intent(DTexHome.this, DrinkList.class);
				intent.putExtra("query_type", "day_query");
				intent.putExtra("query_args", "Monday");
				startActivity(intent);
				break;
			case R.id.tuesday :
				//Log.d(TAG, "tuesday press");
				setContentView(R.layout.activity_bar_drinklist);
				intent = new Intent(DTexHome.this, DrinkList.class);
				intent.putExtra("query_type", "day_query");
				intent.putExtra("query_args", "Tuesday");
				startActivity(intent);
				break;
			case R.id.wednesday :
				//Log.d(TAG, "wednesday press");			
				setContentView(R.layout.activity_bar_drinklist);
				intent = new Intent(DTexHome.this, DrinkList.class);
				intent.putExtra("query_type", "day_query");
				intent.putExtra("query_args", "Wednesday");
				startActivity(intent);
				break;
			case R.id.thursday :
				//Log.d(TAG, "thursday press");
				setContentView(R.layout.activity_bar_drinklist);
				intent = new Intent(DTexHome.this, DrinkList.class);	
				intent.putExtra("query_type", "day_query");
				intent.putExtra("query_args", "Thursday");
				startActivity(intent);
				break;
			case R.id.friday :
				//Log.d(TAG, "friday press");
				setContentView(R.layout.activity_bar_drinklist);
				intent = new Intent(DTexHome.this, DrinkList.class);
				intent.putExtra("query_type", "day_query");
				intent.putExtra("query_args", "Friday");
				startActivity(intent);
				break;
			case R.id.saturday :
				//Log.d(TAG, "saturday press");	
				setContentView(R.layout.activity_bar_drinklist);
				intent = new Intent(DTexHome.this, DrinkList.class);
				intent.putExtra("query_type", "day_query");
				intent.putExtra("query_args", "Saturday");
				startActivity(intent);
				break;
			case R.id.sunday :
				//Log.d(TAG, "sunday press");	
				setContentView(R.layout.activity_bar_drinklist);
				intent = new Intent(DTexHome.this, DrinkList.class);
				intent.putExtra("query_type", "day_query");
				intent.putExtra("query_args", "Sunday");
				startActivity(intent);
				break;
			case R.id.dirty_sixth :
				Log.d(TAG, "dirty sixth area press: ");
				setContentView(R.layout.main);
				intent = new Intent(DTexHome.this, BarList.class);
				intent.putExtra("query_type", "area_query");
				intent.putExtra("query_args", "Dirty Sixth");
				startActivity(intent);
				break;
			case R.id.west_sixth :
				Log.d(TAG, "dirty sixth area press: ");
				setContentView(R.layout.main);
				intent = new Intent(DTexHome.this, BarList.class);
				intent.putExtra("query_type", "area_query");
				intent.putExtra("query_args", "West Sixth");
				startActivity(intent);
				break;
			case R.id.west_campus :
				Log.d(TAG, "rainey area press: ");
				setContentView(R.layout.main);
				intent = new Intent(DTexHome.this, BarList.class);
				intent.putExtra("query_type", "area_query");
				intent.putExtra("query_args", "West Campus");
				startActivity(intent);
				break;
			case R.id.north_austin :
				Log.d(TAG, "domain area press: ");
				setContentView(R.layout.main);
				intent = new Intent(DTexHome.this, BarList.class);
				intent.putExtra("query_type", "area_query");
				intent.putExtra("query_args", "North Austin");
				startActivity(intent);
				break;
			}
			
		}
		
	}
	
	
	public void setDayButtons() {
		Monday = (Button) findViewById(R.id.monday);
		Monday.setOnClickListener(new ButtonClickListener(0));
		Tuesday = (Button) findViewById(R.id.tuesday);
		Tuesday.setOnClickListener(new ButtonClickListener(0));
		Wednesday = (Button) findViewById(R.id.wednesday);
		Wednesday.setOnClickListener(new ButtonClickListener(0));
		Thursday = (Button) findViewById(R.id.thursday);
		Thursday.setOnClickListener(new ButtonClickListener(0));
		Friday = (Button) findViewById(R.id.friday);
		Friday.setOnClickListener(new ButtonClickListener(0));
		Saturday = (Button) findViewById(R.id.saturday);
		Saturday.setOnClickListener(new ButtonClickListener(0));
		Sunday = (Button) findViewById(R.id.sunday);
		Sunday.setOnClickListener(new ButtonClickListener(0));
	}
	public void setAreaButtons() {
		WestSixthArea = (Button) findViewById(R.id.west_sixth);
		WestSixthArea.setOnClickListener(new ButtonClickListener(0));
		DirtySixthArea = (Button) findViewById(R.id.dirty_sixth);
		DirtySixthArea.setOnClickListener(new ButtonClickListener(0));
		NorthAustinArea = (Button) findViewById(R.id.north_austin);
		NorthAustinArea.setOnClickListener(new ButtonClickListener(0));
		WestCampusArea = (Button) findViewById(R.id.west_campus);
		WestCampusArea.setOnClickListener(new ButtonClickListener(0));
	}
	public void setHomeButtons() {
		TodaySpecials = (Button) findViewById(R.id.todays_specials);
		TodaySpecials.setOnClickListener(new ButtonClickListener(0));
		SearchDay = (Button) findViewById(R.id.search_day);
		SearchDay.setOnClickListener(new ButtonClickListener(0));
		SearchArea = (Button) findViewById(R.id.search_area);
		SearchArea.setOnClickListener(new ButtonClickListener(0));
		SearchBar = (Button) findViewById(R.id.search_bar);
		SearchBar.setOnClickListener(new ButtonClickListener(0));
		SearchArea = (Button) findViewById(R.id.search_area);
		SearchArea.setOnClickListener(new ButtonClickListener(0));
		
		SearchDTexMap = (Button) findViewById(R.id.search_map);
		SearchDTexMap.setOnClickListener(new ButtonClickListener(0));
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
