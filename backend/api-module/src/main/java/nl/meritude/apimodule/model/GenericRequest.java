package nl.meritude.apimodule.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenericRequest {
    private String email;
    private String password;

    public GenericRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
