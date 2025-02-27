package toy.board.global.auth.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import toy.board.global.auth.util.CookieUtil;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletResponse response) {
        CookieUtil.deleteCookie(response, "access_token");
        CookieUtil.deleteCookie(response, "isLoggedIn");

        return ResponseEntity.ok().build();
    }
}