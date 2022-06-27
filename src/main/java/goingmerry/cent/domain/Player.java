package goingmerry.cent.domain;

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
선수이메일 : email
등번호 : back
이름 : name
배치여부 : already
선호포지 : like
실배치포지 : select
팀명 : teamName
주장여부 : leader
     */
    @Id
    private String userEmail;

    @Column(nullable = false)
    private String back; // 등번호는 어디서 받아? -> 물어보기

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

    private boolean leader; // 주장여부

    @Column(nullable = true)
    private Integer formationIndex; // 현재 원 번호? -> 선발 선수들
    /*
    포메이션(선수 정보) 저장 api 1
    불러오는 api 1
     */

    // 팀 관리에서 명단 볼때는 맴버 긁어오나 선수 긁어오나?

    @Builder
    public Player(String userEmail, String back, String name, boolean already, String want, String current, String teamName, boolean leader, Integer formationIndex) {
        this.userEmail = userEmail;
        this.back = back;
        this.name = name;
        this.already = already;
        this.want = want;
        this.current = current;
        this.teamName = teamName;
        this.leader = leader;
        this.formationIndex = formationIndex;
    }
}



