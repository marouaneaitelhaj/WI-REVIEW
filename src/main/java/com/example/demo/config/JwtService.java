package com.example.demo.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
    public String extractUsername(String jwt) {
        return extractClaim(jwt, Claims::getSubject);
    }

    private Claims extractAllClaims(String jwt) {
        return Jwts.parserBuilder().setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(jwt)
                .getBody();
    }


    public String generateToken(UserDetails userDetails){
        return generateToken(new HashMap<>(), userDetails);
    }

    public boolean validateToken(String jwt, UserDetails userDetails){
        final String username = extractUsername(jwt);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(jwt));
    }

    private boolean isTokenExpired(String jwt) {
        return  extractExpiration(jwt).before(new Date());
    }

    private Date extractExpiration(String jwt) {
        return extractClaim(jwt, Claims::getExpiration);
    }


    private String generateToken(Map<String,Object> exctraClaims, UserDetails userDetails){
        return Jwts.builder()
                .setClaims(exctraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new java.util.Date(System.currentTimeMillis()))
                .setExpiration(new java.util.Date(System.currentTimeMillis() + 1000 * 60 * 24))
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Key getSignKey() {
        byte[] key = Decoders.BASE64.decode("ayrwwtjldywrexirqvbrbbeithefgjhcsogreixvffkgvbxhq");
        return Keys.hmacShaKeyFor(key);
    }
}
