package br.fiap.com.gamearchive.game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/games")
public class GameController {

    @Autowired
    GameService gameService;

    @GetMapping
    public List<Game> findAll(@RequestParam(required = false) String name){
        return gameService.findAll();
    }

    @PostMapping
    public ResponseEntity<Game> create(@RequestBody Game game){
        Game newGame = gameService.createGame(game);
        return ResponseEntity.ok(newGame);
    }
}
