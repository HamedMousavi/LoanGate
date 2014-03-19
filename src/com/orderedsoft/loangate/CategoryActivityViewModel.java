package com.orderedsoft.loangate;

import java.util.List;

import com.orderedsoft.loangate.serviceProxies.CategoriesProxy;
import com.orderedsoft.loangate.serviceProxies.LoanCategory;

public class CategoryActivityViewModel 
{

	private List<LoanCategory> _categories;
	
	
	public CategoryActivityViewModel()
	{
	}

	
	public void ReloadCategories()
	{
		CategoriesProxy proxy = new CategoriesProxy("http://Hamed-Laptop/LoanGate/api/loancategories");
		setCategories(proxy.LoadCategories());
	}


	/**
	 * @return the _categories
	 */
	public List<LoanCategory> getCategories() {
		return _categories;
	}


	/**
	 * @param _categories the _categories to set
	 */
	private void setCategories(List<LoanCategory> _categories) {
		this._categories = _categories;
	}

}
