package HLib;

import java.util.ArrayList;
import java.util.List;


public class Observable 
{
	
	private List<IObserver> _observers;
	
	
	public Observable()
	{
		_observers = new ArrayList<IObserver>();
	}

	
	public boolean RegisterEventObserver(IObserver observer)
	{
		if (!_observers.contains(observer))
		{
			_observers.add(observer);
			return true;
		}
		
		return false;
	}

	
	public boolean UnRegisterEventObserver(IObserver observer)
	{
		if (_observers.contains(observer))
		{
			_observers.remove(observer);
			return true;
		}
		
		return false;
	}
	
	
	public void SendEvent(int eventId, Object sender, Object params)
	{
		for (IObserver observer : _observers) {
			observer.OnEvent(eventId, sender, params);
		}
	}
}