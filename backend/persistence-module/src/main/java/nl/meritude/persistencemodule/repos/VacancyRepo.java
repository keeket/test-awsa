package nl.meritude.persistencemodule.repos;

import nl.meritude.persistencemodule.domain.Vacancy;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VacancyRepo extends CrudRepository<Vacancy, Long> {
}
