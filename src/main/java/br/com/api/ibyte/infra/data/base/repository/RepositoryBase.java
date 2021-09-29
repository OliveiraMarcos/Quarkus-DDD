package br.com.api.ibyte.infra.data.base.repository;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.transaction.Transactional;

import org.hibernate.Session;

import br.com.api.ibyte.domain.base.entities.EntityBase;
import br.com.api.ibyte.domain.base.interfaces.repository.IRepositoryBase;


public abstract class RepositoryBase<TEntity extends EntityBase<TIdentity>, TIdentity extends Serializable> implements IRepositoryBase<TEntity,TIdentity> {

    protected final EntityManager em;
    protected final Session session;
    private Class<TEntity> persistedClass;
    private EntityTransaction t;

    protected RepositoryBase() {
        super();
        em = null;
        session = null;
    }

    public RepositoryBase(EntityManager em, Class<TEntity> persistedClass) {
        // final ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass(); 
        this.persistedClass = persistedClass; //(Class<TEntity>) (type).getActualTypeArguments()[0];
        this.em = em;
        this.session = em.unwrap(Session.class);
    }

    @Override
    public TEntity getById(TIdentity id) {
        return getByIdType(id);
    }

    @Override
    public List<TEntity> getAll() {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<TEntity> query = builder.createQuery(persistedClass);
        query.from(persistedClass);
        return em.createQuery(query).getResultList();
    }

    @Override
    @Transactional
    public void add(TEntity entity) {
        var id =(TIdentity) session.save(entity);
        entity.setId(id);
    }

    @Override
    @Transactional
    public void attach(TEntity entity) {
        session.update(entity);
        
    }

    @Override
    public void remove(TIdentity id) {
        removeByIdType(id);
    }

    @Override
    @Transactional
    public void commit() {
        saveChanges();
        if(t != null && t.isActive()){
            t.commit();
        }
    }

    @Override
    public void beginTransaction() {
        t = em.getTransaction();
        t.begin();
    }

    @Override
    public void rollback() throws Exception {
        if(t != null && t.isActive()){
            t.rollback();
        }else{
            throw new Exception("Transaction not found! Use beginTransaction to start once.");
        }
    }

    @Override
    public <TId extends Serializable> TEntity getByIdType(TId id) {
        return session.find(persistedClass, id);
    }

    @Override
    @Transactional
    public <TId extends Serializable> void removeByIdType(TId id) {
        var entity = this.getByIdType(id);
        session.delete(entity);
    }
    
    @Override
    @Transactional
    public void saveChanges() {
        session.flush();
    }
}
