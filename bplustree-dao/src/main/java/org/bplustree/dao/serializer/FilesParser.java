package org.bplustree.dao.serializer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.bplustree.dao.datastructure.BPlusTree;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FilesParser {
	
	final String dbBaseFolder = "database/";
	
	public void writeObjectToFile(BPlusTree objectToWrite, String tableName) throws Exception {
		File dbBaseDirectory = new File(dbBaseFolder);
		if(!dbBaseDirectory.exists()) {
			dbBaseDirectory.mkdirs();
		}
		
		File databaseFile = new File(dbBaseFolder + tableName);
		FileOutputStream outputStream = new FileOutputStream(databaseFile);
		ObjectOutputStream writer = new ObjectOutputStream(outputStream);
		writer.writeObject(objectToWrite);
		writer.close();
		outputStream.close();
		
	}
	
	public BPlusTree readObjectFromFile(String tableName) throws Exception {
		File dbBaseDirectory = new File(dbBaseFolder);
		if(!dbBaseDirectory.exists()) {
			dbBaseDirectory.mkdirs();
		}
		
		File databaseFile = new File(dbBaseFolder + tableName);
		FileInputStream inputStream = new FileInputStream(databaseFile);
		ObjectInputStream reader = new ObjectInputStream(inputStream);
		Object readedObject = reader.readObject();
		reader.close();
		inputStream.close();
		return (BPlusTree)readedObject;
	}
}
