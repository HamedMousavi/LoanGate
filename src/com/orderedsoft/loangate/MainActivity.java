package com.orderedsoft.loangate;


import com.orderedsoft.loangate.navigation.CategoryListTabFragment;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;


public class MainActivity extends FragmentActivity 
{

	 private FragmentTabHost _tabHost;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        
    	setContentView(R.layout.activity_main);
    	
    	SetupTabLayout(savedInstanceState);
	}

	
	private void SetupTabLayout(Bundle savedInstanceState)
	{
		// Check that the activity is using the layout version with
        // the fragment_container FrameLayout
        if (findViewById(R.id.tab_main_content) != null) 
        {

            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            }

            _tabHost = (FragmentTabHost) findViewById(R.id.tabhost);
            _tabHost.setup(this, getSupportFragmentManager(), R.id.tab_main_content);
            _tabHost.addTab(
            		_tabHost.newTabSpec("tab1").
            			setIndicator(
            					getResources().getText(R.string.list), 
            					getResources().getDrawable(R.drawable.icon1)),
                    CategoryListTabFragment.class, null);

            _tabHost.addTab(
            		_tabHost.newTabSpec("tab2").
            			setIndicator(
            					getResources().getText(R.string.search), 
            					getResources().getDrawable(R.drawable.icon1)),
                    CategoryListTabFragment.class, null);

            _tabHost.addTab(
            		_tabHost.newTabSpec("tab3").
        			setIndicator(
        					getResources().getText(R.string.sync), 
        					getResources().getDrawable(R.drawable.icon1)),
                    CategoryListTabFragment.class, null);

            _tabHost.addTab(
            		_tabHost.newTabSpec("tab4").
        			setIndicator(
        					getResources().getText(R.string.settings), 
        					getResources().getDrawable(R.drawable.icon1)),
                    CategoryListTabFragment.class, null);
}
	}
}
