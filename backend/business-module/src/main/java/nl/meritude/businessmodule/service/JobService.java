package nl.meritude.businessmodule.service;

import lombok.extern.slf4j.Slf4j;
import nl.meritude.businessmodule.exception.BusinessException;
import nl.meritude.persistencemodule.domain.Employer;
import nl.meritude.persistencemodule.domain.Vacancy;
import nl.meritude.persistencemodule.exception.PersistenceException;
import nl.meritude.persistencemodule.service.VacancyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class JobService {
    @Autowired
    private VacancyService vacancyBean;

    public Long newJob(String title, List<String> softSkills, List<String> hardSkills, String qualification,
                       int salary, int experience, String country, String city, Employer employer) {
        log.info("Creating new job with title: {}", title);
        Vacancy vacancy = new Vacancy(title, softSkills, hardSkills, qualification, salary, experience,
                country, city, employer);
        return vacancyBean.newVacancy(vacancy);
    }

    public Vacancy getJob(Long vacancyId) throws BusinessException {
        try {
            log.info("Getting job with id: {}", vacancyId);
            return vacancyBean.getVacancy(vacancyId);
        } catch(PersistenceException pe) {
            throw new BusinessException(pe.getMessage());
        }
    }

    public void deleteJob(Long vacancyId) throws BusinessException{
        try {
            vacancyBean.deleteVacancy(vacancyId);
        } catch(PersistenceException pe) {
            throw new BusinessException(pe.getMessage());
        }
    }

    public void updateJob(Vacancy vacancy) {
        vacancyBean.updateVacancy(vacancy);
    }
}
