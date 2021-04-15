package org.bplustree.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.bplustree.entity.PersonEntity;
import org.bplustree.entity.request.PersonRequest;
import org.bplustree.service.PersonService;
import org.bplustree.service.response.FinalResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/person")
@Validated
public class PersonController {
	
	@Autowired PersonService personServices;
	@Autowired FinalResponse finalResponse;
	private HttpHeaders responseHeaders = new HttpHeaders();
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<String> addPerson( @RequestBody(required = true) @Valid PersonRequest person) throws Exception{
		personServices.createPerson(person);
		return finalResponse.getResponse("Se ha creado la persona: " + person.getName() + " con éxito", responseHeaders, HttpStatus.OK).toResponseEntity();
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<String> getPersons() throws Exception{
		
		List<PersonEntity> personsFound = personServices.getAllPersons();
		return finalResponse.getResponse(personsFound, responseHeaders, HttpStatus.OK).toResponseEntity();
	}
	
	@RequestMapping(value="/{person_id}", method = RequestMethod.GET)
	public ResponseEntity<String> getPerson(
			@PathVariable(name="person_id", required=true) Integer personId) throws Exception{
		
		PersonEntity personFound = personServices.getPerson(personId);
		return finalResponse.getResponse(personFound, responseHeaders, HttpStatus.OK).toResponseEntity();
	}
	
	
}
