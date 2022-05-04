package nl.meritude.businessmodule.service;

import nl.meritude.businessmodule.exception.BusinessException;
import nl.meritude.persistencemodule.domain.Employer;
import nl.meritude.persistencemodule.domain.Vacancy;
import nl.meritude.persistencemodule.exception.PersistenceException;
import nl.meritude.persistencemodule.service.VacancyService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.doThrow;

@ExtendWith(MockitoExtension.class)
public class JobServiceTest {

    @Mock
    private VacancyService vacancyBeanMock;

    @InjectMocks
    private JobService jobService;

    @Captor
    private ArgumentCaptor<Vacancy> vacancyArgumentCaptor;

    @Test
    void testNewJobReturnsVacancyId() {
        // given
        Long expectedId = 1L;
        Vacancy expectedVacancy = getVacancy();
        when(vacancyBeanMock.newVacancy(vacancyArgumentCaptor.capture())).thenReturn(expectedId);

        // when
        Long actualId = jobService.newJob(
                expectedVacancy.getTitle(),
                expectedVacancy.getSoftSkills(),
                expectedVacancy.getHardSkills(),
                expectedVacancy.getQualification(),
                expectedVacancy.getSalary(),
                expectedVacancy.getExperience(),
                expectedVacancy.getCountry(),
                expectedVacancy.getCity(),
                expectedVacancy.getEmployer()
        );

        //then
        assertVacancyIsEqual(expectedVacancy, vacancyArgumentCaptor);
        assertEquals(expectedId, actualId);
    }

    @Test
    void testGetJobReturnsVacancy() throws PersistenceException, BusinessException {
        // given
        Vacancy expectedVacancy = getVacancy();
        when(vacancyBeanMock.getVacancy(1L)).thenReturn(expectedVacancy);

        // when
        Vacancy actualVacancy = jobService.getJob(1L);

        //then
        assertVacancyIsEqual(expectedVacancy, actualVacancy);
    }

    @Test
    void testGetJobThrowsBusinessException() throws PersistenceException {
        // given
        String expectedErrorMessage = "foutje";
        when(vacancyBeanMock.getVacancy(1L)).thenThrow(new PersistenceException(expectedErrorMessage));

        // when
        Exception exception = assertThrows(BusinessException.class, () -> {
            jobService.getJob(1L);
        });

        //then
        assertEquals(expectedErrorMessage, exception.getMessage());
    }

    @Test
    void testDeleteJobExecutesSuccesfully() throws PersistenceException, BusinessException {
        // when
        jobService.deleteJob(1L);

        // then
        verify(vacancyBeanMock).deleteVacancy(1L);
    }

    @Test
    void testDeleteJobThrowsBusinessException() throws PersistenceException {
        // given
        String expectedErrorMessage = "foutje";
        doThrow(new PersistenceException(expectedErrorMessage)).when(vacancyBeanMock).deleteVacancy(1L);

        // when
        Exception exception = assertThrows(BusinessException.class, () -> {
            jobService.deleteJob(1L);
        });

        //then
        assertEquals(expectedErrorMessage, exception.getMessage());
    }

    @Test
    void testUpdateJobExecutesSuccesfully() {
        // given
        Vacancy expectedVacancy = getVacancy();

        // when
        jobService.updateJob(expectedVacancy);

        // then
        verify(vacancyBeanMock).updateVacancy(expectedVacancy);
    }

    private void assertVacancyIsEqual(Vacancy expected, ArgumentCaptor<Vacancy> actual) {
        assertVacancyIsEqual(expected, actual.getValue());
    }

    private void assertVacancyIsEqual(Vacancy expected, Vacancy actual) {
        assertEquals(expected.getTitle(), actual.getTitle());
        assertEquals(expected.getSoftSkills(), actual.getSoftSkills());
        assertEquals(expected.getHardSkills(), actual.getHardSkills());
        assertEquals(expected.getQualification(), actual.getQualification());
        assertEquals(expected.getSalary(), actual.getSalary());
        assertEquals(expected.getExperience(), actual.getExperience());
        assertEquals(expected.getCountry(), actual.getCountry());
        assertEquals(expected.getCity(), actual.getCity());
        assertEquals(expected.getEmployer(), actual.getEmployer());
    }

    private Vacancy getVacancy() {
        Vacancy vacancy = new Vacancy();

        vacancy.setId(1L);
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

    private Employer getEmployer(Long employerId) {
        Employer employer = new Employer();
        employer.setId(employerId);
        employer.setName("employer name");
        return employer;
    }
}
