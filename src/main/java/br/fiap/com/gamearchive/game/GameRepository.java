package br.fiap.com.gamearchive.game;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface GameRepository extends JpaRepository<Game, Long> {

    Page<Game> findByUserId(Long id, Pageable pageable);
    Optional<Game> findByName(String name);

}
