package com.wesleytb.project_login.dto;

import com.wesleytb.project_login.model.BaseUser;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;


@Getter
@Setter
public class UserGetDto extends BaseUser {

    private Long id;

    @Length(min = 3, max = 255, message = "O nome deve ter entre 3 e 255 caracteres.")
    private String name;

    @Range(min = 1, max = 150, message = "A idade deve ser entre 1 e 150 anos.")
    private Short age;

    @Email(message = "Formato de e-mail incorreto.")
    @Length(max = 255, message = "O e-mail deve ter no máximo 255 caracteres.")
    private String email;

    @JsonIgnore
    @Length(min = 8, max = 50, message = "A senha deve ter entre 8 e 50 caracteres.")
    private String password;

    public UserGetDto(BaseUser user) {
        super(user);
    }
}
