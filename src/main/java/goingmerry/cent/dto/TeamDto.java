package goingmerry.cent.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TeamDto {

    private String teamName;

    private String logo;

    private String intro;

    private String area;

    @Builder
    public TeamDto(String teamName, String logo, String intro, String area) {
        this.teamName = teamName;
        this.logo = logo;
        this.intro = intro;
        this.area = area;
    }
}
