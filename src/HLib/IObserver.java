package HLib;

public abstract interface IObserver 
{
	public abstract void OnEvent(int eventId, Object observable, Object params);
}
