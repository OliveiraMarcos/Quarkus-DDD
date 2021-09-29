package br.com.api.ibyte.application.base.services;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;



import com.google.common.reflect.TypeToken;

import org.modelmapper.ModelMapper;

import br.com.api.ibyte.application.base.dtos.IdentityDto;
import br.com.api.ibyte.application.base.interfaces.services.IAppServiceBase;
import br.com.api.ibyte.domain.base.entities.EntityBase;
import br.com.api.ibyte.domain.base.interfaces.services.IServiceBase;
public class AppServiceBase<TDto extends IdentityDto<TDto,?>, TEntity extends EntityBase<?>> implements IAppServiceBase<TDto, TEntity>  {
    
    private final IServiceBase<TEntity, Object> _serviceBase;
    
    private final ModelMapper _mapper;

    protected AppServiceBase() {
        _serviceBase = null;
        _mapper = null;
    }

    public AppServiceBase(IServiceBase<TEntity, ?> serviceBase, ModelMapper mapper) {
        this._serviceBase = (IServiceBase<TEntity, Object>) serviceBase;
        this._mapper = mapper;
    }

    public <D> D map(Object from,Class<D> typeTo){
        return _mapper.map(from, typeTo);
    }
    public <D> D map(Object from, Type typeTo) {
        return _mapper.map(from, typeTo);
    }
    public <D, T> List<D> mapAll(final Collection<T> entityList, Class<D> outCLass) {
        return entityList.stream()
                .map(entity -> map(entity, outCLass))
                .collect(Collectors.toList());
    }

    @Override
    public TDto getById(Object id) {
        var entity = this._serviceBase.getById(id);
        if(entity == null){return null;}
        TDto dto = map(entity, new TypeToken<TDto>(getClass()){}.getType());
        return dto;
    }

    @Override
    public List<TDto> getAll() {
        var entitys = this._serviceBase.getAll();
        if(entitys == null){return null;}
        List<TDto> dtos = map(entitys, new TypeToken<List<TDto>>(getClass()){}.getType());
        return dtos;
    }

    @Override
    public TDto create(TDto dto) {
        TEntity entity = map(dto, new TypeToken<TEntity>(getClass()){}.getType());
        entity = this._serviceBase.create(entity);
        dto = map(entity, new TypeToken<TDto>(getClass()){}.getType());
        return dto;
    }

    @Override
    public TDto update(TDto dto) {
        TEntity entity = map(dto, new TypeToken<TEntity>(getClass()){}.getType());
        entity = this._serviceBase.update(entity);
        dto = map(entity, new TypeToken<TDto>(getClass()){}.getType());
        return dto;
    }

    @Override
    public int delete(Object id) {
        return this._serviceBase.delete(id);
    }

    @Override
    public void commit() {
        this._serviceBase.commit();
    }
}
