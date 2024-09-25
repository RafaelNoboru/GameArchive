package br.fiap.com.gamearchive.game;

import br.fiap.com.gamearchive.user.User;
import br.fiap.com.gamearchive.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameService {

    @Autowired
    GameRepository gameRepository;

    @Autowired
    UserRepository userRepository;

    public List<Game> findAll() {
        return gameRepository.findAll();
    }

    public Game createGame(Game game) {
        return gameRepository.save(game);
    }

    public Game findByName(String name) {
        Optional<Game> gameOptional = gameRepository.findByName(name);
        return gameOptional.orElse(null);
    }

    public Page<Game> listGames(Long userId, Pageable pageable) {
        return gameRepository.findByUserId(userId, pageable);
    }
    public Game createGameForUser(Long userId, Game game) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        game.setUser(user);
        return gameRepository.save(game);
    }
}
