package com.orderedsoft.loangate.navigation;



import com.orderedsoft.loangate.R;
import com.orderedsoft.loangate.categoryFragments.LoanCategoryListFragment;
import com.orderedsoft.loangate.categoryFragments.LoanDetailFragment;
import com.orderedsoft.loangate.categoryFragments.LoanListFragment;
import com.orderedsoft.loangate.models.Loan;
import com.orderedsoft.loangate.models.LoanCategory;

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
	private LoanCategory _selectedCategory;
	private Loan _selectedLoan;
	private LoanCategoryListFragment _loanCategoryListFragment;
	private LoanListFragment _loanListFragment;
	private LoanDetailFragment _loanDetailFragment;


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


	private Fragment CreateFragment(int fragmentId) 
	{
		Fragment result = null;
		
		switch(fragmentId)
		{
		case R.id.loanCategoryListFragment:
			if (_loanCategoryListFragment == null)
			{
				_loanCategoryListFragment = new LoanCategoryListFragment();
				_loanCategoryListFragment.SetCategoryListItemClickListener(_onCategoryItemClicked);
			}			
			result = _loanCategoryListFragment;
			break;
			
		case R.id.loanListFragment:
			if (_loanListFragment == null)
			{
				_loanListFragment = new LoanListFragment();
				_loanListFragment.SetLoanListItemClickListener(_onLoanItemClicked);
			}
			_loanListFragment.setLoanCategory(getSelectedCategory());
			result = _loanListFragment;
			break;
			
		case R.id.loanDetailFragment:
			if (_loanDetailFragment == null)
			{
				_loanDetailFragment = new LoanDetailFragment();
			}
			_loanDetailFragment.SetLoan(getSelectedLoan());
			result = _loanDetailFragment;
			break;
		}
		
		return result;
	}


	private Loan getSelectedLoan() {
		// TODO Auto-generated method stub
		return _selectedLoan;
	}
	
	
	private void setSelectedLoan(Loan loan)
	{
		_selectedLoan = loan;
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
			setSelectedLoan(_loanListFragment.getModel().getLoan(position));
			
			LoanDetailFragment detailFragment = (LoanDetailFragment) 
	                getFragmentManager().findFragmentById(R.id.loanDetailFragment);
	            
           //---if the detail fragment is not in the current activity as myself---
           if (detailFragment != null && detailFragment.isInLayout()) {
               //---the detail fragment is in the same activity as the master---
               //detailFragment.setSelectedPresident(selectedPresident);
           } else {
               //---the detail fragment is in its own activity---
        	   SetActiveFragment(R.id.mainContainer, R.id.loanDetailFragment);
           }
		}
	 };
}
