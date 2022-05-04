package nl.meritude.persistencemodule.repos;

import nl.meritude.persistencemodule.domain.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepo extends CrudRepository<Employee, Long> {

    Optional<Employee> findByEmail(String email);
}
