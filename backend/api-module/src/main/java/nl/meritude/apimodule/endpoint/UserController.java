package nl.meritude.apimodule.endpoint;

import lombok.extern.slf4j.Slf4j;
import nl.meritude.apimodule.model.EmployeeRequest;
import nl.meritude.apimodule.model.GenericRequest;
import nl.meritude.apimodule.model.GenericResponse;
import nl.meritude.apimodule.model.UserProfileResponse;
import nl.meritude.apimodule.util.JwtService;
import nl.meritude.businessmodule.exception.BusinessException;
import nl.meritude.businessmodule.service.UserService;
import nl.meritude.businessmodule.service.JobService;
import nl.meritude.persistencemodule.domain.Employee;
import nl.meritude.persistencemodule.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("api")
public class UserController {

    @Autowired
    private UserService userBean;
    @Autowired
    private JobService jobBean;
    @Autowired
    private JwtService jwtService;

    @PostMapping("/register")
    @CrossOrigin
    public GenericResponse register(@RequestBody GenericRequest body) {
        try {
            log.info("Starting register");
            String email = body.getEmail();
            String password = body.getPassword();

            Long userId = userBean.registerEmployee(email, password);
            log.info("Finishing register");
            return new GenericResponse(103, new UserProfileResponse(userId, email));
        } catch(BusinessException be) {
            log.error("Caught business exception with message {}", be.getMessage());
            return new GenericResponse(403, "Error registering user");
        }
    }

    @PostMapping("/registerEmployer")
    @CrossOrigin
    public GenericResponse registerEmployer(@RequestBody GenericRequest body) {
        try {
            log.info("Starting register");
            String email = body.getEmail();
            String password = body.getPassword();

            Long userId = userBean.registerEmployer(email, password);
            log.info("Finishing register");
            return new GenericResponse(103, new UserProfileResponse(userId, email));
        } catch(BusinessException be) {
            log.error("Caught business exception with message {}", be.getMessage());
            return new GenericResponse(403, "Error registering user");
        }
    }

    @PostMapping("/login")
    @CrossOrigin
    public GenericResponse login(@RequestBody GenericRequest body) {
        try {
            log.info("Starting login");
            String email = body.getEmail();
            String password = body.getPassword();

            User user = userBean.login(email, password);
            log.info("Login successful");
            String userType;

            if(user instanceof Employee) {
                log.info("User is employee");
                userType = "employee";
            } else {
                log.info("User is employer");
                userType = "employer";
            }

            log.info("Creating JWT");
            String jwt = jwtService.createJWT(user.getId(), userType, body.getEmail());
            log.info("JWT creation finished");
            return new GenericResponse(101, jwt);
        } catch (BusinessException be) {
            log.error("Caught business exception with message {}", be.getMessage());
            return new GenericResponse(401, "Error during user login");
        }
    }

    @PutMapping("/register")
    @CrossOrigin
    public GenericResponse addEmployees(@RequestBody List<EmployeeRequest> body) {
        List<Long> ids = new ArrayList<>();
        for (EmployeeRequest er : body) {
            Employee employee = new Employee(er.getEmail(), er.getName(), er.getPhone(), er.getSoftSkills(), er.getHardSkills(),
                    er.getJobPreference(), er.getCompanyPreference(), er.getSalaryAim(), er.getYearsOfExperience(),
                    er.getCountry(), er.getCity());
            try {
                ids.add(userBean.registerUser(employee));
            } catch(BusinessException be) {
                return new GenericResponse(408, "Something failed");
            }
        }
        return new GenericResponse(108, ids);
    }
}
