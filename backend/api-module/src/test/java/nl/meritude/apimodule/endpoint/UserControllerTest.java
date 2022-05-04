package nl.meritude.apimodule.endpoint;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import nl.meritude.apimodule.model.UserProfileResponse;
import nl.meritude.apimodule.model.GenericRequest;
import nl.meritude.apimodule.model.UserType;
import nl.meritude.apimodule.util.JwtService;
import nl.meritude.businessmodule.exception.BusinessException;
import nl.meritude.businessmodule.service.JobService;
import nl.meritude.businessmodule.service.UserService;
import nl.meritude.persistencemodule.domain.Employee;
import nl.meritude.persistencemodule.domain.Employer;
import nl.meritude.persistencemodule.domain.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static nl.meritude.apimodule.model.UserType.EMPLOYEE;
import static nl.meritude.apimodule.model.UserType.EMPLOYER;
import static org.hamcrest.Matchers.is;

import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    UserService userBeanMock;

    @MockBean
    JobService jobBeanMock;

    @MockBean
    JwtService jwtServiceMock;

    @AfterEach
    void tearDown() {
        reset(userBeanMock);
        reset(jobBeanMock);
        reset(jwtServiceMock);
    }

    @Test
    void testRegisterReturnsStatus103() throws Exception {
        // given
        String genericRequest = getGenericRequestAsJsonString();
        UserProfileResponse expectedResponse = new UserProfileResponse(1L, "email");
        when(userBeanMock.registerEmployee("email", "password")).thenReturn(1L);

        // when
        mockMvc.perform(
                post("/api/register")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(genericRequest)
                )

                // then
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.statuscode", is(103)))
                .andExpect(jsonPath("$.body.id", is(expectedResponse.getId().intValue())))
                .andExpect(jsonPath("$.body.email", is(expectedResponse.getEmail())));
    }

    @Test
    void testRegisterReturnsStatus403_exceptionWasThrown() throws Exception {
        // given
        String genericRequest = getGenericRequestAsJsonString();
        when(userBeanMock.registerEmployee("email", "password")).thenThrow(new BusinessException("foutje"));

        // when
        mockMvc.perform(
                post("/api/register")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(genericRequest)
                )

                // then
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.statuscode", is(403)))
                .andExpect(jsonPath("$.body", is("Error registering user")));
    }

    @Test
    void testRegisterEmployerReturnsStatus103() throws Exception {
        // given
        String genericRequest = getGenericRequestAsJsonString();
        UserProfileResponse expectedResponse = new UserProfileResponse(1L, "email");
        when(userBeanMock.registerEmployer("email", "password")).thenReturn(1L);

        // when
        mockMvc.perform(
                post("/api/registerEmployer")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(genericRequest)
                )

                // then
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.statuscode", is(103)))
                .andExpect(jsonPath("$.body.id", is(expectedResponse.getId().intValue())))
                .andExpect(jsonPath("$.body.email", is(expectedResponse.getEmail())));
    }

    @Test
    void testRegisterEmployerReturnsStatus403_exceptionWasThrown() throws Exception {
        // given
        String genericRequest = getGenericRequestAsJsonString();
        when(userBeanMock.registerEmployer("email", "password")).thenThrow(new BusinessException("foutje"));

        // when
        mockMvc.perform(
                post("/api/registerEmployer")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(genericRequest)
                )

                // then
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.statuscode", is(403)))
                .andExpect(jsonPath("$.body", is("Error registering user")));
    }

    @Test
    void testLoginEmployeeReturnsStatus101() throws Exception {
        // given
        String genericRequest = getGenericRequestAsJsonString();
        User user = getUser(EMPLOYEE);
        String expectedJwt = "stubJwt";
        when(userBeanMock.login("email", "password")).thenReturn(user);
        when(jwtServiceMock.createJWT(1L, "employee", "email")).thenReturn(expectedJwt);

        // when
        mockMvc.perform(
                post("/api/login")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(genericRequest)
                )

                // then
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.statuscode", is(101)))
                .andExpect(jsonPath("$.body", is(expectedJwt)));
    }

    @Test
    void testLoginEmployerReturnsStatus101() throws Exception {
        // given
        String genericRequest = getGenericRequestAsJsonString();
        User user = getUser(EMPLOYER);
        String expectedJwt = "stubJwt";
        when(userBeanMock.login("email", "password")).thenReturn(user);
        when(jwtServiceMock.createJWT(1L, "employer", "email")).thenReturn(expectedJwt);

        // when
        mockMvc.perform(
                post("/api/login")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(genericRequest)
                )

                // then
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.statuscode", is(101)))
                .andExpect(jsonPath("$.body", is(expectedJwt)));
    }

    @Test
    void testLoginReturnsStatus401_exceptionWasThrown() throws Exception {
        // given
        String genericRequest = getGenericRequestAsJsonString();
        when(userBeanMock.login("email", "password")).thenThrow(new BusinessException("foutje"));

        // when
        mockMvc.perform(
                post("/api/login")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(genericRequest)
                )

                // then
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.statuscode", is(401)))
                .andExpect(jsonPath("$.body", is("Error during user login")));
    }

    private String getGenericRequestAsJsonString() throws JsonProcessingException {
        ObjectWriter writer = new ObjectMapper().writer().withDefaultPrettyPrinter();
        return writer.writeValueAsString(new GenericRequest("email", "password"));
    }

    private User getUser(UserType usertype) {
        return usertype.equals(EMPLOYEE) ? getEmployee() : getEmployer();
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
