package org.bplustree.dao.iface;

import java.util.List;

import org.bplustree.entity.PersonEntity;

public interface PersonDAO {
	
	void save(PersonEntity newPerson) throws Exception;
	PersonEntity findById(Integer id) throws Exception;
	List<PersonEntity> findAll() throws Exception;
	
}
