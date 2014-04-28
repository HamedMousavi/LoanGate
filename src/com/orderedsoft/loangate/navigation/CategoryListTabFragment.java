package com.orderedsoft.loangate.navigation;



import com.orderedsoft.loangate.CategoryActivityViewModel;
import com.orderedsoft.loangate.Events;
import com.orderedsoft.loangate.LoanListViewModel;
import com.orderedsoft.loangate.R;
import com.orderedsoft.loangate.categoryFragments.LoanCategoryListFragment;
import com.orderedsoft.loangate.categoryFragments.LoanDetailFragment;
import com.orderedsoft.loangate.categoryFragments.LoanListFragment;
import com.orderedsoft.loangate.models.Loan;
import com.orderedsoft.loangate.models.LoanCategory;

import HLib.IObserver;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Checkable;


public class CategoryListTabFragment extends Fragment implements IObserver
{
	
	private LoanCategory _selectedCategory;
	private int _selectedCategoryIndex = -1;
	private Loan _selectedLoan;
	private int _selectedLoanIndex = -1;
	private LoanCategoryListFragment _loanCategoryListFragment;
	private LoanListFragment _loanListFragment;
	private LoanDetailFragment _loanDetailFragment;
	private int _activeFragment = -1;
	private View _view;
	private AdapterView<?> _categoryAdapterView;
	private AdapterView<?> _loanAdapterView;
	private ProgressDialog _progressDialog;
	

	private AdapterView.OnItemClickListener _onCategoryItemClicked = new AdapterView.OnItemClickListener() 
	{
		public void onItemClick(AdapterView<?> parent, View view, int position, long id)
		{
			_categoryAdapterView = parent;
			HighlightListItem(_loanAdapterView, _selectedLoanIndex, false);
			SelectCategory(position, false);
		}
	};
	 
	
	private AdapterView.OnItemClickListener _onLoanItemClicked = new AdapterView.OnItemClickListener() 
	{
		public void onItemClick(AdapterView<?> parent, View view, int position, long id)
		{
			_loanAdapterView = parent;
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
	
	
	public CategoryListTabFragment()
	{
		super();
		Events.get_instance().RegisterEventObserver(this);
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

		_progressDialog = new ProgressDialog(getActivity());
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
    	HideMessageBox();
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
    public void onResume() 
    {
        super.onResume();

		RestoreSelections();
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


	private void RestoreSelections() 
	{
    	HighlightListItem(_categoryAdapterView, _selectedCategoryIndex, true);
    	HighlightListItem(_loanAdapterView, _selectedLoanIndex, true);
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
				MessageBox(R.string.message_load_title, R.string.message_load_category_list);

				_loanCategoryListFragment = new LoanCategoryListFragment();
				_loanCategoryListFragment.set_model(new CategoryActivityViewModel());
				_loanCategoryListFragment.SetCategoryListItemClickListener(_onCategoryItemClicked);
			}			
			result = _loanCategoryListFragment;
			break;
			
		case LOAN_LIST:
			MessageBox(R.string.message_load_title, R.string.message_load_loan_list);
			if (_loanListFragment == null)
			{
				_loanListFragment = new LoanListFragment();
				_loanListFragment.set_model(new LoanListViewModel());
				_loanListFragment.SetLoanListItemClickListener(_onLoanItemClicked);
			}
			_loanListFragment.setLoanCategory(getSelectedCategory());
			result = _loanListFragment;
			break;
			
		case LOAN_DETAIL:
			MessageBox(R.string.message_load_title, R.string.message_load_loan_detail);
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
		setSelectedCategory(_loanCategoryListFragment.get_model().getCategory(position));
	    
	    SetActiveFragment(R.id.mainContainer, FragmentId.LOAN_LIST);
	    
	    if (updateGui)
	    {
	    	HighlightListItem(_categoryAdapterView, _selectedCategoryIndex, true);
	    }
	}


	private void SelectLoan(int position, boolean updateGui) 
	{
		_selectedLoanIndex = position;
		setSelectedLoan(_loanListFragment.get_model().getLoan(position));
	        
		SetActiveFragment(R.id.mainContainer, FragmentId.LOAN_DETAIL);
	    
	    if (updateGui)
	    {
	    	HighlightListItem(_loanAdapterView, _selectedLoanIndex, true);
	    }
	}


	private void MessageBox(int messageTitle, int messageContent) 
	{
		_progressDialog.setCancelable(false);
		_progressDialog.setTitle(getActivity().getResources().getText(messageTitle));
		_progressDialog.setMessage(getActivity().getResources().getText(messageContent));
		_progressDialog.show();
	}

	
	private void HighlightListItem(AdapterView<?> adapterView, int itemIndex, boolean highlight)
	{
    	if (adapterView != null)
    	{
    		Checkable child = (Checkable) adapterView.getChildAt(itemIndex);
    		if (child != null) 
			{
    			child.toggle();
				if ( (highlight && !child.isChecked()) ||
					 (!highlight && child.isChecked()) ) child.toggle();
			}
    	}
	}


	private Loan getSelectedLoan() {
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


	private void HideMessageBox() 
	{
		if (_progressDialog.isShowing()) 
    	{
			_progressDialog.dismiss();
        }
	}


	@Override
	public void OnEvent(int eventId, Object observable, Object params) 
	{
		if (eventId == Events.CategoriesLoadCompleted ||
			eventId == Events.LoanListLoadCompleted ||
			eventId == Events.LoanDetailLoadCompleted)
		{
			HideMessageBox();
		}
	}
}