package HLib;


import com.orderedsoft.loangate.Events;

import android.os.AsyncTask;


public class WebServiceTask<T> extends  AsyncTask<Void, Void, T>
{

	private WebServiceInvoker<T> _invoker;
	private Object _eventSource;


	public WebServiceTask(WebServiceInvoker<T> invoker, Object eventSource)
	{
		_invoker = invoker;	
		_eventSource = eventSource;
	}
	

	@Override
	protected T doInBackground(Void... arg0) 
	{
		return _invoker.Invoke();
	}

	
	@Override
	protected void onPostExecute(T result)
	{
		Events.get_instance().SendEvent(Events.AsyncOperationCompleted, _eventSource, result);
	}
}
