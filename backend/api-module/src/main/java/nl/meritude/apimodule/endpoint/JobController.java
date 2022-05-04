package nl.meritude.apimodule.endpoint;

import lombok.extern.slf4j.Slf4j;
import nl.meritude.apimodule.model.GenericResponse;
import nl.meritude.apimodule.model.JobRequest;
import nl.meritude.apimodule.model.JobResponse;
import nl.meritude.apimodule.util.JwtService;
import nl.meritude.businessmodule.exception.BusinessException;
import nl.meritude.businessmodule.service.JobService;
import nl.meritude.businessmodule.service.UserService;
import nl.meritude.persistencemodule.domain.Employer;
import nl.meritude.persistencemodule.domain.Vacancy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

import static nl.meritude.apimodule.model.UserType.EMPLOYER;

@Slf4j
@RestController
public class JobController {
    @Autowired
    private UserService userBean;
    @Autowired
    private JobService jobBean;
    @Autowired
    private JwtService jwtService;

    @GetMapping("/job")
    @CrossOrigin
    public GenericResponse getJob(@RequestParam Long jobId) {
        try{
            log.info("Getting job with id: {}", jobId);
            Vacancy job = jobBean.getJob(jobId);
            log.info("Job retrieved");
            JobResponse jobResponse = new JobResponse(job.getId(), job.getTitle(), job.getSoftSkills(),
                    job.getHardSkills(), job.getQualification(), job.getSalary(), job.getExperience(), job.getCountry(),
                    job.getCity(), job.getEmployer().getId(), job.getEmployer().getName());
            log.info("JobResponse created");
            return new GenericResponse(104, jobResponse);
        } catch(BusinessException be) {
            log.error("Caught error with message: {}", be.getMessage());
            return new GenericResponse(404, "Invalid action");
        }
    }

    @PostMapping("/job")
    @CrossOrigin
    public GenericResponse updateJob(HttpServletRequest req, @RequestParam Long jobId, @RequestBody JobRequest job) {
        try {
            log.info("Updating job with id: {}", jobId);
            if (jwtService.userIsOfUsertype(req, EMPLOYER)) {
                log.info("User is employer");
                Long userId = jwtService.getUserId(req);
                Employer employer = userBean.getEmployer(userId);
                log.info("Employer retrieved");
                Vacancy vacancy = jobBean.getJob(jobId);
                log.info("Job retrieved");
                if (vacancy.getEmployer().equals(employer)) {
                    log.info("Employer updates own vacancy");
                    vacancy.setTitle(job.getTitle());
                    vacancy.setSoftSkills(job.getSoftSkills());
                    vacancy.setHardSkills(job.getHardSkills());
                    vacancy.setQualification(job.getQualifications());
                    vacancy.setSalary(job.getSalary());
                    vacancy.setExperience(job.getExperience());
                    vacancy.setCountry(job.getCountry());
                    vacancy.setCity(job.getCity());
                    jobBean.updateJob(vacancy);
                    return new GenericResponse(104, "Job updated");
                } else {
                    log.error("Employer attempted updating someone else's vacancy");
                    return new GenericResponse(404, "Invalid action");
                }
            } else {
                log.error("User is employee");
                return new GenericResponse(404, "Invalid action");
            }
        } catch(BusinessException be) {
            log.error("Caught error with message: {}", be.getMessage());
            return new GenericResponse(404, "Invalid action");
        }
    }

    @PutMapping("/job")
    @CrossOrigin
    public GenericResponse newJob(HttpServletRequest req, @RequestBody JobRequest job) {
        try {
            log.info("Checking rights for creating job");
            if (jwtService.userIsOfUsertype(req, EMPLOYER)) {
                log.info("Rights suffice, making new job");
                Long userId = jwtService.getUserId(req);
                Employer employer = userBean.getEmployer(userId);
                Long id = createJob(job, employer);
                log.info("Job creation successful with id: {}", id);
                return new GenericResponse(104, id);
            } else {
                log.error("Rights not reaching for user");
                return new GenericResponse(404, "Invalid action");
            }
        } catch (BusinessException be) {
            log.error("Caught error with message: {}", be.getMessage());
            return new GenericResponse(404, "Invalid action");
        }
    }

    @DeleteMapping("/job")
    @CrossOrigin
    public GenericResponse deleteJob(HttpServletRequest req, @RequestParam Long jobId) {
        try {
            log.info("Checking rights for creating jobs");
            if (jwtService.userIsOfUsertype(req, EMPLOYER)) {
                log.info("User is employer, proceeding");
                Long userId = jwtService.getUserId(req);
                Employer employer = userBean.getEmployer(userId);
                log.info("User retrieved");
                if (jobBean.getJob(jobId).getEmployer().equals(employer)) {
                    log.info("User is owner of vacancy");
                    jobBean.deleteJob(jobId);
                    log.info("Vacancy deleted");
                    return new GenericResponse(104, "Job deleted");
                } else {
                    log.error("User tried deleting someone else's vacancy");
                    return new GenericResponse(404, "Invalid action");
                }
            }
            log.error("Rights not reaching for user");
            return new GenericResponse(404, "Invalid action");
        } catch(BusinessException be) {
            log.error("Caught error with message: {}", be.getMessage());
            return new GenericResponse(404, "Invalid action");
        }
    }

    @PutMapping("/jobs")
    public GenericResponse addJobs(HttpServletRequest req, @RequestBody List<JobRequest> list) {
        try {
            log.info("Checking rights for creating jobs");
            List<Long> ids = new ArrayList<>();
            if (jwtService.userIsOfUsertype(req, EMPLOYER)) {
                log.info("Rights suffice, making new jobs");
                Long userId = jwtService.getUserId(req);
                Employer employer = (Employer) userBean.getUser(userId);
                for (JobRequest job : list) {
                    ids.add(createJob(job, employer));
                }
                log.info("Jobs created");
                return new GenericResponse(104, ids);
            }
            log.error("Rights not reaching for user");
            return new GenericResponse(404, "Invalid action");
        } catch(BusinessException be) {
            log.error("Caught error with message: {}", be.getMessage());
            return new GenericResponse(404, "Invalid action");
        }
    }

    private Long createJob(JobRequest job, Employer employer) {
        return jobBean.newJob(job.getTitle(), job.getSoftSkills(), job.getHardSkills(), job.getQualifications(),
                job.getSalary(), job.getExperience(), job.getCountry(), job.getCity(), employer);
    }
}
