package goingmerry.cent.dto.alarm;

import goingmerry.cent.domain.Alarm;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Setter
@Getter
@NoArgsConstructor
public class getAlarmDto {

    private Long id;

    private String fromEmail; // 누가

    // notnull
    private String toEmail; // 누구에게

    private String teamName; // 어떤 팀에서~

    // notnull
    private String value;

    private String createDate;

    @Builder
    public getAlarmDto(Alarm entity) {
        this.id = entity.getId();
        this.fromEmail = entity.getFromEmail();
        this.toEmail = entity.getToEmail();
        this.teamName = entity.getTeamName();
        this.value = entity.getValue();
        this.createDate = toStringDateTime(entity.getCreateDate());
    }

    public String toStringDateTime(LocalDateTime localDateTime){
        if(localDateTime == null){
            return "";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return formatter.format(localDateTime);
    }


}
