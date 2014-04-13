package com.orderedsoft.loangate.navigation;



import com.orderedsoft.loangate.R;
import com.orderedsoft.loangate.categoryFragments.LoanCategoryListFragment;
import com.orderedsoft.loangate.categoryFragments.LoanListFragment;
import com.orderedsoft.loangate.models.LoanCategory;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;


public class CategoryListTabFragment extends Fragment
{
	
	private View _view;
	private LoanCategory _selectedCategory;
	private LoanCategoryListFragment _loanCategoryListFragment;
	private LoanListFragment _loanListFragment;


	@Override
    public void onCreate(Bundle savedInstanceState) {
    	// Create menu
		super.onCreate(savedInstanceState);
		
		int fragmentId = R.id.loanCategoryListFragment;
		int containerId = R.id.mainContainer;

		SetActiveFragment(containerId, fragmentId);
    }


	@Override
    public void onAttach(Activity activity) 
	{
        super.onAttach(activity);
	}

	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) 
    {
    	 _view = inflater.inflate(R.layout.tab_page_category_list, container, false);
    	 return  _view;
    }
    

	@Override
    public void onStart()
    {
		super.onStart();
    }	

	
	private void SetActiveFragment(int containerId, int fragmentId) 
	{
		FragmentManager fm = getFragmentManager();
		Fragment fragment = fm.findFragmentById(fragmentId);
		if (fragment == null)
		{
			fragment = CreateFragment(fragmentId);
		}

		fm.beginTransaction().replace(containerId, fragment).commit();
	}


	private Fragment CreateFragment(int fragmentId) {
		switch(fragmentId)
		{
		case R.id.loanCategoryListFragment:
			if (_loanCategoryListFragment == null)
			{
				_loanCategoryListFragment = new LoanCategoryListFragment();
				_loanCategoryListFragment.SetCategoryListItemClickListener(_onCategoryItemClicked);
			}			
			return _loanCategoryListFragment;
			
		case R.id.loanListFragment:
			if (_loanListFragment == null)
			{
				_loanListFragment = new LoanListFragment();
				_loanListFragment.SetLoanListItemClickListener(_onLoanItemClicked);
			}
			_loanListFragment.setLoanCategory(getSelectedCategory());
			return _loanListFragment;
		}
		
		return null;
	}


	/**
	 * @return the _selectedCategory
	 */
	public LoanCategory getSelectedCategory() {
		return _selectedCategory;
	}


	/**
	 * @param _selectedCategory the _selectedCategory to set
	 */
	public void setSelectedCategory(LoanCategory selectedCategory) {
		this._selectedCategory = selectedCategory;
	}


	private AdapterView.OnItemClickListener _onCategoryItemClicked = new AdapterView.OnItemClickListener() 
	 {
		public void onItemClick(AdapterView<?> parent, View view, int position, long id)
		{
		    //Toast.makeText(_view.getContext(), "You have selected " + Model.getCategory(position).getName(), 
		    //    Toast.LENGTH_SHORT).show();
						
			setSelectedCategory(_loanCategoryListFragment.Model.getCategory(position));
		    
		    LoanListFragment detailFragment = (LoanListFragment) 
	                getFragmentManager().findFragmentById(R.id.loanListFragment);
	            
           //---if the detail fragment is not in the current activity as myself---
           if (detailFragment != null && detailFragment.isInLayout()) {
               //---the detail fragment is in the same activity as the master---
               //detailFragment.setSelectedPresident(selectedPresident);
           } else {
               //---the detail fragment is in its own activity---
        	   SetActiveFragment(R.id.mainContainer, R.id.loanListFragment);
           }
		}
	 };

	
	 private AdapterView.OnItemClickListener _onLoanItemClicked = new AdapterView.OnItemClickListener() 
	 {
		public void onItemClick(AdapterView<?> parent, View view, int position, long id)
		{
		    Toast.makeText(_view.getContext(), "You have selected item # " + Integer.toString(position), 
		        Toast.LENGTH_SHORT).show();
		}
	 };
}
