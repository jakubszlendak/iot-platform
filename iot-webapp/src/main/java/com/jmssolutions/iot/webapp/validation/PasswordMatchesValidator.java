package com.jmssolutions.iot.webapp.validation;

import com.jmssolutions.iot.webapp.DTO.UserDTO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by jakub on 19.01.16.
 */
public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {
    public void initialize(PasswordMatches passwordMatches) {

    }

    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        UserDTO user = (UserDTO) o;
        return user.getPassword().equals(user.getMatchingPassword());
    }
}
