package com.orderedsoft.loangate.serviceProxies;


import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import com.orderedsoft.loangate.models.LoanCategory;

import HLib.Convert;
import HLib.WebServiceHandler;


public class CategoryXmlHandler extends WebServiceHandler<List<LoanCategory>>
{
	
	// WARNING: IN CASE OF CHANGES IN BELOW LIST, UPDATE 'DetectIgnorable' METHOD
	private static final String NAME = "Name";
	private static final String ID = "Id";
	private static final String MODIFIED = "LastModified";
	private static final String COUNT = "LoanerCount";
	private static final String DESCRIPTION = "Description";
	private static final String ITEM = "LoanCategory";
	private StringBuilder _chars;
	private LoanCategory _currentLoan;
	private List<LoanCategory> _categories;
	private boolean _inLoanCategory;
	private int _started;


	@Override
	public void startDocument()
	{
		try {
			super.startDocument();
			_inLoanCategory = false;
			_started = 0;
		} catch (SAXException e) {
			e.printStackTrace();
		}
		_categories = new ArrayList<LoanCategory>();
		_chars = new StringBuilder();
	}


	@Override
	public void endDocument()
	{
		try {
			super.endDocument();
		} catch (SAXException e) {
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
		
			if (_inLoanCategory) _started += 1;
			if (localName.equalsIgnoreCase(ITEM))
			{
				_inLoanCategory = true;
				_currentLoan = new LoanCategory();
			}
			
			//DetectIgnorable(localName);

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
			if (!_inLoanCategory) return;

			// Ignore nested elements
			_started -= 1;
			if (_started > 0) return;
		
			if (localName.equalsIgnoreCase(NAME))
			{
				_currentLoan.setName(_chars.toString());
			}
			else if (localName.equalsIgnoreCase(ID))
			{
				_currentLoan.setId(Convert.ToLong(_chars.toString()));
			}
			else if (localName.equalsIgnoreCase(MODIFIED))
			{
				_currentLoan.setLastModified(Convert.ToDate(_chars.toString()));
			}
			else if (localName.equalsIgnoreCase(COUNT))
			{
				_currentLoan.setLoanerCount(Convert.ToInt(_chars.toString()));
			}
			else if (localName.equalsIgnoreCase(DESCRIPTION))
			{
				_currentLoan.setDescription(_chars.toString());
			}
			else if (localName.equalsIgnoreCase(ITEM))
			{
				_categories.add(_currentLoan);
				_inLoanCategory = false;
				_started = 0;
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
	

	public List<LoanCategory> getResults() {
		return _categories;
	}

}