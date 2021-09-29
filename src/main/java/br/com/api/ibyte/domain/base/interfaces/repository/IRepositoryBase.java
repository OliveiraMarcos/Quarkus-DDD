package br.com.api.ibyte.domain.base.interfaces.repository;

import java.io.Serializable;
import java.util.List;

import br.com.api.ibyte.domain.base.entities.EntityBase;

public interface IRepositoryBase<TEntity extends EntityBase<TIdentity>, TIdentity extends Serializable> {
    TEntity getById(TIdentity id);
    <TId extends Serializable> TEntity getByIdType(TId id);
    List<TEntity> getAll();
    void add(TEntity entity);
    void attach(TEntity entity);
    void remove(TIdentity id);
    <TId extends Serializable> void removeByIdType(TId id);
    void commit();
    void saveChanges();
    void beginTransaction();
    void rollback() throws Exception;
}
