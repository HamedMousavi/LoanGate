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
import android.widget.Checkable;
import android.widget.ListView;


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
	private View _view;
	

	private AdapterView.OnItemClickListener _onCategoryItemClicked = new AdapterView.OnItemClickListener() 
	{
		public void onItemClick(AdapterView<?> parent, View view, int position, long id)
		{
			HighlightListItem(R.id.lvw_loans, _selectedLoanIndex, false);
			SelectCategory(position, false);
		}
	};
	 
	
	private AdapterView.OnItemClickListener _onLoanItemClicked = new AdapterView.OnItemClickListener() 
	{
		public void onItemClick(AdapterView<?> parent, View view, int position, long id)
		{
			SelectLoan(position, false);
		}
	};

	
	// This defines dynamic fragments that might present in
	// different layouts
	public enum FragmentId
	{
		LOAN_CAT_LIST(R.id.loanCategoryListFragment),
		LOAN_LIST(R.id.loanListFragment),
		LOAN_DETAIL(R.id.loanDetailFragment);
		
		private int _id;
		
		FragmentId(int id)
		{
			set_id(id);
		}

		/**
		 * @return the _id
		 */
		public int get_id() {
			return _id;
		}

		/**
		 * @param _id the _id to set
		 */
		public void set_id(int _id) {
			this._id = _id;
		}

		
		public static FragmentId Find(int fragmentId) {
			for (FragmentId item : values()) {
				if (item.get_id() == fragmentId) return item;
			}
			
			return null;
		}
	}
	
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) 
    {
	    _view = inflater.inflate(R.layout.tab_page_category_list, container, false);
		
	    if (savedInstanceState != null)
		{
			RestoreState(savedInstanceState);
		}
		else if (_activeFragment == -1)
		{
			SetActiveFragment(R.id.mainContainer, FragmentId.LOAN_CAT_LIST);
		}

		return  _view;
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
			SetActiveFragment(R.id.mainContainer, FragmentId.Find(fragmentId));
		}
		
		if (selectedCategory != -1)
		{
			SelectCategory(selectedCategory, true);
		}
		
		if (selectedLoan != -1)
		{
			SelectLoan(selectedLoan, true);
		}
	}
	
	
	private void SetActiveFragment(int containerId, FragmentId fragmentId) 
	{
		
		containerId = EnsureContainerMatch(containerId, fragmentId);
		
		_activeFragment = fragmentId.get_id();
		FragmentManager fm = getChildFragmentManager();
		Fragment fragment = CreateFragment(fragmentId);
		
		FragmentTransaction transaction = fm.beginTransaction();
		transaction.replace(containerId, fragment);
		transaction.show(fragment);
		transaction.addToBackStack(null);
		transaction.commit();
	}


	private int EnsureContainerMatch(int containerId, FragmentId fragmentId) 
	{
		if (_view == null) return containerId;
/**		
	    LoanListFragment detailFragment = (LoanListFragment) 
	    		getChildFragmentManager().findFragmentById(R.id.loanListFragment);
	            
		LoanDetailFragment detailFragment = (LoanDetailFragment) 
				getChildFragmentManager().findFragmentById(R.id.loanDetailFragment);
	    //---if the detail fragment is not in the current activity as myself---
	    if (detailFragment != null && detailFragment.isInLayout()) {
	        //---the detail fragment is in the same activity as the master---
	        //detailFragment.setSelectedPresident(selectedPresident);
	    } else {
	        //---the detail fragment is in its own activity---
*/
		int id = fragmentId.get_id();
		if (_view.findViewById(id) != null)
		{
			return id;
		}
		else return containerId;
	}


	private Fragment CreateFragment(FragmentId fragmentId)
	{
		Fragment result = null;
		
		switch(fragmentId)
		{
		case LOAN_CAT_LIST:
			if (_loanCategoryListFragment == null)
			{
				_loanCategoryListFragment = new LoanCategoryListFragment();
				_loanCategoryListFragment.SetCategoryListItemClickListener(_onCategoryItemClicked);
			}			
			result = _loanCategoryListFragment;
			break;
			
		case LOAN_LIST:
			if (_loanListFragment == null)
			{
				_loanListFragment = new LoanListFragment();
				_loanListFragment.SetLoanListItemClickListener(_onLoanItemClicked);
			}
			_loanListFragment.setLoanCategory(getSelectedCategory());
			result = _loanListFragment;
			break;
			
		case LOAN_DETAIL:
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

	 
	private void SelectCategory(int position, boolean updateGui) 
	{
		_selectedCategoryIndex = position;
		setSelectedCategory(_loanCategoryListFragment.Model.getCategory(position));
	    
	    SetActiveFragment(R.id.mainContainer, FragmentId.LOAN_LIST);
	    
	    if (updateGui)
	    {
	    	HighlightListItem(R.id.lvw_categories, position, true);
	    }
	}


	private void SelectLoan(int position, boolean updateGui) 
	{
		_selectedLoanIndex = position;
		setSelectedLoan(_loanListFragment.getModel().getLoan(position));
	        
		SetActiveFragment(R.id.mainContainer, FragmentId.LOAN_DETAIL);
	    
	    if (updateGui)
	    {
	    	HighlightListItem(R.id.lvw_loans, position, true);
	    }
	}

	
	private void HighlightListItem(int listViewId, int itemIndex, boolean highlight) 
	{
    	ListView lv = (ListView)_view.findViewById(listViewId);
    	if (lv != null)
    	{
    		Checkable child = (Checkable) lv.getChildAt(itemIndex);
    		if (child != null) 
			{
				if ( (highlight && !child.isChecked()) ||
					 (!highlight && child.isChecked()) ) child.toggle();
			}
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