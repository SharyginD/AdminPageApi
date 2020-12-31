package my.project.controller;

import my.project.domain.dto.User;
import my.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public Collection<User> getAll() {
        return userService.getAll();
    }

    @PostMapping("/admin/user")
    public User save() {
        return userService.save(new User());
    }

    @DeleteMapping("/admin/user/id")
    public User delete() {
        return userService.delete(2);
    }
}
