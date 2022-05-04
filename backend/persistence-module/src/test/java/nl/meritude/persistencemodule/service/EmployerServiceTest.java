package nl.meritude.persistencemodule.service;

import nl.meritude.persistencemodule.domain.Employer;
import nl.meritude.persistencemodule.domain.Employer;
import nl.meritude.persistencemodule.exception.PersistenceException;
import nl.meritude.persistencemodule.repos.EmployerRepo;
import nl.meritude.persistencemodule.repos.EmployerRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployerServiceTest {

    @Mock
    private EmployerRepo employerRepoMock;

    @InjectMocks
    private EmployerService databaseBean;

    @Test
    void testNewEmployerReturnsEmployerId() {
        // given
        Employer employer = getEmployer();
        Employer persistedEmployer = getPersistedEmployer(1L);
        Long expectedEmployerId = persistedEmployer.getId();
        when(employerRepoMock.save(employer)).thenReturn(persistedEmployer);

        // when
        Long actualEmployerId = databaseBean.newEmployer(employer);

        // then
        verify(employerRepoMock).save(employer);
        assertEquals(expectedEmployerId, actualEmployerId);
    }

    @Test
    void testGetEmployerByIdReturnsEmployer() throws PersistenceException {
        // given
        Employer expectedEmployer = getPersistedEmployer(1L);
        Long expectedEmployerId = expectedEmployer.getId();
        when(employerRepoMock.findById(expectedEmployerId)).thenReturn(Optional.of(expectedEmployer));

        // when
        Employer actualEmployer = databaseBean.getEmployer(expectedEmployerId);

        // then
        assertEquals(expectedEmployer, actualEmployer);
    }

    @Test
    void testGetEmployerByIdThrowsPersistenceException() {
        // given
        String expectedErrorMessage = "Employer does not exist with id: 1";
        when(employerRepoMock.findById(1L)).thenReturn(Optional.empty());

        // when
        Exception exception = assertThrows(PersistenceException.class, () -> {
            databaseBean.getEmployer(1L);
        });

        // then
        assertEquals(expectedErrorMessage, exception.getMessage());
    }

    @Test
    void testGetEmployerByEmailReturnsEmployer() throws PersistenceException {
        // given
        Employer expectedEmployer = getPersistedEmployer(1L);
        String testEmail = expectedEmployer.getEmail();
        when(employerRepoMock.findByEmail(testEmail)).thenReturn(Optional.of(expectedEmployer));

        // when
        Employer actualEmployer = databaseBean.getEmployer("email");

        // then
        assertEquals(expectedEmployer, actualEmployer);
    }

    @Test
    void testGetEmployerByEmailThrowsPersistenceException() {
        // given
        String testEmail = "email";
        String expectedErrorMessage = "No employer found with email: " + testEmail;
        when(employerRepoMock.findByEmail(testEmail)).thenReturn(Optional.empty());

        // when
        Exception exception = assertThrows(PersistenceException.class, () -> {
            databaseBean.getEmployer(testEmail);
        });

        // then
        assertEquals(expectedErrorMessage, exception.getMessage());
    }

    @Test
    void testUpdateEmployer() {
        // given
        Employer employer = getPersistedEmployer(1L);

        // when
        databaseBean.updateEmployer(employer);

        // then
        verify(employerRepoMock).save(employer);
    }

    private Employer getEmployer() {
        Employer employer = new Employer();

        employer.setEmail("email");
        employer.setPassword("password");
        employer.setName("name");
        employer.setPhoneNr("0612345678");

        return employer;
    }

    private Employer getPersistedEmployer(Long employerId) {
        Employer employer = getEmployer();

        employer.setId(employerId);

        return employer;
    }
}
