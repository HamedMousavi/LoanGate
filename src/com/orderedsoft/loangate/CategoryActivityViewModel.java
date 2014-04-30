package com.orderedsoft.loangate;


import java.util.List;
import HLib.IObserver;
import com.orderedsoft.loangate.models.LoanCategory;
import com.orderedsoft.loangate.serviceProxies.CategoriesProxy;


public class CategoryActivityViewModel implements IObserver
{

	private List<LoanCategory> _categories;
	private CategoriesProxy _proxy;
	
	
	public void ReloadCategories()
	{
		Events.get_instance().RegisterEventObserver(this);

		_proxy = new CategoriesProxy(AppSettings.get_loanCtegoriesUrl());
		_proxy.LoadCategories();
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
		Events.get_instance().SendEvent(Events.CategoriesLoadCompleted, this, _categories);
	}
	

	@SuppressWarnings("unchecked")
	@Override
	public void OnEvent(int eventId, Object observable, Object params) 
	{
		if (eventId == Events.AsyncOperationCompleted && observable == _proxy)
		{
			List<LoanCategory> categories = null;
			if (params != null)
			{
				categories = (List<LoanCategory>)params;
			}
	
			setCategories(categories);
		}
	}


	public LoanCategory getCategory(int position) {
		return _categories.get(position);
	}
}
