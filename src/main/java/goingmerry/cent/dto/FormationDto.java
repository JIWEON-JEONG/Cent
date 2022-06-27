package goingmerry.cent.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class FormationDto {

    private String formation;

    private String teamName;

    public FormationDto(String formation, String teamName) {
        this.formation = formation;
        this.teamName = teamName;
    }
}
