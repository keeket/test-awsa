package nl.meritude.apimodule.endpoint;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import nl.meritude.apimodule.model.JobRequest;
import nl.meritude.apimodule.util.JwtService;
import nl.meritude.apimodule.util.JwtUtil;
import nl.meritude.businessmodule.exception.BusinessException;
import nl.meritude.businessmodule.service.JobService;
import nl.meritude.businessmodule.service.UserService;
import nl.meritude.persistencemodule.domain.Employer;
import nl.meritude.persistencemodule.domain.Vacancy;
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
import static nl.meritude.apimodule.model.UserType.EMPLOYER;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(JobController.class)
public class JobControllerTest {

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
    void testGetJobReturnsStatus104() throws Exception {
        // given
        Vacancy vacancy = getVacancy();
        when(jobBeanMock.getJob(1L)).thenReturn(vacancy);

        // when
        mockMvc.perform(
                get("/job")
                    .param("jobId", "1")
                    .header(AUTHORIZATION_HEADER, BEARER_PREFIX + JwtUtil.createJWT(1L, "userTypeValue", "subjectValue"))
                )

                // then
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.statuscode", is(104)))
                .andExpect(jsonPath("$.body.jobId", is(vacancy.getId().intValue())))
                .andExpect(jsonPath("$.body.title", is(vacancy.getTitle())))
                .andExpect(jsonPath("$.body.softSkills", is(vacancy.getSoftSkills())))
                .andExpect(jsonPath("$.body.hardSkills", is(vacancy.getHardSkills())))
                .andExpect(jsonPath("$.body.qualifications", is(vacancy.getQualification())))
                .andExpect(jsonPath("$.body.salary", is(vacancy.getSalary())))
                .andExpect(jsonPath("$.body.experience", is(vacancy.getExperience())))
                .andExpect(jsonPath("$.body.country", is(vacancy.getCountry())))
                .andExpect(jsonPath("$.body.city", is(vacancy.getCity())))
                .andExpect(jsonPath("$.body.employerId", is(vacancy.getEmployer().getId().intValue())))
                .andExpect(jsonPath("$.body.employerName", is(vacancy.getEmployer().getName())));
    }

    @Test
    void testGetJobReturnsStatus404_exceptionWasThrown() throws Exception {
        // given
        when(jobBeanMock.getJob(1L)).thenThrow(new BusinessException("foutje"));

        // when
        mockMvc.perform(
                get("/job")
                    .param("jobId", "1")
                    .header(AUTHORIZATION_HEADER, BEARER_PREFIX + JwtUtil.createJWT(1L, "userTypeValue", "subjectValue"))
                )

                // then
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.statuscode", is(404)))
                .andExpect(jsonPath("$.body", is("Invalid action")));
    }

    @Test
    void testUpdateJobReturnsStatus104() throws Exception {
        // given
        Vacancy vacancy = getVacancy();
        String jobRequest = getJobRequestAsJsonString();
        when(jwtServiceMock.userIsOfUsertype(any(HttpServletRequest.class), eq(EMPLOYER))).thenReturn(true);
        when(jwtServiceMock.getUserId(any(HttpServletRequest.class))).thenReturn(1L);
        when(userBeanMock.getEmployer(1L)).thenReturn(getEmployer(1L));
        when(jobBeanMock.getJob(1L)).thenReturn(vacancy);

        // when
        mockMvc.perform(
                post("/job")
                    .param("jobId", "1")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(jobRequest)
                    .header(AUTHORIZATION_HEADER, BEARER_PREFIX + JwtUtil.createJWT(1L, "userTypeValue", "subjectValue"))
                )

                // then
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.statuscode", is(104)))
                .andExpect(jsonPath("$.body", is("Job updated")));

        verify(jobBeanMock).updateJob(vacancy);
    }

    @Test
    void testUpdateJobReturnsStatus404_employersDontMatch() throws Exception {
        // given
        Vacancy vacancy = getVacancy();
        String jobRequest = getJobRequestAsJsonString();
        when(jwtServiceMock.userIsOfUsertype(any(HttpServletRequest.class), eq(EMPLOYER))).thenReturn(true);
        when(jwtServiceMock.getUserId(any(HttpServletRequest.class))).thenReturn(1L);
        when(userBeanMock.getEmployer(1L)).thenReturn(getEmployer(2L));
        when(jobBeanMock.getJob(1L)).thenReturn(vacancy);

        // when
        mockMvc.perform(
                post("/job")
                    .param("jobId", "1")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(jobRequest)
                    .header(AUTHORIZATION_HEADER, BEARER_PREFIX + JwtUtil.createJWT(1L, "userTypeValue", "subjectValue"))
                )

                // then
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.statuscode", is(404)))
                .andExpect(jsonPath("$.body", is("Invalid action")));
    }

