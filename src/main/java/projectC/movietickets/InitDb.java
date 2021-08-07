package projectC.movietickets;

import static projectC.movietickets.domain.Rates.Fifteen;

import java.time.LocalTime;
import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import projectC.movietickets.domain.Member;
import projectC.movietickets.domain.Movie;
import projectC.movietickets.domain.Reservation;
import projectC.movietickets.domain.ScreeningInfo;

@Component
@RequiredArgsConstructor
public class InitDb {

    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.dbInit1();
        initService.dbInit2();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {
        private final EntityManager em;
        public void dbInit1() {

            // create a member
            Member member1 = Member.makeMember("fene", "kim", 35);
            em.persist(member1);

            // create screeningInfos
            ScreeningInfo screeningInfo11 = ScreeningInfo.makeScreeningInfo(100, LocalTime.of(15, 00)
                , 13000, 11000);
            ScreeningInfo screeningInfo12 = ScreeningInfo.makeScreeningInfo(110, LocalTime.of(9, 00)
                , 9000, 7000);

            // create movies
            Movie movie1 = Movie.makeMovie("Black Widow", LocalTime.of(2,10), Fifteen, screeningInfo11, screeningInfo12);
            em.persist(movie1);

        }

        public void dbInit2() {

            Member member2 = Member.makeMember("hyundai", "yang", 33);
            em.persist(member2);

            ScreeningInfo screeningInfo21 = ScreeningInfo.makeScreeningInfo(100, LocalTime.of(18, 00)
                , 13000, 11000);
            ScreeningInfo screeningInfo22 = ScreeningInfo.makeScreeningInfo(110, LocalTime.of(14, 20)
                , 13000, 11000);

            Movie movie2 = Movie.makeMovie("Avengers", LocalTime.of(3,00), Fifteen, screeningInfo21, screeningInfo22);
            em.persist(movie2);

            Reservation reservation = Reservation.createReservation(movie2.getId(), member2.getId(), 1, 0, member2, screeningInfo22);
            em.persist(reservation);
        }
    }

}
