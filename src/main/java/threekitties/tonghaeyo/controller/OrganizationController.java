package threekitties.tonghaeyo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import threekitties.tonghaeyo.domain.Authority;
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
    public String getAllOrganization(Model model) {
        model.addAttribute("organizations", organizationService.findAll());
        return "pages/register";
    }

    // TODO: 하드 코딩 된 부분 수정 필요
    @GetMapping("/register/{id}")
    public String registerOrganization(@PathVariable Long id) {
        Member member = memberService.findByName("yuri").get();
        Organization organization = organizationService.findById(id).get();

        memberService.registerOrganization(member, organization);

        if (member.getAuthority() == Authority.MANAGER) {
            return "redirect:/manager/main";
        } else if (member.getAuthority() == Authority.DRIVER) {
            return "redirect:/driver/main";
        } else return "redirect:/user/main";
    }

}
