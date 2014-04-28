package com.orderedsoft.loangate;


import java.util.List;

import HLib.Convert;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.orderedsoft.loangate.models.Loan;

public class LoanListAdapter extends ArrayAdapter<Loan> {

	
	private List<Loan> _items;
	private Context _context;
	private int _resource;

	
	public LoanListAdapter(Context context, int resource, List<Loan> objects) 
	{
		super(context, resource, objects);
		
		_resource = resource;
		_items = objects;
		_context = context;
	}
	
	
	@Override
	public View getView(int position, View view, ViewGroup parent)
	{
		LoanItemViewControls info;
		
		if (view == null)
		{
			info = new LoanItemViewControls();
			view = CreateView(info);
		}
		else // A second visit, use last visit data
		{
			info = (LoanItemViewControls)view.getTag();
		}
		
		Loan item = _items.get(position);
		if (info != null && item != null) ExchangeData(info, item);
		
		return view;
	}

	
	private void ExchangeData(LoanItemViewControls info, Loan item) 
	{
		String expLabel = (String) _context.getResources().getText(R.string.expires_at);
		expLabel += ": ";
		
		info.get_tbxLoanAmount().setText(String.format("%.0f ", item.getValue()));
		info.get_tbxLoanAmountUnit().setText(item.getLoanAmountUnit());
		info.get_tbxLoanExpiration().setText(expLabel + Convert.ToString(item.getEpiration()));
		info.get_tbxLoanTitle().setText(item.getTitle());
	}


	private View CreateView(LoanItemViewControls info) {
		LayoutInflater inflater = LayoutInflater.from(_context);
		View view = inflater.inflate(_resource, null, false);
		
		info.set_tbxLoanAmount((TextView)view.findViewById(R.id.tbxLoanAmount));
		info.set_tbxLoanAmountUnit((TextView)view.findViewById(R.id.tbxLoanAmountUnit));
		info.set_tbxLoanExpiration((TextView)view.findViewById(R.id.tbxLoanExpiration));
		info.set_tbxLoanTitle((TextView)view.findViewById(R.id.tbxLoanTitle));

		return view;
	}
}
