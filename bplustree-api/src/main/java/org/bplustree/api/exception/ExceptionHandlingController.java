package org.bplustree.api.exception;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import org.bplustree.service.exception.LogicInConflictException;
import org.bplustree.service.exception.EntityNotFoundException;
import org.bplustree.service.exception.InvalidDataProvidedException;
import org.bplustree.service.exception.RepositoryDAOException;
import org.bplustree.service.response.FinalResponse;

@ControllerAdvice
public class ExceptionHandlingController {

	@Autowired FinalResponse finalResponse; 
    
	//Errores de la lógica de negocio
	@ExceptionHandler({ MethodArgumentNotValidException.class })
    public ResponseEntity<String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Iterator<ObjectError> dataErrors = ex.getBindingResult().getAllErrors().iterator();
        if(dataErrors.hasNext()) {
        	ObjectError objectError = dataErrors.next();
        	String errorMessage = String.format("Datos ingresados son incorrectos: %s : %s", ((FieldError) objectError).getField(),objectError.getDefaultMessage());
			return finalResponse.getResponse(errorMessage, new HttpHeaders(), HttpStatus.BAD_REQUEST).toResponseEntity();
        }else {
    		return finalResponse.getResponse(ex.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST).toResponseEntity();
        }  
    }
	
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<String> handleEntityNotFoundException(final EntityNotFoundException notFoundException, final WebRequest request){
		return finalResponse.getResponse(notFoundException.getMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND).toResponseEntity();
	}
	
	@ExceptionHandler(InvalidDataProvidedException.class)
	public ResponseEntity<String> handleEntityValidationException(final InvalidDataProvidedException validationException, final WebRequest request){
		return finalResponse.getResponse(validationException.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST).toResponseEntity();
	}
	
	@ExceptionHandler(LogicInConflictException.class)
	public ResponseEntity<String> handleLogicInConflicException(final LogicInConflictException conflictException, final WebRequest request){
		return finalResponse.getResponse(conflictException.getMessage(), new HttpHeaders(), HttpStatus.CONFLICT).toResponseEntity();
	}
	
	@ExceptionHandler(RepositoryDAOException.class)
	public ResponseEntity<String> handleRepositoryDaoException(final RepositoryDAOException repoException, final WebRequest request){
		return finalResponse.getResponse(repoException.getMessage(), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR).toResponseEntity();
	}
	
	@ExceptionHandler(IOException.class)
	public ResponseEntity<String> handleIOException(final IOException ioException, final WebRequest webRequest){
		System.out.println(ioException);
		System.out.println(ExceptionUtils.getRootCauseMessage(ioException));
		
		return finalResponse.getResponse(ExceptionUtils.getRootCauseMessage(ioException), new HttpHeaders(), HttpStatus.BAD_REQUEST).toResponseEntity();
	}
	
	//Errores generales del servidor
    /*@ExceptionHandler(value = {InvalidFormatException.class, MismatchedInputException.class})
	public ResponseEntity<String> handleIllegalArgumentException(JsonProcessingException exception) throws IOException {
        return finalResponse.getResponse(exception.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST).toResponseEntity();
	}*/
    
    @ExceptionHandler(value = { NullPointerException.class, IllegalArgumentException.class, IllegalStateException.class })
    public ResponseEntity<String> handleInternalError(final RuntimeException ex, final WebRequest request) {
    	Map<String, String> bodyOfResponse = new HashMap<>();
		bodyOfResponse.put("message", "Error imprevisto en el servidor ejecutando servicio en " + request.getDescription(false));
		bodyOfResponse.put("cause", ex.getMessage());
		bodyOfResponse.put("details", ExceptionUtils.getStackTrace(ex));
        return finalResponse.getResponse(bodyOfResponse, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR).toResponseEntity();
    }
    
    @ExceptionHandler(value = {MethodArgumentTypeMismatchException.class})
    public ResponseEntity<String> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
        String paramName = ex.getName();
        String paramType = ex.getRequiredType().getSimpleName();
        Object currentParamValue = ex.getValue();
        String errorMessage = String.format("El parámetro %s de la petición, debería ser del tipo %s y el valor ingresado fue: %s",paramName, paramType, currentParamValue.toString());
        return finalResponse.getResponse(errorMessage, new HttpHeaders(), HttpStatus.BAD_REQUEST).toResponseEntity();
    }
    
    @ExceptionHandler(value = { Exception.class })
	protected ResponseEntity<String> handleUncaughtException(final Exception ex, final WebRequest request) {
		Map<String, String> bodyOfResponse = new HashMap<>(); 
		bodyOfResponse.put("message", "Error imprevisto en el servidor ejecutando el servicio en " + request.getDescription(false));
		bodyOfResponse.put("cause", ex.getMessage());
		bodyOfResponse.put("details", ExceptionUtils.getStackTrace(ex));
		return finalResponse.getResponse(bodyOfResponse, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR).toResponseEntity();
	}
}
