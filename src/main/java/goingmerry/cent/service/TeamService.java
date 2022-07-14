package goingmerry.cent.service;

import goingmerry.cent.domain.Team;
import goingmerry.cent.dto.TeamDto;
import goingmerry.cent.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class TeamService {
    @Autowired
    private final TeamRepository teamRepository;

    @Autowired
    private final FormationService formationService;

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
    public Map<String, String> createTeam(TeamDto team) {

        Map<String, String> resultMap = new HashMap<>();

        resultMap.put("팀명", team.getTeamName());
        resultMap.put("로고", team.getLogo());
        resultMap.put("소개글", team.getIntro());
        resultMap.put("활동지역", team.getArea());

        //팀의 업데이트 또한 여기서 판단한다. 업데이트 하는 경우는 일단 필수값 이외 필드로 한정한다.
        Team dbTeam = teamRepository.findByTeamName(team.getTeamName())
                .map(entity -> entity.update(team.getIntro(), team.getLogo()))
                        .orElse(toEntity(team));

        teamRepository.save(dbTeam);

//        formationService.createDefaultFormation(team.getTeamName()); // 기본 포메이션 생성 -> 현재 사용하지 않음.
        log.info("팀 저장. 저장된 팀 정보 : {}", resultMap);

        return resultMap;
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
    public void deleteTeam(String deleteTeamName) {

        //팀 삭제 시 존재하는 팀을 삭제하는가는 일단 고려하지 않았다. 팀장 한명당 팀 정보는 하나밖에 없을 테니까.
        teamRepository.deleteByTeamName(deleteTeamName);
    }


    Team toEntity(TeamDto team){
        return Team.builder()
                .teamName(team.getTeamName())
                .logo(team.getLogo())
                .intro(team.getIntro())
                .area(team.getArea())
                .build();
    }

}
