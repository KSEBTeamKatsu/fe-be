package Katsu.Katsu_spring.token;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

public class JwtUtil {

    private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public static String generateToken(String id) {
        long now = System.currentTimeMillis();
        long expiry = now + (1000 * 60 * 60); // 1시간 유효

        return Jwts.builder()
                .setSubject(id)
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(expiry))
                .signWith(key)
                .compact();
    }

    public static String validateToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}