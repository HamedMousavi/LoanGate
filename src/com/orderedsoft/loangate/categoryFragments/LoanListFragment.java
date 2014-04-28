package com.orderedsoft.loangate.categoryFragments;


import com.orderedsoft.loangate.Events;
import com.orderedsoft.loangate.LoanListAdapter;
import com.orderedsoft.loangate.LoanListViewModel;
import com.orderedsoft.loangate.R;
import com.orderedsoft.loangate.models.Loan;
import com.orderedsoft.loangate.models.LoanCategory;

import HLib.IObserver;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;


public class LoanListFragment extends Fragment implements IObserver
{
	
	private View _view;
	private LoanListViewModel _model = null;
	private ArrayAdapter<Loan> _loanListAdapter;
	private AdapterView.OnItemClickListener _onLoanListItemClicked;
	private LoanCategory _category;
	private ListView _lvw_loans;

	
	public LoanListFragment()
	{
		super();
		Events.get_instance().RegisterEventObserver(this);
	}
	
	
	public void SetLoanListItemClickListener(OnItemClickListener listener) 
	{
		_onLoanListItemClicked = listener;
	}
	
	
	@Override
    public void onCreate(Bundle savedInstanceState) 
	{
    	// Create menu
		super.onCreate(savedInstanceState);
	}
	
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) 
    {
    	_view = inflater.inflate(R.layout.activity_loan_list, container, false);
    	_lvw_loans = (ListView) _view.findViewById(R.id.lvw_loans);
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
        ReBindLoans();
		get_model().ReloadLoans(_category.getId());
	}

	protected void ReBindLoans()
	{
    	// Bind ListView to Categories
		if (get_model().getLoans() != null) {
	    	_loanListAdapter = new LoanListAdapter(
	    			_lvw_loans.getContext(), R.layout.list_item_loan, get_model().getLoans());
	    	ListView lvwCategories = (ListView)_view.findViewById(R.id.lvw_loans);
			lvwCategories.setAdapter(_loanListAdapter);
			lvwCategories.setOnItemClickListener(_onLoanListItemClicked);
			
			_loanListAdapter.notifyDataSetChanged();
		}
		else
		{
			_loanListAdapter = null;
		}
	}
	
	
	public void OnEvent(int eventId, Object observable, Object params)
	{
		if (eventId == Events.LoanListLoadCompleted) ReBindLoans();
	}

	public void setLoanCategory(LoanCategory category) {
		_category = category;
		get_model().ReloadLoans(_category.getId());
	}

	/**
	 * @return the model
	 */
	public LoanListViewModel get_model() {
		return _model;
	}

	/**
	 * @param model the model to set
	 */
	public void set_model(LoanListViewModel model) {
		_model = model;
	}
}