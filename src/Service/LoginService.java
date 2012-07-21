package Service;

import java.io.Serializable;

import Dao.BaseHibernateDao;
import Entity.Example;

public interface LoginService {
	public void setBasedao(BaseHibernateDao<Example, Serializable> basedao);
	public void add(String username);
}
