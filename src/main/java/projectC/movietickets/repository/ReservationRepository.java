package projectC.movietickets.repository;

import java.util.List;
import javax.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import projectC.movietickets.domain.Movie;
import projectC.movietickets.domain.Reservation;

@Repository
@RequiredArgsConstructor
public class ReservationRepository {

    private final EntityManager em;

    public void save(Reservation reservation) {
        em.persist(reservation);
    }

    public List<Movie> findAllMovies() {
        return em.createQuery("select m from Movie m")
            .getResultList();
    }

    public Movie findMovie(int id) {
        return em.find(Movie.class, id);
    }

}
