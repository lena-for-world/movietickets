package projectC.movietickets.controller;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReserveForm {

    private Long screeningInfoId;
    private String memberId;
    private int adultCount;
    private int childCount;

}
