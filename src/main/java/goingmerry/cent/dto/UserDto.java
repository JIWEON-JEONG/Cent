package goingmerry.cent.dto;

import goingmerry.cent.domain.Role;
import goingmerry.cent.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
public class UserDto {

    private Long id;
    private String userName;
    private String email;
    private String activityArea;
    private String Position;
    private boolean isExpert;
    private String gender;
    private String ageRange;
    private String birth;
    private String userImage;
    private Long userId;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Builder
    public UserDto(User entity) {
        this.userName = userName;
        this.email = email;
        this.gender = gender;
        this.ageRange = ageRange;
        this.birth = birth;
        this.userImage = userImage;
        this.userId = userId;
        this.role = role;
    }
}
