package my.project.domain.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Admin {

    private int id;

    @NotBlank(message = "Login can't be null")
    @Size(max = 50, message = "Max length is 20 symbols")
    private String login;

    @NotBlank(message = "Password can't be null")
    @Size(max = 50, message = "Max length is 20 symbols")
    private String password;
}
