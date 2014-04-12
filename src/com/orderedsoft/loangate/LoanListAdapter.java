package com.orderedsoft.loangate;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.orderedsoft.loangate.models.Loan;

public class LoanListAdapter extends ArrayAdapter<Loan> {

	
	private List<Loan> _items;
	private Context _context;

	
	public LoanListAdapter(Context context, int resource, List<Loan> objects) 
	{
		super(context, resource, objects);
		
		_items = objects;
		_context = context;
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
		}
		
		Loan item = _items.get(position);
		ExchangeData(info, item);
		
		return view;
	}

	
	private void ExchangeData(CategoryItemViewControls info, Loan item) {
		//info.getCategoryCount().setText(Integer.toString(item.getLoanerCount()));
		//info.getCategoryDescription().setText(item.getDescription());
		//info.getCategoryIcon().setImage
		//info.getCategoryModified().setText(Convert.ToString(item.getLastModified()));
		info.getCategoryTitle().setText(item.getTitle());
	}


	private View CreateView(CategoryItemViewControls info) {
		LayoutInflater inflater = ((Activity)_context).getLayoutInflater();
		View view = inflater.inflate(R.layout.category_list_item, null);
		
		//info.setCategoryCount((TextView)view.findViewById(R.id.tbxCategoryCount));
		//info.setCategoryDescription((TextView)view.findViewById(R.id.tbxCategoryDescription));
		//info.setCategoryIcon((ImageView)view.findViewById(R.id.ivwCategoryIcon));
		//info.setCategoryModified((TextView)view.findViewById(R.id.tbxCategoryModified));
		info.setCategoryTitle((TextView)view.findViewById(R.id.tbxCategoryTitle));

		return view;
	}
}
