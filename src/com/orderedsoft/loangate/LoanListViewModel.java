package com.orderedsoft.loangate;

import java.util.List;

import com.orderedsoft.loangate.models.Loan;
import com.orderedsoft.loangate.serviceProxies.LoansProxy;

import HLib.IObserver;


public class LoanListViewModel implements IObserver
{
	
	private List<Loan> _loans;
	private IObserver _observer;
	
	
	public LoanListViewModel(IObserver modelEventObserver) {
		_observer = modelEventObserver;
	}

	public void ReloadLoans(long loanId) {
		LoansProxy proxy = new LoansProxy("http://10.0.2.2/LoanGate/api/loanlist/" + Long.toString(loanId));
		proxy.Load(this);
	}

	public List<Loan> getLoans() {
		return _loans;
	}
	
	public void setLoans(List<Loan> loans)
	{
		_loans = loans;
		_observer.OnSubjectChanged(this, _loans);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void OnSubjectChanged(Object observable, Object params) 
	{
		List<Loan> list = null;
		if (params != null)
		{
			list = (List<Loan>)params;
		}

		setLoans(list);
	}
}
/**
		
		_loans = new ArrayList<Loan>();
		
		Loan loan = new Loan();
		loan.setValue(2000000);
		loan.setTitle("To motherfucked couples only");
		
		_loans.add(loan);*/