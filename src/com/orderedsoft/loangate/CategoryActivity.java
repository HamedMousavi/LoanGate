package com.orderedsoft.loangate;


import com.orderedsoft.loangate.serviceProxies.LoanCategory;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;


public class CategoryActivity extends Activity 
{
	
	public CategoryActivityViewModel Model;
	private ArrayAdapter<LoanCategory> _categoriesAdapter;

	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        
        if (Model == null) Model = new CategoryActivityViewModel();

        SetupBindings();
    }


    private void SetupBindings() {

    	// Bind ListView to Categories
    	_categoriesAdapter = new ArrayAdapter<LoanCategory>(
    			this, android.R.layout.simple_list_item_1, Model.getCategories());
    	ListView lvwCategories = (ListView)findViewById(R.id.lvw_categories);
		lvwCategories.setAdapter(_categoriesAdapter);
        
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
		_categoriesAdapter.notifyDataSetChanged();
	}
}
