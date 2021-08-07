package projectC.movietickets.ReservationTest;

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
import projectC.movietickets.domain.Reservation;
import projectC.movietickets.domain.ScreeningInfo;
import projectC.movietickets.service.ReservationService;

@SpringBootTest
@Transactional
public class DeleteTest {

    @Autowired private EntityManager em;
    @Autowired private ReservationService reservationService;

    /*@Test
    @DisplayName("delete Reservation")
    public void deleteReservationTest() {

        // given
        List<Reservation> list = reservationService.findAllReservations();
        Reservation reservation = em.find(Reservation.class, list.get(0).getReservationCode());

        // when
        reservationService.deleteReservation(reservation);

        // then
        List<Reservation> list2 = reservationService.findAllReservations();
        System.out.println("list2.size() = " + list2.size());
        Assertions.assertEquals(list2.size(), 0);
    }*/

    @Test
    @DisplayName("delete Movie")
    public void deleteMovieTest() {

        // given
        List<Movie> movieList = reservationService.findAllMovies();
        List<ScreeningInfo> screeningInfoList = reservationService.findAllScreeningInfos();

        // when
        reservationService.deleteMovie(movieList.get(0));

        // then
        List<Movie> movieList2 = reservationService.findAllMovies();
        List<ScreeningInfo> screeningInfoList2 = reservationService.findAllScreeningInfos();
        Assertions.assertNotEquals(movieList.size(), movieList2.size());
        Assertions.assertNotEquals(screeningInfoList.size(), screeningInfoList2.size());
        System.out.println(movieList.size() + " " + movieList2.size());
        System.out.println(screeningInfoList.size() + " " + screeningInfoList2.size());

    }

}
