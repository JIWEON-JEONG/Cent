package goingmerry.cent.domain;

import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@NoArgsConstructor
@Entity
public class Team extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String teamName;

    private String logo;

    private String intro;

    @Column(nullable = false)
    private String area;
//    @Enumerated(EnumType.STRING)
//    private Area area;

    @Builder
    public Team(String teamName, String logo, String intro, String area) {
        this.teamName = teamName;
        this.logo = logo;
        this.intro = intro;
        this.area = area;
    }


    public Team update(String intro, String logo) {
        this.intro = intro;
        this.logo = logo;
        return this;
    }
}
