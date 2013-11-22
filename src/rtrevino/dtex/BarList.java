package rtrevino.dtex;

import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;



// -------------
// BarList Class
// -------------

public class BarList extends ListActivity implements OnItemClickListener {

	
	public ListView DTexBarListView;

	
	//public Button BarSelection;
	public TextView BarSelection;
	public TextView BarAddress;
	public Button BarChoice;
	
	public String BarIndex;
	
	//private RatingBar BarRating;
	
	private ImageButton BarRating;
	
	public static final String TAG = "BarList";
	
	public boolean mFirstCreate = false;
	
	public static final int DIALOG_DIFFICULTY_ID = 0;
	public static final int DIALOG_QUIT_ID = 1;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_barlist);

		DTex.DTexDatasource.open();
		
		Bundle extras = getIntent().getExtras();
		//String query_type = extras.getString("query_type");
		//String query_args = extras.getString("query_args");
		//String today = extras.getString("today");
		String query_type = "";
		String query_args = "";
		try {
			String s = null;
			query_type = extras.getString("query_type");
			query_args = extras.getString("query_args").trim();
		}
		catch (Exception e) {
			query_type = null;
			query_args = null;
		}
		Log.d(TAG, "BarList query_type = " + query_type);
		Log.d(TAG, "BarList query_args = " + query_args);
//		List<DTexBar> bars = getBarLst(DTex.DTexDatasource);
//		printBarLst(bars);
//		List<DTexDrink> drinks = getDrinkLst(DTex.DTexDatasource);
//		printDrinkLst(drinks);	
//		Log.d(TAG, "Drink data = " + DTex.DTexDatasource.getDTexDatabase().getDTex_DrinksLst().size());
//		printDrinkLst(DTex.DTexDatasource.getDTexDatabase().getDTex_DrinksLst());
		
		List<DTexBar> barlst = DTex.DTexDatasource.getAllBars();
		if (query_args.equals("Dirty 6th")) {
			barlst = DTex.DTexDatasource.getAreaSpecials(query_args);
			Log.d(TAG, "BarList query arg");
		}
		else if (query_type.equals("area_query")) {
			barlst = DTex.DTexDatasource.getAreaBars(query_args);
		}
		else if (query_type.equals("bar_query")) {
			if (query_args.equals("all"))
				barlst = DTex.DTexDatasource.getAllBars();
			else
				barlst = DTex.DTexDatasource.getAllBars();
		}
		
		DTexBarListView = (ListView) findViewById(R.id.listview2);
		
		DTexBarArrayAdapter adapter = new DTexBarArrayAdapter(this, R.layout.barlist_row_item, barlst);
		
		DTexBarListView.setAdapter(adapter);
		
		
		//ArrayAdapter<DTexBar> adapter = new ArrayAdapter<DTexBar>(this, android.R.layout.simple_list_item_1, barlst);
		
		// use the SimpleCursorAdapter to show the elements in a ListView
		//ArrayAdapter<DTexBar> adapter = new ArrayAdapter<DTexBar>(this, android.R.layout.simple_list_item_1, barlst);
		//List<String> barlst2 = DTex.DTexDatasource.getAllBarNames();
		//String[] from = new String[]{ MySQLiteHelper.COLUMN_NAME };
		//int[] to = new int[]{ R.id.label };
		
		//Cursor c = DTex.DTexDatasource.getDatabase().rawQuery("select * from DTex_Bars", new String[]{});
		
		//Cursor c2 = Datasource.getDatabase().rawQuery("select * from DTex_Drinks", new String[]{});
		//SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.main, c, from, to);
