package app.store.service.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class UserDto {

    public UserDto() {
    }

    private String id;

    @NotNull
    @Size(min = 10, max = 10)
    private Long mobile;

    @NotNull
    @Size(min = 1, max = 50)
    private String login;


    @Size(max = 50)
    private String firstName;

    @Size(max = 50)
    private String lastName;

    @Email
    @Size(min = 5, max = 254)
    private String email;

    @Size(min = 2, max = 6)
    private String gender;

    @Size(min = 2, max = 6)
    private String langKey;

    @Size(max = 256)
    private String imageUrl;

    private String cardNumber;

    private List<AddressDto> addresses;


}
