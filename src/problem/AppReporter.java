package problem;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class AppReporter implements IAppReporter{

	private Collection<IDisplay> displays;
	private IAppData data;
	
	public AppReporter() {
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
	public void setData(IAppData data) {
		synchronized(this.displays) {
			if(data.equals(this.data))
				return;
			
			this.data = data;
			this.notifyDisplays();
		}
	}
	
	public void notifyDisplays() {
		synchronized(this.displays) { // Sync this.displays so there is no concurrent modification
			for(IDisplay d : this.displays)
				d.dataChanged(this.data);
		}
	}

}
