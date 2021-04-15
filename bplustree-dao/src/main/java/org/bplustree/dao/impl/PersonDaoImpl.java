package org.bplustree.dao.impl;

import java.io.FileNotFoundException;
import java.util.List;

import org.bplustree.dao.datastructure.BPlusTree;
import org.bplustree.dao.iface.PersonDAO;
import org.bplustree.dao.serializer.FilesParser;
import org.bplustree.entity.PersonEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PersonDaoImpl implements PersonDAO {
	
	private FilesParser IOdatabase;
	private BPlusTree dbInMemory;
	private final String tableName = "PersonTable";
	
	@Autowired
	public PersonDaoImpl() {
		IOdatabase = new FilesParser();
	}
	
	@Override
	public void save(PersonEntity newPerson) throws Exception {
		if(dbInMemory == null) {
			initializeBPlusTree(tableName);
		}
		dbInMemory.insert(newPerson.getId(), newPerson);
		IOdatabase.writeObjectToFile(dbInMemory, tableName);
	}

	@Override
	public PersonEntity findById(Integer id) throws Exception {
		if(dbInMemory == null) {
			initializeBPlusTree(tableName);
		}
		return (PersonEntity) dbInMemory.search(id);
	}

	@Override
	public List<PersonEntity> findAll() throws Exception {
		if(dbInMemory == null) {
			initializeBPlusTree(tableName);
		}
		return (List<PersonEntity>)(List<?>)dbInMemory.search(0, 2147483647);
	}
	
	private void initializeBPlusTree(String treeName) throws Exception {
		try {
			dbInMemory = IOdatabase.readObjectFromFile(treeName);
		} catch (Exception e) {
			if(e.getClass().equals(FileNotFoundException.class)) {
				dbInMemory = new BPlusTree(3);
			}else {
				throw e;
			}
		}
	}

}
