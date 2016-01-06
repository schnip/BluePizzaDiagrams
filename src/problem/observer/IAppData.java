package problem.observer;

import java.nio.file.Path;
import java.nio.file.WatchEvent;
import java.util.HashMap;

public interface IAppData {
	
	public WatchEvent<Path> getEvent();
	public AppLauncher getApp();
	public Path getPath();
	public HashMap<String, String> getHash();
	
}
