package projectC.movietickets.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import projectC.movietickets.domain.Member;
import projectC.movietickets.domain.Movie;
import projectC.movietickets.domain.Reservation;
import projectC.movietickets.domain.ScreeningInfo;
import projectC.movietickets.repository.ReservationRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;

    public void save(Reservation reservation) {

        ScreeningInfo screeningInfo = reservation.getScreeningInfo();
        int totalCount = reservation.getAdultCount() + reservation.getChildCount();
        if (!checkRemnant(screeningInfo, totalCount)) {
            throw new IllegalStateException("cannot reserve -- not enough remnants");
        }
        reservationRepository.save(reservation, totalCount);
    }

    public boolean checkRemnant(ScreeningInfo screeningInfo, int count) {
        if(screeningInfo.getRemnant() - count < 0) return false;
        else return true;
    }

    public void deleteReservation(Reservation reservation) {
        int totalCount = reservation.getAdultCount() + reservation.getChildCount();
        reservationRepository.deleteReservation(reservation, totalCount);
    }
    public void deleteMovie(Movie movie) {
        reservationRepository.deleteMovie(movie);
    }

    public Movie findMovie(Long id) {
        return reservationRepository.findMovie(id);
    }

    public Reservation findReservation(String id) {
        return reservationRepository.findReservation(id);
    }

    public ScreeningInfo findScreeningInfo(Long id) {
        return reservationRepository.findScreeningInfo(id);
    }

    public List<Movie> findAllMovies() {
        return reservationRepository.findAllMovies();
    }

    public List<Member> findAllMembers() {
        return reservationRepository.findAllMembers();
    }

    public List<Reservation> findAllReservations() {
        return reservationRepository.findAllReservations();
    }

    public List<ScreeningInfo> findAllScreeningInfos() {
        return reservationRepository.findAllScreeningInfos();
    }


}
