package bill.management.electricity.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.*;
import java.util.function.Function;

@Component
public class JWTService {
    public static final String SECRET ="fVfbAWEQKkKI+5Ymwvrish1EyGoeuMcN6/34l001B8lVnIVGZU62efKH2MUg/B1G/ibeBzRKFPJmQoCHl6cj3Pq0ND6w+iW9zNrbLB9LJTkcOwYYuNdGzSoBcrNxU587ip0t6OCnonEEC4QFROcq0oAN7E5+M61gmfQTaFwxvyklGs5OaO4QuQVovcepObzVwjSoFTJ+sbnjcIsMZTLIL9eTS8FZynRBD+JvABYWKM0MshoL6zWOg3L5iJjcWvYg1Pf8Nz79XnWi0pwFT6J9fxWrNYAziLONT6MkRKcKH1bhAtg1qJ9Qlov3+dDCKOeK52iTR5FG75g9Exffd4pW8+Cdxj9TO3Z8H8ArC8DKiSQ=";

    public String extractUserName(String token){
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token){
        return extractClaim(token,Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims,T> claimsResolver){
        final Claims claims= extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token){
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Boolean isTokenExpired(String token){
        return  extractExpiration(token).before(new Date());
    }

    public Boolean validateToken(String token, UserDetails userDetails){
        final String username=extractUserName(token);
        return (username.equals(userDetails.getUsername())&& !isTokenExpired(token));
    }


    public String generateToken(String name){
        Map<String, Object> claims=new HashMap<>();
        return  createToken(claims,name);
    }

    private String createToken(Map<String,Object>claims,String name){
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(name)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*60*24))
                .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
    }

    private Key getSignKey(){
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
