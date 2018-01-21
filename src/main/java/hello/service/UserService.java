package hello.service;

import hello.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService {

    public ArrayList<User> user = new ArrayList<User>();

    public void addNewUser(String name, String email, String phone, String status) {
        user.add(new User(name, email, phone, status));
    }

    public User getUser(int index) {
        return user.get(index);
    }

    public ArrayList<User> getAllUser() {
        return user;
    }

    public void deleteUser(int index) {
        user.remove(index);
    }
}
