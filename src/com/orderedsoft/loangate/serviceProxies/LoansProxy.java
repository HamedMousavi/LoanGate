package com.orderedsoft.loangate.serviceProxies;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import com.orderedsoft.loangate.models.Loan;

import HLib.IObserver;
import HLib.WebServiceInvoker;
import HLib.WebServiceTask;

public class LoansProxy {

	private URL _url;
	

	public LoansProxy(String url)
	{
		try {
			_url = new URL(url);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void Load(IObserver loadCompleteObserver)
	{
		WebServiceInvoker<List<Loan>> invoker = 
				new WebServiceInvoker<List<Loan>>(_url, new LoanXmlHandler());

		new WebServiceTask<List<Loan>>(invoker, loadCompleteObserver).execute();
	}


}
