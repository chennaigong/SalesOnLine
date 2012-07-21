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

/**  
* DAO�������� ��DAO��ʵ����ͨ�õ����ݲ���  
*   
* @author ����  
*   
* @param <T>  
*            POJOʵ�����  
* @param <ID>  
*            ID  
*/  
public class BaseHibernateDaoImpl<T, ID extends Serializable> extends HibernateDaoSupport implements BaseHibernateDao<T, Serializable>  {   
  
  
    /**  
     * ����ָ��ʵ����  
     *   
     * @param entityobj  
     *            ʵ����  
     */  
    public void save(T entity) {           
        try {   
            getHibernateTemplate().save(entity);   
        } catch (RuntimeException e) {   
            throw e;   
        }   
    }   
  
    /**  
     * ɾ��ָ��ʵ��  
     *   
     * @param entityobj  
     *            ʵ����  
     */  
    public void delete(T entity) {   
        try {   
            getHibernateTemplate().delete(entity);   
        } catch (RuntimeException e) {   
            logger.error("ɾ��ʵ���쳣", e);   
            throw e;   
        }   
    }   
       
    /**  
     * ���»򱣴�ָ��ʵ��  
     *   
     * @param entity ʵ����  
     */  
    public void saveOrUpdate(T entity) {   
        try {   
            getHibernateTemplate().saveOrUpdate(entity);   
        } catch (RuntimeException e) {   
            logger.error("���»򱣴�ʵ���쳣", e);   
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
            logger.error("����ָ������ʵ�弯���쳣��������" + propertyName, e);   
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
            throw e;   
        }   
    }

	

	
       
//    /**  
//     * ��Spring�������л�ȡ�������  
//     * �˷������ܴ����̲߳������⣨���⣩  
//     *   
//     * @param context Spring������  
//     * @return �������   
//     */  
//    @SuppressWarnings("unchecked")   
//    public static BaseHibernateDAO getFromApplicationContext(WebApplicationContext context) {   
//        return (BaseHibernateDAO)context.getBean("BaseHibernateDAO");   
//    }
}  
