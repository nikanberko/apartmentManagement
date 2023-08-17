package com.properbooker.apartmentmanagement.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.function.Function;

import static com.properbooker.apartmentmanagement.constants.securityConstants.Constants.SIGNING_KEY;

@Component
public class TokenProvider implements Serializable {
    private String generatedToken;

    public String getUsernameFromToken(final String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

   /* public String getUsernameFromToken(final String token) {
        Claims claims = getAllClaimsFromToken(token);
        Object userIdObject = claims.get("username");

        if (userIdObject instanceof String) {
            return (String) userIdObject;
        } else if (userIdObject instanceof Integer) {
            return String.valueOf(userIdObject);
        } else {
            throw new IllegalArgumentException("Invalid user ID type in token claims");
        }    }*/
    public String getUserIdFromToken(final String token) {
        Claims claims = getAllClaimsFromToken(token);
        Object userIdObject = claims.get("userId");

        if (userIdObject instanceof String) {
            return (String) userIdObject;
        } else if (userIdObject instanceof Integer) {
            return String.valueOf(userIdObject);
        } else {
            throw new IllegalArgumentException("Invalid user ID type in token claims");
        }    }

    public Date getExpirationDateFromToken(final String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(final String token, final Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    public Claims getAllClaimsFromToken(final String token) {
        System.out.println(token);
        return Jwts.parser()
                .setSigningKey("secret-key".getBytes())
                .parseClaimsJws(token)
                .getBody();
    }

    private Boolean isTokenExpired(final String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }
}
