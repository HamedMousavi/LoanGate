package com.orderedsoft.loangate;

import java.util.List;

import com.orderedsoft.loangate.models.LoanCategory;

//import HLib.Convert;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class CategoryAdapter extends ArrayAdapter<LoanCategory> {

	
	private List<LoanCategory> _items;
	private Context _context;
	private int[] _imageId;
	private int _resource;

	
	public CategoryAdapter(Context context, int resource, List<LoanCategory> objects) 
	{
		super(context, resource, objects);
		
		_resource = resource;
		_items = objects;
		_context = context;
		_imageId = new int[]
				{
					R.drawable.home,
					R.drawable.home,
					R.drawable.car,
					R.drawable.repair,
					R.drawable.wedding,
					R.drawable.graduation,
					R.drawable.plane,
					R.drawable.ambulance_dark,
					R.drawable.store,
					R.drawable.manufactory
				};
	}
	
	
	@Override
	public View getView(int position, View view, ViewGroup parent)
	{
		CategoryItemViewControls info;
		
		if (view == null)
		{
			info = new CategoryItemViewControls();
			view = CreateView(info);
		}
		else // A second visit, use last visit data
		{
			info = (CategoryItemViewControls)view.getTag();
			if (info == null)
			{
				info = new CategoryItemViewControls();
				view = CreateView(info);
			}
		}
		
		LoanCategory item = _items.get(position);
		if (info != null && item != null) ExchangeData(info, item);
		
		return view;
	}

	
	private void ExchangeData(CategoryItemViewControls info, LoanCategory item) {
		String expLabel = (String) _context.getResources().getText(R.string.view_all);
		info.getCategoryCount().setText(expLabel + Integer.toString(item.getLoanerCount()));
		info.getCategoryDescription().setText(item.getDescription());
		info.getCategoryIcon().setImageResource(_imageId[(int) item.getId()]);
		//info.getCategoryModified().setText(Convert.ToString(item.getLastModified()));
		info.getCategoryTitle().setText(item.getName());
	}


	private View CreateView(CategoryItemViewControls info) {
		LayoutInflater inflater = LayoutInflater.from(_context);
		View view = inflater.inflate(_resource, null, false);
		
		info.setCategoryCount((TextView)view.findViewById(R.id.tbxCategoryCount));
		info.setCategoryDescription((TextView)view.findViewById(R.id.tbxCategoryDescription));
		info.setCategoryIcon((ImageView)view.findViewById(R.id.ivwCategoryIcon));
		//info.setCategoryModified((TextView)view.findViewById(R.id.tbxCategoryModified));
		info.setCategoryTitle((TextView)view.findViewById(R.id.tbxCategoryTitle));

		view.setTag(info);
		
		return view;
	}
}
