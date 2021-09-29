package br.com.api.ibyte.infra.crosscutting.ioc.config;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;

import org.modelmapper.ModelMapper;

import io.quarkus.arc.DefaultBean;

@Dependent
public class ModelMapperConfiguration {
    @Produces
    @DefaultBean
    public ModelMapper ModelMapper() {
        return new ModelMapper();
    }
}
