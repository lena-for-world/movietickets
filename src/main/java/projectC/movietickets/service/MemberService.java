package projectC.movietickets.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import projectC.movietickets.domain.Member;
import projectC.movietickets.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public void save(Member member) {
        memberRepository.save(member);
    }

}
