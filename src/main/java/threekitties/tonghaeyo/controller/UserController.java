package threekitties.tonghaeyo.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import threekitties.tonghaeyo.domain.Authority;
import threekitties.tonghaeyo.domain.Member;
import threekitties.tonghaeyo.service.MemberService;

@RequiredArgsConstructor
@Controller
@RequestMapping("/user")
public class UserController {

    private final MemberService memberService;

    @GetMapping("/main")
    public String showMain(HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        Long id = Long.parseLong(session.getAttribute("sessionId").toString());
        Member user = memberService.findById(id);

        if (user.getAuthority().equals(Authority.USER)) {
            return "pages/user/main";
        } else {
            return "pages/error/authority";
        }
    }

}
