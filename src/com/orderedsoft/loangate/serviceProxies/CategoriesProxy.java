package com.orderedsoft.loangate.serviceProxies;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

public class CategoriesProxy 
{
	
	private URL _url;
	

	public CategoriesProxy(String url)
	{
		if (url.equals(null) || url.equals(""))
		{
			url = "http://Hamed-Laptop/LoanGate/api/loancategories";
		}
		
		try {
			_url = new URL(url);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public List<LoanCategory> LoadCategories()
	{
		try {
			
			// Obtain a factory object for creating SAX parsers
			SAXParserFactory factory = SAXParserFactory.newInstance();

			// Configure the factory object to specify attributes of the parsers it creates
			factory.setValidating(true);
			factory.setNamespaceAware(true);

			SAXParser parser = factory.newSAXParser();
			
			HttpURLConnection connection = (HttpURLConnection)_url.openConnection();
			connection.setRequestProperty("Accept", "XML");
			connection.setRequestProperty("Accept-Encoding", "UTF-8");
			
			InputStream stream = connection.getInputStream();
			CategoryXmlHandler handler = new CategoryXmlHandler();
			
			parser.parse(stream, handler);
			
			return handler.getMessages();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			// PARSE EXCEPTION
			e.printStackTrace();
		}
		
		return null;
	}
}
