package org.bplustree.dao.iface;

import java.util.List;

import org.bplustree.entity.AnimalEntity;
import org.springframework.stereotype.Repository;

public interface AnimalDAO {
	
	void save(AnimalEntity newAnimal) throws Exception;
	AnimalEntity findById(Integer id) throws Exception;
	List<AnimalEntity> findAll() throws Exception;
}
