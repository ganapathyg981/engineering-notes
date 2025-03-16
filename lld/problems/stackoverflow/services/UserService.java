package stackoverflow.services;

import stackoverflow.models.User;

import java.util.ArrayList;
import java.util.List;

public class UserService {
    List<User> userList;
    static UserService instance;

    private UserService() {
        userList = new ArrayList<>();
    }

    public static synchronized UserService getInstance() {
        if(instance == null){
            instance = new UserService();
        }
        return instance;
    }


    User getUser(String userId) {
        return userList.stream().filter(user -> user.getId().equals(userId)).findFirst().get();
    }

    List<User> getAllUsers() {
        return userList;
    }

    public void addUser(User user) {
        userList.add(user);
    }

}
