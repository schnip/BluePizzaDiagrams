package problem;

import static java.nio.file.StandardWatchEventKinds.*;

import java.io.IOException;

public class ModifyHandler implements IDisplay{

	@Override
	public void dataChanged(IAppData data) {
		if (!data.getEvent().kind().equals(ENTRY_MODIFY))
			return;
		System.out.println("MODIFICATION MADE");
		
	}
	
	

}
