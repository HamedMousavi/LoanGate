package com.orderedsoft.loangate.categoryFragments;
import com.orderedsoft.loangate.CategoryActivityViewModel;
import com.orderedsoft.loangate.CategoryAdapter;
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
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


public class LoanCategoryListFragment extends Fragment  implements IObserver
{
	
	private View _view;
	public CategoryActivityViewModel Model = null;
	private ArrayAdapter<LoanCategory> _categoriesAdapter;


	@Override
    public void onCreate(Bundle savedInstanceState) {
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
    	if (_view == null) _view = inflater.inflate(R.layout.activity_loan_category_list, container, false);
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
        if (Model == null) 
    	{
    		Model = new CategoryActivityViewModel(this);
            ReBindCategories();
    		Model.ReloadCategories();
    	}
	}

	
	protected void ReBindCategories()
	{
    	// Bind ListView to Categories
		if (Model.getCategories() != null) {
	    	_categoriesAdapter = new CategoryAdapter(
	    			_view.getContext(), R.layout.category_list_item, Model.getCategories());
	    	ListView lvwCategories = (ListView)_view.findViewById(R.id.lvw_categories);
			lvwCategories.setAdapter(_categoriesAdapter);
			lvwCategories.setOnItemClickListener(_onCategoryItemClicked);
			
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

	
	 private AdapterView.OnItemClickListener _onCategoryItemClicked = new AdapterView.OnItemClickListener() 
	 {
		public void onItemClick(AdapterView<?> parent, View view, int position, long id)
		{
		    Toast.makeText(_view.getContext(), "You have selected " + Model.getCategory(position).getName(), 
		        Toast.LENGTH_SHORT).show();
		}
	 };

}
