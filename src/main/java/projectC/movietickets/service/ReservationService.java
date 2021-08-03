package projectC.movietickets.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import projectC.movietickets.domain.Movie;
import projectC.movietickets.domain.Reservation;
import projectC.movietickets.repository.ReservationRepository;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;

    public List<Movie> findAllMovies() {
        return reservationRepository.findAllMovies();
    }

    public Movie findMovie(int id) {
        return reservationRepository.findMovie(id);
    }

    public void save(Reservation reservation) {
        reservationRepository.save(reservation);
    }

}
