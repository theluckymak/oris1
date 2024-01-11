package itis.models;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.core.SpringVersion;

@Setter
@Getter
@Builder
@EqualsAndHashCode
public class User {
    private Long id;
    private String nameOfUser;
    private String surnameOfUser;
    private Integer ageOfUser;
}
