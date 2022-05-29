package goingmerry.cent.service;

import goingmerry.cent.domain.Formation;
import goingmerry.cent.domain.Player;
import goingmerry.cent.dto.PlayerDto;
import goingmerry.cent.repository.FormationRepository;
import goingmerry.cent.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class FormationService {

    private final FormationRepository formationRepository;
    
    private final PlayerRepository playerRepository;
/*
int back, int circle, String name, int x, int y, String position, String teamName
 */
    // 팀 생성 시 기본 11개의 포메이션판 생성. 차후 기본 포메이션 값 지정해서 넣을 예정
    public void createDefaultFormation(String teamName) {

        List<Formation> formations = new ArrayList<>();

        for(int i=0;i<11;i++) {
            formations.add(new Formation(null, i+1, null, 0, 0, null, teamName));
        }
        
        System.out.println(formations);
        
        formationRepository.saveAll(formations);
    }
    
    // 팀명에 해당하는 포메이션 가져오기
    public List<Formation> selectTeamFormation(String teamName) {
     
        List<Formation> formationList = formationRepository.findFormationByTeamName(teamName);
        return formationList;
    }
    
    // 팀명에 해당하는 선수 리스트 가져오기
    public List<PlayerDto> findPlayersByTeamName(String teamName) {
        
        List<PlayerDto> players = null;
        try {
            List<Player> playerEntity = playerRepository.findPlayersByTeamName(teamName);

            for (int i=0;i<playerEntity.size();i++){
//                players.add(playerEntity.get(i))
            }
        }catch (Exception e) {
            log.error("playerList is Null !");
        }

        return players;
    }
        
    // 포메이션 저장
    public void saveFormation(List<Formation> formations) {
        //save에서 업데이트 기능도 해주나? -> 해주더라
        formationRepository.saveAll(formations);
    }



}