//		ListView barview = (ListView) findViewById(R.id.bar_list);		
//		ListView thisview = getListView();
		
		//setListAdapter(adapter);
		//getListView().setTextFilterEnabled(true);
		
		DTexBarListView.setOnItemClickListener(
				new OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> arg0, View view, int position, long id) 	{
						
						BarIndex = DTex.DTexDatasource.getBarName(position);
						
						Intent intent = new Intent(BarList.this, BarInfo.class);
						intent.putExtra("name", BarIndex);
						intent.putExtra("fkey", (long) position);
						startActivity(intent);
					}
				});
	/*	
		BarRating.setOnClickListener( new OnClickListener() {
			
			
			public void onClick(View v) {
				Log.d(TAG, "on favorite click");
			}
		});
		
		*/
		
	/*
		BarRating.setOnTouchListener(new OnTouchListener()   {
		    
			public boolean onTouch(View v, MotionEvent event) {
		        System.out.println( "onTouch");
		        
		        return true;
		    }
		} );
	*/	
		
		//getListView().setOnItemClickListener(new ListItemClickListener(0));
	
	}
	
	
	/*
	protected void onListItemClick(ListView l, View v, int position, long id) {
	    super.onListItemClick(l, v, position, id);
	    
		String query_type = "";
		String query_args = "";
		Bundle extras = getIntent().getExtras();
		try {
			String s = null;
			query_type = extras.getString("query_type");
			query_args = extras.getString("query_args").trim();
		}
		catch (Exception e) {
			query_type = null;
			query_args = null;
		}
		Log.d(TAG, "BarList query_type = " + query_type);
		Log.d(TAG, "BarList query_args = " + query_args);
		
		DTex.DTexDatasource.open();
	    
		List<DTexBar> barlst = DTex.DTexDatasource.getAllBars();
		if (query_args.equals("Dirty 6th")) {
			barlst = DTex.DTexDatasource.getAreaSpecials(query_args);
			Log.d(TAG, "BarList query arg");
		}
		else if (query_type.equals("area_query")) {
			barlst = DTex.DTexDatasource.getAreaBars(query_args);
		}
		else if (query_type.equals("bar_query")) {
			if (query_args.equals("all"))
				barlst = DTex.DTexDatasource.getAllBars();
			else
				barlst = DTex.DTexDatasource.getAllBars();
		}
	    
	    long foreign_id = DTex.DTexDatasource.getAllBars().get(position).getId();
	    String result = DTex.DTexDatasource.getAllBars().get(position).getName();
	    
	    foreign_id = barlst.get(position).getId();
	    Log.d(TAG, "foreign key = " + (int) foreign_id);
	    result = barlst.get(position).getName();
	    //Log.d(TAG, "foreign key name = " + result);
	    
	    
	    
	    Intent myIntent = new Intent(BarList.this, BarInfo.class);
	    myIntent.putExtra("name", result);
	    myIntent.putExtra("fkey", (long) foreign_id);
	    //BarList.this.startActivity(myIntent); 
	    startActivity(myIntent);
	    
//	    LayoutInflater inflate = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        v = inflate.inflate(R.layout.bar_view, null);   
	    //setContentView(R.layout.bar_view);
	    //getListView().setOnItemClickListener(new ListItemClickListener(0));
	    
	}
	*/

	/*
	private class ListItemClickListener extends Activity implements AdapterView.OnItemClickListener { 
		
		int location;
	
		public ListItemClickListener(int location) { 
			this.location = location;
		}
		
		public void onClick(View view) {
			//Log.d(TAG, "on click listener");
			TextView tv = (TextView) findViewById(R.id.bar_title);
			//setContentView(R.layout.bar_view);
		}
		
		protected void onListItemClick(ListView l, View v, int position, long id) {
		    //super.onListItemClick(l, v, position, id);	
			//getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		    TextView tv = (TextView) findViewById(R.id.bar_title);
		    String result = DTex.DTexDatasource.getAllBars().get(position).getName();
		    tv.setText(result);
		    TextView addr_view = (TextView) findViewById(R.id.bar_addr);
		    String bar_addr = DTex.DTexDatasource.getAllBars().get(position).getAddress();
		    addr_view.setText(bar_addr);
		    
//		    LayoutInflater inflate = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//	        v = inflate.inflate(R.layout.bar_view, null);
		    		    
		}

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
			// TODO Auto-generated method stub	    
		    TextView tv = (TextView) findViewById(R.id.bar_title);
		    //String result = DTex.DTexDatasource.getAllBars().get(position).getName();
			//Log.d("TAG", "ARG2 ------------- " + arg2);
		    String result = DTex.DTexDatasource.getAllBars().get(arg2).getName();
		    tv.setText(result);
		    //BarList.this.setContentView(R.layout.bar_view);
		    
//		    LayoutInflater inflater=(LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            View viewNew=inflater.inflate(R.layout.bar_view, null);
			
		}		
	} 
	*/
	
	public DTexDataSource getDataSource() {
		return DTex.DTexDatasource;
	}	
	public List<DTexBar> getBarLst(DTexDataSource source) {
		List<DTexBar> result = DTex.DTexDatasource.getDTexDatabase().getDTex_BarsLst();
		return result;
	}
	public List<DTexDrink> getDrinkLst(DTexDataSource source) {
		List<DTexDrink> result = DTex.DTexDatasource.getDTexDatabase().getDTex_DrinksLst();
		return result;
	}
	
	
	// Will be called via the onClick attribute of the buttons in main.xml
	/*
	public void onClick(View view) {
		@SuppressWarnings("unchecked")
		
		ArrayAdapter<DTexBar> adapter = (ArrayAdapter<DTexBar>) getListAdapter();
	    DTexBar bar = null;
	    switch (view.getId()) {
	    case R.id.add:
	      String[] comments = new String[] { "512", "Aquarium", "El Arroyo" };
	      int nextInt = new Random().nextInt(3);
	      // save the new comment to the database
	      bar = Datasource.createBar(comments[nextInt]);
	      adapter.add(bar);
	      break;
	    case R.id.delete:
	      if (getListAdapter().getCount() > 0) {
	        bar = (DTexBar) getListAdapter().getItem(0);
	        Datasource.deleteBar(bar);
	        adapter.remove(bar);
	      }
	      break;
	    }
	    adapter.notifyDataSetChanged();
	  }
	  */

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.dtex, menu);
		super.onCreateOptionsMenu(menu);
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.options_menu, menu);
		
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		
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
			intent = new Intent(this, DTexHome.class);
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
							BarList.this.finish();
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
		Intent intent = new Intent(this, DTexHome.class);
		intent.putExtra("query_type", "");
		intent.putExtra("query_args", "");
		startActivity(intent);
	}

	@Override
	protected void onResume() {
		DTex.DTexDatasource.open();
		super.onResume();
	}
	@Override
	protected void onPause() {
		DTex.DTexDatasource.close();
		super.onPause();
	}
	
	public void printBarLst(List<DTexBar> lst) {
		for (int i = 0; i < lst.size(); i++) {
			Log.d(TAG, "id ele - " + lst.get(i).getId());
		}
	}
	public void printDrinkLst(List<DTexDrink> lst) {
		Log.d(TAG, "printDrinkLst: ");
		for (int i = 0; i < lst.size(); i++) {
			Log.d(TAG, "Drink ele - " + lst.get(i).getBar() + " - " + lst.get(i).getId());
		}
	}



	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		
	}



	
}


