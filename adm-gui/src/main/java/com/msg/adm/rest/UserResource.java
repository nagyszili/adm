package com.msg.adm.rest;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.msg.adm.business.UsersService;
import com.msg.adm.business.data.User;
import com.msg.adm.common.exception.AdministrationEntityNotFoundException;
import com.msg.adm.rest.AbstractResource;
import com.msg.adm.rest.ValidationResponse;

/**
 * User Resource for users manipulation.
 *
 */
@Stateless
@Produces(MediaType.APPLICATION_JSON)
@Path("user")
public class UserResource extends AbstractResource{

	@Inject
	private UsersService userService;
	
	/**
	 * Returns the user after searching it for after it's Id.
	 * 
	 * @param id
	 * 
	 * @return Response
	 * 			200 OK
	 * 			400 Bad Request
	 * 			404 Not Found
	 * 			500 Internal Error
	 */
	@GET
    @Path("{id}")
    public Response getUser(@PathParam("id") String id) {

        // -- Resource Validation
        ValidationResponse vResponse = validateResourcePathParamAsLong(id);
        if (!vResponse.isValid()) {
            return handleResourceValidation(vResponse);
        }

        try {
        	User user = userService.getUser(new User(Long.valueOf(id)));
        	return Response.ok().entity(user).cacheControl(getNoCache()).build();
        } catch (AdministrationEntityNotFoundException e) {
            return handleException(e);
        }
    }
	
	
    
}
