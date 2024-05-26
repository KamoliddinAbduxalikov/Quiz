package example.quiz.provider;

import example.quiz.domain.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtProvider {

    @Value("${jwt.token.expiration.mills}")
    @Getter
    private Long expiration;

    @Value("${jwt.token.secret.key}")
    private String secret;

    private SecretKey key;

    @PostConstruct
    public void init() {
        key = Keys.hmacShaKeyFor(secret.getBytes());
    }

    public String generateToken(UserEntity user) {
        return Jwts
                .builder()
                .subject(user.getEmail())
                .issuedAt(new Date())
                .claim("id",user.getId())
                .claim("username",user.getUsername())
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(key)
                .compact();
    }

    public boolean validateToken(String token) {
        try{
            final Claims claims = parseClams(token);
            if (claims.getExpiration().before(new Date())){
                return false;
            }
        }catch (JwtException e){
            return false;
        }
        return true;
    }

    public Claims parseClams(String token){
        return Jwts
                .parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
