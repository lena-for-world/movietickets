package projectC.movietickets.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Movie {

    // make sure do not init id when annotated generatedValue exists
    @Id @GeneratedValue
    @Column(name="movie_id")
    private Long id;
    private String title;
    private LocalTime runningTime;

    @Enumerated(EnumType.STRING)
    private Rates rate;

    // 영화 엔티티가 삭제되면 해당 영화 상영 정보도 삭제되어야 해서 cascade.ALL 사용
    @JsonIgnore
    @OneToMany(mappedBy = "movie", cascade= CascadeType.ALL)
    private List<ScreeningInfo> screeningInfos = new ArrayList<>();

    public static Movie makeMovie(String title, LocalTime runningTime, Rates rate, ScreeningInfo... screeningInfos) {
        Movie movie = new Movie();
        movie.title = title;
        movie.runningTime = runningTime;
        movie.rate = rate;
        for(ScreeningInfo screeningInfo : screeningInfos) {
            movie.addScreeningInfo(screeningInfo);
        }
        return movie;
    }

    public void addScreeningInfo(ScreeningInfo screeningInfo) {
        screeningInfos.add(screeningInfo);
        screeningInfo.setMovie(this);
    }
}
