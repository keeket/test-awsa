package nl.meritude.persistencemodule.service;

import nl.meritude.persistencemodule.domain.Employee;
import nl.meritude.persistencemodule.exception.PersistenceException;
import nl.meritude.persistencemodule.repos.EmployeeRepo;
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
public class EmployeeServiceTest {

    @Mock
    private EmployeeRepo employeeRepoMock;

    @InjectMocks
    private EmployeeService databaseBean;

    @Test
    void testNewEmployeeReturnsEmployeeId() {
        // given
        Employee employee = getEmployee();
        Employee persistedEmployee = getPersistedEmployee(1L);
        Long expectedEmployeeId = persistedEmployee.getId();
        when(employeeRepoMock.save(employee)).thenReturn(persistedEmployee);

        // when
        Long actualEmployeeId = databaseBean.newEmployee(employee);

        // then
        verify(employeeRepoMock).save(employee);
        assertEquals(expectedEmployeeId, actualEmployeeId);
    }

    @Test
    void testGetEmployeeByIdReturnsEmployee() throws PersistenceException {
        // given
        Employee expectedEmployee = getPersistedEmployee(1L);
        Long employeeId = expectedEmployee.getId();
        when(employeeRepoMock.findById(employeeId)).thenReturn(Optional.of(expectedEmployee));

        // when
        Employee actualEmployee = databaseBean.getEmployee(employeeId);

        // then
        assertEquals(expectedEmployee, actualEmployee);
    }

    @Test
    void testGetEmployeeByIdThrowsPersistenceException() {
        // given
        String expectedErrorMessage = "Employee does not exist with id: 1";
        when(employeeRepoMock.findById(1L)).thenReturn(Optional.empty());

        // when
        Exception exception = assertThrows(PersistenceException.class, () -> {
            databaseBean.getEmployee(1L);
        });

        // then
        assertEquals(expectedErrorMessage, exception.getMessage());
    }

    @Test
    void testGetEmployeeByEmailReturnsEmployee() throws PersistenceException {
        // given
        Employee expectedEmployee = getPersistedEmployee(1L);
        String testEmail = expectedEmployee.getEmail();
        when(employeeRepoMock.findByEmail(testEmail)).thenReturn(Optional.of(expectedEmployee));

        // when
        Employee actualEmployee = databaseBean.getEmployee("email");

        // then
        assertEquals(expectedEmployee, actualEmployee);
    }

    @Test
    void testGetEmployeeByEmailThrowsPersistenceException() {
        // given
        String testEmail = "email";
        String expectedErrorMessage = "No employee found with email: " + testEmail;
        when(employeeRepoMock.findByEmail(testEmail)).thenReturn(Optional.empty());

        // when
        Exception exception = assertThrows(PersistenceException.class, () -> {
            databaseBean.getEmployee(testEmail);
        });

        // then
        assertEquals(expectedErrorMessage, exception.getMessage());
    }

    @Test
    void testUpdateEmployee() {
        // given
        Employee employee = getPersistedEmployee(1L);

        // when
        databaseBean.updateEmployee(employee);

        // then
        verify(employeeRepoMock).save(employee);
    }

    private Employee getEmployee() {
        Employee employee = new Employee();

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

    private Employee getPersistedEmployee(Long employeeId) {
        Employee employee = getEmployee();

        employee.setId(employeeId);

        return employee;
    }
}
