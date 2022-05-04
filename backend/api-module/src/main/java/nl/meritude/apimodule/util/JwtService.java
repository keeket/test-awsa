package nl.meritude.apimodule.util;

import io.jsonwebtoken.Claims;
import nl.meritude.apimodule.model.UserType;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

import static nl.meritude.apimodule.util.JwtUtil.JWT_KEY_USERID;
import static nl.meritude.apimodule.util.JwtUtil.JWT_KEY_USERTYPE;

@Service
public class JwtService {

    public boolean userIsOfUsertype(HttpServletRequest req, UserType userType) {
        Claims claims = JwtUtil.getClaimsFromRequest(req);
        String userTypeToCheck = (String) claims.get(JWT_KEY_USERTYPE);
        return userTypeToCheck.equals(userType.name());
    }

    public Long getUserId(HttpServletRequest req) {
        Claims claims = JwtUtil.getClaimsFromRequest(req);
        return new Long(claims.get(JWT_KEY_USERID).toString());
    }

    public String createJWT(long uid, String userType, String subject) {
        return JwtUtil.createJWT(uid, userType, subject);
    }
}
