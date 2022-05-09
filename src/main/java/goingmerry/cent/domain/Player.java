package goingmerry.cent.domain;

import com.nimbusds.openid.connect.sdk.federation.policy.language.StringListConfiguration;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Player {
    /*
선수정보 :
등번호 : back
이름 : name
배치여부 : already
선호포지 : like
실배치포지 : select
팀명 : teamName
주장여부 : leader
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String back;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private boolean already;

    private String want;
    //like, select 는 데이터베이스의 예약어이기때문에 sql 문이 오류가 난다.
    //-> want, current 로 변경
    private String current;

    @Column(nullable = false)
    private String teamName;

    private boolean leader;

    @Builder
    public Player(String back, String name, boolean already, String want, String current, String teamName, boolean leader) {
        this.back = back;
        this.name = name;
        this.already = already;
        this.want = want;
        this.current = current;
        this.teamName = teamName;
        this.leader = leader;
    }
}
