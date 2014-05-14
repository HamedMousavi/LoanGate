package com.orderedsoft.loangate.navigation;

import com.orderedsoft.loangate.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class GiftsTabFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) 
    {
    	View view = inflater.inflate(R.layout.tab_page_gifts, container, false);
		return  view;
    }
}