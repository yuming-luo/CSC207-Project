package data_access;

import java.util.HashMap;
import java.util.Map;

import entity.User;
import use_case.signup.SignupUserDataAccessInterface;

public class InMemoryUserDataAccessObject
        implements SignupUserDataAccessInterface {

    private final Map<String, User> users = new HashMap<>();

    @Override
    public boolean existsByName(String username) {
        return users.containsKey(username);
    }

    @Override
    public void save(User user) {
        users.put(user.getUsername(), user);
    }
}