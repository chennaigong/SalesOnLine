package ServiceImpl;


import java.io.Serializable;

import Dao.BaseHibernateDao;
import Entity.Example;
import Service.LoginService;

public class LoginServiceImpl implements LoginService{
	
	BaseHibernateDao<Example, Serializable> basedao;
	
	public BaseHibernateDao<Example, Serializable> getBasedao() {
		return basedao;
	}

	public void setBasedao(BaseHibernateDao<Example, Serializable> basedao) {
		this.basedao = basedao;
	}

	public void add(String username) {
		Example example=new Example();
		example.setExpName(username);
		basedao.save(example);
	}

}
