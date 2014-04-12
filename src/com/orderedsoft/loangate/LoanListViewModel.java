package com.orderedsoft.loangate;

import java.util.ArrayList;
import java.util.List;

import com.orderedsoft.loangate.models.Loan;

import HLib.IObserver;


public class LoanListViewModel 
{
	
	private List<Loan> _loans;
	
	
	public LoanListViewModel(IObserver modelEventObserver) {
		// TODO Auto-generated constructor stub
	}

	public void ReloadLoans() {
		// TODO Auto-generated method stub
		
	}

	public List<Loan> getLoans() {
		
		_loans = new ArrayList<Loan>();
		
		Loan loan = new Loan();
		loan.setValue(2000000);
		loan.setTitle("To motherfucked couples only");
		
		_loans.add(loan);
		
		return _loans;
	}
}
