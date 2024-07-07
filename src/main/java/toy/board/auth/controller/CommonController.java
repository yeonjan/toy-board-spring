package toy.board.auth.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import toy.board.member.MemberService;
import toy.board.model.entity.Member;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api")
public class CommonController {
    private final MemberService memberService;


    private final OAuth2AuthorizedClientService authorizedClientService;

    @GetMapping("/login/success")
    public String processLoginUser(Model model, @AuthenticationPrincipal OidcUser principal) {
        Member member = memberService.saveLoginMember(principal);
        model.addAttribute("member", member);
        return "index2";
    }

}
