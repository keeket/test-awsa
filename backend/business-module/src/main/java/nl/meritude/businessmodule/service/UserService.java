package nl.meritude.businessmodule.service;

import lombok.extern.slf4j.Slf4j;
import nl.meritude.businessmodule.exception.BusinessException;
import nl.meritude.persistencemodule.domain.Employee;
import nl.meritude.persistencemodule.domain.Employer;
import nl.meritude.persistencemodule.domain.User;
import nl.meritude.persistencemodule.exception.PersistenceException;
import nl.meritude.persistencemodule.service.EmployeeService;
import nl.meritude.persistencemodule.service.EmployerService;
import nl.meritude.persistencemodule.service.PersistentUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserService {

    @Autowired
    private EmployeeService employeeBean;
    @Autowired
    private EmployerService employerBean;
    @Autowired
    private PersistentUserService userBean;

    public User login(String email, String pass) throws BusinessException {
        try {
            User user = userBean.getUser(email);
            if (user.getPassword().equals(pass)) {
                return user;
            }
            throw new BusinessException("Invalid login details for email: " + user.getEmail());
        } catch(PersistenceException pe) {
            throw new BusinessException(pe.getMessage());
        }
    }

    public Employee loginEmployee(String email, String pass) throws BusinessException {
        try {
            log.info("Employee login with: {}", email);
            Employee employee = employeeBean.getEmployee(email);
            log.info("Employee retrieved for email: {}", employee.getEmail());
            if (employee.getPassword().equals(pass)) {
                log.info("Successful employee login for email: {}", employee.getEmail());
                return employee;
            }
            throw new BusinessException("Invalid login details for email: " + employee.getEmail());
        } catch(PersistenceException pe) {
            throw new BusinessException(pe.getMessage());
        }
    }

    public Employer loginEmployer(String email, String pass) throws BusinessException {
        try {
            log.info("Employer login with: {}", email);
            Employer employer = employerBean.getEmployer(email);
            log.info("Employer retrieved for email: {}", employer.getEmail());
            if (employer.getPassword().equals(pass)) {
                log.info("Successful employer login for email: {}", employer.getEmail());
                return employer;
            }
            throw new BusinessException("Invalid login details for email: " + employer.getEmail());
        } catch(PersistenceException pe) {
            throw new BusinessException(pe.getMessage());
        }
    }

    public Long registerUser(User user) throws BusinessException {
        log.info("Checking availability of email: {}", user.getEmail());
        if(isEmailAlreadyInUse(user.getEmail())) {
            throw new BusinessException("User already registered for email: " + user.getEmail());
        }
        log.info("No user found with email: {}", user.getEmail());
        Long userId = userBean.newUser(user);
        return userId;
    }

    public Long registerEmployee(String email, String password) throws BusinessException {
        log.info("New registration with email: {}", email);
        Employee employee = new Employee(email, password);
        Long userId = registerUser(employee);
        log.info("Registration successful for email: {}", email);
        return userId;
    }

    public Long registerEmployer(String email, String password) throws BusinessException {
        log.info("New registration with email: {}", email);
        Employer employer = new Employer(email, password);
        Long userId = registerUser(employer);
        log.info("Registration successful for email: {}", email);
        return userId;
    }

    public User getUser(Long userId) throws BusinessException {
        try {
            return userBean.getUser(userId);
        } catch(PersistenceException pe) {
            throw new BusinessException(pe.getMessage());
        }
    }

    public Employer getEmployer(Long userId) throws BusinessException {
        try {
            log.info("Getting employer with id {}", userId);
            return employerBean.getEmployer(userId);
        } catch(PersistenceException pe) {
            throw new BusinessException(pe.getMessage());
        }
    }

    public Employee getEmployee(Long userId) throws BusinessException {
        try {
            log.info("Getting employee with id {}", userId);
            return employeeBean.getEmployee(userId);
        } catch(PersistenceException pe) {
            throw new BusinessException(pe.getMessage());
        }
    }

    public void updateUser(User user) {
        userBean.updateUser(user);
    }

    private boolean isEmailAlreadyInUse(String email) {
        try {
            userBean.getUser(email);
            return true;
        } catch(PersistenceException pe) {
            return false;
        }
    }
}
