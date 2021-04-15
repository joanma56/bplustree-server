package org.bplustree.service;

import java.util.List;

import org.bplustree.entity.PersonEntity;
import org.bplustree.entity.request.PersonRequest;
import org.springframework.stereotype.Service;

public interface PersonService {

	PersonEntity getPerson(Integer id) throws Exception;
	List<PersonEntity> getAllPersons() throws Exception;
	void createPerson(PersonRequest request) throws Exception;
}
