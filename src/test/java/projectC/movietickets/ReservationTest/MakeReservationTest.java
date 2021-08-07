package projectC.movietickets.ReservationTest;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import projectC.movietickets.domain.Member;
import projectC.movietickets.domain.Movie;
import projectC.movietickets.domain.Rates;
import projectC.movietickets.domain.Reservation;
import projectC.movietickets.domain.ScreeningInfo;

@SpringBootTest
@Transactional
public class MakeReservationTest {

    @Autowired private EntityManager em;

    @Test
    @DisplayName("예약 생성")
    public void makeReservationTest() {


        Movie movie = makeMovieTest(1L, "black widow", "2h30m", Rates.Fifteen);
        ScreeningInfo screeningInfo1 = makeScreeningTest(5L, 50, 5000,3000, movie);
        Member member = makeMemberTest("user1", "kim", 35);


        em.persist(movie);

        Movie findMovie = findMovie(movie.getId());

        Reservation reservation = createReservationTest(movie.getId(), member.getId(), 30000, member, screeningInfo1);
        // Who has 'JoinColumn'(means it has fk) is child ??
        // the entity who is affected by another entity who is being removed is a child (I think)

        List<Reservation> rlist = new ArrayList<>();
        rlist.add(reservation);
        member.setReservedList(rlist);

        em.persist(member);

        Member findMember = findMember(member.getId());

        Assertions.assertEquals(findMember.getReservedList().get(0).getReservationCode(), member.getReservedList().get(0).getReservationCode());
        System.out.println("findMember.getReservedList().get(0).getReservationCode() = " + findMember.getReservedList().get(0).getReservationCode());

        Reservation reservation1 = findReservation(reservation.getReservationCode());

        System.out.println(reservation1.getReservationCode());

        System.out.println("reservation1.getMember().getId() = " + reservation1.getMember().getId());

    }

    public Reservation findReservation(String id) {
        return em.find(Reservation.class, id);
    }

    public Movie findMovie(Long id) {
        return em.find(Movie.class, id);
    }

    public Member findMember(String id) {
        return em.find(Member.class, id);
    }

    public ScreeningInfo findScreeningInfo(Long id) {
        return em.find(ScreeningInfo.class, id);
    }

    public Reservation createReservationTest(Long movieId, String memberId, int totalPrice, Member member, ScreeningInfo screeningInfo) {
        Reservation reservation = new Reservation();
        int randomValue = (int)(Math.random() * 1000);
        reservation.setReservationCode(movieId + memberId + randomValue);
        reservation.setTotalPrice(totalPrice);
        reservation.setMember(member);
        reservation.setScreeningInfo(screeningInfo);
        return reservation;
    }

    public Member makeMemberTest(String id, String name, int age) {
        Member member = new Member();
        member.setId(id);
        member.setName(name);
        member.setAge(age);
        return member;
    }

    public Movie makeMovieTest(Long id, String title, String runningTime, Rates rate) {
        Movie movie = new Movie();
        movie.setId(id);
        movie.setTitle(title);
        movie.setRate(rate);
        return movie;
    }

    public ScreeningInfo makeScreeningTest(Long screeningInfoId, int remnant, int adultPrice, int childPrice, Movie movie) {
        ScreeningInfo screeningInfo = new ScreeningInfo();
        screeningInfo.setScreeningInfoId(screeningInfoId);
        screeningInfo.setRemnant(remnant);
        screeningInfo.setAdultPrice(adultPrice);
        screeningInfo.setChildPrice(childPrice);
        screeningInfo.setMovie(movie);
        return screeningInfo;
    }
}
