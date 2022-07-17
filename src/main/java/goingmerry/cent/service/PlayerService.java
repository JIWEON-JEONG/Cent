package goingmerry.cent.service;

import goingmerry.cent.domain.Player;
import goingmerry.cent.domain.Team;
import goingmerry.cent.domain.User;
import goingmerry.cent.dto.UserDto;
import goingmerry.cent.dto.player.PlayerDto;
import goingmerry.cent.dto.player.PlayerSaveDto;
import goingmerry.cent.dto.player.formationPlayerDto;
import goingmerry.cent.repository.PlayerRepository;
import goingmerry.cent.repository.TeamRepository;
import goingmerry.cent.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class PlayerService {

//    private final FormationRepository formationRepository;

    private final PlayerRepository playerRepository;

    private final UserRepository userRepository;

    private final TeamRepository teamRepository;

    // 전체 선수 목록 조회
//    public List<PlayerDto> findAllPlayers() {
//        log.info("[service find_All_User call ");
//
//        List<Player> entity = playerRepository.findAll();
//        List<PlayerDto> response = new ArrayList<>();
//
//        for (Player player : entity) {
//            response.add(new PlayerDto(player));
//        }
//
//        return response;
//    }

    // 선수 저장
    public PlayerDto save(PlayerSaveDto playerInfo) {
        log.info("[service player_save call, save player name is {}]");

        playerInfo.setAlready(false);
        playerInfo.setBack("0");
        playerInfo.setCaptain(false);

        Optional<User> user = userRepository.findById(playerInfo.getUserId());
        Optional<Team> team = teamRepository.findById(playerInfo.getTeamId());

        Player entity = playerRepository.save(Player.builder()
                .user(user.get())
                .team(team.get())
                .back(playerInfo.getBack())
                .current(null)
                .already(playerInfo.isAlready())
                .formationIndex(null)
                .isCaptain(playerInfo.isCaptain())
                .build());

        PlayerDto res = PlayerDto
                .builder()
                .entity(entity)
                .build();

        return res;
    }

//    // 선수 업데이트
//    public void updatePlayers(List<PlayerDto> players) {
//        log.info("[service player_update call]");
//
//        List<Player> playersEntity = new ArrayList<>();
//
//        for (PlayerDto player : players) {
//            playersEntity.add(toEntity(player));
//        }
//
//        playerRepository.saveAll(playersEntity);
//    }


    // 팀 소속 선수 명 수
//    public int countPlayer(Long teamId) {
//
//        Optional<Team> teamEntity =
//
//        return playerRepository.countPlayersByTeamName(teamId);
//    }

    // 유저 정보 get. merge 뒤에 유저서비스로 옮기는게 좋을 것 같다.
//    public UserDto findUserInfo(Long id) {
//
////        Map<String, String> userInfo = userRepository.findUserInfoByEmail(email);
//
//        Optional<User> entity = userRepository.findById(id);
//
//        return UserDto.builder()
//                .entity(entity.get())
//                .build();
//    }

    // 팀 정보 페이지 용, wireflow에서 보여지는 선수 정보. 등번호, 포지션, 선수명 -> 맵 반환
//    public List<formationPlayerDto> formationPlayerInfo(String teamName) { // teamName -> 테이블 연결 후 팀Id로 변경 필요
//
//        log.info("teamName = {}",teamName);
//
//        List<Player> entityList = playerRepository.findPlayersByTeamName(teamName);
//        List<formationPlayerDto> resList = new ArrayList<>();
//
//        for (Player player : entityList) {
//            resList.add(formationPlayerDto
//                    .builder()
//                    .entity(player)
//                    .build());
//        }
//
//        return resList;
//    }

    // 선수 관리 페이지 용, 팀 전체 선수 정보 get
//    public List<PlayerDto> getAllPlayerByTeamName(String teamName) {
//
//        List<PlayerDto> players = new ArrayList<>();
//
//        List<Player> entity = playerRepository.findPlayersByTeamName(teamName);
//
//        for (Player player : entity) {
//            players.add(PlayerDto
//                    .builder()
//                    .entity(player)
//                    .build());
//        }
//
//        return players;
//    }
//
//    public PlayerDto patchBackNum(Long playerId, String newNum) {
//
//        Optional<Player> player = playerRepository.findById(playerId);
////
////        playerRepository.save(Player.builder()
////                .id(player.get().getId())
////                .back(newNum)
////                .build());
//
//        playerRepository.updateBack(playerId, newNum);
//
//        return PlayerDto
//                .builder()
//                .entity(playerRepository.getById(playerId))
//                .build();
//    }

    // update 하나로 합치기 위해서 아래 트랜젝션 연산으로 변경중
    @Transactional // 디비 영속성 이용해서 트랜젝션 연산, 리포지토리에 update 로직 넣지 않고 엔티티 클래스에서 업데이트 가능함.
//    public Long update(Long id, PlayerUpdateDto requestDto) {
    public Long update(Long id, String newNum) {
        Player player = playerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id = " +id));

//        player.update(requestDto.getBack());
        player.update(newNum);

        return id;
    }

//    public PlayerDto delete(Long playerId) {
//
//        Optional<Player> player = playerRepository.findById(playerId);
//
//        playerRepository.deleteById(player.get().getId());
//
//        return PlayerDto
//                .builder()
//                .entity(player.get())
//                .build();
//    }

    Player toEntity(PlayerSaveDto req) {

        return Player.builder()
                .build();
    }

}
