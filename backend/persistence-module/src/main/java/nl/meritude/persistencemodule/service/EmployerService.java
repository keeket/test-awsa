package nl.meritude.persistencemodule.service;

import lombok.extern.slf4j.Slf4j;
import nl.meritude.persistencemodule.domain.Employer;
import nl.meritude.persistencemodule.exception.PersistenceException;
import nl.meritude.persistencemodule.repos.EmployerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmployerService {

    @Autowired
    private EmployerRepo employerRepo;

    public Long newEmployer(Employer employer) {
        log.info("Registering new employer: {}", employer);
        employer = employerRepo.save(employer);
        log.info("Employer added with name: {}", employer.getName());
        return employer.getId();
    }

    public Employer getEmployer(Long employerId) throws PersistenceException {
        log.info("Get employer with id: {}", employerId);
        return employerRepo.findById(employerId).orElseThrow(() -> new PersistenceException("Employer does not exist with id: " + employerId));
    }

    public Employer getEmployer(String email) throws PersistenceException {
        log.info("Get employer with email: {}", email);
        return employerRepo.findByEmail(email).orElseThrow(() -> new PersistenceException("No employer found with email: " + email));
    }

    public void updateEmployer(Employer employer) {
        employerRepo.save(employer);
    }
}
