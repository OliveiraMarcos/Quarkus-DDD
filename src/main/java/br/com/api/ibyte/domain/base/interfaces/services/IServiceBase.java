package br.com.api.ibyte.domain.base.interfaces.services;

import java.util.List;

public interface IServiceBase<TEntity, TId> {
    TEntity getById(TId id);
    List<TEntity> getAll();
    TEntity create(TEntity entity);
    TEntity update(TEntity entity);
    int delete(TId id);
    void commit();
}
