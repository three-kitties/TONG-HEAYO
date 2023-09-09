package threekitties.tonghaeyo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import threekitties.tonghaeyo.domain.Member;
import threekitties.tonghaeyo.repository.MemberRepository;

@RequiredArgsConstructor
@Service
public class HomeService {

    private final MemberRepository repository;

    public Member login(String name){
        return repository.findByName(name).get();
    }

}
