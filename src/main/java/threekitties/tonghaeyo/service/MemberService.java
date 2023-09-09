package threekitties.tonghaeyo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import threekitties.tonghaeyo.domain.Member;
import threekitties.tonghaeyo.domain.Organization;
import threekitties.tonghaeyo.repository.MemberRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    public Member findById(Long id) {
        Optional<Member> opMember = memberRepository.findById(id);
        validateMemberExists(opMember);
        return opMember.get();
    }

    public Member findByName(String name) {
        Optional<Member> opMember = memberRepository.findByName(name);
        validateMemberExists(opMember);
        return opMember.get();
    }

    private void validateMemberExists(Optional<Member> opMember) {
        if (opMember.isEmpty()) {
            throw new IllegalArgumentException("Such a member doesn't exist.");
        }
    }

    public List<Member> findByOrganization(Organization organization) {
        return memberRepository.findByOrganization(organization);
    }

    public List<Member> findByOrganizationId(Long organizationId) {
        return memberRepository.findAll().stream()
                .filter(m -> m.getOrganization().getId().equals(organizationId)).toList();
    }

    @Transactional
    public void save(Member member) {
        memberRepository.save(member);
    }

    @Transactional
    public void registerOrganization(Member member, Organization organization) {
        save(member.toBuilder().organization(organization).build());
    }

}
