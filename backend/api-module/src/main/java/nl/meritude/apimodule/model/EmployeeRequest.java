package nl.meritude.apimodule.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class EmployeeRequest {
    private String name;
    private String phone;
    private String email;
    private List<String> softSkills;
    private List<String> hardSkills;
    private List<String> jobPreference;
    private List<String> companyPreference;
    private int salaryAim;
    private int yearsOfExperience;
    private String country;
    private String city;
}
