//package net.javaguides.ems.utility;
//
//import io.jsonwebtoken.*;
//import io.jsonwebtoken.security.Keys;
//
//import java.security.Key;
//import java.util.Date;
//
//public class JwtUtil {
//    private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
//
//    public static String generateToken(String email) {
//        return Jwts.builder()
//                .setSubject(email)
//                .setIssuedAt(new Date())
//                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 1 day
//                .signWith(key)
//                .compact();
//    }
//}


package net.javaguides.ems.utility;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;

import java.security.Key;
import java.util.Date;

public class JwtUtil {

    // 🔥 strong + fixed key (>= 32 chars)
    private static final String SECRET =
            "my-super-secret-key-my-super-secret-key-my-super-secret-key";

    @Getter
    private static final Key key = Keys.hmacShaKeyFor(SECRET.getBytes());

    public static String generateToken(String email, String role) {
        return Jwts.builder()
                .setSubject(email)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000))
                .signWith(key,SignatureAlgorithm.HS256)
                .compact();
    }

}