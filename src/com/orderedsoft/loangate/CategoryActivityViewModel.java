package com.orderedsoft.loangate;

import java.util.List;

import HLib.IObserver;

import com.orderedsoft.loangate.models.LoanCategory;
import com.orderedsoft.loangate.serviceProxies.CategoriesProxy;


public class CategoryActivityViewModel implements IObserver
{

	private List<LoanCategory> _categories;
	private IObserver _observer;
	
	
	public CategoryActivityViewModel(IObserver modelEventObserver)
	{
		_observer = modelEventObserver;
	}

	
	public void ReloadCategories()
	{
		CategoriesProxy proxy = new CategoriesProxy("http://10.0.2.2/LoanGate/api/loancategories");
		proxy.LoadCategories(this);
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
		_observer.OnSubjectChanged(this, _categories);
	}
	

	@SuppressWarnings("unchecked")
	@Override
	public void OnSubjectChanged(Object observable, Object params) 
	{
		List<LoanCategory> categories = null;
		if (params != null)
		{
			categories = (List<LoanCategory>)params;
		}

		setCategories(categories);
	}


	public LoanCategory getCategory(int position) {
		return _categories.get(position);
	}
}
