package thymeleafexamples.stsm.business.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import thymeleafexamples.stsm.business.entities.User;

/**
 * @ Author     ?xzp.
 * @ Date       ?Created in 2:12 PM 11/09/2018
 * @ Description?${description}
 */
@Component
public final class UserConversionService implements Converter<String, User> {

    private UserService userService = new UserService();

    @Override
    public User convert(String s) {
        return userService.find(s);
    }
}
