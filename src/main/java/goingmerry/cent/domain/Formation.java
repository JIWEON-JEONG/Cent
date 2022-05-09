package goingmerry.cent.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.*;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String back;

    @Column(nullable = false)
    private int circle;

    private String name;

    private int x;

    private int y;

    private String position;

    @Column(nullable = false)
    private String teamName;

    @Builder
    public Formation(String back, int circle, String name, int x, int y, String position, String teamName) {
        this.back = back;
        this.circle = circle;
        this.name = name;
        this.x = x;
        this.y = y;
        this.position = position;
        this.teamName = teamName;
    }
}
