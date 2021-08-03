package projectC.movietickets.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Reservation {

    @Id
    private String reservationCode;
    private int price;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="movie_id")
    private Movie movieId;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member memberId;

    public static Reservation createReservation(int movieId, String memberId, int price) {
        Reservation reservation = new Reservation();
        int randomValue = (int)(Math.random() * 1000);
        reservation.reservationCode = Integer.toString(movieId) + memberId + Integer.toString(randomValue);
        reservation.price = price;
        return reservation;
    }
}
