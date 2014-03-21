package HLib;


import android.os.AsyncTask;


public class WebServiceTask<T> extends  AsyncTask<Void, Void, T>
{

	private WebServiceInvoker<T> _invoker;
	private IObserver _observer;


	public WebServiceTask(WebServiceInvoker<T> invoker)
	{
		_invoker = invoker;	
		_observer = null;
	}
	

	/** UNDONE: replace one observer with an event */
	public WebServiceTask(WebServiceInvoker<T> invoker,
			IObserver completeObserver)
	{
		_invoker = invoker;	
		_observer = completeObserver;	
	}


	@Override
	protected T doInBackground(Void... arg0) 
	{
		return _invoker.Invoke();
	}

	
	@Override
	protected void onPostExecute(T result)
	{
		if (_observer != null) _observer.OnSubjectChanged(_invoker, result);
	}
}
