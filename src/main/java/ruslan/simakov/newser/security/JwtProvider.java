package ruslan.simakov.newser.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import ruslan.simakov.newser.exeption.SpringKeyStoreException;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.time.Instant;
import java.util.Date;

import static io.jsonwebtoken.Jwts.parser;

@Service
public class JwtProvider {

    private KeyStore keyStore;

    @Value("${jwt.expiration.time}")
    private Long jwtExpirationInMilis;

    @PostConstruct
    public void init() {
        try {
            keyStore = KeyStore.getInstance("JKS");
            FileInputStream fileInputStream = new FileInputStream("D:\\Projects\\NEWSER\\src\\main\\resources\\newser.jks");
            keyStore.load(fileInputStream, "root1234".toCharArray());
        } catch (KeyStoreException | IOException | NoSuchAlgorithmException | CertificateException e) {
            throw new SpringKeyStoreException("Exception occurred while loading keystore!");
        }
    }

    public String generateToken(Authentication authentication) {
        User principal = (User) authentication.getPrincipal();
        return Jwts.builder()
                .setSubject(principal.getUsername())
                .signWith(getPrivateKey())
                .setExpiration(Date.from(Instant.now().plusMillis(jwtExpirationInMilis)))
                .compact();
    }

    private PrivateKey getPrivateKey() {
        try {
            return (PrivateKey) keyStore.getKey("newser", "root1234".toCharArray());
        } catch (KeyStoreException | UnrecoverableKeyException | NoSuchAlgorithmException e) {
            throw new SpringKeyStoreException("Exception occurred while retrieving private key from keystore!");
        }
    }

    public boolean validateToken(String jwt) {
        parser().setSigningKey(getPublicKey()).parseClaimsJws(jwt);
        return true;
    }

    private PublicKey getPublicKey() {
        try {
            return keyStore.getCertificate("newser").getPublicKey();
        } catch (KeyStoreException e) {
            throw new SpringKeyStoreException("Exception occurred while retrieving public key from keystore!");
        }
    }

    public String getUsernameFromJwt(String token) {
        Claims claims = parser()
                .setSigningKey(getPublicKey())
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }
}
