package nl.meritude.apimodule.model;

import lombok.Getter;

@Getter
public class UserProfileResponse {
    private Long id;
    private String email;
    private String name;
    private String phoneNr;

    public UserProfileResponse(Long id, String email) {
        this.id = id;
        this.email = email;
    }

    public UserProfileResponse(Long id, String email, String name, String phoneNr) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.phoneNr = phoneNr;
    }
}
