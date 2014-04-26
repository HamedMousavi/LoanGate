package com.orderedsoft.loangate.activities;


import com.orderedsoft.loangate.R;
import com.orderedsoft.loangate.navigation.CategoryListTabFragment;
import com.orderedsoft.loangate.navigation.NotifyTabFragment;
import com.orderedsoft.loangate.navigation.SearchTabFragment;
import com.orderedsoft.loangate.navigation.SettingsTabFragment;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


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
            Context context = _tabHost.getContext();
            _tabHost.addTab(
            		_tabHost.newTabSpec("tab1").
            			setIndicator(createTabView(context,
            					getResources().getText(R.string.list), 
            					getResources().getDrawable(R.drawable.list))),
                    CategoryListTabFragment.class, null);

            _tabHost.addTab(
            		_tabHost.newTabSpec("tab2").
            			setIndicator(createTabView(context,
            					getResources().getText(R.string.search), 
            					getResources().getDrawable(R.drawable.search))),
                    SearchTabFragment.class, null);

            _tabHost.addTab(
            		_tabHost.newTabSpec("tab3").
        			setIndicator(createTabView(context,
        					getResources().getText(R.string.notify), 
        					getResources().getDrawable(R.drawable.bell))),
                    NotifyTabFragment.class, null);

            _tabHost.addTab(
            		_tabHost.newTabSpec("tab4").
        			setIndicator(createTabView(context,
        					getResources().getText(R.string.settings), 
        					getResources().getDrawable(R.drawable.settings))),
                    SettingsTabFragment.class, null);
        }
	}
	

	private View createTabView(Context context, CharSequence text, Drawable drawable) 
	{
		View view = LayoutInflater.from(context).inflate(R.layout.tab_host_header, null);
		TextView tv = (TextView) view.findViewById(R.id.tbxTabTitle);
		tv.setText(text);
		
		ImageView iv = (ImageView) view.findViewById(R.id.ivwTabIcon);
		iv.setImageDrawable(drawable);

		return view;
	}
}