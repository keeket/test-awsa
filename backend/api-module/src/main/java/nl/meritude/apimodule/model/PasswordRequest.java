package nl.meritude.apimodule.model;

import lombok.Getter;

@Getter
public class PasswordRequest {
    private String email;
    private String oldPass;
    private String newPass;

    public PasswordRequest(String email, String oldPass, String newPass) {
        this.email = email;
        this.oldPass = oldPass;
        this.newPass = newPass;
    }
}
