package goingmerry.cent.dto;

import goingmerry.cent.domain.Player;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class PlayerDto {

    private String userEmail;

    private String back;

    private String name;

    private boolean already;

    private String want;

    private String current;

    private String teamName;

    private boolean leader;

    private Integer formationIndex;

    // 추가적인 생성자의 생성으로 기본 생성자가 없기 때문에 컨트롤러에서 dto로 받게 되었을 시에 못받는 오류 발생.
    // 컨트롤러에서 dto로 받게 되는 것도 하나의 객체를 생성하고 전달하는 것이기 때문에 기본 생성자는 꼭 필요하다.
    // 때문에 @noArgsConstructor 붙여준 것(기본 생성자 생성)

    @Builder
    public PlayerDto(Player entity) {
        this.userEmail = entity.getUserEmail();
        this.back = entity.getBack();
        this.name = entity.getName();
        this.already = entity.isAlready();
        this.want = entity.getWant();
        this.current = entity.getCurrent();
        this.teamName = entity.getTeamName();
        this.leader = entity.isLeader();
        this.formationIndex = entity.getFormationIndex();
    }

    public PlayerDto(String userEmail, String back, String name, boolean already, String want, String current, String teamName, boolean leader, Integer formationIndex) {
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
