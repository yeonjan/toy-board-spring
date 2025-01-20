package toy.board.global.auth.jwt.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import toy.board.domain.member.service.MemberService;
import toy.board.global.auth.jwt.dto.JwtAuthenticationToken;
import toy.board.global.auth.jwt.service.JwtService;
import toy.board.model.entity.Member;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final MemberService memberService;


    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String BEARER_PREFIX = "Bearer ";


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        log.debug("JWT Authentication Filter");
        String jwt = resolveToken(request);

        if (!StringUtils.hasText(jwt)) {
            filterChain.doFilter(request, response);
            return;
        }

        Integer memberId = jwtService.extractMemberId(jwt);

        if (memberId != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            Optional<Member> member = memberService.findById(memberId);

            if (member.isPresent() && jwtService.isValid(jwt, member.get())) {
                Authentication jwtAuthenticationToken = new JwtAuthenticationToken(member.get(), null, List.of(new SimpleGrantedAuthority(member.get().getRole().name())));
                SecurityContextHolder.getContext().setAuthentication(jwtAuthenticationToken);
            }
        }

        filterChain.doFilter(request, response);

    }

    private String resolveToken(HttpServletRequest request) {
        if (request.getCookies() == null) {
            return null;
        }

        for (Cookie cookie : request.getCookies()) {
            if ("access_token".equals(cookie.getName())) {
                return cookie.getValue();
            }
        }
        return null;
    }
}