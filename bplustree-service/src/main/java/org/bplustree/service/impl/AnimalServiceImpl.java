package org.bplustree.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.bplustree.dao.iface.AnimalDAO;
import org.bplustree.entity.AnimalEntity;
import org.bplustree.entity.enums.AnimalType;
import org.bplustree.entity.request.AnimalRequest;
import org.bplustree.service.AnimalService;
import org.bplustree.service.exception.EntityNotFoundException;
import org.bplustree.service.exception.LogicInConflictException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnimalServiceImpl implements AnimalService {

	@Autowired AnimalDAO animalRepository;
	
	@Override
	public AnimalEntity getAnimal(Integer id) throws Exception {
		AnimalEntity animalFound = animalRepository.findById(id);
		if(animalFound == null) {
			throw new EntityNotFoundException("No fue encontrado animal con identificación: " + Integer.toString(id));
		}
		return animalFound;
	}

	@Override
	public List<AnimalEntity> getAllAnimals() throws Exception {
		List<AnimalEntity> animalsFound = new ArrayList<AnimalEntity>();
		animalsFound = animalRepository.findAll();
		return animalsFound;
	}

	@Override
	public void addAnimal(AnimalRequest reqForNewAnimal) throws Exception {
		AnimalEntity newAnimal = new AnimalEntity(
				reqForNewAnimal.getId(),
				reqForNewAnimal.getName(),
				AnimalType.valueOf(reqForNewAnimal.getType()));
		
		AnimalEntity animalInConflict = animalRepository.findById(reqForNewAnimal.getId());
		if(animalInConflict != null) {
			throw new LogicInConflictException("Ya existe un animal con el id: " + Integer.toString(reqForNewAnimal.getId()));
		}
		
		animalRepository.save(newAnimal);
	}


}
