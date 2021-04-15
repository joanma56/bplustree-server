package org.bplustree.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.bplustree.entity.AnimalEntity;
import org.bplustree.entity.PersonEntity;
import org.bplustree.entity.request.AnimalRequest;
import org.bplustree.entity.request.PersonRequest;
import org.bplustree.service.AnimalService;
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
@RequestMapping("/animal")
@Validated
public class AnimalController {
	
	@Autowired AnimalService animalServices;
	@Autowired FinalResponse finalResponse;
	private HttpHeaders responseHeaders = new HttpHeaders();
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<String> addAnimal( @RequestBody(required = true) @Valid AnimalRequest animal) throws Exception{
		animalServices.addAnimal(animal);
		return finalResponse.getResponse("Se ha creado el animal: " + animal.getName() + " con éxito", responseHeaders, HttpStatus.CREATED).toResponseEntity();
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<String> getAnimals() throws Exception{
		
		List<AnimalEntity> animalsFound = animalServices.getAllAnimals();
		return finalResponse.getResponse(animalsFound, responseHeaders, HttpStatus.OK).toResponseEntity();
	}
	
	@RequestMapping(value="/{animal_id}", method = RequestMethod.GET)
	public ResponseEntity<String> getAnimal(
			@PathVariable(name="animal_id", required=true) Integer animalId) throws Exception{
		
		AnimalEntity animalFound = animalServices.getAnimal(animalId);
		return finalResponse.getResponse(animalFound, responseHeaders, HttpStatus.OK).toResponseEntity();
	}
	
	
}
