package threekitties.tonghaeyo.base;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import threekitties.tonghaeyo.domain.Authority;
import threekitties.tonghaeyo.domain.Group;
import threekitties.tonghaeyo.domain.Member;
import threekitties.tonghaeyo.repository.GroupRepository;
import threekitties.tonghaeyo.repository.MemberRepository;

import static threekitties.tonghaeyo.domain.Authority.*;

@RequiredArgsConstructor
@Component
public class DataLoader implements CommandLineRunner {

    private final MemberRepository memberRepository;
    private final GroupRepository groupRepository;

    @Override
    public void run(String... args) throws Exception {
        Group group1 = groupRepository.save(Group.builder().name("삼냥이 유치원").build());
        Group group2 = groupRepository.save(Group.builder().name("삼냥이 고등학교").build());

        memberRepository.save(Member.builder().name("manager1").group(group1).authority(MANAGER).build());
        memberRepository.save(Member.builder().name("driver1").group(group1).authority(DRIVER).build());
        memberRepository.save(Member.builder().name("yuri").group(group1).authority(USER).build());

        memberRepository.save(Member.builder().name("manager2").group(group2).authority(MANAGER).build());
        memberRepository.save(Member.builder().name("driver2").group(group2).authority(DRIVER).build());
        memberRepository.save(Member.builder().name("hyein").group(group2).authority(USER).build());
    }

}