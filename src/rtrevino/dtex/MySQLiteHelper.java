package rtrevino.dtex;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper {

	public static final String TABLE_BARS = "DTex_Bars";
	public static final String TABLE_DRINKS = "DTex_Drinks";
	
	public static final String COLUMN_ID = "_id";
	
	public static final String COLUMN_NAME = "Name";
	public static final String COLUMN_ADDRESS = "Address";
	public static final String COLUMN_CITY = "City";
	public static final String COLUMN_PHONE = "Phone";
	public static final String COLUMN_WEBSITE = "Website";
	
	public static final String COLUMN_OPEN_TIME = "Open";
	public static final String COLUMN_CLOSE_TIME = "Close";
	
	public static final String COLUMN_LATITUDE = "Latitute";
	public static final String COLUMN_LONGITUDE = "Longitude";
	
	public static final String COLUMN_AREA = "Area";
	
	public static final String COLUMN_BAR_ID = "BarId";
	
	public static final String COLUMN_BAR = "Bar";
	public static final String COLUMN_SUMMARY = "Summary";
	public static final String COLUMN_DAY = "Day";
	public static final String COLUMN_SPECIAL = "Special";
	public static final String COLUMN_DISPLAY = "Display";
	public static final String COLUMN_PRICE = "Price";
	public static final String COLUMN_RATING = "Rating";
	public static final String COLUMN_DRINKNUMBER = "DrinkNumber";
	
	public static final String DATABASE_NAME = "DTex.db";
	private static final int DATABASE_VERSION = 9;
	
	// Create Table DTex_BarsTable
	public static final String CREATE_TABLE_BARS = "CREATE TABLE " + TABLE_BARS + " (" +
			COLUMN_ID + " INTEGER , " +
			COLUMN_NAME + " TEXT NOT NULL, " +
			COLUMN_ADDRESS + " TEXT NOT NULL, " +
			COLUMN_CITY + " TEXT NOT NULL, " +
			COLUMN_PHONE + " TEXT, " +
			COLUMN_WEBSITE + " TEXT, " +
			COLUMN_AREA + " TEXT, " +
			COLUMN_LATITUDE + " DOUBLE, " +
			COLUMN_LONGITUDE + " DOUBLE, " +
			COLUMN_RATING + " INTEGER);" ;
	
	// Create Table DTex_DrinksTable
	public static final String CREATE_TABLE_DRINKS = "CREATE TABLE " + TABLE_DRINKS + " (" +
			COLUMN_ID + " INTEGER, " +
			COLUMN_BAR_ID + " INTEGER, " +
			COLUMN_BAR + " TEXT NOT NULL, " +
			COLUMN_SUMMARY + " TEXT NOT NULL, " +
			COLUMN_DAY + " TEXT NOT NULL, " +
			COLUMN_SPECIAL + " TEXT NOT NULL, " +
			COLUMN_DISPLAY + " TEXT, " +
			COLUMN_PRICE + " INTEGER, " +
			COLUMN_RATING + " INTEGER, " +
			COLUMN_DRINKNUMBER + " INTEGER);" ;
	
	public MySQLiteHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase database) {
		//System.out.println( "QUERY CREATE = " + CREATE_TABLE_DRINKS );
	    database.execSQL("DROP TABLE IF EXISTS " + TABLE_BARS);
	    database.execSQL("DROP TABLE IF EXISTS " + TABLE_DRINKS);
		database.execSQL(CREATE_TABLE_DRINKS);
		database.execSQL(CREATE_TABLE_BARS);
	}
			
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(MySQLiteHelper.class.getName(), "Upgrading database from version " + oldVersion + " to "
	            + newVersion + ", which will destroy all old data");
	    
	    db.execSQL("DROP TABLE IF EXISTS " + TABLE_BARS);
	    db.execSQL("DROP TABLE IF EXISTS " + TABLE_DRINKS);
	    onCreate(db);
	}	
	
	
}
