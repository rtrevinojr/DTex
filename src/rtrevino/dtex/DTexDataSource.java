package rtrevino.dtex;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

public class DTexDataSource {
	
	// Database Fields
	private SQLiteDatabase Database;
	private MySQLiteHelper DBhelper;
	
	public DTexDatabase DTex_database;
	
	public static final String TAG = "BarsDataSource";
	
	private String[] Bars_Columns = { MySQLiteHelper.COLUMN_ID, MySQLiteHelper.COLUMN_NAME,
			MySQLiteHelper.COLUMN_ADDRESS, MySQLiteHelper.COLUMN_CITY, MySQLiteHelper.COLUMN_PHONE, MySQLiteHelper.COLUMN_WEBSITE, MySQLiteHelper.COLUMN_AREA, 
			MySQLiteHelper.COLUMN_LATITUDE, MySQLiteHelper.COLUMN_LONGITUDE, MySQLiteHelper.COLUMN_RATING };
	
	//private String[] Drinks_Columns = { MySQLiteHelper.
	
	public DTexDataSource(Context context, InputStreamReader bars_istream_reader, InputStreamReader drinks_istream_reader) {
		DBhelper = new MySQLiteHelper(context);
		
		try {
			DTex_database = new DTexDatabase(bars_istream_reader, drinks_istream_reader);
			//DTexDatabase.printBarsLst(DTex_database.DTex_BarsLst);
			//DTexDatabase.printDrinksLst(DTex_database.DTex_DrinksLst);	
		}
		catch(Exception e) {
			System.out.println( "IOException: " );
		}
	}
	
	
	public void open() throws SQLException {
		Database = DBhelper.getWritableDatabase();
	}
	public void close() {
		DBhelper.close();
	}
	
