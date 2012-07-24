package ServiceImpl;

import java.io.Serializable;

import Dao.BaseHibernateDao;

public class BaseServiceImpl<T> {
	
	protected BaseHibernateDao<T, Serializable> basedao;

	public BaseHibernateDao<T, Serializable> getBasedao() {
		return basedao;
	}

	public void setBasedao(BaseHibernateDao<T, Serializable> basedao) {
		this.basedao = basedao;
	}
	
}
