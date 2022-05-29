package goingmerry.cent.controller;

import goingmerry.cent.dto.ApplicationListDto;
import goingmerry.cent.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/application")
public class ApplicationController {
    /*
    - 신청 목록을 조회하면 선수를 클릭해 신청한 유저의 상세 정보를 볼 수 있다.
    - 이는 user table 을 조회해야 함을 알 수 있음. 테이블의 연결을 통해 같은 이메일을 가진 유저의 정보를 get해야 한다.
    이름, 성별, 소개글, 생년월일, 이메일 ,닉네임, 지역, 포지션(선호), 선출여부
     */

    private final ApplicationService applicationService;

    // 신청 목록 조회(팀장)
    @GetMapping(value = "/get/team/{teamName}")
    public ResponseEntity<List<ApplicationListDto>> getApplicationListTeam(@PathVariable String teamName) {
        // 선수가 팀에 신청을 넣었을 경우
        log.info("[API CALL : /api/application/get/team, teamName is {} ]", teamName);
        HttpStatus status = HttpStatus.OK;

        List<ApplicationListDto> applicationList = applicationService.getApplicationList_team(teamName);

        return new ResponseEntity<>(status);
    }

    // 신청 목록 조회(선수)
    @GetMapping(value = "/get/user/{email}")
    public ResponseEntity<List<ApplicationListDto>> getApplicationListUser(@PathVariable String email) {
        // 해당 선수에게 온 가입 권유 목록
        log.info("[API CALL : /api/application/get/user, email is {} ]", email);
        HttpStatus status = HttpStatus.OK;

        List<ApplicationListDto> applicationList = applicationService.getApplicationList_user(email);

        return new ResponseEntity<>(status);
    }

    // 신청 보내기(User -> Team)
    @PostMapping(value = "")
    public ResponseEntity<String> ApplyToTeamFromUser(Map<String, Object> ApplyInfo) {
        HttpStatus status = HttpStatus.OK;


        return new ResponseEntity<>(status);
    }
//
//    // 가입 권유 보내기(Team_leader -> user)
//    @PostMapping(value = "")
//    public ResponseEntity<String> ApplyToUserFromTeam(Map<String, Object> ApplyInfo) {
//        HttpStatus status = HttpStatus.OK;
//
//        return new ResponseEntity<>(status);
//    }
//

}
