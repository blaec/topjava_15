package ru.javawebinar.topjava.web.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.javawebinar.topjava.service.UserService;
import ru.javawebinar.topjava.to.UserTo;

@Component
public class EmailValidator implements Validator {

    private final UserService service;

    @Autowired
    public EmailValidator(UserService service) {
        this.service = service;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return UserTo.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserTo userTo = (UserTo) target;
        try {
            if (service.getByEmail(userTo.getEmail()) != null) {
                errors.rejectValue("email", "", "User with this email already exists");
            }
        } catch (Exception ignored) {
        }
    }
}
