package com.orderedsoft.loangate.categoryFragments;

import com.orderedsoft.loangate.Events;

import android.webkit.WebView;
import android.webkit.WebViewClient;

public class LoanDetailWebViewCallback extends WebViewClient
{

	@Override
    public boolean shouldOverrideUrlLoading( 
            WebView view, String url) {
        return(false);
    }
	
	@Override
	public void  onPageFinished (WebView view, String url)
	{
		Events.get_instance().SendEvent(Events.LoanDetailLoadCompleted, view, url);
	}
	
}
