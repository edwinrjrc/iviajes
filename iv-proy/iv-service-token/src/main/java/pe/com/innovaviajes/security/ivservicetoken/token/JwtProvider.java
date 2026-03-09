/**
 * 
 */
package pe.com.innovaviajes.security.ivservicetoken.token;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

/**
 * @author Edwin
 *
 */
@Component
public class JwtProvider {

	// Esta clave debe tener al menos 64 caracteres para HS512
	@Value("${jwt.secret}")
	private String secret;

	@Value("${jwt.expiration:3600000}")
	private int expiration;

	private Key getSigningKey() {
		return Keys.hmacShaKeyFor(secret.getBytes());
	}

	public String generateAnonymousToken(String username, List<String> roles) {
        // Convertimos el secret string en una Key segura
        Key key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));

        return Jwts.builder()
                .setSubject(username)
                .claim("roles", roles)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 3600000)) // 1 hora
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }
	
	public String createToken(String username) {
        Key key = Keys.hmacShaKeyFor(secret.getBytes());
        
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 3600000)) // 1 hora
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }
}
