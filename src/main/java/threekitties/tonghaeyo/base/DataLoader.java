package threekitties.tonghaeyo.base;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import threekitties.tonghaeyo.domain.Member;
import threekitties.tonghaeyo.domain.Organization;
import threekitties.tonghaeyo.repository.MemberRepository;
import threekitties.tonghaeyo.repository.OrganizationRepository;

import static threekitties.tonghaeyo.domain.Authority.*;

@RequiredArgsConstructor
@Component
public class DataLoader implements CommandLineRunner {

    private final OrganizationRepository organizationRepository;
    private final MemberRepository memberRepository;

    @Override
    public void run(String... args) throws Exception {
        Organization organization1 = organizationRepository.save(Organization.builder().name("삼냥이 유치원").build());
        Organization organization2 = organizationRepository.save(Organization.builder().name("삼냥이 고등학교").build());

        memberRepository.save(Member.builder().name("manager1").organization(organization1).authority(MANAGER).build());
        memberRepository.save(Member.builder().name("driver1").organization(organization1).authority(DRIVER).build());
        memberRepository.save(Member.builder().name("yuri").organization(organization1).authority(USER).build());

        memberRepository.save(Member.builder().name("manager2").organization(organization2).authority(MANAGER).build());
        memberRepository.save(Member.builder().name("driver2").organization(organization2).authority(DRIVER).build());
        memberRepository.save(Member.builder().name("hyein").organization(organization2).authority(USER).build());
    }

}