package br.fiap.com.gamearchive.game;

import jakarta.persistence.*;
import lombok.Data;
import br.fiap.com.gamearchive.user.User;

@Entity
@Table(name = "TB_JAVA_GA_GAMES")
@Data
public class Game {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id_game;
    String name;
    String genre;
    String region;
    String plataform;

    @ManyToOne
    User user;

}
