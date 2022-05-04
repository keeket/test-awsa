package nl.meritude.apimodule.util;

import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;

import java.util.Arrays;
import java.util.List;

import static nl.meritude.apimodule.filter.JwtRequestFilter.AUTHORIZATION_HEADER;
import static nl.meritude.apimodule.filter.JwtRequestFilter.BEARER_PREFIX;
import static nl.meritude.apimodule.util.JwtUtil.JWT_KEY_USERID;
import static nl.meritude.apimodule.util.JwtUtil.JWT_KEY_USERTYPE;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class JwtUtilTest {

    private List<String> expectedClaims = Arrays.asList("iat", "exp", "sub", "iss", JWT_KEY_USERTYPE, JWT_KEY_USERID);

    @Test
    public void testCreateJwt() {
        // given
        String jwt = JwtUtil.createJWT(1L, "userTypeValue", "subjectValue");

        // when
        JwtUtil.decodeJWT(jwt);

        // then
        // test is succesful if no error is thrown
    }

    @Test
    public void testExpectedClaimsAvailable() {
        // given
        String jwt = JwtUtil.createJWT(1L, "userTypeValue", "subjectValue");

        // when
        Claims resultClaims = JwtUtil.decodeJWT(jwt);

        // then
        assertClaims(resultClaims);
    }

    @Test
    public void testExpectedClaimsAvailableFromRequest() {
        // given
        MockHttpServletRequest request = new MockHttpServletRequest();
        String jwt = JwtUtil.createJWT(1L, "userTypeValue", "subjectValue");
        request.addHeader(AUTHORIZATION_HEADER, BEARER_PREFIX + jwt);

        // when
        Claims resultClaims = JwtUtil.getClaimsFromRequest(request);

        // then
        assertClaims(resultClaims);
    }

    private void assertClaims(Claims claims) {
        for (String claim : expectedClaims) {
            assertTrue(claims.get(claim) != null);
        }
    }
}
