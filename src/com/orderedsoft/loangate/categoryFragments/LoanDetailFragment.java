/**
 * 
 */
package com.orderedsoft.loangate.categoryFragments;

import com.orderedsoft.loangate.R;
import com.orderedsoft.loangate.models.Loan;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

/**
 * @author Hamed
 *
 */
public class LoanDetailFragment extends Fragment 
{

	private View _view;
	private Uri _uri;

	
	public void SetLoan(Loan loan) 
	{
		_uri = Uri.parse("http://10.0.2.2/LoanGate/LoanDetail/Catalog/" + Long.toString(loan.getId()));
	}



	@Override
    public void onCreate(Bundle savedInstanceState) {
    	// Create menu
		super.onCreate(savedInstanceState);
    }

	
	@Override
    public void onAttach(Activity activity) 
	{
        super.onAttach(activity);
	}    

	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) 
    {
    	_view = inflater.inflate(R.layout.activity_loan_detail, container, false);
        return _view;
    }
    

	@Override
    public void onStart()
    {
		super.onStart();
		
    	SetupBindings();
    }

	
	private void SetupBindings() 
	{
        if (_uri == null) {
        	return;
        }
        
		WebView webView = (WebView) _view.findViewById(R.id.loanDetailWebView); 
        webView.setWebViewClient(new LoanDetailWebViewCallback()); 
        
        webView.loadUrl(_uri.toString());
	}
}

