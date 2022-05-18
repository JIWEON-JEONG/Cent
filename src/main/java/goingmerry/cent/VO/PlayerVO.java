package goingmerry.cent.VO;

import lombok.Builder;

public class PlayerVO {

    private String userEmail;

    private String back;

    private String name;

    private boolean already;

    private String want;

    private String current;

    private String teamName;

    private boolean leader;
    @Builder
    public PlayerVO(String userEmail, String back, String name, boolean already, String want, String current, String teamName, boolean leader) {
        this.userEmail = userEmail;
        this.back = back;
        this.name = name;
        this.already = already;
        this.want = want;
        this.current = current;
        this.teamName = teamName;
        this.leader = leader;
    }

}
