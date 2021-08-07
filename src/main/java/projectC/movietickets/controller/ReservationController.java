package projectC.movietickets.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import projectC.movietickets.domain.Reservation;
import projectC.movietickets.service.ReservationService;

@Controller
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    /*@GetMapping("/reserve/detail/{movieId}")
    public String selectComplete(@PathVariable("movieId") int movieId, Model model) {
        // 영화 정보 싣고, 예약 폼 실어서 model에 보낼 것
        model.addAttribute("movie", reservationService.findMovie(movieId));
        model.addAttribute("reserveForm", new ReserveForm());
        return "reservation/reservationDetail";
    }

    // 예매폼으로부터 정보를 받아서 저장하고 메인으로 리다이렉트
    @PostMapping("/reserve/detail")
    public String writeReservationDetails(ReserveForm reserveForm) {
        // form으로부터 받은 정보를 reservation 객체에 옮겨 담고 저장
        Reservation reservation = Reservation.createReservation(reserveForm.getMovieId(), reserveForm.getMemberId(), reserveForm.getPrice());
        reservationService.save(reservation);
        return "redirect:/";
    }*/

    @GetMapping("/reserve/list")
    public String reservationlist() {
        return "reservationList";
    }

}
