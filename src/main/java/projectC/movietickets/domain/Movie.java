package projectC.movietickets.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Getter;

@Entity
@Getter
public class Movie {

    @Id @GeneratedValue
    @Column(name="movie_id")
    int id;
    private String name;
    private String startTime;
    private String runningTime;

    @OneToMany(mappedBy = "movieId")
    private List<Reservation> reservationList = new ArrayList<>();
}
