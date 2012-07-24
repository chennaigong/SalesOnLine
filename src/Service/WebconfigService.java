package Service;

import java.util.List;

import Entity.SolWebconfig;

public interface WebconfigService {
	
	public List<SolWebconfig> webconfigList();
	public void addWebconfig(String... config);
	
}
