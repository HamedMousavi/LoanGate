package com.orderedsoft.loangate.categoryFragments;

import android.webkit.WebView;
import android.webkit.WebViewClient;

public class LoanDetailWebViewCallback extends WebViewClient
{

	@Override
    public boolean shouldOverrideUrlLoading( 
            WebView view, String url) {
        return(false);
    }
	
}
