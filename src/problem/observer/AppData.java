package problem.observer;

import java.nio.file.Path;
import java.nio.file.WatchEvent;
import java.util.HashMap;

public class AppData implements IAppData{
		HashMap<String, String> hash;
		WatchEvent<Path> event;
		AppLauncher app;
		Path path;
	
	public AppData(WatchEvent<Path> event, AppLauncher app, Path path) {
		this.event = event;
		this.app = app;
		this.path = path;
		hash = new HashMap<String, String>();
		hash.put("html", "explorer");
		hash.put("htm", "explorer");
		hash.put("txt", "Notepad");
		hash.put("py", "python");
	}

	@Override
	public WatchEvent<Path> getEvent() {
		// TODO Auto-generated method stub
		return this.event;
	}

	@Override
	public AppLauncher getApp() {
		// TODO Auto-generated method stub
		return this.app;
	}

	@Override
	public Path getPath() {
		// TODO Auto-generated method stub
		return this.path;
	}

	@Override
	public HashMap<String, String> getHash() {
		// TODO Auto-generated method stub
		return hash;
	}

}
