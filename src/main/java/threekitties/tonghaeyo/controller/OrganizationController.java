package threekitties.tonghaeyo.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import threekitties.tonghaeyo.domain.Member;
import threekitties.tonghaeyo.domain.Organization;
import threekitties.tonghaeyo.service.MemberService;
import threekitties.tonghaeyo.service.OrganizationService;

@RequiredArgsConstructor
@Controller
@RequestMapping("/organization")
public class OrganizationController {

    private final OrganizationService organizationService;
    private final MemberService memberService;

    @GetMapping("")
    public String getAllOrganizations(Model model) {
        model.addAttribute("organizations", organizationService.findAll());
        return "pages/organization/list";
    }

    @GetMapping("/register/{id}")
    public String registerOrganization(@PathVariable Long id, HttpServletRequest request) {
        Member member = getMember(request);
        Organization organization = organizationService.findById(id);

        memberService.registerOrganization(member, organization);

        return switch (member.getAuthority()) {
            case MANAGER -> "redirect:/manager/main";
            case DRIVER -> "redirect:/driver/main";
            case USER -> "redirect:/user/main";
            default -> "redirect:/error/authority";
        };
    }

    private Member getMember(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Long id = Long.parseLong(session.getAttribute("sessionId").toString());
        return memberService.findById(id);
    }

}
