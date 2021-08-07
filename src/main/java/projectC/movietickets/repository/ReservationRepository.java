package projectC.movietickets.repository;

import java.util.List;
import javax.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import projectC.movietickets.domain.Member;
import projectC.movietickets.domain.Movie;
import projectC.movietickets.domain.Reservation;
import projectC.movietickets.domain.ScreeningInfo;

@Repository
@RequiredArgsConstructor
public class ReservationRepository {

    private final EntityManager em;

    public void save(Reservation reservation, int totalCount) {
        reservation.getScreeningInfo().subtractRemnant(totalCount);
        em.persist(reservation);
    }

    public void deleteReservation(Reservation reservation, int totalCount) {
        reservation.getScreeningInfo().addRemnant(totalCount);
        em.remove(reservation);
    }

    public void deleteMovie(Movie movie) {
        em.remove(movie);
    }

    public List<Movie> findAllMovies() {
        return em.createQuery("select m from Movie m")
            .getResultList();
    }

    public List<Member> findAllMembers() {
        return em.createQuery("select mb from Member mb")
            .getResultList();
    }

    public List<Reservation> findAllReservations() {
        return em.createQuery("select r from Reservation r")
            .getResultList();
    }

    public List<ScreeningInfo> findAllScreeningInfos() {
        return em.createQuery("select s from ScreeningInfo s")
            .getResultList();
    }

    public Movie findMovie(Long id) {
        return em.find(Movie.class, id);
    }

    public Reservation findReservation(String id) {
        return em.find(Reservation.class, id);
    }

    public ScreeningInfo findScreeningInfo(Long id) {
        return em.find(ScreeningInfo.class, id);
    }
}
