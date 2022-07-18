package goingmerry.cent.service;

import goingmerry.cent.domain.Formation;
import goingmerry.cent.dto.FormationDto;
import goingmerry.cent.repository.FormationRepository;
import goingmerry.cent.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class FormationService {

    private final FormationRepository formationRepository;
    
    private final PlayerRepository playerRepository;


    public FormationDto getFormation(String teamName) {

        FormationDto currentFormation = new FormationDto();
        Formation formation = formationRepository.findFormationByTeamName(teamName);

        currentFormation.setTeamName(formation.getTeamName());
        currentFormation.setFormation(formation.getFormation());
        return currentFormation;
    }

    // 팀 생성 시 기본 포메이션 생성 -> 팀 생성시 로직에 포함시켰으므로 삭제
//    public FormationDto createDefaultFormation(String teamName) {
//
//        log.info("팀 최초 생성, 기본 포메이션 생성!");
//
//        String defaultFormation = "4421";
//        Formation formation = Formation
//                .builder()
//                .formation(defaultFormation)
//                .teamName(teamName)
//                .build();
//
//        formationRepository.save(formation);
//
//        FormationDto formationDto = new FormationDto();
//        formationDto.setTeamName(formation.getTeamName());
//        formationDto.setFormation(formation.getFormation());
//
//        return formationDto;
//    }


    // 포메이션 저장
    public FormationDto saveFormation(FormationDto formation) {
        //save에서 업데이트 기능도 해주나? -> 해주더라

        Formation entity = Formation.builder()
                .formation(formation.getFormation())
                .teamName(formation.getTeamName())
                .build();

        formationRepository.save(entity);
        return formation;
    }


//
//    // 팀 생성 시 기본 11개의 포메이션판 생성. 차후 기본 포메이션 값 지정해서 넣을 예정
//    public void createDefaultFormation(String teamName) {
//
//        List<Formation> formations = new ArrayList<>();
//
//        for(int i=0;i<11;i++) {
//            formations.add(new Formation(null, i+1, null, 0, 0, null, teamName));
//        }
//
//        System.out.println(formations);
//
//        formationRepository.saveAll(formations);
//    }
//
//    // 팀명에 해당하는 포메이션 가져오기
//    public List<Formation> selectTeamFormation(String teamName) {
//
//        List<Formation> formationList = formationRepository.findFormationByTeamName(teamName);
//        return formationList;
//    }
//
//    // 팀명에 해당하는 선수 리스트 가져오기
//    public List<PlayerDto> findPlayersByTeamName(String teamName) {
//
//        List<PlayerDto> players = null;
//        try {
//            List<Player> playerEntity = playerRepository.findPlayersByTeamName(teamName);
//
//            for (int i=0;i<playerEntity.size();i++){
////                players.add(playerEntity.get(i))
//            }
//        }catch (Exception e) {
//            log.error("playerList is Null !");
//        }
//
//        return players;
//    }
//
//    // 포메이션 저장
//    public void saveFormation(List<Formation> formations) {
//        //save에서 업데이트 기능도 해주나? -> 해주더라
//        formationRepository.saveAll(formations);
//    }
//


}
