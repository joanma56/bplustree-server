package org.bplustree.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PersonEntity implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7101476515197180465L;
	private Integer id;
	private String name;
	private String email;
	
}
