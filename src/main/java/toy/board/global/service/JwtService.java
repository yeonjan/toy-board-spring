package toy.board.global.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.security.KeyPair;
import java.security.interfaces.RSAPublicKey;
import java.util.Date;

@Component
public class JwtService {

    private static final long ACCESS_TOKEN_EXPIRE_TIME = 30 * 60 * 1000;
    //TODO: jwt decoder 이용한 인증 부분 구현하기
    private final SignatureAlgorithm alg = Jwts.SIG.RS512;
    private final KeyPair pair = alg.keyPair().build();


    public String getAccessToken(String subject) {
        return generateToken(subject, ACCESS_TOKEN_EXPIRE_TIME);
    }


    public long getAccessTokenExpireTime() {
        return ACCESS_TOKEN_EXPIRE_TIME;
    }


    private String generateToken(String subject, long expirationTime) {
        return Jwts.builder()
                .signWith(pair.getPrivate(), alg)
                .subject(subject)
                .expiration(new Date(System.currentTimeMillis() + expirationTime))
                .notBefore(new Date())
                .issuedAt(new Date())
                .compact();
    }

    public String getSubject(String token) {
        return Jwts.parser()
                .verifyWith(pair.getPublic())
                .build().parseSignedClaims(token).getPayload().getSubject();
    }

    public RSAPublicKey getPublicKey() {
        return (RSAPublicKey) pair.getPublic();
    }
}