package rtrevino.dtex;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


public class DTexArrayAdapter extends ArrayAdapter {

	Context context;
	int resource_id;
	
	public List<DTexDrink> Drink_Items;
	
	
	public TextView BarNameItem;
	public TextView DaySpecialItem;
	public TextView SummaryItem;
	public TextView DrinkSpecialItem;
	
	public static final String TAG = "DTexArrayAdapter";
	
	
	public DTexArrayAdapter(Context context, int resource, List<DTexDrink> items) {
		super(context, resource, items);
		
		this.context = context;
		this.resource_id = resource;
		this.Drink_Items = items;

	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		//Button b = (Button) convertView;
		
		View row = convertView;
		
		LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        row = inflater.inflate(resource_id, parent, false);
		
		DTexDrink drink_item = Drink_Items.get(position);
		
		BarNameItem = (TextView) row.findViewById(R.id.bar_name_item);
		DaySpecialItem = (TextView) row.findViewById(R.id.day_special_item);
		SummaryItem = (TextView) row.findViewById(R.id.drink_summary_item);
		DrinkSpecialItem = (TextView) row.findViewById(R.id.drink_special_item);
		
		BarNameItem.setText(drink_item.getBar());
		DaySpecialItem.setText(drink_item.getDay());
		SummaryItem.setText(drink_item.getSummary());
		DrinkSpecialItem.setText(drink_item.getSpecial());
		
	
		return row;
	}
	
}


