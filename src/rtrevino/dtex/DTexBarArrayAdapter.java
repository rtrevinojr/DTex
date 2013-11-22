package rtrevino.dtex;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class DTexBarArrayAdapter extends ArrayAdapter {
	Context context;
	int resource_id;
	
	public List<DTexBar> Bar_Items;
	
	public String AdapterBarName;
	public String AdapterBarAddress;
	
	
	public TextView BarNameItem;
	public TextView BarAddressItem;
	
//	public TextView DaySpecialItem;
//	public TextView SummaryItem;
//	public TextView DrinkSpecialItem;
	
	public int Position;
	
	public static final String TAG = "DTexBarArrayAdapter";
	
	
	public DTexBarArrayAdapter(Context context, int resource, List<DTexBar> items) {
		super(context, resource, items);
		
		this.context = context;
		this.resource_id = resource;
		this.Bar_Items = items;

	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		View row = convertView;
		
		LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        row = inflater.inflate(resource_id, parent, false);
		
		DTexBar bar_item = Bar_Items.get(position);
		
		AdapterBarName = bar_item.getName();
		BarNameItem = (TextView) row.findViewById(R.id.bar_name_2);
		BarNameItem.setText(bar_item.getName());
		
		AdapterBarAddress = bar_item.getAddress();
		BarAddressItem = (TextView) row.findViewById(R.id.bar_address_2);
		BarAddressItem.setText(bar_item.getAddress());
		
		Position = position;
		
		row.setOnClickListener( new OnClickListener() {
			public long pos = (long) Position;
			
//			public OnClickListener() {
//				pos = (long) p;
//			}
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(context, BarInfo.class);
				intent.putExtra("name", AdapterBarName);
				intent.putExtra("address", AdapterBarAddress);
				intent.putExtra("fkey", (long) pos + 1	);
				
				context.startActivity(intent);
			}
			
		} );
	
		return row;
	}
	
}