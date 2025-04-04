package it.step.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Integer id;
    private String name;
    private String surname;
    private String cf;
    private String email;
    private String password;
    private Date birthdate;
    private Date disabledAt;
    private Boolean passReset;

}
