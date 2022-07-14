package goingmerry.cent.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
public class ApplicationList extends BaseTimeEntity{

    // 신청 목록

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String fromEmail; // 신청한 사람

    @Column(nullable = false)
    private String toEmail; // 받는 사람

    @Column(nullable = false)
    private String status; // status flag,승인 - A 거절 - D 대기 - W(default - W)

    private String teamName;

    @Builder
    public ApplicationList(Long id, String fromEmail, String toEmail, String teamName, String status) {
        this.id = id;
        this.fromEmail = fromEmail;
        this.toEmail = toEmail;
        this.teamName = teamName;
        this.status = status;
    }

    // 선수 팀 가입 시 신청 삭제 로직 생성
    //
}
