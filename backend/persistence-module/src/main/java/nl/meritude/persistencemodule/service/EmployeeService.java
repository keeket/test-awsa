package nl.meritude.persistencemodule.service;

import lombok.extern.slf4j.Slf4j;
import nl.meritude.persistencemodule.domain.Employee;
import nl.meritude.persistencemodule.exception.PersistenceException;
import nl.meritude.persistencemodule.repos.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;

    public Long newEmployee(Employee employee) {
        log.info("Registering new employee: {}", employee);
        employee = employeeRepo.save(employee);
        log.info("Employee added with name: {}", employee.getName());
        return employee.getId();
    }

    public Employee getEmployee(Long employeeId) throws PersistenceException {
        log.info("Get employee with id: {}", employeeId);
        return employeeRepo.findById(employeeId).orElseThrow(() -> new PersistenceException("Employee does not exist with id: " + employeeId));
    }

    public Employee getEmployee(String email) throws PersistenceException {
        log.info("Get employee with email: {}", email);
        return employeeRepo.findByEmail(email).orElseThrow(() -> new PersistenceException("No employee found with email: " + email));
    }

    public void updateEmployee(Employee employee) {
        employeeRepo.save(employee);
    }
}
