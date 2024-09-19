package br.fiap.com.gamearchive.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import br.fiap.com.gamearchive.user.User;
import br.fiap.com.gamearchive.user.UserRepository;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    private final UserRepository userRepository;
    private Algorithm algorithm;

    public TokenService(UserRepository userRepository, @Value("${jwt.secret}") String secret) {
        this.userRepository = userRepository;
        this.algorithm = Algorithm.HMAC256(secret);
    }

    public Token createToken(Credentials credentials) {
        var expiresAt = LocalDateTime.now().plusHours(1).toInstant(ZoneOffset.ofHours(-3));
        String token = JWT.create()
                .withSubject(credentials.email())
                .withIssuer("game_archive")
                .withExpiresAt(expiresAt)
                .sign(algorithm);

        return new Token(token, credentials.email());
    }

    public User getUserFromToken(String token) {
        var email = JWT.require(algorithm)
                .withIssuer("game_archive")
                .build()
                .verify(token)
                .getSubject();

        return userRepository
                .findByEmail(email)
                .orElseThrow( () -> new UsernameNotFoundException("User not found"));
    }
}
