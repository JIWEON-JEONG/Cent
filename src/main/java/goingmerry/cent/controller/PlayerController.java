package goingmerry.cent.controller;

import goingmerry.cent.VO.PlayerVO;
import goingmerry.cent.dto.PlayerDto;
import goingmerry.cent.service.PlayerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.LifecycleState;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
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

    // 새로운 선수를 저장하는 api. 이메일 받아서 검색해서 정보 끌어와서 저장?
    // playerInfo {playerName, email}
    @PostMapping(value = "/save")
    public ResponseEntity playerSave(@RequestBody Map<String, String> playerInfo) {

        log.info("[API CALL] : /api/player/save");


        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping(value = "/getuser")
    public ResponseEntity<List<Map<String, String>>> userInfo() {

        log.info("[API CALL] : /api/player/getuser");

        List<Map<String, String>> userList = playerService.findAllUser();
        HttpStatus status = null;

        log.debug(userList.toString());

        return new ResponseEntity<>(userList, status);
    }

    @GetMapping(value = "/finduser/{email}")
    public ResponseEntity<Map<String, String>> findUserByEmail(@PathVariable String email) {

        log.info("[API CALL] : /api/player/finduser/{}",email);

        HttpStatus status = null;
        Map<String, String> userInfo = playerService.findUserInfo(email);

        if (email.equals("")) {
            log.error("email is not null value!");

        }
        else if (userInfo.get("name") == null) {

        }

        return new ResponseEntity<>(userInfo, status);
    }

    @GetMapping(value = "/findplayers/{teamName}")
    public ResponseEntity<List<PlayerVO>> findAllPlayers(@PathVariable String teamName) {

        log.info("[API CALL] : /api/player/findplayers/{}",teamName);

        List<PlayerVO> players;
        HttpStatus status = null;

        players = playerService.getAllPlayer(teamName);

        return new ResponseEntity<>(players, status);
    }
}
