package com.orderedsoft.loangate;


import java.util.List;

import com.orderedsoft.loangate.models.Loan;
import com.orderedsoft.loangate.serviceProxies.LoansProxy;

import HLib.IObserver;


public class LoanListViewModel implements IObserver
{
	
	private List<Loan> _loans;
	private LoansProxy _proxy;
	

	public void ReloadLoans(long loanId) {
		Events.get_instance().RegisterEventObserver(this);

		_proxy = new LoansProxy(AppSettings.get_loanListUrl() + Long.toString(loanId));
		_proxy.Load();
	}

	
	public List<Loan> getLoans() {
		return _loans;
	}
	
	public void setLoans(List<Loan> loans)
	{
		_loans = loans;
		Events.get_instance().SendEvent(Events.LoanListLoadCompleted, this, _loans);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void OnEvent(int eventId, Object observable, Object params) 
	{
		if (eventId == Events.AsyncOperationCompleted && observable == _proxy)
		{
			List<Loan> list = null;
			if (params != null)
			{
				list = (List<Loan>)params;
			}
	
			setLoans(list);
		}
	}

	public Loan getLoan(int position) {
		// TODO Auto-generated method stub
		return getLoans().get(position);
	}
}
/**
		
		_loans = new ArrayList<Loan>();
		
		Loan loan = new Loan();
		loan.setValue(2000000);
		loan.setTitle("To motherfucked couples only");
		
		_loans.add(loan);*/