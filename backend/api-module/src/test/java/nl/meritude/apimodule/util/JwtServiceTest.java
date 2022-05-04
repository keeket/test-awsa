package nl.meritude.apimodule.util;

import io.jsonwebtoken.Claims;
import nl.meritude.apimodule.model.UserType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletRequest;

import java.util.stream.Collectors;

import static nl.meritude.apimodule.filter.JwtRequestFilter.AUTHORIZATION_HEADER;
import static nl.meritude.apimodule.filter.JwtRequestFilter.BEARER_PREFIX;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class JwtServiceTest {

    @InjectMocks
    JwtService jwtService;

    @Test
    public void testUserIsUserTypeEmployee() {
        // given
        UserType expectedUserType = UserType.EMPLOYEE;
        MockHttpServletRequest request = new MockHttpServletRequest();
        String jwt = JwtUtil.createJWT(1L, expectedUserType.name(), "subjectValue");
        request.addHeader(AUTHORIZATION_HEADER, BEARER_PREFIX + jwt);

        // when
        boolean result = jwtService.userIsOfUsertype(request, expectedUserType);

        // then
        assertTrue(result);
    }

    @Test
    public void testUserIsUserTypeEmployer() {
        // given
        UserType expectedUserType = UserType.EMPLOYER;
        MockHttpServletRequest request = new MockHttpServletRequest();
        String jwt = JwtUtil.createJWT(1L, expectedUserType.name(), "subjectValue");
        request.addHeader(AUTHORIZATION_HEADER, BEARER_PREFIX + jwt);

        // when
        boolean result = jwtService.userIsOfUsertype(request, expectedUserType);

        // then
        assertTrue(result);
    }

    @Test
    public void testGetUserId() {
        // given
        Long expectedUserId = 1L;
        MockHttpServletRequest request = new MockHttpServletRequest();
        String jwt = JwtUtil.createJWT(expectedUserId, "userTypeValue", "subjectValue");
        request.addHeader(AUTHORIZATION_HEADER, BEARER_PREFIX + jwt);

        // when
        Long actualUserId = jwtService.getUserId(request);

        // then
        assertEquals(expectedUserId, actualUserId);
    }

    @Test
    public void testCreateJWT() {
        // given
        String utilJwt = JwtUtil.createJWT(1L, "userTypeValue", "subjectValue");
        Claims expectedClaims = JwtUtil.decodeJWT(utilJwt);
        int numberOfUnequalClaims = 0;

        // when
        String serviceJwt = jwtService.createJWT(1L, "userTypeValue", "subjectValue");
        Claims actualClaims = JwtUtil.decodeJWT(serviceJwt);

        // then
        assertEquals(numberOfUnequalClaims, filterUnequalClaims(expectedClaims, actualClaims));
    }

    private int filterUnequalClaims(Claims expectedClaims, Claims actualClaims) {
        return expectedClaims.entrySet().stream()
                .filter(claimEntry -> isNotTimestampedClaim(claimEntry.getKey()))
                .filter(claimEntry -> claimContentIsUnequal(claimEntry.getKey(), expectedClaims, actualClaims))
                .collect(Collectors.toList()).size();
    }

    private boolean isNotTimestampedClaim(String claimKeyname) {
        return !(claimKeyname.equals("iat") || claimKeyname.equals("exp"));
    }

    private boolean claimContentIsUnequal(String claimKeyname, Claims expected, Claims actual) {
        Object expectedClaimValue = expected.get(claimKeyname);
        Object actualClaimValue = actual.get(claimKeyname);

        return !expectedClaimValue.equals(actualClaimValue);
    }

}
