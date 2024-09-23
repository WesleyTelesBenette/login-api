package com.wesleytb.project_login.dto;

import com.wesleytb.project_login.model.BaseUser;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserPostDto extends BaseUser {

    @Nullable
    private Long id;

    @NotNull(message = "O nome não pode estar vazia.")
    @Length(min = 3, max = 255, message = "O nome deve ter entre 3 e 255 caracteres.")
    private String name;

    @NotNull(message = "A idade não pode estar vazia.")
    @Range(min = 1, max = 150, message = "A idade deve ser entre 1 e 150 anos.")
    private Short age;

    @NotNull(message = "O e-mail não pode estar vazio.")
    @Email(message = "Formato de e-mail incorreto.")
    @Length(max = 255, message = "O e-mail deve ter no máximo 255 caracteres.")
    private String email;

    @NotNull(message = "A senha não pode estar vazia.")
    @Length(min = 8, max = 50, message = "A senha deve ter entre 8 e 50 caracteres.")
    private String password;

    public UserPostDto(BaseUser user) {
        super(user);
    }
}
