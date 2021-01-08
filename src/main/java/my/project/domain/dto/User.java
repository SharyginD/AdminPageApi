package my.project.domain.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class User {

    private int id;

    @NotBlank(message = "First name can't be null")
    @Size(max = 50, message = "Max length is 50 symbols")
    private String firstName;

    @NotBlank(message = "Last name can't be null")
    @Size(max = 50, message = "Max length is 50 symbols")
    private String lastName;

    @NotBlank(message = "Mail name can't be null")
    @Pattern(regexp = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$",
             message = "Email is not valid")
    @Size(max = 50, message = "Long email")
    private String mail;
}
