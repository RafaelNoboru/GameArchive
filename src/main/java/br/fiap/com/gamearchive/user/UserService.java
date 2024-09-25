package br.fiap.com.gamearchive.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User createUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User findByEmail(String email){
        Optional<User> userOptional = userRepository.findByEmail(email);
        return userOptional.orElse(null);
    }

    public User findById(Long id){
        Optional<User> userOptional = userRepository.findById(id);
        return userOptional.orElse(null);
    }

    public void updateAvatar(String email, MultipartFile file){

        if(file.isEmpty()){
            throw new RuntimeException("Invalid file");
        }
        try(InputStream is = file.getInputStream()){
            Path destinationDir = Path.of("src/main/resources/static/avatars");
            Path destinationFile = destinationDir
                    .resolve(System.currentTimeMillis() + file.getOriginalFilename())
                    .normalize()
                    .toAbsolutePath();
            Files.copy(is, destinationFile);

            System.out.println("Archive saved succesfully");

            var user = userRepository.findByEmail(email).orElseThrow( () -> new UsernameNotFoundException("User not found"));
            var baseUrl = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
            var avatarUrl = baseUrl + "/users/avatars/" + destinationFile.getFileName();
            user.setAvatar(avatarUrl);
            userRepository.save(user);

        } catch (Exception e) {
            System.out.println("Error while copying file. " + e.getCause());
        }
    }


}
