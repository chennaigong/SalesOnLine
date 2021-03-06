package ServiceImpl;

import java.util.List;

import Entity.SolWebconfig;
import Service.WebconfigService;

public class WebconfigServiceImpl extends BaseServiceImpl<SolWebconfig> implements WebconfigService{

	@Override
	public List<SolWebconfig> webconfigList() {
		return basedao.findAll(SolWebconfig.class);
	}

	@Override
	public void addWebconfig(String... config) {
		
		SolWebconfig webconfig1=new SolWebconfig();
		webconfig1.setWebconfigKey(config[0]);
		webconfig1.setWebconfigValue(config[1]);
		basedao.save(webconfig1);
		
		SolWebconfig webconfig2=new SolWebconfig();
		webconfig2.setWebconfigKey(config[2]);
		webconfig2.setWebconfigValue(config[3]);
		basedao.save(webconfig2);
	}

	@Override
	public void updateWebconfig(String... config) {
		
		List<SolWebconfig> webconfigList=basedao.findAll(SolWebconfig.class);
		
		SolWebconfig webconfig1=webconfigList.get(0);
		webconfig1.setWebconfigValue(config[0]);
		basedao.save(webconfig1);
		
		SolWebconfig webconfig2=webconfigList.get(1);
		webconfig2.setWebconfigValue(config[1]);
		basedao.save(webconfig2);
	}

}
