package nl.meritude.persistencemodule.service;

import nl.meritude.persistencemodule.domain.Employer;
import nl.meritude.persistencemodule.domain.Vacancy;
import nl.meritude.persistencemodule.exception.PersistenceException;
import nl.meritude.persistencemodule.repos.VacancyRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class VacancyServiceTest {

    @Mock
    private VacancyRepo vacancyRepoMock;

    @InjectMocks
    private VacancyService vacancyService;

    @Test
    void testGetVacancyReturnsVacancy() throws PersistenceException {
        // given
        Vacancy expectedVacancy = getVacancy();
        when(vacancyRepoMock.findById(1L)).thenReturn(Optional.of(expectedVacancy));

        // when
        Vacancy actualVacancy = vacancyService.getVacancy(1L);

        // then
        assertEquals(expectedVacancy, actualVacancy);
    }

    @Test
    void testGetVacancyThrowsPersistenceException() {
        // given
        Long vacancyId = 1L;
        String expectedErrorMessage = "Vacancy not found with id: " + vacancyId;
        when(vacancyRepoMock.findById(vacancyId)).thenReturn(Optional.empty());

        // when
        Exception exception = assertThrows(PersistenceException.class, () -> {
            vacancyService.getVacancy(vacancyId);
        });

        // then
        assertEquals(expectedErrorMessage, exception.getMessage());
    }

    @Test
    void testNewVacancyReturnsVacancyId() {
        // given
        Vacancy vacancy = getVacancy();
        Vacancy persistedVacancy = getPersistedVacancy(1L);
        Long expectedVacancyId = persistedVacancy.getId();
        when(vacancyRepoMock.save(vacancy)).thenReturn(persistedVacancy);

        // when
        Long actualVacancyId = vacancyService.newVacancy(vacancy);

        // then
        assertEquals(expectedVacancyId, actualVacancyId);
    }

    @Test
    void testDeleteVacancy() throws PersistenceException {
        // given
        Vacancy expectedVacancy = getVacancy();
        Long vacancyId = expectedVacancy.getId();
        when(vacancyRepoMock.findById(vacancyId)).thenReturn(Optional.of(expectedVacancy));

        // when
        vacancyService.deleteVacancy(vacancyId);

        // then
        verify(vacancyRepoMock).findById(vacancyId);
        verify(vacancyRepoMock).delete(expectedVacancy);
    }

    @Test
    void testDeleteVacancyThrowsPersistenceException() {
        // given
        Long vacancyId = 1L;
        String expectedErrorMessage = "Vacancy not found with id: " + vacancyId;
        when(vacancyRepoMock.findById(vacancyId)).thenReturn(Optional.empty());

        // when
        Exception exception = assertThrows(PersistenceException.class, () -> {
            vacancyService.deleteVacancy(vacancyId);
        });

        // then
        assertEquals(expectedErrorMessage, exception.getMessage());
    }

    @Test
    void testUpdateVacancy() {
        // given
        Vacancy vacancy = getVacancy();

        // when
        vacancyService.updateVacancy(vacancy);

        // then
        verify(vacancyRepoMock).save(vacancy);

    }

    private Vacancy getVacancy() {
        Vacancy vacancy = new Vacancy();

        vacancy.setTitle("title");
        vacancy.setSoftSkills(Arrays.asList("softskill-1","softskill-2"));
        vacancy.setHardSkills(Arrays.asList("hardskill-1","hardskill-2"));
        vacancy.setQualification("qualification");
        vacancy.setSalary(1);
        vacancy.setExperience(1);
        vacancy.setCountry("country");
        vacancy.setCity("city");
        vacancy.setEmployer(getEmployer(1L));

        return vacancy;
    }

    private Vacancy getPersistedVacancy(Long vacancyId) {
        Vacancy vacancy = getVacancy();

        vacancy.setId(vacancyId);

        return vacancy;
    }

    private Employer getEmployer(Long employerId) {
        Employer employer = new Employer();
        employer.setId(employerId);
        employer.setName("employer name");
        return employer;
    }
}