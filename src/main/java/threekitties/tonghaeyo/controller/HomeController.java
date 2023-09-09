package threekitties.tonghaeyo.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import threekitties.tonghaeyo.DTO.LoginRequest;
import threekitties.tonghaeyo.domain.Member;
import threekitties.tonghaeyo.service.HomeService;

@RequiredArgsConstructor
@Controller
public class HomeController {

    private final HomeService service;

    @GetMapping("/")
    public String chooseRole() {
        return "pages/main";
    }

    @PostMapping("/login")
    @ResponseBody
    public String login(HttpServletRequest request, @RequestBody LoginRequest loginRequest) throws Exception {
        Member member = service.login(loginRequest.getName());
        HttpSession session = request.getSession();
        session.setAttribute("sessionId", member.getId());

        return switch (member.getAuthority()) {
            case MANAGER -> "/manager/main";
            case DRIVER -> "/driver/main";
            case USER -> "/user/main";
            default -> "/error/authority";
        };
    }

    @GetMapping("/error/authority")
    public String authorityErrorPage() throws Exception {
        return "pages/error/authority";
    }

}
