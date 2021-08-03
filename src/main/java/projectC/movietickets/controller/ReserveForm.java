package projectC.movietickets.controller;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReserveForm {

    private int movieId;
    private String memberId;
    private int price;

}
