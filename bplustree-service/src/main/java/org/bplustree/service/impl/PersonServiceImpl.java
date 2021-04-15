package org.bplustree.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.bplustree.dao.impl.PersonDaoImpl;
import org.bplustree.entity.PersonEntity;
import org.bplustree.entity.request.PersonRequest;
import org.bplustree.service.PersonService;
import org.bplustree.service.exception.EntityNotFoundException;
import org.bplustree.service.exception.LogicInConflictException;
import org.bplustree.service.response.FinalResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl implements PersonService {

	@Autowired PersonDaoImpl personRepository;
	
	@Override
	public PersonEntity getPerson(Integer id) throws Exception {
		PersonEntity personFound = personRepository.findById(id);
		if(personFound == null) {
			throw new EntityNotFoundException("No fue encontrada persona con identificación: " + id);
		}
		return personFound;
	}

	@Override
	public List<PersonEntity> getAllPersons() throws Exception {
		List<PersonEntity> personsFound = new ArrayList<PersonEntity>();
		personsFound = personRepository.findAll();
		return personsFound;
	}

	@Override
	public void createPerson(PersonRequest requestForNewPerson) throws Exception {
		
		PersonEntity newPerson = new PersonEntity(
				requestForNewPerson.getIdentification(),
				requestForNewPerson.getName(),
				requestForNewPerson.getEmail());
		
		PersonEntity personInConflict = personRepository.findById(newPerson.getId());
		if(personInConflict != null) {
			throw new LogicInConflictException("Ya existe un usuario con el id: " + Integer.toString(newPerson.getId()));
		}

		personRepository.save(newPerson);
	}

}
