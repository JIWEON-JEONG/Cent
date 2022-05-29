package goingmerry.cent.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
public class ApplicationList {

    // 신청 목록

    @Id
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
}
