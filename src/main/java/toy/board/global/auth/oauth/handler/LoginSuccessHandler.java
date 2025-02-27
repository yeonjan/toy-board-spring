package toy.board.global.auth.oauth.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import toy.board.domain.member.service.MemberService;
import toy.board.global.auth.jwt.service.JwtService;
import toy.board.global.auth.oauth.dto.CustomOidcUser;
import toy.board.global.auth.util.CookieUtil;

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
            String accessToken = jwtService.getAccessToken(memberId);
            CookieUtil.addCookie(response, "access_token", accessToken, jwtService.getAccessTokenExpireTime(), true);
            CookieUtil.addCookie(response, "isLoggedIn", "True", 7 * 24 * 60 * 60 * 1000, false);

        }

        response.sendRedirect("http://localhost:5173/");
    }

}
