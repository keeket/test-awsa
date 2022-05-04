package nl.meritude.apimodule.util;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;
import nl.meritude.apimodule.model.UserType;

import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

import static nl.meritude.apimodule.filter.JwtRequestFilter.AUTHORIZATION_HEADER;
import static nl.meritude.apimodule.filter.JwtRequestFilter.BEARER_PREFIX;

public class JwtUtil {
    public final static String JWT_KEY_USERID = "userid";
    public final static String JWT_KEY_USERTYPE = "usertype";

    private final static int EXPIRY_TIMEOUT = 1000 * 60 * 10;   // set JWT expiry time to 10 minutes
    private final static String SECRET_KEY = "super_secret";
    private final static String ISSUER = "Meritude";

    public static String createJWT(Long uid, String userType, String subject) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        Date now = new Date(System.currentTimeMillis());
        Date expiryTimeout = new Date(System.currentTimeMillis() + EXPIRY_TIMEOUT);

        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        JwtBuilder builder = Jwts.builder()
                .setIssuedAt(now)
                .setExpiration(expiryTimeout)
                .setSubject(subject)
                .setIssuer(ISSUER)
                .signWith(signatureAlgorithm, signingKey)
                .claim(JWT_KEY_USERID, uid)
                .claim(JWT_KEY_USERTYPE, userType.toUpperCase());

        return builder.compact();
    }

    public static Claims decodeJWT(String jwt) {
        return Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
                .parseClaimsJws(jwt).getBody();
    }

    public static Claims getClaimsFromRequest(HttpServletRequest req) {
        String authorizationHeader = req.getHeader(AUTHORIZATION_HEADER);
        String jwt = authorizationHeader.substring(BEARER_PREFIX.length());
        return decodeJWT(jwt);
    }
}
