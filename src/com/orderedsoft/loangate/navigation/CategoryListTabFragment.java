package com.orderedsoft.loangate.navigation;



import com.orderedsoft.loangate.CategoryListTabPage;
import com.orderedsoft.loangate.R;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class CategoryListTabFragment extends Fragment
{
	
	private View _view;


	@Override
    public void onCreate(Bundle savedInstanceState) {
    	// Create menu
		super.onCreate(savedInstanceState);
    }

	
	@Override
    public void onAttach(Activity activity) 
	{
        super.onAttach(activity);
	}    

	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) 
    {
    	if (_view == null) _view = inflater.inflate(R.layout.tab_page_category_list, container, false);
        return _view;
    }
    

	@Override
    public void onStart()
    {
		super.onStart();
		
    	CategoryListTabPage page = new CategoryListTabPage();
    	page.Start(_view);
    }
}