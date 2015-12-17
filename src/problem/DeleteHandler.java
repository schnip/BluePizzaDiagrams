package problem;

import static java.nio.file.StandardWatchEventKinds.*;

public class DeleteHandler implements IDisplay{

	@Override
	public void dataChanged(IAppData data) {
		if (!data.getEvent().kind().equals(ENTRY_DELETE))
			return;
		System.out.println("DELETE MADE");
		
	}
	
	

}