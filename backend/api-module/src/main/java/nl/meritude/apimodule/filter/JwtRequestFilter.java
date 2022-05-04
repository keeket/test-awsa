package nl.meritude.apimodule.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import nl.meritude.apimodule.util.JwtUtil;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String BEARER_PREFIX = "Bearer ";
    public static final String LOGIN_URL_PATH = "/api/login";
    public static final String REGISTER_URL_PATH = "/api/register";

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain filter) throws ServletException, IOException {
        MDC.put("traceid", UUID.randomUUID().toString());
        log.info("New request on {}", req.getRequestURI());

        try {
            verifyRequest(req);
            filter.doFilter(req, res);
        } catch (ExpiredJwtException expiredJwtException) {
            log.error("Jwt expired");
            res.sendError(403, "Expired Jwt");
        } catch (Exception exception) {
            log.error("Error parsing jwt: {}", exception.getMessage());
            res.sendError(403, "Error parsing jwt");
        } finally {
            MDC.clear();
        }
    }

    private void verifyRequest(HttpServletRequest req) {
        // throws exception if request or jwt is invalid
        String authorizationHeader = req.getHeader(AUTHORIZATION_HEADER);
        String jwt = authorizationHeader.substring(BEARER_PREFIX.length());
        JwtUtil.decodeJWT(jwt);
    }

    private void printClaims(Claims claims) {
        for (String claim : claims.keySet()) {
            log.info("Claim: {}; Value: {}", claim, claims.get(claim));
        }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getRequestURI();
        return path.startsWith(LOGIN_URL_PATH) || path.startsWith(REGISTER_URL_PATH);
    }
}
