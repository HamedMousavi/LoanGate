package com.orderedsoft.loangate;

public class AppSettings 
{

	private static String _baseUrl = "http://10.0.2.2/LoanGate/";
	
	
	public static String get_loanCtegoriesUrl()
	{
		return _baseUrl + "api/loancategories";
	}
	
	
	public static String get_loanListUrl()
	{
		return _baseUrl + "api/loanlist/";
	}

	
	public static String get_loanDetailUrl()
	{
		return _baseUrl + "LoanDetail/Catalog/";
	}
}
