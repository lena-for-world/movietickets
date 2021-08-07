package projectC.movietickets.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import projectC.movietickets.domain.Member;
import projectC.movietickets.domain.Reservation;
import projectC.movietickets.domain.ScreeningInfo;
import projectC.movietickets.service.MemberService;
import projectC.movietickets.service.ReservationService;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final MemberService memberService;
    private final ReservationService reservationService;

    @GetMapping("/")
    public String home(Model model) {
        return "home";
    }

    @GetMapping("/reserve")
    public String makeReserve(Model model) {
        model.addAttribute("screeningInfos", reservationService.findAllScreeningInfos());
        model.addAttribute("members", reservationService.findAllMembers());
        model.addAttribute("reserveForm", new ReserveForm());
        return "selection";
    }

    @PostMapping("/reserve")
    public String makePostReserve(ReserveForm reserveForm) {

        Member member = memberService.findMember(reserveForm.getMemberId());
        ScreeningInfo screeningInfo = reservationService.findScreeningInfo(reserveForm.getScreeningInfoId());

        Reservation reservation = Reservation.createReservation(reserveForm.getScreeningInfoId()
        , reserveForm.getMemberId(), reserveForm.getAdultCount(), reserveForm.getChildCount()
        , member, screeningInfo);

        reservationService.save(reservation);

        return "redirect:/";
    }

    @GetMapping("/register")
    public String registerMember(Model model) {
        // 회원등록 폼 만들어서 전달
        model.addAttribute("form", new MemberForm());
        return "member/register";
    }

    @PostMapping("/register")
    public String registerMember(MemberForm form) {
        // 회원등록 폼 만들어서 전달
        Member member = Member.makeMember(form.getId(), form.getName(), form.getAge());
        memberService.save(member);
        return "redirect:/";
    }

    @GetMapping("/reservedList")
    public String reservedList(Model model) {
        model.addAttribute("reservations", reservationService.findAllReservations());
        return "reserve/reservationList";
    }

    @GetMapping("/reserve/{id}/delete")
    public String reserveDeletion(@PathVariable("id") String id) {
        Reservation reservation = reservationService.findReservation(id);
        reservationService.deleteReservation(reservation);
        return "redirect:/";
    }
}
