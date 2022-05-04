package nl.meritude.apimodule.model;

import lombok.Getter;

@Getter
public class ProfileRequest {
    private String name;
    private String phoneNr;

    public ProfileRequest(String name, String phoneNr) {
        this.name = name;
        this.phoneNr = phoneNr;
    }
}
