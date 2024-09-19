package br.fiap.com.gamearchive.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public List<User> findAll(@RequestParam(required = false) String name){
        return userService.findAll();
    }

    @PostMapping
    public ResponseEntity<User> create(@RequestBody User user){
        User newUser= userService.createUser(user);
        return ResponseEntity.ok(newUser);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id){
        User user = userService.findById(id);
        if (user != null){
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
