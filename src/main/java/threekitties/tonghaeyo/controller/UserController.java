package threekitties.tonghaeyo.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
        Member user = getUser(request);

        if (user == null) return "pages/error/authority";

        return "pages/user/main";
    }

    @GetMapping("/map")
    public String getMap(Model model, HttpServletRequest request) {
        Member user = getUser(request);

        if (user.getOrganization() == null) {
            model.addAttribute("organization", null);
        } else {
            model.addAttribute("organization", user.getOrganization());
            model.addAttribute("route", user.getOrganization().getRoute());
        }

        return "/pages/user/map";
    }

    private Member getUser(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Long id = Long.parseLong(session.getAttribute("sessionId").toString());
        Member user = memberService.findById(id);

        if (!user.getAuthority().equals(Authority.USER)) {
            return null;
        }

        return user;
    }

}
