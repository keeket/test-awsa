package nl.meritude.persistencemodule.repos;

import nl.meritude.persistencemodule.domain.Employer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployerRepo extends CrudRepository<Employer, Long> {

    Optional<Employer> findByEmail(String email);

    Optional<Employer> findByName(String name);
}