    @Test
    void testUpdateJobReturnsStatus404_userIsEmployee() throws Exception {
        // given
        String jobRequest = getJobRequestAsJsonString();
        when(jwtServiceMock.userIsOfUsertype(any(HttpServletRequest.class), eq(EMPLOYER))).thenReturn(false);

        // when
        mockMvc.perform(
                post("/job")
                    .param("jobId", "1")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(jobRequest)
                    .header(AUTHORIZATION_HEADER, BEARER_PREFIX + JwtUtil.createJWT(1L, "userTypeValue", "subjectValue"))
                )

                // then
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.statuscode", is(404)))
                .andExpect(jsonPath("$.body", is("Invalid action")));
    }

    @Test
    void testUpdateJobReturnsStatus404_exceptionWasThrown() throws Exception {
        // given
        String jobRequest = getJobRequestAsJsonString();
        when(jwtServiceMock.userIsOfUsertype(any(HttpServletRequest.class), eq(EMPLOYER))).thenReturn(true);
        when(jwtServiceMock.getUserId(any(HttpServletRequest.class))).thenReturn(1L);
        when(userBeanMock.getEmployer(1L)).thenThrow(new BusinessException("foutje"));

        // when
        mockMvc.perform(
                post("/job")
                    .param("jobId", "1")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(jobRequest)
                    .header(AUTHORIZATION_HEADER, BEARER_PREFIX + JwtUtil.createJWT(1L, "userTypeValue", "subjectValue"))
                )

                // then
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.statuscode", is(404)))
                .andExpect(jsonPath("$.body", is("Invalid action")));
    }

    @Test
    void testNewJobReturnsStatus104() throws Exception {
        // given
        Employer employer = getEmployer(1L);
        String jobRequest = getJobRequestAsJsonString();
        when(jwtServiceMock.userIsOfUsertype(any(HttpServletRequest.class), eq(EMPLOYER))).thenReturn(true);
        when(jwtServiceMock.getUserId(any(HttpServletRequest.class))).thenReturn(1L);
        when(userBeanMock.getEmployer(1L)).thenReturn(employer);
        when(jobBeanMock.newJob(
                "title",
                Arrays.asList("softskill-1","softskill-2"),
                Arrays.asList("hardskill-1","hardskill-2"),
                "qualification",
                1,
                1,
                "country",
                "city",
                employer
                )).thenReturn(1L);

        // when
        mockMvc.perform(
                put("/job")
                    .param("jobId", "1")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(jobRequest)
                    .header(AUTHORIZATION_HEADER, BEARER_PREFIX + JwtUtil.createJWT(1L, "userTypeValue", "subjectValue"))
                )

                // then
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.statuscode", is(104)))
                .andExpect(jsonPath("$.body", is(1)));
    }

    @Test
    void testNewJobReturnsStatus404_userIsEmployee() throws Exception {
        // given
        Employer employer = getEmployer(1L);
        String jobRequest = getJobRequestAsJsonString();
        when(jwtServiceMock.userIsOfUsertype(any(HttpServletRequest.class), eq(EMPLOYER))).thenReturn(false);

        // when
        mockMvc.perform(
                put("/job")
                    .param("jobId", "1")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(jobRequest)
                    .header(AUTHORIZATION_HEADER, BEARER_PREFIX + JwtUtil.createJWT(1L, "userTypeValue", "subjectValue"))
                )

                // then
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.statuscode", is(404)))
                .andExpect(jsonPath("$.body", is("Invalid action")));
    }

