package br.com.api.ibyte.services.api.controllers;


import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import br.com.api.ibyte.application.auth.dtos.UserDto;
import br.com.api.ibyte.application.auth.interfaces.services.IUserAppService;
import br.com.api.ibyte.application.base.dtos.ResultJson;
import br.com.api.ibyte.application.base.dtos.enums.StatusCodeEnum;
import br.com.api.ibyte.services.base.controllers.BaseController;

@Path("/api/auth")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Inteface of Product", description = "View with endpoints to work product")
public class AuthController extends BaseController{
    @Inject
    IUserAppService service;

    @GET
    @Path("{id}")
    public ResultJson get(@PathParam String id) {
        var dto = service.getById(id);
        var result = new ResultJson();
        result.setCode(StatusCodeEnum.OK_200);
        result.setSuccess(true);
        result.setMessage("Success!");
        result.setResponseObject(dto);
        return result;
    }

    @GET
    public ResultJson getAll() {
        
        try {
            var data = service.getAll();
            return response(StatusCodeEnum.OK_200, data);
        } catch (Exception e) {
            return response(e);
        }
    }


    @POST
    public ResultJson post(UserDto dto) {
        
        try {
            service.create(dto);
            return response(StatusCodeEnum.CREATED_201, dto);
        } catch (Exception e) {
            return response( e);
        }
    }


    @PUT
    public ResultJson put(UserDto dto) {
      
        try {
            service.update(dto);
            return response(StatusCodeEnum.OK_200, dto, "Updated!");
        } catch (Exception e) {
            return response( e);
        }
    }

    @DELETE
    @Path("{id}")
    public ResultJson delete(@PathParam String id) {
        
        try {
            service.delete(id);
            return response(StatusCodeEnum.NO_CONTENT_204, null, "Deleted!");
        } catch (Exception e) {
            return response( e);
        }
    }

}
