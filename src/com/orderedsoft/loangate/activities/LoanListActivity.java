package com.orderedsoft.loangate.activities;

import com.orderedsoft.loangate.R;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;


public class LoanListActivity extends FragmentActivity 
{

	@Override
    protected void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        
    	setContentView(R.layout.activity_loan_list);

        //---if the user switches to landscape mode; destroy the activity---
        if (getResources().getConfiguration().orientation == 
                Configuration.ORIENTATION_LANDSCAPE) 
        {
            finish();
            return;
        }
    }

}
