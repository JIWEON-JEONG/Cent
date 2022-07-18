package goingmerry.cent.service;

import goingmerry.cent.domain.Player;
import goingmerry.cent.domain.Team;
import goingmerry.cent.domain.User;
import goingmerry.cent.dto.TeamDto;
import goingmerry.cent.dto.TeamFormationDto;
import goingmerry.cent.dto.TeamSaveDto;
import goingmerry.cent.dto.player.PlayerDto;
import goingmerry.cent.repository.PlayerRepository;
import goingmerry.cent.repository.TeamRepository;
import goingmerry.cent.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class TeamService {

    private final TeamRepository teamRepository;

    private final PlayerRepository playerRepository;

    private final UserRepository userRepository;

    //팀명 조회해서 있는지 없는지 판단해주는 메소드
    public boolean isExistTeam(String teamName){
        if(teamRepository.findByTeamName(teamName).isEmpty()){
            log.info("Not Exist Team");
            return false;
        }
        else {
            log.info("Exist TeamName : {}", teamName);
            return true;
        }
    }

    //존재하지 않던 팀의 새로운 생성. 팀 정보 받아서 디비에 저장해주는 역할을 한다.
    public TeamDto create(TeamSaveDto req) {

        // 7.17 기존로직 삭제
//              팀의 업데이트 또한 여기서 판단한다. 업데이트 하는 경우는 일단 필수값 이외 필드로 한정한다.
//                teamRepository.findByTeamName(req.getTeamName())
//                .map(entity -> entity.update(req.getIntro(), req.getLogo()))
//                        .orElse(toEntity(req));

        req.setFormationName("4421"); // default formationName = 4421;

        Team entity = toEntity(req);

        teamRepository.save(entity);

        TeamDto res = TeamDto.builder()
                .entity(entity).build();


        log.info("팀 저장. 저장된 팀 정보 : {}", res);

        return res;
    }

    public Team toEntity(TeamSaveDto req) {
        Optional<User> leader = userRepository.findById(req.getLeaderId());
        return Team
                .builder()
                .teamName(req.getTeamName())
                .leaderUser(leader.get())
                .area(req.getArea())
                .formationName(req.getFormationName())
                .intro(req.getIntro())
                .logo(req.getLogo())
                .build();
    }

    public Map<String, Boolean> isRequireValueNull(Map<String,String> teamInfo) {

        Map<String, Boolean> resultMap = new HashMap<>();

        String teamName = teamInfo.get("teamName");
        String area = teamInfo.get("area");

        if(teamName.equals("")) {
            resultMap.put("teamName", true);
        }
        else {
            resultMap.put("teamName", false);
        }

        if (area.equals("")) {
            resultMap.put("area", true);
        }
        else {
            resultMap.put("area", false);
        }


        return resultMap;
    }

    @Transactional
    public void deleteTeam(Long teamId) {

        //팀 삭제 시 존재하는 팀을 삭제하는가는 일단 고려하지 않았다. 팀장 한명당 팀 정보는 하나밖에 없을 테니까.

        Optional<Team> entity = teamRepository.findById(teamId);

        teamRepository.delete(entity.get());
    }

    // 해당 어노테이션 없으면 update 로직 안 돌아감.
    @Transactional
    public TeamFormationDto updateFormation(TeamFormationDto req) {

        Long teamId = req.getTeamId();
        String formationName = req.getFormationName();

        // team get
        Optional<Team> optionalTeam = teamRepository.findById(teamId);
        Team teamEntity = optionalTeam.get();

        // team update formationName;
        teamEntity.update(formationName);

        // get players;
        List<PlayerDto> players = req.getPlayerList();

        // player update
        for (PlayerDto player : players) {
            Player entity = playerRepository.getById(player.getId());

            entity.update(player);

        }

        return req;
    }

}
