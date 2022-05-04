package nl.meritude.persistencemodule.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Vacancy {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    @ElementCollection
    private List<String> softSkills;
    @ElementCollection
    private List<String> hardSkills;
    //todo Alternative without extra tables https://stackoverflow.com/questions/287201/how-to-persist-a-property-of-type-liststring-in-jpa
    private String qualification;
    private int salary;
    private int experience;
    private String country;
    private String city;
    @ManyToOne
    @JoinColumn(name = "employerId")
    private Employer employer;

    public Vacancy() {}

    public Vacancy(String title, List<String> softSkills, List<String> hardSkills, String qualification, int salary,
                   int experience, String country, String city, Employer employer) {
        this.title = title;
        this.softSkills = softSkills;
        this.hardSkills = hardSkills;
        this.qualification = qualification;
        this.salary = salary;
        this.experience = experience;
        this.country = country;
        this.city = city;
        this.employer = employer;
    }
}
