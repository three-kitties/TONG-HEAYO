package threekitties.tonghaeyo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import threekitties.tonghaeyo.domain.Member;
import threekitties.tonghaeyo.service.MemberService;

import java.util.List;
import java.util.Optional;

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
        // TODO : 하드 코딩 된 부분 수정 필요
        Member manager = memberService.findByName("manager1").get();

        List<Member> members = memberService.findByOrganization(manager.getOrganization());

        model.addAttribute("organizationName", manager.getOrganization().getName());
        model.addAttribute("members", members);
        return "manager/members";
    }

    @GetMapping("/delete/{id}")
    public String deleteMemberOrganization(@PathVariable Long id) {
        Optional<Member> opMember = memberService.findById(id);
        validateIfMemberExists(opMember);

        Member member = opMember.get();

        memberService.save(member.toBuilder().organization(null).build());

        return "redirect:/manager/members";
    }

    private void validateIfMemberExists(Optional<Member> opMember) {
        if (opMember.isEmpty()) {
            throw new IllegalArgumentException("Such a member doesn't exist.");
        }
    }

}
