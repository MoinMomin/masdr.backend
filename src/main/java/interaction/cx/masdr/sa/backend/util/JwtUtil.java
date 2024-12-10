package interaction.cx.masdr.sa.backend.util;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@Service
@NoArgsConstructor
public class JwtUtil {
    @Value("${jwt.secret}")
    private  String secretKey;

    @Value("${jwt.expiration-time}")
    private long expirationTime;

    public  String getTenantIdFromToken(String token) {
        try {
            // Parse the token and extract claims
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(secretKey) // Set the signing key
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            // Retrieve the tenantId from claims
            return claims.get("tenantId", String.class); // Assuming tenantId is stored as a claim
        } catch (Exception e) {
            throw new RuntimeException("Invalid JWT token", e);
        }
    }
    public  String generateToken(String tenantId, String username) {
        // Define claims
        Map<String, Object> claims = new HashMap<>();
        claims.put("tenantId", tenantId);
        claims.put("username", username);

        // Generate the token
        return Jwts.builder()
                .setClaims(claims) // Add claims
                .setSubject(username) // Subject of the token
                .setIssuedAt(new Date()) // Token issue time
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime)) // Token expiration
                .signWith(SignatureAlgorithm.HS256, secretKey) // Sign the token
                .compact();
    }
}
