package goingmerry.cent.controller;

//import goingmerry.cent.domain.DesignateFormation;
import goingmerry.cent.domain.Formation;
import goingmerry.cent.domain.Player;
import goingmerry.cent.dto.PlayerDto;
import goingmerry.cent.repository.FormationRepository;
import goingmerry.cent.repository.PlayerRepository;
import goingmerry.cent.service.FormationDesignService;
import goingmerry.cent.service.FormationService;
import goingmerry.cent.service.PlayerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/formation")
public class FormationController {
/*
전술판 페이지에서 이루어지는 Api 들의 공간.
선수 관리 페이지에서 이루어질 Api 는 별개로 작성한다.
 */

    private final FormationService formationService;

    private final PlayerService playerService;


    //해당하는 팀명의 포메이션 리스트를 검색, 반환한다.
    // player 테이블과 formation 테이블 두개 다 조회해서 한번에 담아 줄 것.
    /*
     * {
     *   formation
     *   playlist(플레이어 테이블){
     *       ~
     *       }
     * }
     */


    // 22.6.26 기존 코드 주석처리.
//    @GetMapping(value = "/get")
//    public List<Formation> listFormation(@RequestParam String teamName) {
//
//        List<Formation> formationList = null;
//
//        formationList = formationService.selectTeamFormation(teamName);
//
//        return formationList;
//    }

//
//    //전술판(포메이션)을 post 로 받아 데이터베이스에 저장한다.
//    @PostMapping(value = "/save")
//    public List<Formation> saveFormation(@RequestBody List<Formation> formation) {
//
//        List<Formation> formations = formation;
//
//        formationService.saveFormation(formations);
//
//        return formations;
//    }
//

//
//    // 드롭다운으로 선택한 포메이션명에 따른 포메이션을 보내준다.
//    @GetMapping(value = "/select")
//    public List<Map<String, String>> dropDownSelect(@RequestParam String formationName) {
//
//        List<Map<String, String>> designedFormation = null;
//
//        designedFormation = designFormationService.getSelectedFormation(formationName);
//
//        return designedFormation;
//    }
//
//    // 선수 리스트에서 해당하는 팀명의 선수 리스트를 꺼내오는 것. 팀 정보 페이지
//    @GetMapping(value = "/players/{teamName}")
//    public ResponseEntity<List<Map<String, Object>>> playerList(@PathVariable String teamName) {
//
//        log.info("[API CALL] : /api/player/players/{}",teamName);
//
//        List<Map<String, Object>> players;
//        HttpStatus status = HttpStatus.OK;
//
//        players = playerService.teamInfoPlayerInfo(teamName);
//
//        return new ResponseEntity<>(players, status);
//    }
//
////    // 포메이션 업데이트 -> save에서 같은 역할 수행하기 때문에 필요 없는 메소드입니다.
////    @PostMapping(value = "/update")
////    public List<Formation> updateFormation(@RequestBody List<Formation> formation) {
////
////        List<Formation> formations = formation;
////
////        log.debug("teamName : {}", teamName);
////        log.debug(String.valueOf(formation));
////
////        formationService.saveFormation(formations);
////
////        return formations;
////    }

}
