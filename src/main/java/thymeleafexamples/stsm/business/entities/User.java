package thymeleafexamples.stsm.business.entities;

/**
 * @ Author     ?xzp.
 * @ Date       ?Created in 2:13 PM 11/09/2018
 * @ Description?${description}
 * @ Modified By?
 * @Version: $version$
 */
public class User {

    private String userName;

    public User(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
