package goingmerry.cent.controller;

import goingmerry.cent.domain.DesignateFormation;
import goingmerry.cent.domain.Formation;
import goingmerry.cent.domain.Player;
import goingmerry.cent.repository.FormationRepository;
import goingmerry.cent.repository.PlayerRepository;
import goingmerry.cent.service.FormationDesignService;
import goingmerry.cent.service.FormationService;
import goingmerry.cent.service.PlayerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    private final FormationRepository formationRepository;

    private final PlayerRepository playerRepository;

    private final FormationService formationService;

    private final PlayerService playerService;

    private final FormationDesignService designFormationService;

    //전술판(포메이션)을 post 로 받아 데이터베이스에 저장한다.
    // 포메이션 받을 때 어떻게 받을 지 부기씨한테 물어볼 것
    @PostMapping(value = "/save")
    public List<Formation> saveFormation(@RequestBody List<Formation> formation) {

        List<Formation> formations = formation;

        formationService.saveFormation(formations);

        return formations;
    }

    //해당하는 팀명의 포메이션 리스트를 검색, 반환한다.
    @GetMapping(value = "/get")
    public List<Formation> listFormation(@RequestParam String teamName) {

        List<Formation> formationList = null;

        formationList = formationService.selectTeamFormation(teamName);

        return formationList;
    }

    @GetMapping(value = "/select")
    public List<Map<String, String>> dropDownSelect(@RequestParam String formationName) {

        List<Map<String, String>> designedFormation = null;

        designedFormation = designFormationService.getSelectedFormation(formationName);

        return designedFormation;
    }

    // 선수 리스트에서 해당하는 팀명의 선수 리스트를 꺼내오는 것
    @GetMapping(value = "/players")
    public List<Player> playerList(@RequestParam String teamName) {

        List<Player> players = null;
        players = formationService.findPlayersByTeamName(teamName);
        return players;
    }

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
