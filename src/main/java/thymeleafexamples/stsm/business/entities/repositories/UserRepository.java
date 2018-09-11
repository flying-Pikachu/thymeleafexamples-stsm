package thymeleafexamples.stsm.business.entities.repositories;

import thymeleafexamples.stsm.business.entities.User;

import java.util.List;

/**
 * @Author     ?xzp.
 * @Date       ?Created in 2:13 PM 11/09/2018
 * @Description?${description}
 */
public class UserRepository {

    public User find(String userName) {
        return new User(userName);
    }
}
