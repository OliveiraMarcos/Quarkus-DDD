package br.com.api.ibyte.domain.base.services;

import java.io.Serializable;
import java.util.List;

import br.com.api.ibyte.domain.base.entities.EntityBase;
import br.com.api.ibyte.domain.base.interfaces.repository.IRepositoryBase;
import br.com.api.ibyte.domain.base.interfaces.services.IServiceBase;

public class ServiceBase<TEntity extends EntityBase<TId>, TId extends Serializable> implements IServiceBase<TEntity, TId>{

    private final IRepositoryBase<TEntity, TId> _repositoryBase;

    public ServiceBase(IRepositoryBase<TEntity, TId> _repositoryBase) {
        this._repositoryBase = _repositoryBase;
    }

    protected ServiceBase() {
        super();
        this._repositoryBase = null;
    }

    @Override
    public TEntity getById(TId id) {
        return this._repositoryBase.getById(id);
    }

    @Override
    public List<TEntity> getAll() {
        return this._repositoryBase.getAll();
    }

    @Override
    public TEntity create(TEntity entity) {
        this._repositoryBase.add(entity);
        return entity;
    }

    @Override
    public TEntity update(TEntity entity) {
        this._repositoryBase.attach(entity);
        return entity;
    }

    @Override
    public int delete(TId id) {
        try {
            this._repositoryBase.remove(id);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public void commit() {
        this._repositoryBase.commit();        
    }

    
}
