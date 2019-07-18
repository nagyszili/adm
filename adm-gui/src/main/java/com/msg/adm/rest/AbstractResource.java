package com.msg.adm.rest;

import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.Response;

public abstract class AbstractResource {

	/**
     * Validiert das Path Parameter des Resource.
     *
     * @param pathParam
     *
     * @return ValidationResponse
     *
     */
    protected ValidationResponse validateResourcePathParamAsLong(String pathParam) {
        // -- Validate nach null oder empty
        if (pathParam == null || pathParam.isEmpty()) {
            return createValidationresponse(false, Response.Status.BAD_REQUEST, "Incorrect Path Param");
        }

        // -- Validate convert
        try {
            Long.valueOf(pathParam);
        } catch (NumberFormatException nfe) {
            return createValidationresponse(false, Response.Status.BAD_REQUEST, "Incorrect Path Param");
        }

        return new ValidationResponse(true);
    }
    
    /**
     * Erstellt ein {@link ValidationResponse} anhand der uebergebene Parametern.
     *
     * @param valid
     * @param status
     * @param message
     *
     * @return ValidationResponse
     */
    protected ValidationResponse createValidationresponse(boolean valid, Response.Status status, String message) {
        ValidationResponse vResponse = new ValidationResponse(valid);
        vResponse.setStatus(status);
        vResponse.setMessage(message);

        return vResponse;
    }
	
    /**
     * Erzeugt ein Response Objekt aus den gegebenen Parametern.
     *  Benutz wenn ein Validierungsfehler auf Resource Seite aufgetreten ist.
     *
     * @param vResponse
     *
     * @return Response
     */
    protected Response handleResourceValidation(ValidationResponse vResponse) {
        return Response.status(vResponse.getStatus()).entity(vResponse).cacheControl(getNoCache()).build();
    }
	
    /**
     * Erzeugt ein Response Objekt aus den gegebenen Parametern.
     *  Benutz wenn ein Validierungsfehler auf Backend(Geschaeftslogik) Seite aufgetreten ist.
     *
     * @param e
     *
     * @return Response
     */
    protected Response handleException(Exception e) {
    	ValidationResponse validationResponse = new ValidationResponse();
    	validationResponse.setValid(false);
    	validationResponse.setStatus(Response.Status.BAD_REQUEST);
    	validationResponse.setMessage(e.getMessage());
    	
        return Response.status(validationResponse.getStatus()).entity(validationResponse).cacheControl(getNoCache()).build();
    }
    
    /**
     * Liefert ein CacheControl(An abstraction for the value of a HTTP Cache-Control response header) zurueck,
     *  der passend eingerichtet wurde.
     *
     * @return CacheControl
     */
    protected CacheControl getNoCache() {
        CacheControl control = new CacheControl();
        control.setNoCache(true);
        control.setNoStore(true);
        return control;
    }
}
