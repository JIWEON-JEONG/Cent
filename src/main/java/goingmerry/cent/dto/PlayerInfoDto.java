package goingmerry.cent.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PlayerInfoDto {

    private Long playerId;
    private Long userId;
    private Long teamId;

    private String userName;// get User
    private String email;

    private String wantPosition; // Get Player
    private String currentPosition;
    private boolean already;
    private boolean isCaptain;


    @Builder
    public PlayerInfoDto(Long playerId, Long userId, Long teamId, String userName, String email, String wantPosition, String currentPosition, boolean already, boolean isCaptain) {
        this.playerId = playerId;
        this.userId = userId;
        this.teamId = teamId;
        this.userName = userName;
        this.email = email;
        this.wantPosition = wantPosition;
        this.currentPosition = currentPosition;
        this.already = already;
        this.isCaptain = isCaptain;
    }
}
