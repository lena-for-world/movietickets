package projectC.movietickets.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Member {

    @Id
    @Column(name = "member_id")
    private String id;
    private String name;
    private int age;

    // cascade 사용
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Reservation> reservedList = new ArrayList<>();

    public static Member makeMember(String id, String name, int age) {
        Member member = new Member();
        member.id = id;
        member.name= name;
        member.age = age;
        return member;
    }
}
