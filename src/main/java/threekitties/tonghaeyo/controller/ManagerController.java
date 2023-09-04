package threekitties.tonghaeyo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import threekitties.tonghaeyo.domain.Member;
import threekitties.tonghaeyo.service.MemberService;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/manager")
public class ManagerController {

    private final MemberService memberService;

    @GetMapping("/main")
    public String showMain() {
        return "manager/main";
    }

    @GetMapping("/members")
    public String getAllMembers(Model model) {
        Member manager = memberService.findByName("manager1").get();

        List<Member> members = memberService.findByGroup(manager.getOrganization());

        model.addAttribute("groupName", manager.getOrganization().getName());
        model.addAttribute("members", members);
        return "manager/members";
    }

}
