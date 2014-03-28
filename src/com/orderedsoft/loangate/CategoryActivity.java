package com.orderedsoft.loangate;


import com.orderedsoft.loangate.serviceProxies.LoanCategory;

import HLib.IObserver;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;


public class CategoryActivity extends FragmentActivity  implements IObserver
{
	
	public CategoryActivityViewModel Model = null;
	private ArrayAdapter<LoanCategory> _categoriesAdapter;

	
    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        
        if (null != savedInstanceState) restoreState(savedInstanceState);
        
        // Create & display view 
        if (Model == null) Model = new CategoryActivityViewModel(this);
        
        //SetupBindings();

		ReloadCategories();
    }


    private void restoreState(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
	}


	private void SetupBindings() {

		// Bind Sync click event
    	Button btnSync = (Button)findViewById(R.id.btn_sync);
    	btnSync.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				ReloadCategories();
			}
		});
    	
    	// Bind settings page
    	Button btnSettings = (Button)findViewById(R.id.btn_settings);
    	btnSettings.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent settingPage = new Intent(CategoryActivity.this, SettingsActivity.class);
				startActivity(settingPage);
			}
		});
    	
	}


	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.category, menu);
        return true;
    }
    

	protected void ReloadCategories() 
	{
		Model.ReloadCategories();
	}
	
	
	protected void ReBindCategories()
	{
    	// Bind ListView to Categories
		if (Model.getCategories() != null) {
	    	_categoriesAdapter = new CategoryAdapter(
	    			this, R.layout.category_list_item, Model.getCategories());
	    	ListView lvwCategories = (ListView)findViewById(R.id.lvw_categories);
			lvwCategories.setAdapter(_categoriesAdapter);
			
			_categoriesAdapter.notifyDataSetChanged();
		}
		else
		{
			_categoriesAdapter = null;
		}
	}
	
	
	/** UNDONE: REPLACE THIS WITH BEANS PROPERTY CHANGED EVENT */
	public void OnSubjectChanged(Object observable, Object params)
	{
		ReBindCategories();
	}
}
