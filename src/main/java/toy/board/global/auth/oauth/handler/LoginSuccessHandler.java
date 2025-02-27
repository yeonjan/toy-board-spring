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
            addAccessTokenCookie(response, memberId);
            addLoginStateCookie(response);

        }

        response.sendRedirect("http://localhost:5173/");
    }

    private static void addLoginStateCookie(HttpServletResponse response) {
        Cookie cookie = new Cookie("isLoggedIn", "True");
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    private void addAccessTokenCookie(HttpServletResponse response, String subject) {
        String accessToken = jwtService.getAccessToken(subject);

        Cookie cookie = new Cookie("access_token", accessToken);
        cookie.setHttpOnly(true); // 클라이언트 측 스크립트가 쿠키에 접근하지 못하도록 설정
        cookie.setSecure(false); // HTTPS 환경에서만 전송되도록 설정
        cookie.setPath("/"); // 쿠키가 모든 경로에서 접근 가능하도록 설정
        cookie.setMaxAge((int) jwtService.getAccessTokenExpireTime());
        response.addCookie(cookie);

    }





}
