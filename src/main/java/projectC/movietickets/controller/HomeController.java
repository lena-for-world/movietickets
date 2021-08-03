package projectC.movietickets.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import projectC.movietickets.domain.Member;
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
        model.addAttribute("movies", reservationService.findAllMovies());
        return "selection";
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
        Member member = Member.createMember(form.getId(), form.getPassword(), form.getAge(), form.getPhoneNumber(), form.getEmail());
        memberService.save(member);
        return "member/register";
    }

    @GetMapping("/reservedList")
    public String reservedList() {
        return "reservation/reservationList";
    }
}
