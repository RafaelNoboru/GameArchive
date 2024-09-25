package br.fiap.com.gamearchive.game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users/{userId}/games")
public class GameController {

    @Autowired
    private GameService gameService;

    @GetMapping
    public ResponseEntity<Page<Game>> listGames(@PathVariable Long userId, Pageable pageable) {
        Page<Game> games = gameService.listGames(userId, pageable);
        return ResponseEntity.ok(games);
    }

    @PostMapping
    public ResponseEntity<Game> create(@PathVariable Long userId, @RequestBody Game game) {
        Game newGame = gameService.createGameForUser(userId, game);
        return ResponseEntity.status(HttpStatus.CREATED).body(newGame);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Game>> findAll() {
        List<Game> games = gameService.findAll();
        return ResponseEntity.ok(games);
    }

}
