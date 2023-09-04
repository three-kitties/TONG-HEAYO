package threekitties.tonghaeyo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import threekitties.tonghaeyo.domain.Member;
import threekitties.tonghaeyo.repository.MemberRepository;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public Optional<Member> findById(Long id) {
        return memberRepository.findById(id);
    }

    public Optional<Member> findByName(String name) {
        return memberRepository.findByName(name);
    }

}
