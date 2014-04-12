package com.orderedsoft.loangate.categoryFragments;

import com.orderedsoft.loangate.LoanListAdapter;
import com.orderedsoft.loangate.LoanListViewModel;
import com.orderedsoft.loangate.R;
import com.orderedsoft.loangate.models.Loan;

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
	private LoanListViewModel Model = null;
	private ArrayAdapter<Loan> _loanListAdapter;
	private AdapterView.OnItemClickListener _onLoanListItemClicked;

	
	public void SetLoanListItemClickListener(OnItemClickListener listener) 
	{
		_onLoanListItemClicked = listener;
	}
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) 
    {
    	if (_view == null) _view = inflater.inflate(R.layout.activity_loan_list, container, false);
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
    		Model = new LoanListViewModel(this);
            ReBindLoans();
    		Model.ReloadLoans();
    	}
	}

	protected void ReBindLoans()
	{
    	// Bind ListView to Categories
		if (Model.getLoans() != null) {
	    	_loanListAdapter = new LoanListAdapter(
	    			_view.getContext(), R.layout.category_list_item, Model.getLoans());
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
	
	
	/** UNDONE: REPLACE THIS WITH BEANS PROPERTY CHANGED EVENT */
	public void OnSubjectChanged(Object observable, Object params)
	{
		ReBindLoans();
	}
}