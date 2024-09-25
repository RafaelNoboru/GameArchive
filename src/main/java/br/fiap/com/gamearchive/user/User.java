package br.fiap.com.gamearchive.user;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "TB_JAVA_GA_USERS")
@Data
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    String password;
    String email;
    String avatar;

}
