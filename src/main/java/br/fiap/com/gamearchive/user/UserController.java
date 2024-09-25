package br.fiap.com.gamearchive.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<User> findAll(@RequestParam(required = false) String name){
        return userService.findAll();
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

    @GetMapping("avatar/{filename}")
    public ResponseEntity<Resource> getAvatar(@PathVariable MultipartFile filename){
        Path path = Paths.get("src/main/resources/static/avatars/" + filename);
        Resource file = UrlResource.from(path.toUri());

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(file);
    }

    @PostMapping
    public ResponseEntity<User> create(@RequestBody User user){
        User newUser= userService.createUser(user);
        return ResponseEntity.ok(newUser);
    }

    @PostMapping("avatar")
    public void uploadAvatar(@RequestBody MultipartFile file){
        var email = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        userService.updateAvatar(email, file);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable Long id, @RequestBody @Validated User updatedUser){
        Optional<User> existingUser = userRepository.findById(id);
        if(existingUser.isPresent()){
            updatedUser.setId(id);
            User savedUser = userRepository.save(updatedUser);
            return ResponseEntity.ok(savedUser);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> delete(@PathVariable Long id){
        Optional<User> existingUser = userRepository.findById(id);
        if(existingUser.isPresent()){
            userRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }


}
