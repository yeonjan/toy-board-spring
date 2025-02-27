package toy.board.global.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import toy.board.global.auth.jwt.filter.JwtAuthenticationFilter;
import toy.board.global.auth.oauth.handler.LoginSuccessHandler;
import toy.board.global.auth.oauth.service.CustomOidcUserService;


@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {

    private final CorsConfig corsConfig;
    private final LoginSuccessHandler loginSuccessHandler;
    private final CustomOidcUserService customOidcUserService;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(cors -> cors.configurationSource(corsConfig.corsConfigurationSource()))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/", "/login", "/oauth2/**", "/public/**").permitAll()
                        .anyRequest().authenticated()

                )
                .oauth2Login(oauth2 ->
                        oauth2
                                .userInfoEndpoint(userInfo -> userInfo.oidcUserService(customOidcUserService))
                                .successHandler(loginSuccessHandler))

                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
//                .exceptionHandling(exceptionHandling ->
//                        exceptionHandling
//                                .authenticationEntryPoint(new BearerTokenAuthenticationEntryPoint())
//                                .accessDeniedHandler(new BearerTokenAccessDeniedHandler())
//                );

        return http.build();
    }


}
