package nl.meritude.apimodule.endpoint;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import nl.meritude.apimodule.model.PasswordRequest;
import nl.meritude.apimodule.model.ProfileRequest;
import nl.meritude.apimodule.util.JwtService;
import nl.meritude.apimodule.util.JwtUtil;
import nl.meritude.businessmodule.exception.BusinessException;
import nl.meritude.businessmodule.service.UserService;
import nl.meritude.persistencemodule.domain.Employee;
import nl.meritude.persistencemodule.domain.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

import static nl.meritude.apimodule.filter.JwtRequestFilter.AUTHORIZATION_HEADER;
import static nl.meritude.apimodule.filter.JwtRequestFilter.BEARER_PREFIX;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(ProfileController.class)
public class ProfileControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    UserService userBeanMock;

    @MockBean
    JwtService jwtServiceMock;

    @AfterEach
    void tearDown() {
        reset(userBeanMock);
        reset(jwtServiceMock);
    }

    @Test
    void testUpdateProfileReturnsStatus105() throws Exception {
        // given
        String profileRequest = getProfileRequestAsJsonString();
        User user = getEmployeeUser();
        when(jwtServiceMock.getUserId(any(HttpServletRequest.class))).thenReturn(1L);
        when(userBeanMock.getUser(1L)).thenReturn(user);

        // when
        mockMvc.perform(
                post("/profile")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(profileRequest)
                    .header(AUTHORIZATION_HEADER, BEARER_PREFIX + JwtUtil.createJWT(1L, "userTypeValue", "subjectValue"))
                )

                // then
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.statuscode", is(105)))
                .andExpect(jsonPath("$.body", is("Edited successfully")));

        verify(userBeanMock).updateUser(user);
        assertEquals("nameFromRequest", user.getName());
        assertEquals("0612121212", user.getPhoneNr());
    }

    @Test
    void testUpdateProfileReturnsStatus405_exceptionWasThrown() throws Exception {
        // given
        String profileRequest = getProfileRequestAsJsonString();
        when(jwtServiceMock.getUserId(any(HttpServletRequest.class))).thenReturn(1L);
        when(userBeanMock.getUser(1L)).thenThrow(new BusinessException("foutje"));

        // when
        mockMvc.perform(
                post("/profile")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(profileRequest)
                    .header(AUTHORIZATION_HEADER, BEARER_PREFIX + JwtUtil.createJWT(1L, "userTypeValue", "subjectValue"))
                )

                // then
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.statuscode", is(405)))
                .andExpect(jsonPath("$.body", is("Invalid action")));
    }

    @Test
    void testGetProfileReturnsStatus205() throws Exception {
        // given
        User user = getEmployeeUser();
        when(jwtServiceMock.getUserId(any(HttpServletRequest.class))).thenReturn(1L);
        when(userBeanMock.getUser(1L)).thenReturn(user);

        // when
        mockMvc.perform(
                get("/profile")
                    .header(AUTHORIZATION_HEADER, BEARER_PREFIX + JwtUtil.createJWT(1L, "userTypeValue", "subjectValue"))
                )

                // then
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.statuscode", is(205)))
                .andExpect(jsonPath("$.body.id", is(user.getId().intValue())))
                .andExpect(jsonPath("$.body.email", is(user.getEmail())))
                .andExpect(jsonPath("$.body.name", is(user.getName())))
                .andExpect(jsonPath("$.body.phoneNr", is(user.getPhoneNr())));
    }

    @Test
    void testGetProfileReturnsStatus405_exceptionWasThrown() throws Exception {
        // given
        when(jwtServiceMock.getUserId(any(HttpServletRequest.class))).thenReturn(1L);
        when(userBeanMock.getUser(1L)).thenThrow(new BusinessException("foutje"));

        // when
        mockMvc.perform(
                get("/profile")
                    .header(AUTHORIZATION_HEADER, BEARER_PREFIX + JwtUtil.createJWT(1L, "userTypeValue", "subjectValue"))
                )

                // then
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.statuscode", is(405)))
                .andExpect(jsonPath("$.body", is("Invalid action")));
    }

    @Test
    void testUpdatePasswordReturnsStatus105() throws Exception {
        // given
        String passwordRequest = getPasswordRequestAsJsonString();
        User user = getEmployeeUser();
        when(jwtServiceMock.getUserId(any(HttpServletRequest.class))).thenReturn(1L);
        when(userBeanMock.getUser(1L)).thenReturn(user);
        when(userBeanMock.login("email", "password")).thenReturn(user);

        // when
        mockMvc.perform(
                post("/password")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(passwordRequest)
                    .header(AUTHORIZATION_HEADER, BEARER_PREFIX + JwtUtil.createJWT(1L, "userTypeValue", "subjectValue"))
                )

                // then
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.statuscode", is(105)))
                .andExpect(jsonPath("$.body", is("Edited successfully")));

        verify(userBeanMock).updateUser(user);
        assertEquals("newpass", user.getPassword());
    }

    @Test
    void testUpdatePasswordReturnsStatus405_exceptionWasThrown() throws Exception {
        // given
        String passwordRequest = getPasswordRequestAsJsonString();
        when(jwtServiceMock.getUserId(any(HttpServletRequest.class))).thenReturn(1L);
        when(userBeanMock.getUser(1L)).thenThrow(new BusinessException("foutje"));

        // when
        mockMvc.perform(
                post("/password")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(passwordRequest)
                    .header(AUTHORIZATION_HEADER, BEARER_PREFIX + JwtUtil.createJWT(1L, "userTypeValue", "subjectValue"))
                )

                // then
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.statuscode", is(405)))
                .andExpect(jsonPath("$.body", is("Invalid action")));
    }

    private String getProfileRequestAsJsonString() throws JsonProcessingException {
        ObjectWriter writer = new ObjectMapper().writer().withDefaultPrettyPrinter();
        return writer.writeValueAsString(new ProfileRequest("nameFromRequest", "0612121212"));
    }

    private String getPasswordRequestAsJsonString() throws JsonProcessingException {
        ObjectWriter writer = new ObjectMapper().writer().withDefaultPrettyPrinter();
        return writer.writeValueAsString(new PasswordRequest("email", "oldpass", "newpass"));
    }

    private User getEmployeeUser() {
        Employee user = new Employee();

        user.setId(1L);
        user.setEmail("email");
        user.setPassword("password");
        user.setName("name");
        user.setPhoneNr("0612345678");

        user.setSoftSkills(Arrays.asList("softskill-1","softskill-2"));
        user.setSoftSkills(Arrays.asList("hardskill-1","hardskill-2"));
        user.setJobPreference(Arrays.asList("jobpreference-1", "jobpreference-2"));
        user.setCompanyPreference(Arrays.asList("companypreference-1", "companypreference-2"));
        user.setSalaryAim(1);
        user.setYearsOfExperience(1);
        user.setCountry("country");
        user.setCity("city");

        return user;
    }
}
