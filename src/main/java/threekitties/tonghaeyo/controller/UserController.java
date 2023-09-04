package threekitties.tonghaeyo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import threekitties.tonghaeyo.service.MemberService;

@RequiredArgsConstructor
@Controller
@RequestMapping("/user")
public class UserController {

    private final MemberService memberService;

    @GetMapping("/main")
    public String showMain() {
        return "pages/user/main";
    }

}
