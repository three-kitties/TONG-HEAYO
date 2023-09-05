package threekitties.tonghaeyo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import threekitties.tonghaeyo.service.MemberService;

@RequiredArgsConstructor
@Controller
@RequestMapping("/driver")
public class DriverController {

    private final MemberService memberService;

    @GetMapping("/main")
    public String showMain() {
        return "pages/driver/main";
    }

    @GetMapping("/map")
    public String showMap() {
        return "pages/driver/map";
    }

}
