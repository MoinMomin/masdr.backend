package interaction.cx.masdr.sa.backend.util;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.*;
import java.util.logging.Logger;

@Service
@NoArgsConstructor
public class JwtUtil {
    @Value("${jwt.secret}")
    private  String secretKey;

    @Value("${jwt.expiration-time}")
    private long expirationTime;

    public  Map<String,String> getClaimPropertiesFromToken(String token, List<String> properties) {
        try {
            // Parse the token and extract claims
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(secretKey) // Set the signing key

                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            // Retrieve the tenantId from claims
            Map<String,String> claimProperties =new HashMap<>();
            for (String property:  properties) {
                claimProperties.put(property,claims.get("tenantId", String.class));
            }
            return claimProperties;
        } catch (Exception e) {
            throw new RuntimeException("Invalid JWT token", e);
        }
    }
    public  String generateToken(String tenantId, String userId) {
        // Define claims
        Map<String, Object> claims = new HashMap<>();
        claims.put("tenantId", tenantId);
        claims.put("userId", userId);

        // Generate the token
        return Jwts.builder()
                .setClaims(claims) // Add claims
                .setSubject(userId) // Subject of the token
                .setIssuedAt(new Date()) // Token issue time
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime)) // Token expiration
                .signWith(SignatureAlgorithm.HS256, secretKey) // Sign the token
                .compact();
    }
}
