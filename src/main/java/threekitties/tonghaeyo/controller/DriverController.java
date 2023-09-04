package threekitties.tonghaeyo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import threekitties.tonghaeyo.service.MemberService;

@RequiredArgsConstructor
@Controller
public class DriverController {

    private final MemberService memberService;

}
