package com.orderedsoft.loangate.categoryFragments;
import com.orderedsoft.loangate.CategoryActivityViewModel;
import com.orderedsoft.loangate.CategoryAdapter;
import com.orderedsoft.loangate.Events;
import com.orderedsoft.loangate.R;
import com.orderedsoft.loangate.models.LoanCategory;

import HLib.IObserver;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class LoanCategoryListFragment extends Fragment  implements IObserver
{
	
	private View _view;
	private CategoryActivityViewModel _model;
	private ArrayAdapter<LoanCategory> _categoriesAdapter;
	private ListView _lvw_categories;
	
	
	public LoanCategoryListFragment()
	{
		super();
		Events.get_instance().RegisterEventObserver(this);
	}
	
	
	public void SetCategoryListItemClickListener(OnItemClickListener listener) 
	{
		_onCategoryItemClicked = listener;
	}


	@Override
    public void onCreate(Bundle savedInstanceState) 
	{
    	// Create menu
		super.onCreate(savedInstanceState);
	}

	
	@Override
    public void onAttach(Activity activity) 
	{
        super.onAttach(activity);
	}    

	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) 
    {
    	_view = inflater.inflate(R.layout.activity_loan_category_list, container, false);
    	
    	_lvw_categories = (ListView) _view.findViewById(R.id.lvw_categories);
    	
        return _view;
    }
    

	@Override
    public void onStart()
    {
		super.onStart();
		
    	SetupBindings();
    }


	private void SetupBindings()
	{
        // Create & display view 
        ReBindCategories();
		if (get_model() != null) get_model().ReloadCategories();
	}

	
	protected void ReBindCategories()
	{
    	// Bind ListView to Categories
		if (get_model() != null && 
			get_model().getCategories() != null && 
			_lvw_categories != null && 
			_categoriesAdapter != null && 
			_onCategoryItemClicked != null) {
	    	_categoriesAdapter = new CategoryAdapter(
	    			_lvw_categories.getContext(), R.layout.list_item_category, get_model().getCategories());
	    	_lvw_categories.setItemsCanFocus(false);
	    	_lvw_categories.setAdapter(_categoriesAdapter);
	    	_lvw_categories.setOnItemClickListener(_onCategoryItemClicked);
			
			_categoriesAdapter.notifyDataSetChanged();
		}
		else
		{
			_categoriesAdapter = null;
		}
	}
	
	
	public void OnEvent(int eventId, Object observable, Object params)
	{
		if (eventId == Events.CategoriesLoadCompleted) ReBindCategories();
	}

	
	 /**
	 * @return the _model
	 */
	public CategoryActivityViewModel get_model() {
		return _model;
	}


	/**
	 * @param _model the _model to set
	 */
	public void set_model(CategoryActivityViewModel model) {
		_model = model;
	}


	private AdapterView.OnItemClickListener _onCategoryItemClicked;

}
