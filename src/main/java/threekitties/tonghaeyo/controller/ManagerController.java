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
        Member manager = getManager(request);

        if (manager == null) return "redirect:/error/authority";

        return "pages/manager/main";
    }

    @GetMapping("/members")
    public String getAllMembers(Model model, HttpServletRequest request) {
        Member manager = getManager(request);

        if (manager == null) return "redirect:/error/authority";

        List<Member> members = memberService.findByOrganization(manager.getOrganization());

        model.addAttribute("organizationName", manager.getOrganization().getName());
        model.addAttribute("members", members);
        return "pages/manager/members";
    }

    @GetMapping("/delete/{id}")
    public String deleteMemberOrganization(@PathVariable Long id, HttpServletRequest request) {
        Member manager = getManager(request);

        if (manager == null) return "redirect:/error/authority";

        Member targetMember = memberService.findById(id);
        memberService.save(targetMember.toBuilder().organization(null).build());
        return "redirect:/manager/members";
    }

    private Member getManager(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Long managerId = Long.parseLong(session.getAttribute("sessionId").toString());
        Member manager = memberService.findById(managerId);

        if (!manager.getAuthority().equals(Authority.MANAGER)) {
            return null;
        }

        return manager;
    }

}
