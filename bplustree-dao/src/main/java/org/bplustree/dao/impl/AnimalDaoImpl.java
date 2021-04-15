package org.bplustree.dao.impl;

import java.io.FileNotFoundException;
import java.util.List;

import org.bplustree.dao.datastructure.BPlusTree;
import org.bplustree.dao.iface.AnimalDAO;
import org.bplustree.dao.serializer.FilesParser;
import org.bplustree.entity.AnimalEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AnimalDaoImpl implements AnimalDAO {
	
	private FilesParser IOdatabase;
	private BPlusTree dbInMemory;
	private final String tableName = "AnimalTable";
	
	@Autowired
	public AnimalDaoImpl() {
		IOdatabase = new FilesParser();
	}
	
	@Override
	public void save(AnimalEntity newAnimal) throws Exception {
		if(dbInMemory == null) {
			initializeBPlusTree(tableName);
		}
		dbInMemory.insert(newAnimal.getId(), newAnimal);
		IOdatabase.writeObjectToFile(dbInMemory, tableName);
	}

	@Override
	public AnimalEntity findById(Integer id) throws Exception {
		if(dbInMemory == null) {
			initializeBPlusTree(tableName);
		}
		return (AnimalEntity) dbInMemory.search(id);
	}

	@Override
	public List<AnimalEntity> findAll() throws Exception {
		if(dbInMemory == null) {
			initializeBPlusTree(tableName);
		}
		return (List<AnimalEntity>)(List<?>)dbInMemory.search(0, 2147483647);
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
