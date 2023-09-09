package threekitties.tonghaeyo.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import threekitties.tonghaeyo.domain.Authority;
import threekitties.tonghaeyo.domain.Member;
import threekitties.tonghaeyo.service.MemberService;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/manager")
public class ManagerController {

    private final MemberService memberService;

    @GetMapping("/main")
    public String showMain(HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        Long id = Long.parseLong(session.getAttribute("sessionId").toString());
        Member member = memberService.findById(id);

        if (member.getAuthority().equals(Authority.MANAGER)) {
            return "pages/manager/main";
        } else {
            return "pages/error/authority";
        }

    }

    @GetMapping("/members")
    public String getAllMembers(Model model) {
        // TODO : 하드 코딩 된 부분 수정 필요
        Member manager = memberService.findByName("manager1");

        List<Member> members = memberService.findByOrganization(manager.getOrganization());

        model.addAttribute("organizationName", manager.getOrganization().getName());
        model.addAttribute("members", members);
        return "pages/manager/members";
    }

    @GetMapping("/delete/{id}")
    public String deleteMemberOrganization(@PathVariable Long id) {
        Member member = memberService.findById(id);
        memberService.save(member.toBuilder().organization(null).build());
        return "redirect:/manager/members";
    }

}
