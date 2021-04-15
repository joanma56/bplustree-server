package org.bplustree.service;

import java.util.List;

import org.bplustree.entity.AnimalEntity;
import org.bplustree.entity.request.AnimalRequest;

public interface AnimalService {

	AnimalEntity getAnimal(Integer id) throws Exception;
	List<AnimalEntity> getAllAnimals() throws Exception;
	void addAnimal(AnimalRequest requestForAnimal) throws Exception;
}
