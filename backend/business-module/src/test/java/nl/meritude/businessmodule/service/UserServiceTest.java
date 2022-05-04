package nl.meritude.businessmodule.service;

import nl.meritude.businessmodule.exception.BusinessException;
import nl.meritude.persistencemodule.domain.Employee;
import nl.meritude.persistencemodule.domain.Employer;
import nl.meritude.persistencemodule.domain.User;
import nl.meritude.persistencemodule.exception.PersistenceException;
import nl.meritude.persistencemodule.service.EmployeeService;
import nl.meritude.persistencemodule.service.EmployerService;
import nl.meritude.persistencemodule.service.PersistentUserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private EmployeeService employeeBeanMock;

    @Mock
    private EmployerService employerBeanMock;

    @Mock
    private PersistentUserService userBeanMock;

    @InjectMocks
    private UserService userService;

    @Test
    void testLoginReturnsUser() throws PersistenceException, BusinessException {
        // given
        User expectedUser = getUser(mock(Employee.class));
        when(userBeanMock.getUser("email")).thenReturn(expectedUser);

        // when
        User actualUser = userService.login("email", "password");

        // then
        Assertions.assertEquals(expectedUser, actualUser);
    }

    @Test
    void testLoginThrowsBusinessException_invalidPassword() throws PersistenceException {
        // given
        String expectedErrorMessage = "Invalid login details for email: email";
        String invalidPassword = "invalid";
        when(userBeanMock.getUser("email")).thenReturn(getUser(mock(Employee.class)));

        // when
        Exception exception = assertThrows(BusinessException.class, () -> {
            userService.login("email", invalidPassword);
        });

        // then
        Assertions.assertEquals(expectedErrorMessage, exception.getMessage());
    }

    @Test
    void testLoginThrowsBusinessException_caughtPersistenceException() throws PersistenceException {
        // given
        String expectedErrorMessage = "foutje";
        when(userBeanMock.getUser("email")).thenThrow(new PersistenceException(expectedErrorMessage));

        // when
        Exception exception = assertThrows(BusinessException.class, () -> {
            userService.login("email", "password");
        });

        // then
        Assertions.assertEquals(expectedErrorMessage, exception.getMessage());
    }

    @Test
    void testLoginEmployeeReturnsEmployee() throws PersistenceException, BusinessException {
        // given
        Employee expectedUser = getUser(mock(Employee.class));
        when(employeeBeanMock.getEmployee("email")).thenReturn(expectedUser);

        // when
        User actualUser = userService.loginEmployee("email", "password");

        // then
        Assertions.assertEquals(expectedUser, actualUser);
    }

    @Test
    void testLoginEmployeeThrowsBusinessException_invalidPassword() throws PersistenceException {
        // given
        String expectedErrorMessage = "Invalid login details for email: email";
        String invalidPassword = "invalid";
        when(employeeBeanMock.getEmployee("email")).thenReturn(getUser(mock(Employee.class)));

        // when
        Exception exception = assertThrows(BusinessException.class, () -> {
            userService.loginEmployee("email", invalidPassword);
        });

        // then
        Assertions.assertEquals(expectedErrorMessage, exception.getMessage());
    }

    @Test
    void testLoginEmployeeThrowsBusinessException_caughtPersistenceException() throws PersistenceException {
        // given
        String expectedErrorMessage = "foutje";
        when(employeeBeanMock.getEmployee("email")).thenThrow(new PersistenceException(expectedErrorMessage));

        // when
        Exception exception = assertThrows(BusinessException.class, () -> {
            userService.loginEmployee("email", "password");
        });

        // then
        Assertions.assertEquals(expectedErrorMessage, exception.getMessage());
    }

    @Test
    void testLoginEmployerReturnsEmployee() throws PersistenceException, BusinessException {
        // given
        Employer expectedUser = getUser(mock(Employer.class));
        when(employerBeanMock.getEmployer("email")).thenReturn(expectedUser);

        // when
        User actualUser = userService.loginEmployer("email", "password");

        // then
        Assertions.assertEquals(expectedUser, actualUser);
    }

    @Test
    void testLoginEmployerThrowsBusinessException_invalidPassword() throws PersistenceException {
        // given
        String expectedErrorMessage = "Invalid login details for email: email";
        String invalidPassword = "invalid";
        when(employerBeanMock.getEmployer("email")).thenReturn(getUser(mock(Employer.class)));

        // when
        Exception exception = assertThrows(BusinessException.class, () -> {
            userService.loginEmployer("email", invalidPassword);
        });

        // then
        Assertions.assertEquals(expectedErrorMessage, exception.getMessage());
    }

    @Test
    void testLoginEmployerThrowsBusinessException_caughtPersistenceException() throws PersistenceException {
        // given
        String expectedErrorMessage = "foutje";
        when(employerBeanMock.getEmployer("email")).thenThrow(new PersistenceException(expectedErrorMessage));

        // when
        Exception exception = assertThrows(BusinessException.class, () -> {
            userService.loginEmployer("email", "password");
        });

        // then
        Assertions.assertEquals(expectedErrorMessage, exception.getMessage());
    }

    @Test
    void testRegisterUserReturnsUserId() throws BusinessException, PersistenceException {
        // given
        User expectedUser = getUser(mock(Employee.class));
        when(userBeanMock.getUser("email")).thenThrow(new PersistenceException("email not yet in use"));
        when(userBeanMock.newUser(expectedUser)).thenReturn(1L);

        // when
        Long actualUserId = userService.registerUser(expectedUser);

        // then
        assertEquals(expectedUser.getId().intValue(), actualUserId);
    }

    @Test
    void testRegisterUserThrowsException_emailAlreadyInUse() {
        // given
        String expectedErrorMessage = "User already registered for email: email";

        // when
        Exception exception = assertThrows(BusinessException.class, () -> {
            userService.registerUser(getUser(mock(Employee.class)));
        });

        // then
        assertEquals(expectedErrorMessage, exception.getMessage());
    }

    @Test
    void testGetUserReturnsUser() throws PersistenceException, BusinessException {
        // given
        User expectedUser = getUser(mock(Employee.class));
        when(userBeanMock.getUser(1L)).thenReturn(expectedUser);

        // when
        User actualUser = userService.getUser(1L);

        // then
        assertEquals(actualUser, expectedUser);
    }

    @Test
    void testGetUserThrowsException_caughtPersistenceException() throws PersistenceException {
        // given
        String expectedErrorMessage = "foutje";
        when(userBeanMock.getUser(1L)).thenThrow(new PersistenceException(expectedErrorMessage));

        // when
        Exception exception = assertThrows(BusinessException.class, () -> {
            userService.getUser(1L);
        });

        // then
        assertEquals(expectedErrorMessage, exception.getMessage());
    }

    @Test
    void testGetEmployerReturnsEmployer() throws PersistenceException, BusinessException {
        // given
        Employer expectedEmployer = getUser(mock(Employer.class));
        when(employerBeanMock.getEmployer(1L)).thenReturn(expectedEmployer);

        // when
        Employer actualEmployer = userService.getEmployer(1L);

        // then
        assertEquals(actualEmployer, expectedEmployer);
    }

    @Test
    void testGetEmployerThrowsException_caughtPersistenceException() throws PersistenceException {
        // given
        String expectedErrorMessage = "foutje";
        when(employerBeanMock.getEmployer(1L)).thenThrow(new PersistenceException(expectedErrorMessage));

        // when
        Exception exception = assertThrows(BusinessException.class, () -> {
            userService.getEmployer(1L);
        });

        // then
        assertEquals(expectedErrorMessage, exception.getMessage());
    }

    @Test
    void testGetEmployeeReturnsEmployee() throws PersistenceException, BusinessException {
        // given
        Employee expectedEmployee = getUser(mock(Employee.class));
        when(employeeBeanMock.getEmployee(1L)).thenReturn(expectedEmployee);

        // when
        Employee actualEmployee = userService.getEmployee(1L);

        // then
        assertEquals(actualEmployee, expectedEmployee);
    }

    @Test
    void testGetEmployeeThrowsException_caughtPersistenceException() throws PersistenceException {
        // given
        String expectedErrorMessage = "foutje";
        when(employeeBeanMock.getEmployee(1L)).thenThrow(new PersistenceException(expectedErrorMessage));

        // when
        Exception exception = assertThrows(BusinessException.class, () -> {
            userService.getEmployee(1L);
        });

        // then
        assertEquals(expectedErrorMessage, exception.getMessage());
    }

    @Test
    void testUpdateUser() {
        // given
        User expectedUser = getUser(mock(Employee.class));

        // when
        userService.updateUser(expectedUser);

        // then
        verify(userBeanMock).updateUser(expectedUser);
    }

    private <T extends User> T getUser(T usertype) {
        return (usertype instanceof Employee) ? (T) getEmployee() : (T) getEmployer();
    }

    private Employee getEmployee() {
        Employee employee = new Employee();

        employee.setId(1L);
        employee.setEmail("email");
        employee.setPassword("password");
        employee.setName("name");
        employee.setPhoneNr("0612345678");

        employee.setSoftSkills(Arrays.asList("softskill-1","softskill-2"));
        employee.setSoftSkills(Arrays.asList("hardskill-1","hardskill-2"));
        employee.setJobPreference(Arrays.asList("jobpreference-1", "jobpreference-2"));
        employee.setCompanyPreference(Arrays.asList("companypreference-1", "companypreference-2"));
        employee.setSalaryAim(1);
        employee.setYearsOfExperience(1);
        employee.setCountry("country");
        employee.setCity("city");

        return employee;
    }

    private Employer getEmployer() {
        Employer employer = new Employer();

        employer.setId(1L);
        employer.setEmail("email");
        employer.setPassword("password");
        employer.setName("name");
        employer.setPhoneNr("0612345678");

        return employer;
    }
}