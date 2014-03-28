package com.orderedsoft.loangate;


import com.orderedsoft.loangate.serviceProxies.LoanCategory;

import HLib.IObserver;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class CategoryListTabPage  implements IObserver
{
	public CategoryActivityViewModel Model = null;
	private ArrayAdapter<LoanCategory> _categoriesAdapter;
	private View _view;

	public void Start(View view) 
	{
		_view = view;
		
        // Create & display view 
        if (Model == null) 
    	{
    		Model = new CategoryActivityViewModel(this);
            ReBindCategories();
            ReloadCategories();
    	}
	}
    

	protected void ReloadCategories() 
	{
		Model.ReloadCategories();
	}
	
	
	protected void ReBindCategories()
	{
    	// Bind ListView to Categories
		if (Model.getCategories() != null) {
	    	_categoriesAdapter = new CategoryAdapter(
	    			_view.getContext(), R.layout.category_list_item, Model.getCategories());
	    	ListView lvwCategories = (ListView)_view.findViewById(R.id.lvw_categories);
			lvwCategories.setAdapter(_categoriesAdapter);
			
			_categoriesAdapter.notifyDataSetChanged();
		}
		else
		{
			_categoriesAdapter = null;
		}
	}
	
	
	/** UNDONE: REPLACE THIS WITH BEANS PROPERTY CHANGED EVENT */
	public void OnSubjectChanged(Object observable, Object params)
	{
		ReBindCategories();
	}
}
