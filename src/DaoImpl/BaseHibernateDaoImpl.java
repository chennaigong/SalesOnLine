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
     * ����ָ��ʵ����  
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
     * ɾ��ָ��ʵ��  
     * @param entityobj  
     */  
    public void delete(T entity) {   
        try {   
            getHibernateTemplate().delete(entity);   
        } catch (RuntimeException e) {   
            System.out.println("ɾ��ʵ���쳣"+e);   
            throw e;   
        }   
    }   
       
    /**  
     * ���»򱣴�ָ��ʵ��  
     * @param entity ʵ����  
     */  
    public void saveOrUpdate(T entity) {   
        try {   
            getHibernateTemplate().saveOrUpdate(entity);   
        } catch (RuntimeException e) {   
        	System.out.println("���»򱣴�ʵ���쳣"+e);   
            throw e;   
        }   
    }   
  
    /**  
     * ����ָ��IDʵ�������  
     *   
     * @param entityClass  
     *            ʵ��Class  
     * @param id  
     *            ʵ��ID  
     * @return ʵ�����  
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
     * ����ָ�����Ե�ʵ�弯��  
     *   
     * @param entityClass  
     *            ʵ��  
     * @param propertyName  
     *            ������  
     * @param value  
     *            ����  
     * @return ʵ�弯��  
     */  
    @SuppressWarnings("unchecked")   
    public List<T> findByProperty(Class<T> entityClass, String propertyName,   
            Object value) {   
        try {   
            String queryStr = "from " + entityClass.getName()   
                    + " as model where model." + propertyName + "=?";              
            return getHibernateTemplate().find(queryStr, value);   
        } catch (RuntimeException e) {   
        	System.out.println("����ָ������ʵ�弯���쳣��������" +propertyName+e);   
            throw e;   
        }   
    }   
    
    /**  
     * ����ָ�����Ե�ʵ�弯��  
     *   
     * @param entityClass  
     *            ʵ��  
     * @param propertyName  
     *            ������  
     * @param value  
     *            ����  
     * @return ʵ�弯��  
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
        	System.out.println("����ָ������ʵ�弯���쳣��������" +propertyName+e);   
            throw e;   
        }   
    }   
    
    /**  
     * ��������ֵ��ͬ�ļ���
     *   
     * @param entityClass  
     *            ʵ��  
     * @param propertyName  
     *            ������  
     * @param value  
     *            ����  
     * @return ʵ�弯��  
     */  
    @SuppressWarnings("unchecked")   
    public List<T> findByDifferent(Class<T> entityClass, String propertyName) {   
        try {   
            String queryStr = "select distinct model."+propertyName+" from " + entityClass.getName()   
                    + " as model";              
            return getHibernateTemplate().find(queryStr);   
        } catch (RuntimeException e) {   
        	System.out.println("����ָ������ʵ�弯���쳣��������" +propertyName+e);   
            throw e;   
        }   
    } 
  
    /**  
     * ��ѯָ��HQL���ķ�ҳ���ݼ���  
     *   
     * @param hsql  
     *            HQL���  
     * @param firstRow  
     *            ��ʼ��¼��  
     * @param maxRow  
     *            ����¼��  
     * @return ��ҳ���ݼ���  
     * @throws Exception  
     *             �׳��쳣  
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
     * ��ѯȫ������
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
