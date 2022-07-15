package goingmerry.cent.dto.player;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class PlayerUpdateDto {

//    String back, boolean already, String current, boolean leader, Integer formationIndex

    private Long id;

    private String back;

//    private boolean already;
//
//    private String current;

    @Builder
    public PlayerUpdateDto(Long id, String back) {

        this.id = id;
        this.back = back;

    }
}
