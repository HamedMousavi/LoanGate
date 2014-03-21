package HLib;


import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import android.util.Log;


public class WebServiceInvoker<T>
{
	
	private URL _url;
	private WebServiceHandler<T> _handler;


	public WebServiceInvoker (URL url, WebServiceHandler<T> handler)
	{
		_url = url;
		_handler = handler;
	}
	
	
	public T Invoke()
	{
		// Obtain a factory object for creating SAX parsers
		SAXParserFactory factory = SAXParserFactory.newInstance();

		// Configure the factory object to specify attributes of the parsers it creates
		factory.setValidating(false);
		factory.setNamespaceAware(true);

		SAXParser parser;
		try {
			parser = factory.newSAXParser();
		
			HttpURLConnection connection = (HttpURLConnection)_url.openConnection();
			connection.setRequestProperty("Accept", "application/xml");
			connection.setRequestProperty("Accept-Encoding", "UTF-8");
			
			InputStream stream = connection.getInputStream();
			
			parser.parse(stream, _handler);
		
			return _handler.getResults();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.e("Invoke", e.getMessage());
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return null;
	}
}
