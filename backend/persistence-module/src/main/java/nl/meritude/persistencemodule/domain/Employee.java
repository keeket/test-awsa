package nl.meritude.persistencemodule.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Employee extends User {
    @ElementCollection
    private List<String> softSkills;
    @ElementCollection
    private List<String> hardSkills;
    @ElementCollection
    private List<String> jobPreference;
    @ElementCollection
    private List<String> companyPreference;
    private int salaryAim;
    private int yearsOfExperience;
    private String country;
    private String city;

    public Employee() {}

    public Employee(String email, String password) {
        super(email, password);
    }

    public Employee(String email, String password, String name, String phoneNr) {
        super(email, password, name, phoneNr);
    }

    public Employee(String email, String name, String phoneNr, List<String> softSkills, List<String> hardSkills,
                    List<String> jobPreference, List<String> companyPreference, int salaryAim, int yearsOfExperience,
                    String country, String city) {
        super(email, "Test", name, phoneNr);
        this.softSkills = softSkills;
        this.hardSkills = hardSkills;
        this.jobPreference = jobPreference;
        this.companyPreference = companyPreference;
        this.salaryAim = salaryAim;
        this.yearsOfExperience = yearsOfExperience;
        this.country = country;
        this.city = city;
    }
}
