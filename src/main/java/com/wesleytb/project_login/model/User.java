package com.wesleytb.project_login.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "login_app_user")
public class User extends BaseUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Length(min = 3, max = 255, message = "O nome deve ter entre 3 e 255 caracteres.")
    @Column(name = "nome")
    private String name;

    @Range(min = 1, max = 150, message = "A idade deve ser entre 1 e 150 anos.")
    @Column(name = "idade")
    private Short age;

    @Email(message = "Formato de e-mail incorreto.")
    @Length(max = 255, message = "O e-mail deve ter no máximo 255 caracteres.")
    @Column(name = "e-mail")
    private String email;

    @Length(min = 8, max = 50, message = "A senha deve ter entre 8 e 50 caracteres.")
    @Column(name = "senha")
    private String password;

    public User(BaseUser user) {
        super(user);
    }
}
