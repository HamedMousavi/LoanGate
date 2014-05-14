package com.orderedsoft.loangate.serviceProxies;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import HLib.Convert;
import HLib.WebServiceHandler;

import com.orderedsoft.loangate.models.Loan;


public class LoanXmlHandler extends WebServiceHandler<List<Loan>> {

	
	private static final String TITLE = "Title";
	private static final String ID = "Id";
	private static final String EXPIRATION = "Expiration";
	private static final String AMOUNT = "Amount";
	private static final String AMOUNTUNIT = "UnitName";
	//private static final String BANKLIST = "AvailableBanks";
	private static final String ITEM = "Loan";
	private static final String AMOUNTPARENT = "Amount";
	private StringBuilder _chars;
	private Loan _currentLoan;
	private List<Loan> _list;
	private boolean _inLoan;


	@Override
	public void startDocument()
	{
		try {
			super.startDocument();
			_inLoan = false;
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		_list = new ArrayList<Loan>();
		_chars = new StringBuilder();
	}


	@Override
	public void endDocument()
	{
		try {
			super.endDocument();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
    
	// This method is invoked when the parser encounters the opening tag
	// of any XML element.
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) 
	{
		try {
			super.startElement(uri, localName, qName, attributes);
		
			if (localName.equalsIgnoreCase(ITEM))
			{
				_inLoan = true;
				_currentLoan = new Loan();
			}
			else if(localName.equalsIgnoreCase(AMOUNTPARENT))
			{
			}

		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@Override
	public void endElement(String uri, String localName, String qName) 
	{
		try {
			super.endElement(uri, localName, qName);
			if (!_inLoan) return;
		
			if (localName.equalsIgnoreCase(TITLE))
			{
				_currentLoan.setTitle(_chars.toString());
			}
			else if (localName.equalsIgnoreCase(ID))
			{
				_currentLoan.setId(Convert.ToLong(_chars.toString()));
			}
			else if (localName.equalsIgnoreCase(EXPIRATION))
			{
				_currentLoan.setExpiration(Convert.ToDate(_chars.toString()));
			}
			else if (localName.equalsIgnoreCase(AMOUNT))
			{
				_currentLoan.setValue(new BigDecimal(_chars.toString()));
			}
			else if (localName.equalsIgnoreCase(AMOUNTUNIT))
			{
				_currentLoan.setAmountUnit(_chars.toString());
			}
			else if (localName.equalsIgnoreCase(ITEM))
			{
				_list.add(_currentLoan);
				_inLoan = false;
			}

		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e)
		{
			e.printStackTrace();
		}

		// Clear _chars
		_chars.setLength(0);
	}
	
	
    // This method is called for any plain text within an element
	@Override
	public void characters(char[] text, int start, int length) 
	{
		try {
			super.characters(text, start, length);
			
			_chars.append(text, start, length);
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	

	public List<Loan> getResults() {
		return _list;
	}

}
