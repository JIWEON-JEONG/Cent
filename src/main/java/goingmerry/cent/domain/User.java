package goingmerry.cent.domain;

import goingmerry.cent.dto.UserSaveDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class User extends BaseTimeEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;    //DB 저장 인덱스

    private String userName;

    private String nickName;

    private String email;   //ID

    private String password;    //Password

    private String activityArea = "";

    private String position;

    private boolean isExpert;

    private String gender;

    private String birthDate;

    private boolean emailAuth;

//  player | user 통합
//
//    private String back;
//
//    @Column(nullable = false)
//    private boolean already;
//
//    private boolean idCaptain;
//
//    private Integer formationIndex;

    @OneToOne(mappedBy = "user")
    private Player player;



    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private List<String> roles = new ArrayList<>();

//    //연관관계 주인
//    // team 삽입 , 삭제 , 수정 다 가능.
    @OneToOne
    @JoinColumn(name = "team_id")
    private Team team;


    @OneToOne(mappedBy = "leaderUser")
    private Team team1;

    public void setEmailAuth(boolean emailAuth) {
        this.emailAuth = emailAuth;
    }

//    @OneToOne
//    @JoinColumn(name = "team_id")
//    private Team team



    @Builder
    public User(String email, String password, List<String> roles) {
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void additionalInfo(UserSaveDto userSaveDto) {
        this.activityArea = userSaveDto.getActivityArea();
        this.position = userSaveDto.getPosition();
        this.isExpert = userSaveDto.isExpert();
    }
}
