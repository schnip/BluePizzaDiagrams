package problem.observer;

public interface IAppReporter {
	public void registerDisplay(IDisplay d);
	public void removeDisplay(IDisplay d);
	
	/**
	 * Sets the weather data. It is assumed that the data is not null.
	 * @param data
	 */
	public void setData(IAppData data);
	public void notifyDisplays();
}
