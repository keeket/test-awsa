package nl.meritude.persistencemodule.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    private String email;
    private String password;
    private String name;
    private String phoneNr;

    public User () {}

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User(String email, String password, String name, String phoneNr) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.phoneNr = phoneNr;
    }

    public boolean equals(Employer compEmployer) {
        return this.getId() == compEmployer.getId();
    }

    public String toString() {
        return "User(email=" + this.getEmail() + ", password=" + this.getPassword().substring(0, 4) + "*****" + ", name=" + this.getName() + ", phoneNr=" + this.getPhoneNr() + ")";
    }
}
