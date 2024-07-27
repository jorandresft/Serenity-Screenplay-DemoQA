package co.com.aprender.auto.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class UserModel {

    private String firstName;
    private String lastName;
    private String email;
    private String gender;
    private String mobil;
    private String state;
    private String city;
}
