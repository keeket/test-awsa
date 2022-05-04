package nl.meritude.apimodule.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class JobRequest {
    private String title;
    private List<String> softSkills;
    private List<String> hardSkills;
    private String qualifications;
    private int salary;
    private int experience;
    private String country;
    private String city;
}
