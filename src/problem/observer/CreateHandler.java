package problem.observer;

import static java.nio.file.StandardWatchEventKinds.*;

import java.io.IOException;

public class CreateHandler implements IDisplay {

	@Override
	public void dataChanged(IAppData data) {
		// TODO Auto-generated method stub
		if (!data.getEvent().kind().equals(ENTRY_CREATE))
			return;
		String fileName = data.getEvent().context().toString();
		System.out.println("Processing " + fileName + "...");
		String[] splitA = fileName.split("\\.");
		String lastElement = splitA[splitA.length-1];
		String command = data.getHash().get(lastElement);
		if(command == null){
			return;
		}
		ProcessBuilder processBuilder = new ProcessBuilder(command, fileName);
		Process process = null;
		try {
			process = processBuilder.start();
			data.getApp().addProcess(process);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
