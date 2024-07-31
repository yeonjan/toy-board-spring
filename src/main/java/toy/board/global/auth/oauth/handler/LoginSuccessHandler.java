package toy.board.global.auth.oauth.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import toy.board.domain.member.service.MemberService;
import toy.board.global.auth.jwt.service.JwtService;
import toy.board.global.auth.oauth.dto.CustomOidcUser;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    private final MemberService memberService;
    private final JwtService jwtService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        if (authentication.getPrincipal() instanceof CustomOidcUser oidcUser) {
            Integer id = oidcUser.getId();
            String memberId = String.valueOf(id);
            setToken(response, memberId);

        }

        response.sendRedirect("/");
    }

    private void setToken(HttpServletResponse response, String subject) {
        String accessToken = jwtService.getAccessToken(subject);
        setCookie(response, "access_token", accessToken, jwtService.getAccessTokenExpireTime());
    }


    private void setCookie(HttpServletResponse response, String name, String token, long maxAge) {
        Cookie cookie = new Cookie(name, token);
        cookie.setHttpOnly(true); // 클라이언트 측 스크립트가 쿠키에 접근하지 못하도록 설정
        cookie.setSecure(false); // HTTPS 환경에서만 전송되도록 설정
        cookie.setPath("/"); // 쿠키가 모든 경로에서 접근 가능하도록 설정
        cookie.setMaxAge((int) maxAge);
        response.addCookie(cookie);
    }


}
