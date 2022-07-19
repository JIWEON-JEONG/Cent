package goingmerry.cent.controller;

import goingmerry.cent.dto.PlayerInfoDto;
import goingmerry.cent.dto.UserDto;
import goingmerry.cent.dto.player.PlayerDto;
import goingmerry.cent.dto.player.PlayerSaveDto;
import goingmerry.cent.dto.player.formationPlayerDto;
import goingmerry.cent.repository.PlayerRepository;
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

    private final PlayerRepository playerRepository;

    // 새로운 선수를 저장한다.
    // teamid, userid?
    @PostMapping(value = "/save")
    public ResponseEntity<PlayerDto> save(@RequestBody PlayerSaveDto playerInfo) {

        log.info("[API CALL] : /api/player/save, save user Id is {}", playerInfo.getUserId());

        if(!playerRepository.findByUserId(playerInfo.getUserId()).isEmpty()) {
            log.error("is already join this team! ");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        PlayerDto res = playerService.save(playerInfo);

        return new ResponseEntity(res, HttpStatus.OK);
    }

    // 전체 선수 목록 get
    @GetMapping(value = "/getAll")
    public ResponseEntity<List<PlayerDto>> findAllPlayers() {

        log.info("[API CALL] : /api/player/getuser");

        List<PlayerDto> res = playerService.findAllPlayers();

        log.debug(res.toString());

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    // player 단일 선수 조회
    @GetMapping(value = "/find")
    public ResponseEntity findUserById(@RequestParam Long playerId) {

        PlayerInfoDto res = playerService.find(playerId);

        return new ResponseEntity<>(res, HttpStatus.OK);
    }
//
//    // 특정 팀의 선수 관리 목록을 불러오기 위함. 선수정보에 유저정보까지 빼서 갖다 넣어야 하기 때문에 유저 테이블과의 연결이 필요하다.
//    @GetMapping(value = "/findplayers/{teamName}")
//    public ResponseEntity<List<PlayerDto>> findAllPlayers(@PathVariable String teamName) {
//
//        /*
//        이름, 성별, 소개글, 생년월일, 이메일, 닉네임, 지역, 포지션(current, 선발아닐시 want), 선출여부, 등번호
//         */
//
//        log.info("[API CALL] : /api/player/findplayers/{}",teamName);
//
//        List<PlayerDto> players;
//        HttpStatus status = HttpStatus.OK;// 오류 이슈 처리 해줘야되는데
//
//
//        players = playerService.getAllPlayerByTeamName(teamName);
//
//        return new ResponseEntity<>(players, status);
//        // 구현 미완료
//    }
//
//    // 팀이름 받아서 해당 팀 선수 명단 불러와준다. 선수 관리 페이지 x 팀 정보 페이지.
//    @GetMapping(value = "/playerInfo")
//    public ResponseEntity<List<formationPlayerDto>> formationPlayerInfo(@RequestParam String teamName) {
//
//        log.info("[API CALL] : /api/player/playerInfo, teamName={}", teamName);
//        List<formationPlayerDto> res = playerService.formationPlayerInfo(teamName);
//
//        return new ResponseEntity<>(res, HttpStatus.OK);
//    }
//
////    @PatchMapping(value = "/patchBackNum")
////    public ResponseEntity<PlayerDto> patchBackNum(@RequestParam Long playerId, String newNum) {
////        log.info("[API CALL] : /api/player/patchBackNum");
////        PlayerDto res = playerService.patchBackNum(playerId, newNum);
////
////        return new ResponseEntity<>(res, HttpStatus.OK);
////    }
//
//    @PatchMapping(value = "/patchBackNum")
//    public ResponseEntity patchBackNum(@RequestParam Long playerId, String newNum) {
//        log.info("[API CALL] : /api/player/patchBackNum");
//
//        Long patchId = playerService.update(playerId, newNum);
//        return new ResponseEntity<>(patchId, HttpStatus.OK);
//    }
//
//    @DeleteMapping(value = "/delete")
//    public ResponseEntity<PlayerDto> delete(@RequestParam Long playerId) {
//        log.info("[API CALL] : /api/player/delete");
//        PlayerDto res = playerService.delete(playerId);
//
//        return new ResponseEntity<>(HttpStatus.OK);
//    }



}
