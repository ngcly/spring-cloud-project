package com.cn.user.repository;

import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.transform.Transformers;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * @author chenning
 * @Classname BaseRepository
 * @Description 自定义sql 查询
 * @Date 2019/7/2 20:51
 */
public class BaseRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public void save(Object entity) {
        entityManager.persist(entity);
    }

    public void update(Object entity) {
        entityManager.merge(entity);
    }

    public <T> void delete(Class<T> entityClass, Object entityid) {
        delete(entityClass, new Object[] { entityid });
    }

    public <T> void delete(Class<T> entityClass, Object[] entityids) {
        for (Object id : entityids) {
            entityManager.remove(entityManager.getReference(entityClass, id));
        }
    }
    private Query createNativeQuery(String sql, Object... params) {
        Query q = entityManager.createNativeQuery(sql);
        if (params != null && params.length > 0) {
            for (int i = 0; i < params.length; i++) {
                // 与Hiberante不同,jpa query从位置1开始
                q.setParameter(i + 1, params[i]);
            }
        }
        return q;
    }

    public <T> List<T> nativeQueryList(String nativeSql, Object... params) {
        Query q = createNativeQuery(nativeSql, params);
        q.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.TO_LIST);
        return q.getResultList();
    }

    public <T> List<T> nativeQueryListModel(Class<T> resultClass,
                                            String nativeSql, Object... params) {
        Query q = createNativeQuery(nativeSql, params);
        q.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.aliasToBean(resultClass));
        return q.getResultList();
    }

    public <T> List<T> nativeQueryListMap(String nativeSql, Object... params) {
        Query q = createNativeQuery(nativeSql, params);
        q.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        return q.getResultList();
    }
}
