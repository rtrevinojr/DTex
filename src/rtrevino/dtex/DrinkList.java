package rtrevino.dtex;

import java.util.List;

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
import android.widget.ListView;

public class DrinkList extends Activity {
	
	//private BarsDataSource Datasource;
	
	public ListView DTexDrinkListView;
	
	private String BarSelection = "";
	private String QueryType = "";
	private long Fkey = 0;
	
	public static final String TAG = "DrinkList";
	
//	private SharedPreferences mPref;
//	private String BarInformation;
	
	private static final int DIALOG_DIFFICULTY_ID = 0;
	private static final int DIALOG_QUIT_ID = 1;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_drinklist);
		
		DTexDataSource Datasource = DTex.DTexDatasource;
		Datasource.open();
				
		Bundle extras = getIntent().getExtras();
		String query_type = extras.getString("query_type");
		String query_args = extras.getString("query_args");
		String today = extras.getString("today");
		long fkey = extras.getLong("fkey");
		
		Log.d(TAG, "query_type = " + query_type);
		Log.d(TAG, "query_args = " + query_args);
		Log.d(TAG, "foreign key = " + fkey);
		
		BarSelection = query_args;
		QueryType = query_type;
		Fkey = fkey;
		
		List<DTexDrink> result_cond;
		if (query_type.equals("day_query"))
			result_cond = DTex.DTexDatasource.getDaySpecials(query_args);
		else if (query_type.equals("bar_query")) {
			//result_cond = DTex.DTexDatasource.getBarSpecials(query_args);
			result_cond = DTex.DTexDatasource.getKeyBarSpecials(Fkey);
			BarSelection = query_args;
			
		}
		else if (query_type.equals("bar_day_query"))
			result_cond = DTex.DTexDatasource.getBarDaySpecials(query_args, "Monday");
		else
			result_cond = DTex.DTexDatasource.getAllDrinkSpecials();

		//List<DTexDrink> result2 = Datasource.getAllDrinkSpecials();
		//List<DTexDrink> result = Datasource.getDaySpecials("Tuesday");
		//ArrayAdapter<DTexDrink> adapter = new ArrayAdapter<DTexDrink>(this, )
		//ArrayAdapter<DTexDrink> adapter = new ArrayAdapter<DTexDrink>(this, android.R.layout.simple_list_item_1, result_cond);
		//ArrayAdapter<DTexDrink> adapter = new ArrayAdapter<DTexDrink>(this, R.layout.drinklist_row_item, result_cond);
		//setListAdapter(adapter);
		
		
		DTexDrinkListView = (ListView) findViewById(R.id.listview1);
        
        //View header = (View)getLayoutInflater().inflate(R.layout.listview_header_row, null);
        //listView1.addHeaderView(header);
		
		DTexArrayAdapter adapter = new DTexArrayAdapter(this, R.layout.drinklist_row_item, result_cond);
        
        DTexDrinkListView.setAdapter(adapter);
		
	}
/*
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.drink_list, menu);
		return true;
	}
	*/
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
		case R.id.favorite :
			showDialog(DIALOG_DIFFICULTY_ID);
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
							DrinkList.this.finish();
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
		Intent intent_bar = new Intent(this, BarInfo.class);
		Intent intent_home = new Intent(this, DTexHome.class);
		//SharedPreferences.Editor ed = mPref.edit(); 
		//ed.putString("BarChoice", BarInformation); 
		//ed.commit();
		Log.d(TAG, "BAR SELECTION ------------ " + BarSelection);
		Log.d(TAG, "QUERY SELECTION ---------- " + QueryType );
		if (QueryType.equals("bar_query") || QueryType.equals("bar_day_query")) {
			intent_bar.putExtra("name", BarSelection);
			intent_bar.putExtra("fkey", Fkey);
			startActivity(intent_bar);
		}
		else {
			startActivity(intent_home);
		}
	}
	
	
}
