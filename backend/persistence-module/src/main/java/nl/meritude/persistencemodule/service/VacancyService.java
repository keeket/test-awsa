package nl.meritude.persistencemodule.service;

import lombok.extern.slf4j.Slf4j;
import nl.meritude.persistencemodule.domain.Vacancy;
import nl.meritude.persistencemodule.exception.PersistenceException;
import nl.meritude.persistencemodule.repos.VacancyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class VacancyService {
    @Autowired
    private VacancyRepo vacancyRepo;

    public Vacancy getVacancy(Long vacancyId) throws PersistenceException {
        log.info("Retrieving vacancy from database with id: {}", vacancyId);
        return vacancyRepo.findById(vacancyId).orElseThrow(
                () -> new PersistenceException("Vacancy not found with id: " + vacancyId));
    }

    public Long newVacancy(Vacancy vacancy) {
        log.info("Creating new Vacancy with title: {}", vacancy.getTitle());
        vacancy = vacancyRepo.save(vacancy);
        return vacancy.getId();
    }

    public void deleteVacancy(Long vacancyId) throws PersistenceException {
        log.info("Deleting Vacancy with id: {}", vacancyId);
        vacancyRepo.delete(getVacancy(vacancyId));
    }

    public void updateVacancy(Vacancy vacancy) {
        log.info("Updating Vacancy with id: {}", vacancy.getId());
        vacancyRepo.save(vacancy);
    }
}
