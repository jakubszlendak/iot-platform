package com.jmssolutions.iot.webapp.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by jakub on 19.01.16.
 */
public class EmailValidator implements ConstraintValidator<ValidEmail, String>{

    private Pattern pattern;
    private Matcher matcher;

    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-+]+(.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(.[A-Za-z0-9]+)*(.[A-Za-z]{2,})$";

    public void initialize(ValidEmail validEmail) {

    }

    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return (validateEmail(s));
    }

    private boolean validateEmail(String email){
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
