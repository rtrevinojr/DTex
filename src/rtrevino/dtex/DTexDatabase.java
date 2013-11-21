package rtrevino.dtex;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import android.content.Context;

public class DTexDatabase {

	public List<DTexBar> DTex_BarsLst;
	public List<DTexDrink> DTex_DrinksLst;
	
	private Context c;
	
	public DTexDatabase(InputStreamReader bars_istream_reader, InputStreamReader drinks_istream_reader) {

		DTex_BarsLst = parseBars(bars_istream_reader);
		DTex_DrinksLst = parseDrinks(drinks_istream_reader);
		//printBarsLst(DTex_BarsLst);
		//printDrinksLst(DTex_DrinksLst);
	}
	
	public List<DTexBar> getDTex_BarsLst() {
		return DTex_BarsLst;
	}
	public List<DTexDrink> getDTex_DrinksLst() {
		return DTex_DrinksLst;
	}
	
	public List<DTexBar> parseBars(InputStreamReader istream_reader) {	
		// System.out.println( "parseBars(): ");
		List<DTexBar> result = new ArrayList<DTexBar>();
		Scanner scan_table = null;
		InputStream istream = null;
		InputStreamReader istreamreader = null;
		try {
			scan_table = new Scanner(istream_reader);
		}
		catch(Exception e) {
			System.out.print( "parseBars Exception" );
		}
		String nextRow = "";
		
		while (scan_table.hasNextLine()) {
			nextRow = scan_table.nextLine();
			//System.out.println( "nextRow - " + nextRow );
			String delims = "[|]+";
			String[] bar_line = nextRow.split(delims);
			//printArray(bar_line);
			long id = Long.valueOf(bar_line[0]);
			//System.out.println( "bar id = " + id);
			String name = bar_line[1].trim();
			//System.out.println( "bar name = " + name);
		
			String addr = bar_line[2].trim();
			String city = bar_line[3].trim();
			String phone = bar_line[4].trim();
			String site = bar_line[5].trim();
			String area = bar_line[6].trim();
			
			//System.out.println( addr );
			//System.out.println( city );
			//System.out.println( phone );	
			//System.out.println( site );
			//System.out.println( area );
			
			double latitude = Double.parseDouble(bar_line[7]);
			double longitude = Double.parseDouble(bar_line[8]);
			
			int rating = Integer.parseInt(bar_line[9].trim());
			
			//System.out.println( latitude );
			//System.out.println( longitude );
			//System.out.println( rating );
			
			DTexBar bar_row = new DTexBar(id, name, addr, city, phone, site, area, latitude ,longitude, rating);
			result.add(bar_row);
		}
		return result;
	}
	
	public List<DTexDrink> parseDrinks(InputStreamReader istream_reader) {
		
		System.out.println( "parseDrinks(): " );
		//System.out.println( "CREATE = " + MySQLiteHelper.CREATE_TABLE_DRINKS );
		//System.out.println();
		//System.out.println( "CREATE = " + MySQLiteHelper.CREATE_TABLE_BARS);
		
		List<DTexDrink> result = new ArrayList<DTexDrink>();
		String nextRow = null;
		Scanner scan_table = null;
		InputStream istream = null;
		InputStreamReader istreamreader = null;
		try {
			scan_table = new Scanner(istream_reader);
		}
		catch(Exception e) {
			System.out.print( "parseDrinks Exception" );
		}
		
		while (scan_table.hasNextLine()) {
			nextRow = scan_table.nextLine();
			//System.out.println( "nextRow = " + nextRow );
			String delims = "[|]+";
			String[] drink_line = nextRow.split(delims);
			//printArray(drink_line);
			long id = 0;
			//long id = Long.valueOf(drink_line[0]);
			long bar_id = Long.parseLong(drink_line[0].trim());
			//System.out.println( "foreign key: bar_id = " + bar_id );
			String bar = drink_line[1].trim();
			//System.out.println( "bar index = " + bar);
			String summary = drink_line[2].trim();
			//System.out.println( "summary index = " + summary );
			String day = drink_line[3].trim();
			//System.out.println( "day index = " + day );
			String special = drink_line[4].trim();
			//System.out.println( "special index = " + special );
			String display = drink_line[5].trim();
			//System.out.println( "display index = " + display );	
			Double price = Double.parseDouble(drink_line[6].trim());
			//System.out.println( "price index = " + price );
			int rating = Integer.parseInt(drink_line[7].trim());
			//System.out.println( "rating index = " + rating );
			int drink_num = Integer.parseInt(drink_line[8].trim());
			//System.out.println( "drink_num = " + drink_num );
			
			DTexDrink drink_row = new DTexDrink(id, bar_id, bar, summary, day, special, display, price, rating, drink_num);
			result.add(drink_row);
		}
		return result;
	}
	
	
	public static void printBarsLst(List<DTexBar> barlst) {
		System.out.println("BAR LIST: " + "\n");
		for (int i = 0; i < barlst.size(); i++) {
			System.out.println( "bar element - " + i + " - " + barlst.get(i).getId());
			System.out.println( "bar element - " + i + " - " + barlst.get(i).getName());
			System.out.println( "bar element - " + i + " - " + barlst.get(i).getAddress());
			System.out.println( "bar element - " + i + " - " + barlst.get(i).getCity());
			System.out.println( "bar element - " + i + " - " + barlst.get(i).getPhone());
			System.out.println( "bar element - " + i + " - " + barlst.get(i).getWebsite());
			
			//System.out.println( "bar element - " + i + " - " + barlst.get(i).get)
		}
	}
	public static void printDrinksLst(List<DTexDrink> drinklst) {
		System.out.println("DRINK LIST: " + "\n");
		for (int i = 0; i < drinklst.size(); i++) {
			System.out.println( "drink element - " + i + " - " + drinklst.get(i).getId());
			System.out.println( "drink element - " + i + " - " + drinklst.get(i).getBar());
			System.out.println( "drink element - " + i + " - " + drinklst.get(i).getSummary());
			System.out.println( "drink element - " + i + " - " + drinklst.get(i).getDay());
			System.out.println( "drink element - " + i + " - " + drinklst.get(i).getDisplay());
			System.out.println( "drink element - " + i + " - " + drinklst.get(i).getPrice());
			System.out.println( "drink element - " + i + " - " + drinklst.get(i).getRating());
			System.out.println( "drink element - " + i + " - " + drinklst.get(i).getDrinkNumber());
		}
	}
	public static void printArray(String[] arg) {
		System.out.println("PRINT ARRAY");
		System.out.println("array length = " + arg.length);
		for (int i = 0; i < arg.length; i++) 
			System.out.println( "parse array ele - " + i + " - " + arg[i] );
	}

	
}
