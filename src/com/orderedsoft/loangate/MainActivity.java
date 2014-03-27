package com.orderedsoft.loangate;


import com.orderedsoft.loangate.navigation.TabFragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;


public class MainActivity extends FragmentActivity 
{

	@Override
    protected void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        
    	setContentView(R.layout.activity_main);
    	
    	EnsureTabsVisible(savedInstanceState);
	}

	
	private void EnsureTabsVisible(Bundle savedInstanceState)
	{
		// Check that the activity is using the layout version with
        // the fragment_container FrameLayout
        if (findViewById(R.id.tab_fragment) != null) 
        {

            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            }

            // Create a new Fragment to be placed in the activity layout
            TabFragment firstFragment = new TabFragment();
            
            // In case this activity was started with special instructions from an
            // Intent, pass the Intent's extras to the fragment as arguments
            Intent intent = getIntent();
            firstFragment.setArguments(intent.getExtras());
            
            // Add the fragment to the 'fragment_container' FrameLayout
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.tab_fragment, firstFragment).commit();	
        }
	}
}