	public boolean isEmptyTable(String table) {
		Cursor cursor = Database.rawQuery("SELECT * FROM " +  table, new String[]{});
		int count = 0;
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			++count;
			cursor.moveToNext();
		}
		if (count > 0)
			return false;
		else
			return true;
	}
	
	public void clearTable(String table) {
		//Database.rawQuery("DELETE FROM DTex_Bars", new String[]{});
		Database.execSQL("DELETE FROM " + table);
	}
	
	public void deleteBar(DTexBar bar) {	
		long id = bar.getId();
		System.out.println("Bar deleted with id: " + id);
		Database.delete(MySQLiteHelper.TABLE_BARS, MySQLiteHelper.COLUMN_ID + " = " + id, null);
	}
	
	public DTexBar getBarFromKey(long key) {
		System.out.println( "getBarFromKey() ");
		Cursor c = Database.rawQuery("SELECT * FROM DTex_Bars WHERE _id = ?", new String[]{ String.valueOf(key) });
		//Log.d(TAG, c.getColumnName(0));
		//Log.d(TAG, "" + c.getCount());
		//Log.d(TAG, "" + c.toString());
		c.moveToFirst();
		DTexBar bar = cursorToBar(c);
		//DTexBar bar = c.moveToFirst().
		//Log.d(TAG, "cursor to bar = " + bar);
		//c.close();
		return bar;
		
	}
	
	public void updateDTexBarsLike(long key) {
		System.out.println( "UPDATE TABLE DTexBars Like" );
		
		Cursor cursor = Database.rawQuery("UPDATE TABLE DTexBars SET Rating = 1", new String[]{});
		cursor.close();
	}
	public void updateDTexBarsUnlike(long key) {
		System.out.println( "UPDATE TABLE DTexBars Unlike" );
		Cursor cursor = Database.rawQuery("UPDATE TABLE DTexBars SET Rating = 0", new String[]{});
		cursor.close();
	}
	
	public List<DTexBar> getAllBars() {
		List<DTexBar> result = new ArrayList<DTexBar>();	
		Cursor cursor = Database.query(MySQLiteHelper.TABLE_BARS, Bars_Columns, null , null, null, null, null);
		cursor.moveToFirst();
	    while (!cursor.isAfterLast()) {
		      DTexBar bar = cursorToBar(cursor);
		      result.add(bar);
		      cursor.moveToNext();
		    }
		    // make sure to close the cursor
		    cursor.close();
		    return result;
	}
	public List<String> getAllBarAddresses() {
		List<String> result = new ArrayList<String>();
		Cursor cursor = Database.rawQuery("SELECT * FROM DTex_Bars", new String[]{ });
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			String add_item = cursor.getString(2);
			result.add(add_item);
			cursor.moveToNext();
		}
		cursor.close();
		return result;
	}
	public List<DTexBar> getAreaBars(String area) {
		List<DTexBar> result = new ArrayList<DTexBar>();
		Cursor cursor = Database.rawQuery("SELECT * FROM DTex_Bars WHERE Area = ?", new String[]{ area });
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			DTexBar bar = cursorToBar(cursor);
			result.add(bar);
			cursor.moveToNext();	
		}
		cursor.close();
		return result;
	}
	
	public String getBarName(long id) {
		String result = "";
		Cursor c = Database.rawQuery("SELECT * FROM DTex_Bars WHERE _id = ?", new String[]{ String.valueOf(id) });
		c.moveToFirst();
		DTexBar bar = cursorToBar(c);
		//DTexBar bar = c.moveToFirst().
		//Log.d(TAG, "cursor to bar = " + bar);
		result = bar.getName();
		//Log.d(TAG, "getBarAddress() result = " + result);
		c.close();
		return result;
		
	}
	
	public String getBarAddress(long id) {
		String result = "";
		System.out.println( "getBarAddress(long) - " + String.valueOf(id) );
		//Database.open();
		Cursor c = Database.rawQuery("SELECT * FROM DTex_Bars WHERE _id = ?", new String[]{ String.valueOf(id) });
		//Log.d(TAG, c.getColumnName(0));
		//Log.d(TAG, "" + c.getCount());
		//Log.d(TAG, "" + c.toString());
		c.moveToFirst();
		DTexBar bar = cursorToBar(c);
		//DTexBar bar = c.moveToFirst().
		//Log.d(TAG, "cursor to bar = " + bar);
		result = bar.getAddress();
		//Log.d(TAG, "getBarAddress() result = " + result);
		c.close();
		return result;
	}
	public String getBarArea(long id) {
		String result = "";
		System.out.println( "getBarAddress(long) - " + String.valueOf(id) );
		//Database.open();
		Cursor c = Database.rawQuery("SELECT * FROM DTex_Bars WHERE _id = ?", new String[]{ String.valueOf(id) });
		Log.d(TAG, c.getColumnName(0));
		Log.d(TAG, "" + c.getCount());
		Log.d(TAG, "" + c.toString());
		
		c.moveToFirst();
		DTexBar bar = cursorToBar(c);
		//DTexBar bar = c.moveToFirst().
		Log.d(TAG, "cursor to bar = " + bar);

		result = bar.getArea();
		Log.d(TAG, "getBarAddress() result = " + result);
		//c.close();
		return result;
	}
	public String getBarPhone(long id) {
		String result = "";
		System.out.println( "getBarAddress(long) - " + String.valueOf(id) );
		//Database.open();
		Cursor c = Database.rawQuery("SELECT * FROM DTex_Bars WHERE _id = ?", new String[]{ String.valueOf(id) });
		//Log.d(TAG, c.getColumnName(0));
		//Log.d(TAG, "" + c.getCount());
		//Log.d(TAG, "" + c.toString());
		c.moveToFirst();
		DTexBar bar = cursorToBar(c);
		//DTexBar bar = c.moveToFirst().
		//Log.d(TAG, "cursor to bar = " + bar);

		result = bar.getPhone();
		//Log.d(TAG, "getBarAddress() result = " + result);
		//c.close();
		return result;
	}
	public String getBarWebsite(long id) {
		String result = "";
		System.out.println( "getBarAddress(long) - " + String.valueOf(id) );
		//Database.open();
		Cursor c = Database.rawQuery("SELECT * FROM DTex_Bars WHERE _id = ?", new String[]{ String.valueOf(id) });
		//Log.d(TAG, c.getColumnName(0));
		//Log.d(TAG, "" + c.getCount());
		//Log.d(TAG, "" + c.toString());
		
		c.moveToFirst();
		DTexBar bar = cursorToBar(c);
		//DTexBar bar = c.moveToFirst().
		//Log.d(TAG, "cursor to bar = " + bar);

		result = bar.getWebsite();
		//Log.d(TAG, "getBarAddress() result = " + result);
		//c.close();
		return result;
	}
	
	/*
	public List<String> getAllBarNames() {	
		List<DTexBar> barlst = new ArrayList<DTexBar>();
		List<String> result = new ArrayList<String>();	
		List<Button> resultb = new ArrayList<Button>();	
		Cursor cursor = Database.query(MySQLiteHelper.TABLE_BARS, Bars_Columns, null , null, null, null, null);
		cursor.moveToFirst();
	    while (!cursor.isAfterLast()) {
		      DTexBar bar = cursorToBar(cursor);
		      result.add(bar.getName());
		      cursor.moveToNext();
		    }
		    // make sure to close the cursor
		    cursor.close();
		    return result;	
	}
	*/
	
	public DTexDatabase getDTexDatabase() {
		return DTex_database;
	}
	public SQLiteDatabase getDatabase() {
		return Database;
	}
	
	public void insertBarLst(List<DTexBar> bar_lst) {
		for (int i = 0; i < bar_lst.size(); i++) {
			ContentValues insert_vals = new ContentValues();
			insert_vals.put(MySQLiteHelper.COLUMN_ID, bar_lst.get(i).getId());
			insert_vals.put(MySQLiteHelper.COLUMN_NAME, bar_lst.get(i).getName());
			insert_vals.put(MySQLiteHelper.COLUMN_ADDRESS, bar_lst.get(i).getAddress());
			insert_vals.put(MySQLiteHelper.COLUMN_CITY, bar_lst.get(i).getCity());
			insert_vals.put(MySQLiteHelper.COLUMN_PHONE, bar_lst.get(i).getPhone());
			insert_vals.put(MySQLiteHelper.COLUMN_WEBSITE, bar_lst.get(i).getWebsite());
			insert_vals.put(MySQLiteHelper.COLUMN_AREA, bar_lst.get(i).getArea());
			
			insert_vals.put(MySQLiteHelper.COLUMN_LATITUDE, bar_lst.get(i).getLatitude());
			insert_vals.put(MySQLiteHelper.COLUMN_LONGITUDE, bar_lst.get(i).getLongitude());
			
			insert_vals.put(MySQLiteHelper.COLUMN_RATING, bar_lst.get(i).getRating());
			
			Database.insert(MySQLiteHelper.TABLE_BARS, null, insert_vals);
		}
	}

	private DTexBar cursorToBar(Cursor cursor) {
		DTexBar bar = new DTexBar();
		bar.setId(cursor.getLong(0));
		Log.d(TAG, "setId - " + bar.getId());
		bar.setName(cursor.getString(1));
		bar.setAddress(cursor.getString(2));
		bar.setCity(cursor.getString(3));
		bar.setPhone(cursor.getString(4));
		bar.setWebsite(cursor.getString(5));
		bar.setArea(cursor.getString(6));
		bar.setLatitude(cursor.getDouble(7));
		bar.setLongitude(cursor.getDouble(8));
		bar.setRating(cursor.getInt(9));	
		return bar;
	}
	
	// --------------------------
	// Drink Specials Data source
	// --------------------------
	
	public void insertDrinkLst(List<DTexDrink> drinklst) {	
		for (int i = 0; i < drinklst.size(); i++) {
			ContentValues insert_vals = new ContentValues();
			
			insert_vals.put(MySQLiteHelper.COLUMN_BAR, drinklst.get(i).getBar());
			insert_vals.put(MySQLiteHelper.COLUMN_BAR_ID, drinklst.get(i).getBarId());
			insert_vals.put(MySQLiteHelper.COLUMN_SUMMARY, drinklst.get(i).getSummary());
			insert_vals.put(MySQLiteHelper.COLUMN_DAY, drinklst.get(i).getDay());
			insert_vals.put(MySQLiteHelper.COLUMN_SPECIAL, drinklst.get(i).getSpecial());
			insert_vals.put(MySQLiteHelper.COLUMN_DISPLAY, drinklst.get(i).getDisplay());
			insert_vals.put(MySQLiteHelper.COLUMN_PRICE, drinklst.get(i).getPrice());
			insert_vals.put(MySQLiteHelper.COLUMN_RATING, drinklst.get(i).getRating());
			insert_vals.put(MySQLiteHelper.COLUMN_DRINKNUMBER, drinklst.get(i).getDrinkNumber());
			Database.insert(MySQLiteHelper.TABLE_DRINKS, null, insert_vals);
		}
	}
	
	private DTexDrink cursorToDrink(Cursor cursor) {
		DTexDrink drink = new DTexDrink();
		drink.setId(cursor.getLong(0));
		//Log.d(TAG, "setId - " + bar.getId());
		drink.setBarId(cursor.getLong(1));
		drink.setBar(cursor.getString(2));
		drink.setSummary(cursor.getString(3));
		drink.setDay(cursor.getString(4));
		drink.setSpecial(cursor.getString(5));
		drink.setDisplay(cursor.getString(6));
		drink.setPrice(cursor.getDouble(7));
		drink.setRating(cursor.getInt(8));
		drink.setDrinkNumber(cursor.getInt(9));

		return drink;
	}
	
	public List<DTexDrink> getAllDrinkSpecials() {
		List<DTexDrink> result = new ArrayList<DTexDrink>();
		Cursor c = Database.rawQuery("select * from DTex_Drinks", new String[]{});
		c.moveToFirst();
		while (!c.isAfterLast()) {
			DTexDrink drink = cursorToDrink(c);
			result.add(drink);
			c.moveToNext();
		}
		c.close();
		return result;
	}
	public List<DTexDrink> getDaySpecials(String day) {
		List<DTexDrink> result = new ArrayList<DTexDrink>();
		//day = day.trim();
		Cursor c = Database.rawQuery("SELECT * FROM DTex_Drinks WHERE (Day = ? OR Day = ?)", new String[]{ day, "Everyday" });
		c.moveToFirst();
		while (!c.isAfterLast()) {
			DTexDrink drink = cursorToDrink(c);
			result.add(drink);
			c.moveToNext();
		}
		c.close();
		return result;
	}
	public List<DTexDrink> getBarSpecials(String bar) {
		List<DTexDrink> result = new ArrayList<DTexDrink>();
		Cursor c = Database.rawQuery("SELECT * FROM DTex_Drinks WHERE Bar = ?", new String[]{ bar });	
		c.moveToFirst();
		while (!c.isAfterLast()) {
			DTexDrink drink = cursorToDrink(c);
			result.add(drink);
			c.moveToNext();
		}
		c.close();
		return result;
	}
	public List<DTexDrink> getKeyBarSpecials(long id) {
		List <DTexDrink> result = new ArrayList<DTexDrink>();
		Cursor c = Database.rawQuery("SELECT * FROM DTex_Drinks WHERE BarId = ?", new String[]{ String.valueOf(id) });
		c.moveToFirst();
		while (!c.isAfterLast()) {
			DTexDrink drink = cursorToDrink(c);
			result.add(drink);
			c.moveToNext();
		}
		c.close();
		return result;
	}
	public List<DTexBar> getAreaSpecials(String area) {
		List<DTexBar> result = new ArrayList<DTexBar>();
		Cursor c = Database.rawQuery("SELECT * FROM DTex_Bars WHERE Area = ?", new String[]{ area });
		c.moveToFirst();
		while (!c.isAfterLast()) {
			DTexBar bar = cursorToBar(c);
			result.add(bar);
			c.moveToNext();
		}
		c.close();
		return result;	
	}
	public List<DTexDrink> getBarDaySpecials(String bar, String day) {
		List<DTexDrink> result = new ArrayList<DTexDrink>();
		Cursor c = Database.rawQuery("SELECT * FROM DTex_Drinks WHERE Bar = ? AND (Day = ? OR Day = ?)", new String[]{ bar, day, "Everyday" });
		c.moveToFirst();
		while (!c.isAfterLast()) {
			DTexDrink drink = cursorToDrink(c);
			result.add(drink);
			c.moveToNext();
		}
		c.close();
		return result;
	}

}
