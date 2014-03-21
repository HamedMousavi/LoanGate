package HLib;

import org.xml.sax.helpers.DefaultHandler;

public abstract class WebServiceHandler<T> extends DefaultHandler
{
	public abstract T getResults();
}
