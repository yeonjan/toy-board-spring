package toy.board.global.auth.jwt.service;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import toy.board.model.entity.Member;

import java.security.KeyPair;
import java.security.interfaces.RSAPublicKey;
import java.util.Date;
import java.util.function.Function;

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

    public boolean isValid(String token, Member member) {
        Integer memberId = extractMemberId(token);
        return memberId.equals(member.getId());
    }

    public Integer extractMemberId(String token) {
        return Integer.valueOf(extractClaims(token, Claims::getSubject));
    }

    public <T> T extractClaims(String token, Function<Claims, T> resolver) {
        Claims claims = extractAllClaims(token);
        return resolver.apply(claims);

    }

    private Claims extractAllClaims(String token) {
        try {
            return Jwts.parser()
                    .verifyWith(pair.getPublic())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        } catch (MalformedJwtException e) { // 유효하지 않은 JWT
            //            throw new CustomException(TOKEN_INVALID);
            throw new MalformedJwtException("not valid jwt");
        } catch (ExpiredJwtException e) { // 만료된 JWT
            //            throw new CustomException(REFRESH_TOKEN_RENEWAL);
            throw new ExpiredJwtException(null, null, "expired");
        } catch (UnsupportedJwtException e) { // 지원하지 않는 JWT
            //            throw new CustomException(TOKEN_UNSUPPORTED);
            throw new UnsupportedJwtException("unsupported jwt");
        } catch (IllegalArgumentException e) { // 빈값
            //            throw new CustomException(TOKEN_NOT_FOUND);
            throw new IllegalArgumentException("empty jwt");
        }

    }

    public RSAPublicKey getPublicKey() {
        return (RSAPublicKey) pair.getPublic();
    }


}