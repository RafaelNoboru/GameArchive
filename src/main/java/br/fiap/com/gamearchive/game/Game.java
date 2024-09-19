package br.fiap.com.gamearchive.game;

import jakarta.persistence.*;
import lombok.Data;
import br.fiap.com.gamearchive.user.User;

@Entity
@Table(name = "tb_java_ga_games")
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
