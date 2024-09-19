package br.fiap.com.gamearchive.game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameService {

    @Autowired
    GameRepository gameRepository;

    public List<Game> findAll(){
        return gameRepository.findAll();
    }

    public Game createGame(Game game){
        return gameRepository.save(game);
    }

    public Game findByName(String name){
        Optional<Game> gameOptional = gameRepository.findByName(name);
        return gameOptional.orElse(null);

    }
}
