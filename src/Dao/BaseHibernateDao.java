package Dao;

import java.io.Serializable;
import java.util.List;

public interface BaseHibernateDao<T, ID extends Serializable> {
	public void save(T entity);
	public void delete(T entity);
	public void saveOrUpdate(T entity);
	public T findById(Class<T> entityClass, ID id);
	public List<T> findAll(Class<T> entityClass);
	public List<T> findByDifferent(Class<T> entityClass, String propertyName);
	public List<T> findByProperty(Class<T> entityClass, String propertyName,   
            Object value);
	public List<T> findByProperty(Class<T> entityClass, String propertyName,   
            Object value,String order,String DESCORASC);
	public List<T> findByPropertys(Class<T> entityClass, String[] propertyName,   
	        Object[] value);
	public List<T> findByPage(final String hsql, final int firstRow,   
            final int maxRow);
	public List<T> findAll(Class<T> entityClass,String order,String DESCORASC);
}
