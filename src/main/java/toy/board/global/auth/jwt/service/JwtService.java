package toy.board.global.auth.jwt.service;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.MacAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import toy.board.model.entity.Member;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.function.Function;

@Component
public class JwtService {

    private static final int ACCESS_TOKEN_EXPIRE_TIME = 30 * 60 * 1000;
    //TODO: jwt decoder 이용한 인증 부분 구현하기
    private final MacAlgorithm alg = Jwts.SIG.HS256;

    @Value("${jwt.secret_key}")
    private String secretKey;

    public String getAccessToken(String subject) {
        return generateToken(subject, ACCESS_TOKEN_EXPIRE_TIME);
    }


    public int getAccessTokenExpireTime() {
        return ACCESS_TOKEN_EXPIRE_TIME;
    }


    private String generateToken(String subject, int expirationTime) {
        return Jwts.builder()
                .signWith(convertSecretKey(secretKey), alg)
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
                    .verifyWith(convertSecretKey(secretKey))
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

    private static SecretKey convertSecretKey(String secretKey) {
        byte[] decodedKey = Base64.getDecoder().decode(secretKey);
        return new SecretKeySpec(decodedKey, 0, decodedKey.length, "HmacSHA256");
    }


}