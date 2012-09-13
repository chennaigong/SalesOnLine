package DaoImpl;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import Dao.BaseHibernateDao;

public class BaseHibernateDaoImpl<T, ID extends Serializable> extends HibernateDaoSupport implements BaseHibernateDao<T, Serializable>  {   
  
  
    /**  
     * 保存指定实体类  
     * @param entityobj  
     */  
    public void save(T entity) {
        try {
            getHibernateTemplate().save(entity);
        } catch (RuntimeException e) {
        	System.out.println(e);
        }   
    }   
  
    /**  
     * 删除指定实体  
     * @param entityobj  
     */  
    public void delete(T entity) {   
        try {   
            getHibernateTemplate().delete(entity);   
        } catch (RuntimeException e) {   
            System.out.println("删除实体异常"+e);   
            throw e;   
        }   
    }   
       
    /**  
     * 更新或保存指定实体  
     * @param entity 实体类  
     */  
    public void saveOrUpdate(T entity) {   
        try {   
            getHibernateTemplate().saveOrUpdate(entity);   
        } catch (RuntimeException e) {   
        	System.out.println("更新或保存实体异常"+e);   
            throw e;   
        }   
    }   
  
    /**  
     * 查找指定ID实体类对象  
     *   
     * @param entityClass  
     *            实体Class  
     * @param id  
     *            实体ID  
     * @return 实体对象  
     */  
    @SuppressWarnings("unchecked")
	public T findById(Class<T> entityClass, Serializable id) {
    	 try {   
	           return (T) getHibernateTemplate().get(entityClass, id);   
	       } catch (RuntimeException e) { 
	    	   System.out.println(e);
	           throw e;   
	       }   
	}
    /**  
     * 查找指定属性的实体集合  
     *   
     * @param entityClass  
     *            实体  
     * @param propertyName  
     *            属性名  
     * @param value  
     *            条件  
     * @return 实体集合  
     */  
    @SuppressWarnings("unchecked")   
    public List<T> findByProperty(Class<T> entityClass, String propertyName,   
            Object value) {   
        try {   
            String queryStr = "from " + entityClass.getName()   
                    + " as model where model." + propertyName + "=?";              
            return getHibernateTemplate().find(queryStr, value);   
        } catch (RuntimeException e) {   
        	System.out.println("查找指定条件实体集合异常，条件：" +propertyName+e);   
            throw e;   
        }   
    }   
    
    /**  
     * 查找指定属性的实体集合  
     *   
     * @param entityClass  
     *            实体  
     * @param propertyName  
     *            属性名  
     * @param value  
     *            条件  
     * @return 实体集合  
     */  
    @SuppressWarnings("unchecked")   
    public List<T> findByPropertys(Class<T> entityClass, String[] propertyName,   
            Object[] value) {   
        try {   
            String queryStr = "from " + entityClass.getName()   
                    + " as model where ";
            for(int i=0;i<propertyName.length;i++)
            {
            	queryStr+="model."+propertyName[i]+"=?";
            	if(i+1<propertyName.length)
            	{
            		queryStr+=" and ";
            	}
            }
            return getHibernateTemplate().find(queryStr, value);   
        } catch (RuntimeException e) {   
        	System.out.println("查找指定条件实体集合异常，条件：" +propertyName+e);   
            throw e;   
        }   
    }   
    
    /**  
     * 查找属性值不同的集合
     *   
     * @param entityClass  
     *            实体  
     * @param propertyName  
     *            属性名  
     * @param value  
     *            条件  
     * @return 实体集合  
     */  
    @SuppressWarnings("unchecked")   
    public List<T> findByDifferent(Class<T> entityClass, String propertyName) {   
        try {   
            String queryStr = "select distinct model."+propertyName+" from " + entityClass.getName()   
                    + " as model";              
            return getHibernateTemplate().find(queryStr);   
        } catch (RuntimeException e) {   
        	System.out.println("查找指定条件实体集合异常，条件：" +propertyName+e);   
            throw e;   
        }   
    } 
  
    /**  
     * 查询指定HQL语句的分页数据集合  
     *   
     * @param hsql  
     *            HQL语句  
     * @param firstRow  
     *            开始记录号  
     * @param maxRow  
     *            最大记录号  
     * @return 分页数据集合  
     * @throws Exception  
     *             抛出异常  
     */  
    @SuppressWarnings("unchecked")   
    public List<T> findByPage(final String hsql, final int firstRow,   
            final int maxRow) {        
        try {   
            return getHibernateTemplate().executeFind(new HibernateCallback() {   
                public Object doInHibernate(Session s)   
                        throws HibernateException, SQLException {   
                    Query query = s.createQuery(hsql);   
                    query.setFirstResult(firstRow);   
                    query.setMaxResults(maxRow);   
                    List list = query.list();   
                    return list;   
                }   
            });   
        } catch (RuntimeException e) {   
        	System.out.println(e);
            throw e;   
        }   
    }
    /**
     * 查询全部数据
     */
	@SuppressWarnings("unchecked")
	public List<T> findAll(Class<T> entityClass) {
		try {   
            String queryStr = "select model from " + entityClass.getName()   
                    + " as model";              
            return getHibernateTemplate().find(queryStr);   
        } catch (RuntimeException e) {   
        	System.out.println(e);   
            throw e;   
        }   
	}

	
}  
