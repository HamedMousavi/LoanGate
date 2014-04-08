package com.orderedsoft.loangate.serviceProxies;


import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import com.orderedsoft.loangate.models.LoanCategory;

import HLib.IObserver;
import HLib.WebServiceInvoker;
import HLib.WebServiceTask;


public class CategoriesProxy
{
	
	private URL _url;
	

	public CategoriesProxy(String url)
	{
		try {
			_url = new URL(url);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void LoadCategories(IObserver loadCompleteObserver)
	{
		WebServiceInvoker<List<LoanCategory>> invoker = 
				new WebServiceInvoker<List<LoanCategory>>(_url, new CategoryXmlHandler());

		new WebServiceTask<List<LoanCategory>>(invoker, loadCompleteObserver).execute();
	}
}
