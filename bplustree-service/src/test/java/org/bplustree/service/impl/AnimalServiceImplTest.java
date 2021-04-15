package org.bplustree.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.bplustree.dao.iface.AnimalDAO;
import org.bplustree.entity.AnimalEntity;
import org.bplustree.entity.enums.AnimalType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AnimalServiceImplTest {
 
	/*private static List<AnimalEntity> animals;
	
	@Mock
	private AnimalDAO animalRepository;
	
	@InjectMocks
	private AnimalServiceImpl animalServices;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		animals = new ArrayList<AnimalEntity>();
		animals.add(new AnimalEntity(1,"Gallo", AnimalType.valueOf("AVE")));
		animals.add(new AnimalEntity(2,"Elefante", AnimalType.valueOf("MAMIFERO")));
		animals.add(new AnimalEntity(3,"Araña", AnimalType.valueOf("INSECTO")));
	}

	@Test
	final void testGetAnimal() throws Exception {
		int idForExistingAnimal = 1;
		int idForNonExistingAnimal = 17;
		
		Mockito.when(animalRepository.findById(idForNonExistingAnimal)).thenReturn(null);
		Mockito.when(animalRepository.findById(idForExistingAnimal)).thenReturn(animals.get(0));
		
		assertThat(animalServices.getAnimal(idForExistingAnimal)).isInstanceOf(AnimalEntity.class);
		assertThat(animalServices.getAnimal(idForNonExistingAnimal)).isNull();
	}

	@Test
	final void testGetAllAnimals() {
		fail("Not yet implemented");
	}

	@Test
	final void testAddAnimal() {
		fail("Not yet implemented");
	}*/

}
