package br.com.api.ibyte.application.base.interfaces.services;

import java.io.Serializable;
import java.util.List;

import br.com.api.ibyte.application.base.dtos.IdentityDto;
import br.com.api.ibyte.domain.base.entities.EntityBase;


public interface IAppServiceBase<TDto extends IdentityDto<?,?>, TEntity extends EntityBase<?>> {
    TDto getById(Object id);
    List<TDto> getAll();
    TDto create(TDto dto);
    TDto update(TDto dto);
    int delete(Object id);
    void commit();
}
