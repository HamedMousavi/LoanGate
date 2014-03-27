package com.orderedsoft.loangate.navigation;


import java.util.ArrayList;

import com.orderedsoft.loangate.R;
import com.orderedsoft.loangate.navigation.TabControl.OnMenuItemSelectedListener;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


public class TabFragment extends Fragment implements OnMenuItemSelectedListener
{
	
	private final int _resourceId = R.layout.tab_main_horizontal;
	private TabControl _mainTab = null;
	private Context _context;
	private View _view;
	private LayoutInflater _inflater;

	public static final int MENU_ITEM_1 = 1;

	
	@Override
    public void onCreate(Bundle savedInstanceState) {
    	// Create menu
		super.onCreate(savedInstanceState);
    }

	
	@Override
    public void onAttach(Activity activity) 
	{
        super.onAttach(activity);
        
        _context = activity;
	}    

	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    	
    	_inflater = inflater;
    	_view = inflater.inflate(_resourceId, container, false);
    	
    	return _view;
    }
    
    
    private void CreateMainTab() 
    {
		_mainTab = new TabControl(_context, this, _inflater);
		_mainTab.setHideOnSelect(false);
		_mainTab.setItemsPerLineInPortraitOrientation(4);
		_mainTab.setItemsPerLineInLandscapeOrientation(8);

		ArrayList<TabItem> menuItems = new ArrayList<TabItem>();
		TabItem cmi = new TabItem();
		cmi.setCaption("First");
		cmi.setImageResourceId(R.drawable.icon1);
		cmi.setId(MENU_ITEM_1);
		menuItems.add(cmi);
		if (!_mainTab.isShowing())
			try {
				_mainTab.setMenuItems(menuItems);
			} catch (Exception e) {
				AlertDialog.Builder alert = new AlertDialog.Builder(_context);
				alert.setTitle("Egads!");
				alert.setMessage(e.getMessage());
				alert.show();
			}
	}


	@Override
    public void onStart()
    {
		super.onStart();
		
    	try
    	{
	    	if (_mainTab == null)
	    	{
	    		CreateMainTab();
	    		_mainTab.show(_view, _resourceId);
	    	}
    	}
    	catch(Exception e)
    	{
    		Log.e(null, e.getMessage());
    	}
    }


	@Override
	public void MenuItemSelectedEvent(TabItem selection) {
		//Toast t = Toast.makeText(
		//		_context, 
		//		"You selected item #"+Integer.toString(selection.getId()), 
		//		Toast.LENGTH_SHORT);
		//t.setGravity(Gravity.CENTER, 0, 0);
		//t.show();
	}
}
