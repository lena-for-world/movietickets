package projectC.movietickets.repository;

import javax.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import projectC.movietickets.domain.Member;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;

    public Member findMember(String id) {
        return em.find(Member.class, id);
    }

    public void save(Member member) {
        em.persist(member);
    }
}
