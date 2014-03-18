package com.orderedsoft.loangate;

import java.util.ArrayList;

public class CategoryActivityViewModel 
{

	public ArrayList<String> Categories;
	
	
	public CategoryActivityViewModel()
	{
		Categories = new ArrayList<String>();
	}

	
	public void ReloadCategories()
	{
		// UNDONE
		// Call service
		// Get data
		Categories.clear();
    	Categories.add("Hamed 1");
		Categories.add("Hamed 2");
		Categories.add("Hamed 3");
		Categories.add("Hamed 4");
	}

}
