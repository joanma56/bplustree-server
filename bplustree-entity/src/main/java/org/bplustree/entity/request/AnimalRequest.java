package org.bplustree.entity.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.bplustree.entity.enums.AnimalType;
import org.bplustree.entity.enums.validator.MatchesEnum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnimalRequest {
	
	@NotNull
	@Positive
	private Integer id;
	
	@NotBlank
	private String name;
	
	@MatchesEnum(enumClass = AnimalType.class, message="El tipo debe ser uno de los siguientes: MAMIFERO, REPTIL, INSECTO, AVE")
	private String type;
}
