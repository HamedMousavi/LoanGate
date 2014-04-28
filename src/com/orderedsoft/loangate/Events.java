package com.orderedsoft.loangate;

import HLib.Observable;

public class Events extends Observable 
{
	
	private static Events _instance;
	
	public static final int AsyncOperationCompleted = 0;
	public static final int CategoriesLoadCompleted = 1;
	public static final int LoanListLoadCompleted = 2;
	public static final int LoanDetailLoadCompleted = 3;
	
	
	public static Events get_instance()
	{
		if (_instance == null)
		{
			_instance = new Events();
		}
		
		return _instance;
	}
}
