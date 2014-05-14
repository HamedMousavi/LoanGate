package com.orderedsoft.loangate;

public class AppSettings 
{

	private static String _baseUrl = "http://www.orderedsoft.com/";
	
	
	public static String get_loanCtegoriesUrl()
	{
		return _baseUrl + "LoanGate/loancategories";
	}
	
	
	public static String get_loanListUrl()
	{
		return _baseUrl + "LoanGate/loanlist/";
	}

	
	public static String get_loanDetailUrl()
	{
		return _baseUrl + "LoanGateWebUi/LoanDetail/";
	}
}
