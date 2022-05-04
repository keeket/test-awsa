package nl.meritude.persistencemodule.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Employer extends User {
    public Employer () {}

    public Employer(String email, String password) {
        super(email, password);
    }

    public Employer(String email, String password, String name, String phoneNr) {
        super(email, password, name, phoneNr);
    }
}
