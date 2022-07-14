package goingmerry.cent.controller;

import goingmerry.cent.dto.ApplicationListDto;
import goingmerry.cent.dto.UserDto;
import goingmerry.cent.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

        return new ResponseEntity<>(applicationList, status);
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
    // 신청 보냈을 시 팀장에게 알림, 해당 팀의 팀장 이메일이 toEmail 컬럼으로 추가되어야 함.
    @PostMapping(value = "/apply/team")
    public ResponseEntity<String> ApplyToTeamFromUser(@RequestBody Map<String, Object> applyInfo) {

        log.info("[API CALL : /api/application/apply/team, apply Team is {} ]", applyInfo.get("teamName"));
        HttpStatus status = HttpStatus.OK;
        String responseMsg = "";

        Map<String ,Object> applyResponse = applicationService.applyToTeam(applyInfo);

        // 신청 보냈을 때 알림 가게 하는 Api의 작성이 필요하다. get으로 알릴 신청이 있는지 여부 판단.

        if(applyResponse.get("applyFrom").equals(null) || applyResponse.get("applyTo").equals(null) || applyResponse.get("teamName").equals(null)) {
            responseMsg = "ERROR!";
        }
        else {
            responseMsg = "User " + applyResponse.get("applyFrom").toString()
                    + "is apply to " + applyResponse.get("applyTo").toString()
                    + ", apply Team is " + applyResponse.get("teamName").toString();
        }

        return new ResponseEntity<>(responseMsg, status);
    }

    // 유저가 팀 search 하는 경우는 api/team/list/team api 사용하면 된다.(팀명 전체 get. ) -> 물어보고 새로 생성해야 하면 만들기.
    // 팀장이 유저 search
    @GetMapping(value = "/search/user")
    public ResponseEntity<List<UserDto>> findUser(@RequestParam String searchInfo) {
        log.info("[API CALL : /api/application/search/user, search String is {} ]", searchInfo);

        HttpStatus status = HttpStatus.OK;
        List<UserDto> responseInfo = applicationService.getUserInfo(searchInfo);

        return new ResponseEntity<>(responseInfo, status);
    }


    // 가입 권유 보내기(Team_leader -> user)
    @PostMapping(value = "apply/user")
    public ResponseEntity<String> ApplyToUserFromTeam(Map<String, Object> ApplyInfo) {
        HttpStatus status = HttpStatus.OK;

        return new ResponseEntity<>(status);
    }


    // 알림 get
    @GetMapping(value = "/notification")
    public ResponseEntity<List<ApplicationListDto>> getApplyNotification(@RequestParam String email) {
        HttpStatus status = HttpStatus.OK;

        List<ApplicationListDto> applicationResponse = new ArrayList<>();

        //?

        return new ResponseEntity<>(status);
    }





}
