package temp;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class WeatherReporter implements IWeatherReporter {

	private Collection<IDisplay> displays;
	private IWeatherData data;
	
	public WeatherReporter() {
		this.data = null;
		this.displays = Collections.synchronizedCollection(new ArrayList<IDisplay>());
	}
	
	@Override
	public void registerDisplay(IDisplay d) {
		this.displays.add(d);
	}

	@Override
	public void removeDisplay(IDisplay d) {
		this.displays.remove(d);
	}

	@Override
	public void setWeatherData(IWeatherData data) {
		synchronized(this.displays) {
			if(data.equals(this.data))
				return;
			
			this.data = data;
			this.notifyDisplays();
		}
	}
	
	protected void notifyDisplays() {
		synchronized(this.displays) { // Sync this.displays so there is no concurrent modification
			for(IDisplay d : this.displays)
				d.dataChanged(this.data);
		}
	}

}
