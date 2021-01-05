package my.project.controller;

import my.project.domain.dto.User;
import my.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public ResponseEntity<User> save(@Valid @RequestBody User user) {
        return new ResponseEntity<>(userService.save(user), HttpStatus.OK);
    }

    @DeleteMapping("/admin/user/{userId}")
    public ResponseEntity<User> delete(@PathVariable("userId") int id) {
        return new ResponseEntity<>(userService.delete(id), HttpStatus.OK);
    }
}
