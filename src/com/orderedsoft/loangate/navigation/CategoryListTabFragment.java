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
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;


public class CategoryListTabFragment extends Fragment
{
	
	private LoanCategory _selectedCategory;
	private int _selectedCategoryIndex;
	private Loan _selectedLoan;
	private int _selectedLoanIndex;
	private LoanCategoryListFragment _loanCategoryListFragment;
	private LoanListFragment _loanListFragment;
	private LoanDetailFragment _loanDetailFragment;
	private int _activeFragment = -1;


	private AdapterView.OnItemClickListener _onCategoryItemClicked = new AdapterView.OnItemClickListener() 
	{
		public void onItemClick(AdapterView<?> parent, View view, int position, long id)
		{
			SelectCategory(position);
		}
	};
	 
	
	private AdapterView.OnItemClickListener _onLoanItemClicked = new AdapterView.OnItemClickListener() 
	{
		public void onItemClick(AdapterView<?> parent, View view, int position, long id)
		{
			SelectLoan(position);
		}
	};

	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) 
    {
	    View view = inflater.inflate(R.layout.tab_page_category_list, container, false);
		return  view;
    }


	@Override
    public void onCreate(Bundle savedInstanceState) 
	{
    	// Create menu
		super.onCreate(savedInstanceState);
		if (savedInstanceState != null)
		{
			RestoreState(savedInstanceState);
		}
		else
		{
			SetActiveFragment(R.id.mainContainer, R.id.loanCategoryListFragment);
		}
    }
	

	@Override
    public void onAttach(Activity activity) 
	{
        super.onAttach(activity);
	}   

	
	@Override
    public void onStart()
    {
		super.onStart();
    }	

	
    @Override
    public void onPause() 
    {
        super.onPause();
    }


	@Override
	public void onActivityCreated(Bundle savedInstanceState)
	{
		super.onActivityCreated(savedInstanceState);
	}
	
	
	@Override
	public void onDestroyView() 
	{
	    super.onDestroyView();
	    /**
	    if (_view != null) {
	        ViewGroup parentViewGroup = (ViewGroup) _view.getParent();
	        if (parentViewGroup != null) {
	            parentViewGroup.removeAllViewsInLayout();;
	        }
	    }*/
	}	 


	@Override
    public void onSaveInstanceState (Bundle outState) {
        super.onSaveInstanceState(outState);
        SaveState(outState);
    }

	
	private void SaveState(Bundle outState) 
	{
        outState.putInt("activeFragment", _activeFragment);
        outState.putInt("selectedLoanIndex", _selectedLoanIndex);
        outState.putInt("selectedCategoryIndex", _selectedCategoryIndex);
	}

	
	private void RestoreState(Bundle savedInstanceState) 
	{
		int fragmentId = savedInstanceState.getInt("activeFragment");
		int selectedLoan = savedInstanceState.getInt("selectedLoanIndex");
		int selectedCategory = savedInstanceState.getInt("selectedCategoryIndex");

		if (fragmentId != -1) 
		{
			SetActiveFragment(R.id.mainContainer, fragmentId);
		}
		
		if (selectedCategory != -1)
		{
			SelectCategory(selectedCategory);
		}
		
		if (selectedLoan != -1)
		{
			SelectLoan(selectedLoan);
		}
	}
	
	
	private void SetActiveFragment(int containerId, int fragmentId) 
	{
		_activeFragment = fragmentId;
		FragmentManager fm = getChildFragmentManager();
		Fragment fragment = CreateFragment(fragmentId);
		
		FragmentTransaction transaction = fm.beginTransaction();
		transaction.replace(containerId, fragment);
		transaction.show(fragment);
		transaction.addToBackStack(null);
		transaction.commit();
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

	 
	private void SelectCategory(int position) 
	{
		_selectedCategoryIndex = position;
		setSelectedCategory(_loanCategoryListFragment.Model.getCategory(position));
	    
	    LoanListFragment detailFragment = (LoanListFragment) 
	    		getChildFragmentManager().findFragmentById(R.id.loanListFragment);
	            
	    //---if the detail fragment is not in the current activity as myself---
	    if (detailFragment != null && detailFragment.isInLayout()) {
	        //---the detail fragment is in the same activity as the master---
	        //detailFragment.setSelectedPresident(selectedPresident);
	    } else {
	        //---the detail fragment is in its own activity---
	 	   SetActiveFragment(R.id.mainContainer, R.id.loanListFragment);
	    }
	}
	
	private void SelectLoan(int position) 
	{
		_selectedLoanIndex = position;
		setSelectedLoan(_loanListFragment.getModel().getLoan(position));
		
		LoanDetailFragment detailFragment = (LoanDetailFragment) 
				getChildFragmentManager().findFragmentById(R.id.loanDetailFragment);
	        
	   //---if the detail fragment is not in the current activity as myself---
	   if (detailFragment != null && detailFragment.isInLayout()) {
	       //---the detail fragment is in the same activity as the master---
	       //detailFragment.setSelectedPresident(selectedPresident);
	   } else 
	   {
	       //---the detail fragment is in its own activity---
		   SetActiveFragment(R.id.mainContainer, R.id.loanDetailFragment);
	   }
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
}