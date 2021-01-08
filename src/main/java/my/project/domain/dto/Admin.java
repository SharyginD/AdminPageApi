package my.project.domain.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Admin {

    private int id;

    private String login;

    private String password;
}
