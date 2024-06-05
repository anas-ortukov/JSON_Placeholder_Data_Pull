package uz.anas.homework.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.crypto.SecretKey;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtUtil {

    public String genToken(UserDetails userDetails) {
        String roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(","));
        return "Bearer " +  Jwts.builder()
                .subject(userDetails.getUsername())
                .claim("authorities", roles)
                .issuedAt(new Date())
                .issuer("homework.io")
                .expiration(new Date(System.currentTimeMillis() + 1000 * 10))
                .signWith(genKey())
                .compact();
    }

    private SecretKey genKey() {
        byte[] key = Decoders.BASE64.decode("1234567891a345678912345678g123456789123456d89123456789123456a890");
        return Keys.hmacShaKeyFor(key);
    }

    public String genRefreshToken(UserDetails userDetails) {
        String roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(","));
        return "Bearer " +  Jwts.builder()
                .subject(userDetails.getUsername())
                .claim("authorities", roles)
                .issuedAt(new Date())
                .issuer("homework.io")
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 14))
                .signWith(genKey())
                .compact();
    }

    public boolean isValid(String token) {
        try {
            getClaims(token);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    private Claims getClaims(String token) {
        return Jwts.parser()
                .verifyWith(genKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String getUsername(String token) {
        return getClaims(token).getSubject();
    }

    public List<GrantedAuthority> getAuthorities(String token) {
        String authorities = getClaims(token).get("authorities", String.class);
        return Arrays.stream(authorities.split(",")).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    public String getDataFromAPI(String URI) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(URI, String.class);
    }
}
