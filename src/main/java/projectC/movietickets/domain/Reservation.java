package projectC.movietickets.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Reservation {

    @Id
    private String reservationCode;

    private int totalPrice;

    private int adultCount;
    private int childCount;
    private ReservationStatus status;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member member;

    // 일대일 단방향...
    // cascade 미사용
    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="screening_info_id")
    private ScreeningInfo screeningInfo;

    public static Reservation createReservation(Long movieId, String memberId, int adultCount, int childCount, Member member, ScreeningInfo screeningInfo) {

        Reservation reservation = new Reservation();

        int randomValue = (int)(Math.random() * 1000);
        reservation.reservationCode = movieId + memberId + randomValue;

        reservation.adultCount = adultCount;
        reservation.childCount = childCount;
        reservation.totalPrice = screeningInfo.getAdultPrice() * adultCount + screeningInfo.getChildPrice() * childCount;
        reservation.status = ReservationStatus.ORDER;

        reservation.member = member;
        reservation.screeningInfo = screeningInfo;

        return reservation;
    }
}
