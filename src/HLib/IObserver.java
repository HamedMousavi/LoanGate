package HLib;

public abstract interface IObserver 
{
	public abstract void OnSubjectChanged(Object observable, Object params);
}
