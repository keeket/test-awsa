package nl.meritude.apimodule.filter;

import nl.meritude.apimodule.util.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import javax.servlet.ServletException;

import org.springframework.mock.web.MockFilterChain;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import static nl.meritude.apimodule.filter.JwtRequestFilter.AUTHORIZATION_HEADER;
import static nl.meritude.apimodule.filter.JwtRequestFilter.BEARER_PREFIX;
import static nl.meritude.apimodule.filter.JwtRequestFilter.LOGIN_URL_PATH;
import static nl.meritude.apimodule.filter.JwtRequestFilter.REGISTER_URL_PATH;
import static org.assertj.core.api.Assertions.assertThat;

public class JwtRequestFilterTest {

    private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    private MockFilterChain chain;

    @BeforeEach
    public void setup() {
        this.request = new MockHttpServletRequest();
        this.response = new MockHttpServletResponse();
        this.chain = new MockFilterChain();
    }

    @Test
    public void testHandleSuccesfulJwtVerification() throws ServletException, IOException {
        // given
        String validJwt = JwtUtil.createJWT(1L, "userTypeValue", "subjectValue");
        this.request.addHeader(AUTHORIZATION_HEADER, BEARER_PREFIX + validJwt);
        JwtRequestFilter filterUnderTest = new JwtRequestFilter();

        // when
        filterUnderTest.doFilter(this.request, this.response, this.chain);

        // then
        assertThat(this.response.getStatus()).isEqualTo(200);
    }

    @Test
    public void testHandleNoAuthorizationHeader() throws ServletException, IOException {
        // given
        JwtRequestFilter filterUnderTest = new JwtRequestFilter();

        // when
        filterUnderTest.doFilter(this.request, this.response, this.chain);

        // then
        assertThat(this.response.getStatus()).isEqualTo(403);
        assertThat(this.response.getErrorMessage()).isEqualTo("Error parsing jwt");
    }

    @Test
    public void testHandleInvalidJwt() throws ServletException, IOException {
        // given
        this.request.addHeader(AUTHORIZATION_HEADER, BEARER_PREFIX + "invalid-jwt");
        JwtRequestFilter filterUnderTest = new JwtRequestFilter();

        // when
        filterUnderTest.doFilter(this.request, this.response, this.chain);

        // then
        assertThat(this.response.getStatus()).isEqualTo(403);
        assertThat(this.response.getErrorMessage()).isEqualTo("Error parsing jwt");
    }

    @Test
    public void testHandleExpiredJwt() throws ServletException, IOException {
        // given
        String expiredJwt = "eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2MzA3NjQ4MzUsImV4cCI6MTYzMDc2NDgzNSwic3ViIjoidXNlci1lbWFpbCIsImlzcyI6Ik1lcml0dWRlIiwidXNlcmlkIjoxLCJ1c2VydHlwZSI6InVzZXItdHlwZSJ9.jcczUf3uUUcPjqCpsgNcS1OTjCfAiECe9WzwbX7JdHI";
        this.request.addHeader(AUTHORIZATION_HEADER, BEARER_PREFIX + expiredJwt);

        JwtRequestFilter filterUnderTest = new JwtRequestFilter();

        // when
        filterUnderTest.doFilter(this.request, this.response, this.chain);

        // then
        assertThat(this.response.getStatus()).isEqualTo(403);
        assertThat(this.response.getErrorMessage()).isEqualTo("Expired Jwt");
    }

    @Test
    void testShouldNotFilterOnLoginOrRegisterUri() {
        // given
        MockHttpServletRequest loginUriRequest = new MockHttpServletRequest();
        MockHttpServletRequest registerUriRequest = new MockHttpServletRequest();
        MockHttpServletRequest shouldFilterRequest = new MockHttpServletRequest();
        loginUriRequest.setRequestURI(LOGIN_URL_PATH);
        registerUriRequest.setRequestURI(REGISTER_URL_PATH);
        shouldFilterRequest.setRequestURI("/filterThis");

        JwtRequestFilter filterUnderTest = new JwtRequestFilter();

        // when
        boolean resultLoginUri = filterUnderTest.shouldNotFilter(loginUriRequest);
        boolean resultRegisterUri = filterUnderTest.shouldNotFilter(registerUriRequest);
        boolean resultShouldFilterUri = filterUnderTest.shouldNotFilter(shouldFilterRequest);

        // then
        assertThat(resultLoginUri).isTrue();
        assertThat(resultRegisterUri).isTrue();
        assertThat(resultShouldFilterUri).isFalse();
    }

}
