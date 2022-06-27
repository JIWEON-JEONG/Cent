package goingmerry.cent.controller;

import goingmerry.cent.dto.ApplicationListDto;
import goingmerry.cent.dto.PlayerDto;
import goingmerry.cent.service.ApplicationService;
import goingmerry.cent.service.PlayerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/player")
public class PlayerController {
/*
선수 관리 페이지에서 이루어질 Api
 */

    private final PlayerService playerService;
    private final ApplicationService applicationService;


    // 새로운 선수를 저장한다.
    // 한 팀 최초 11명의 선수는 무조건 already=true로 설정한다.
    // playerInfo {playerName, email}
    @PostMapping(value = "/save")
    public ResponseEntity playerSave(@RequestBody PlayerDto playerInfo) {

        log.info("[API CALL] : /api/player/save, save player name is {}", playerInfo.getName());

        // 11명 되기 이전에 선발로 강제 . 현재는 사용 ㄴ
//        int count = playerService.countPlayer(playerInfo.getTeamName());
//
//        if(count > 11) {
//            playerInfo.setAlready(true);
//        }
//        else {
//            playerInfo.setAlready(false);
//        }

        playerInfo.setAlready(false);
        playerInfo.setBack("0"); // 등번호 받아오나?
        playerInfo.setLeader(false);

        PlayerDto savePlayer = playerService.savePlayer(playerInfo);
        return new ResponseEntity(savePlayer, HttpStatus.OK);
    }

    // 전체 선수 목록 get
    @GetMapping(value = "/getAll")
    public ResponseEntity<List<PlayerDto>> userInfo() {

        log.info("[API CALL] : /api/player/getuser");

        List<PlayerDto> playerList = playerService.findAllPlayers();
        HttpStatus status = HttpStatus.OK;

        log.debug(playerList.toString());

        return new ResponseEntity<>(playerList, status);
    }

    // 플레이어 테이블의 키인 이메일로 특정 선수 정보 get
    @GetMapping(value = "/finduser")
    public ResponseEntity<Map<String, String>> findUserByEmail(@RequestParam String email) {

        log.info("[API CALL] : /api/player/finduser/{}",email);

        HttpStatus status = HttpStatus.OK;

        if (email.equals("")) {
            log.error("email is not null value!");
            Map<String, String> response = new HashMap<>();
            response.put("ERRMSG", "email is not null value!");
            return new ResponseEntity<>(response, status);
            //구현 필요
        }

        Map<String, String> userInfo = playerService.findUserInfo(email);
        if (userInfo.get("name") == null) {
            //구현 필요
        }

        return new ResponseEntity<>(userInfo, status);
    }

    // 특정 팀의 선수 관리 목록을 불러오기 위함. 선수정보에 유저정보까지 빼서 갖다 넣어야 하기 떄문에 유저 테이블과의 연결이 필요하다.
    @GetMapping(value = "/findplayers/{teamName}")
    public ResponseEntity<List<PlayerDto>> findAllPlayers(@PathVariable String teamName) {

        /*
        이름, 성별, 소개글, 생년월일, 이메일, 닉네임, 지역, 포지션(current, 선발아닐시 want), 선출여부, 등번호
         */

        log.info("[API CALL] : /api/player/findplayers/{}",teamName);

        List<PlayerDto> players;
        HttpStatus status = HttpStatus.OK;// 오류 이슈 처리 해줘야되는데


        players = playerService.getAllPlayerByTeamName(teamName);

        return new ResponseEntity<>(players, status);
        // 구현 미완료
    }


}
