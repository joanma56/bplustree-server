package org.bplustree.entity.enums.validator;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EnumValidator implements ConstraintValidator<MatchesEnum, CharSequence> {
	private List<String> acceptedValues;
	
	@Override
    public void initialize(MatchesEnum meAnnotation) {
        acceptedValues = Stream.of(meAnnotation.enumClass()
            .getEnumConstants())
            .map(Enum::name)
            .collect(Collectors.toList());
    }
	
	@Override
	public boolean isValid(CharSequence value, ConstraintValidatorContext context) {
		if(value == null){
			return false;
		}
		
		return acceptedValues.contains(value.toString());
	}

}
