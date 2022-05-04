package nl.meritude.apimodule.model;

import lombok.Getter;

import java.util.List;

@Getter
public class JobResponse extends JobRequest {
    private Long jobId;
    private Long employerId;
    private String employerName;

    public JobResponse(Long id, String title, List<String> softSkills, List<String> hardSkills, String qualification, int salary, int experience, String country, String city, long employerId, String companyName) {
        super(title, softSkills, hardSkills, qualification, salary, experience, country, city);
        this.jobId = id;
        this.employerId = employerId;
        this.employerName = companyName;
    }
}
