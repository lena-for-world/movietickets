package projectC.movietickets.domain;

import java.time.LocalTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor
public class ScreeningInfo {

    @Id @GeneratedValue
    @Column(name="screening_info_id")
    private Long screeningInfoId;

    private int remnant;
    private LocalTime startTime;
    private LocalTime endTime;
    private int adultPrice;
    private int childPrice;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="movie_id")
    private Movie movie;

    public static ScreeningInfo makeScreeningInfo(int remnant, LocalTime startTime,
        int adultPrice, int childPrice) {

        ScreeningInfo screeningInfo = new ScreeningInfo();
        screeningInfo.remnant = remnant;
        screeningInfo.startTime = startTime;

        LocalTime newLocalTime = startTime;
        //newLocalTime.plusHours(movie.getRunningTime().getHour());
        //newLocalTime.plusMinutes(movie.getRunningTime().getMinute());

        screeningInfo.endTime = newLocalTime;
        screeningInfo.adultPrice = adultPrice;
        screeningInfo.childPrice = childPrice;

        return screeningInfo;

    }

    public void addRemnant(int count) {
        this.remnant += count;
    }

    public void subtractRemnant(int count) {
        this.remnant -= count;
    }
}

// 메서드 만들기