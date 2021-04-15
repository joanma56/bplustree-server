package org.bplustree.entity.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonRequest {
	
	@NotNull
	@Positive
	private Integer identification;
	
	@NotBlank
	private String name;
	
	@NotBlank
	@Email
	private String email;
}