    @Test
    void testNewJobReturnsStatus404_exceptionWasThrown() throws Exception {
        // given
        String jobRequest = getJobRequestAsJsonString();
        when(jwtServiceMock.userIsOfUsertype(any(HttpServletRequest.class), eq(EMPLOYER))).thenReturn(true);
        when(jwtServiceMock.getUserId(any(HttpServletRequest.class))).thenReturn(1L);
        when(userBeanMock.getEmployer(1L)).thenThrow(new BusinessException("foutje"));

        // when
        mockMvc.perform(
                put("/job")
                    .param("jobId", "1")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(jobRequest)
                    .header(AUTHORIZATION_HEADER, BEARER_PREFIX + JwtUtil.createJWT(1L, "userTypeValue", "subjectValue"))
                )

                // then
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.statuscode", is(404)))
                .andExpect(jsonPath("$.body", is("Invalid action")));
    }

    @Test
    void testDeleteJobReturnsStatus104() throws Exception {
        // given
        when(jwtServiceMock.userIsOfUsertype(any(HttpServletRequest.class), eq(EMPLOYER))).thenReturn(true);
        when(jwtServiceMock.getUserId(any(HttpServletRequest.class))).thenReturn(1L);
        when(userBeanMock.getEmployer(1L)).thenReturn(getEmployer(1L));
        when(jobBeanMock.getJob(1L)).thenReturn(getVacancy());

        // when
        mockMvc.perform(
                delete("/job")
                    .param("jobId", "1")
                    .header(AUTHORIZATION_HEADER, BEARER_PREFIX + JwtUtil.createJWT(1L, "userTypeValue", "subjectValue"))
                )

                // then
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.statuscode", is(104)))
                .andExpect(jsonPath("$.body", is("Job deleted")));

        verify(jobBeanMock).deleteJob(1L);
    }

    @Test
    void testDeleteJobReturnsStatus404_employersDontMatch() throws Exception {
        // given
        when(jwtServiceMock.userIsOfUsertype(any(HttpServletRequest.class), eq(EMPLOYER))).thenReturn(true);
        when(jwtServiceMock.getUserId(any(HttpServletRequest.class))).thenReturn(1L);
        when(userBeanMock.getEmployer(1L)).thenReturn(getEmployer(2L));
        when(jobBeanMock.getJob(1L)).thenReturn(getVacancy());

        // when
        mockMvc.perform(
                delete("/job")
                    .param("jobId", "1")
                    .header(AUTHORIZATION_HEADER, BEARER_PREFIX + JwtUtil.createJWT(1L, "userTypeValue", "subjectValue"))
                )

                // then
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.statuscode", is(404)))
                .andExpect(jsonPath("$.body", is("Invalid action")));
    }

    @Test
    void testDeleteJobReturnsStatus404_userIsEmployee() throws Exception {
        // given
        when(jwtServiceMock.userIsOfUsertype(any(HttpServletRequest.class), eq(EMPLOYER))).thenReturn(false);

        // when
        mockMvc.perform(
                delete("/job")
                    .param("jobId", "1")
                    .header(AUTHORIZATION_HEADER, BEARER_PREFIX + JwtUtil.createJWT(1L, "userTypeValue", "subjectValue"))
                )

                // then
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.statuscode", is(404)))
                .andExpect(jsonPath("$.body", is("Invalid action")));
    }

    @Test
    void testDeleteJobReturnsStatus404_exceptionWasThrown() throws Exception {
        // given
        when(jwtServiceMock.userIsOfUsertype(any(HttpServletRequest.class), eq(EMPLOYER))).thenReturn(true);
        when(jwtServiceMock.getUserId(any(HttpServletRequest.class))).thenReturn(1L);
        when(userBeanMock.getEmployer(1L)).thenThrow(new BusinessException("foutje"));

        // when
        mockMvc.perform(
                delete("/job")
                    .param("jobId", "1")
                    .header(AUTHORIZATION_HEADER, BEARER_PREFIX + JwtUtil.createJWT(1L, "userTypeValue", "subjectValue"))
                )

                // then
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.statuscode", is(404)))
                .andExpect(jsonPath("$.body", is("Invalid action")));
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

    private JobRequest getJobRequest() {
        return JobRequest.builder()
                .title("title")
                .softSkills(Arrays.asList("softskill-1","softskill-2"))
                .hardSkills(Arrays.asList("hardskill-1","hardskill-2"))
                .qualifications("qualification")
                .salary(1)
                .experience(1)
                .country("country")
                .city("city")
                .build();
    }

    private String getJobRequestAsJsonString() throws JsonProcessingException {
        ObjectWriter writer = new ObjectMapper().writer().withDefaultPrettyPrinter();
        return writer.writeValueAsString(getJobRequest());
    }
}
