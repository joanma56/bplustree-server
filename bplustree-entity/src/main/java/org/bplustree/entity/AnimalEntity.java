package org.bplustree.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.bplustree.entity.enums.AnimalType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
public class AnimalEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3702713284978192481L;
	private Integer id;
	private String name;
	private AnimalType type;
}
