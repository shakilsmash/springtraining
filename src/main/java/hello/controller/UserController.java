package hello.controller;

import hello.model.User;
import hello.repository.UserRepository;
import hello.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

@Controller
@RequestMapping(value = "user")
public class UserController {

    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "addUser")
    @ResponseBody
    public String addUser(@RequestParam(value="name") String name,
                        @RequestParam(value="email") String email,
                        @RequestParam(value="phone") String phone,
                        @RequestParam(value="status") String status) {
        userService.addNewUser(name, email, phone, status);
        return "Added: " + name;
    }

    @RequestMapping(value = "showUser")
    @ResponseBody
    public User showUser(@RequestParam(value="id", required=false, defaultValue="0") String id) {
        User user = userService.getUser(Integer.parseInt(id));
        return user;
    }

    @RequestMapping(value = "showAllUsers")
    @ResponseBody
    public ArrayList<User> showAllUser() {
        ArrayList<User> user = userService.getAllUser();
        String output = "";

        for (int i = 0; i < user.size(); i++) {
            output += user.toString();
        }
        return user;
    }

    @RequestMapping(value = "deleteUser")
    @ResponseBody
    public String deleteUser(@RequestParam(value="id") int id) {
        userService.deleteUser(id);
        return "Deleted User : " + id;
    }

    @GetMapping(path="/add") // Map ONLY GET Requests
    public @ResponseBody String addNewUser (@RequestParam String name,
                                            @RequestParam String email,
                                            @RequestParam String phone,
                                            @RequestParam String status) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request

        User n = new User(name, email, phone, status);
        userRepository.save(n);
        return "Saved";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<User> getAllUsers() {
        // This returns a JSON or XML with the users
        return userRepository.findAll();
    }

    @GetMapping(path="/show")
    public @ResponseBody User getUser(@RequestParam long id) {
        // This returns a JSON or XML with the users
        return userRepository.findOne(id);
    }

}

