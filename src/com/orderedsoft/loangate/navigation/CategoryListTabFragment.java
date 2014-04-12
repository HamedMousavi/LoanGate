package com.orderedsoft.loangate.navigation;



import com.orderedsoft.loangate.R;
import com.orderedsoft.loangate.categoryFragments.LoanCategoryListFragment;
import com.orderedsoft.loangate.categoryFragments.LoanListFragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;


public class CategoryListTabFragment extends Fragment
{
	
	private View _view;


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
			LoanCategoryListFragment fragment = new LoanCategoryListFragment();
			fragment.SetCategoryListItemClickListener(_onCategoryItemClicked);
			return fragment;
			
		case R.id.loanListFragment:
			return new LoanListFragment();
		}
		
		return null;
	}

	
	 private AdapterView.OnItemClickListener _onCategoryItemClicked = new AdapterView.OnItemClickListener() 
	 {
		public void onItemClick(AdapterView<?> parent, View view, int position, long id)
		{
		    //Toast.makeText(_view.getContext(), "You have selected " + Model.getCategory(position).getName(), 
		    //    Toast.LENGTH_SHORT).show();
		    
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
}
