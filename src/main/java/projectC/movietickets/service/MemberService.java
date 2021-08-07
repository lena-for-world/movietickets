package projectC.movietickets.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import projectC.movietickets.domain.Member;
import projectC.movietickets.repository.MemberRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Member findMember(String id) {
        return memberRepository.findMember(id);
    }

    public void save(Member member) {
        memberRepository.save(member);
    }

}
