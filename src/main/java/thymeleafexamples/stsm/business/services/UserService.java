package thymeleafexamples.stsm.business.services;

import org.springframework.beans.factory.annotation.Autowired;
import thymeleafexamples.stsm.business.entities.User;
import thymeleafexamples.stsm.business.entities.repositories.UserRepository;

/**
 * @author     ?xzp.
 * @date       ?Created in 2:12 PM 11/09/2018
 * @Description?${description}
 */
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User find(String userName) {
        return userRepository.find(userName);
    }
}
