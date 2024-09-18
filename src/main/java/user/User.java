package user;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tb_java_ga_users")
@Data
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    String password;
    String email;
    String avatar;
}
