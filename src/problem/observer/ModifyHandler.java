package problem.observer;

import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;

public class ModifyHandler implements IDisplay{

	@Override
	public void dataChanged(IAppData data) {
		if (!data.getEvent().kind().equals(ENTRY_MODIFY))
			return;
		System.out.println("MODIFICATION MADE");
		
	}
	
	

}
