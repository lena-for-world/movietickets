package projectC.movietickets.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Member {

    @Id
    @Column(name = "member_id")
    private String id;
    private String password;
    private int age;
    private String phoneNumber;
    private String email;

    @OneToMany(mappedBy = "memberId")
    private List<Reservation> reservedList = new ArrayList<>();

    public static Member createMember(String id, String password, int age, String phoneNumber, String email) {
        Member member = new Member();
        member.id = id;
        member.password = password;
        member.age = age;
        member.phoneNumber = phoneNumber;
        member.email = email;
        return member;
    }
}
