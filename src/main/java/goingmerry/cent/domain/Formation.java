package goingmerry.cent.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@NoArgsConstructor
@Entity
public class Formation {
    /*
원 : 팀 초기에 만들때 11개 생성
등번호 -> null : back
몇번 원인지 : circleNum
선수이름 -> null : name
x -> null
y -> null
포지션 : position
    * */
    @Id
    private String teamName;

    @Column(nullable = false)
    private String formation;

    @Builder
    public Formation(String formation, String teamName) {
        this.formation = formation;
        this.teamName = teamName;
    }

    //    포메이션
//
//    private String back;
//
//    private String name;
//
//    private int x;
//
//    private int y;
//
//    private String position;


    // 현재 포메이션 저장
    // 포메이션 불러오기

}
