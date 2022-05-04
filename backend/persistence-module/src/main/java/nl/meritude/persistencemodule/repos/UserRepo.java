package nl.meritude.persistencemodule.repos;

import nl.meritude.persistencemodule.domain.Employer;
import nl.meritude.persistencemodule.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepo extends CrudRepository<User, Long> {
    Optional<User> findByEmail(String email);

    Optional<User> findByName(String name);
}
