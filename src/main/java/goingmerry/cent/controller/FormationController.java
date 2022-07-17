package goingmerry.cent.controller;

//import goingmerry.cent.domain.DesignateFormation;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import goingmerry.cent.dto.FormationDto;
import goingmerry.cent.dto.player.PlayerDto;
import goingmerry.cent.repository.PlayerRepository;
import goingmerry.cent.service.FormationService;
import goingmerry.cent.service.PlayerService;
import goingmerry.cent.service.TeamService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

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

    private final TeamService teamService;

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
//    // JSONObject 사용 필요
//    @GetMapping(value = "/get")
//    public ResponseEntity listFormation(@RequestParam String teamName) {
//        log.info("[API CALL : /api/formation/get, Team is \"{}\" ]", teamName);
//        JSONObject response = new JSONObject();
//
//        // 존재하지 않는 팀일 경우 -> 경우 없는데 일단 만든거
//        if(!teamService.isExistTeam(teamName)) {
//
//            log.error("is not exist team!");
//            response.put("ERROR","is not exist team!");
//
//            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
//        }
//
//        FormationDto formationDto = formationService.getFormation(teamName);
//        JSONObject formationObject = new JSONObject(); // team info
//
//        formationObject.put("teamName", formationDto.getTeamName());
//        formationObject.put("formation", formationDto.getFormation());
//
//        response.put("teamInfo", formationObject);
//
//        JSONArray playerList = new JSONArray();
//
//        int playerCount = playerService.countPlayer(teamName);
//        log.info("current team player Count = {}", playerCount);
//        List<PlayerDto> players = playerService.getAllPlayerByTeamName(teamName);
//
//        for(int i=0;i<playerCount;i++) {
//            playerList.add(players.get(i));
//        }
//
//        response.put("playerList", playerList);
//        log.info(response.toJSONString());
//
//        return new ResponseEntity<>(response, HttpStatus.OK);
//
//
//    }


//    //전술판(포메이션)을 post 로 받아 데이터베이스에 저장한다.
//    @PostMapping(value = "/save")
//    public FormationDto saveFormation(@RequestBody FormationDto formationDto) {
//
//        log.info("[API CALL : /api/formation/save, Team is \"{}\" ]", formationDto.getTeamName());
//
//        FormationDto dto = formationService.saveFormation(formationDto);
//
//        return dto;
//    }

    // 포메이션 및 선수 리스트의 변경점 업데이트
//    @PostMapping(value = "/update")
//    public ResponseEntity saveFormation(@RequestBody JSONObject json) throws Exception {
//
//        log.info("[API CALL : /api/formation/save");
//        log.info("{}\n",json.toJSONString());
//
//        Object listOb = json.get("playerList");
//        Object formationOb = json.get("teamInfo");
//
//        log.info("listOb : {}\n",listOb);
//        log.info("formationOb : {}\n",formationOb);
//
//        ObjectMapper mapper = new ObjectMapper();
//        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false); //파라미터Map에서 DTO에 들어있지 않는 변수가 있어도 무시함.
//
//        List<PlayerDto> playerList = Arrays.asList(mapper.convertValue(listOb, PlayerDto[].class));
//        FormationDto formation = mapper.convertValue(formationOb,FormationDto.class);
//
//        System.out.println("teamName : " + formation.getTeamName() +"\nformation : "+ formation.getFormation());
//        formationService.saveFormation(formation);
//        playerService.updatePlayers(playerList);
//
//        JSONObject response = new JSONObject();
//
//        response.put("teamName", formation.getTeamName());
//        response.put("formation", formation.getFormation());
//        JSONArray players = new JSONArray();
//
//        List<PlayerDto> teamPlayers = playerService.getAllPlayerByTeamName(formation.getTeamName());
//
//        for(int i=0;i<teamPlayers.size();i++) {
//            players.add(teamPlayers.get(i));
//        }
//        response.put("playerList", players);
//
//        return new ResponseEntity<>(response,HttpStatus.OK);
//    }

//######################################################################################

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
//    // 포메이션 업데이트 -> save에서 같은 역할 수행하기 때문에 필요 없는 메소드입니다.
//    @PostMapping(value = "/update")
//    public List<Formation> updateFormation(@RequestBody List<Formation> formation) {
//
//        List<Formation> formations = formation;
//
//        log.debug("teamName : {}", teamName);
//        log.debug(String.valueOf(formation));
//
//        formationService.saveFormation(formations);
//
//        return formations;
//    }

}
