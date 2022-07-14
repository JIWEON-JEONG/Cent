package goingmerry.cent.service;

import goingmerry.cent.domain.Player;
import goingmerry.cent.domain.User;
import goingmerry.cent.dto.PlayerDto;
import goingmerry.cent.dto.UserDto;
import goingmerry.cent.dto.formationPlayerDto;
import goingmerry.cent.repository.PlayerRepository;
import goingmerry.cent.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class PlayerService {

//    private final FormationRepository formationRepository;

    private final PlayerRepository playerRepository;

    private final UserRepository userRepository;

    // 전체 선수 목록 조회
    public List<PlayerDto> findAllPlayers() {
        log.info("[service find_All_User call ");

        List<Player> entity = playerRepository.findAll();
        List<PlayerDto> response = new ArrayList<>();

        for (Player player : entity) {
            response.add(new PlayerDto(player));
        }

        return response;
    }

    // 선수 저장
    public PlayerDto savePlayer(PlayerDto playerInfo) {
        log.info("[service player_save call, save player name is {}]",playerInfo.getName());
        Player savePlayer = toEntity(playerInfo);

        playerRepository.save(savePlayer);

        return new PlayerDto(savePlayer);
    }

    // 선수 업데이트
    public void updatePlayers(List<PlayerDto> players) {
        log.info("[service player_update call]");

        List<Player> playersEntity = new ArrayList<>();

        for (PlayerDto player : players) {
            playersEntity.add(toEntity(player));
        }

        playerRepository.saveAll(playersEntity);
    }


    // 팀 소속 선수 명 수
    public int countPlayer(String teamName) {

        return playerRepository.countPlayersByTeamName(teamName);
    }

    // 유저 정보 get. merge 뒤에 유저서비스로 옮기는게 좋을 것 같다.
    public UserDto findUserInfo(Long id) {

//        Map<String, String> userInfo = userRepository.findUserInfoByEmail(email);

        Optional<User> entity = userRepository.findById(id);

        return UserDto.builder()
                .entity(entity.get())
                .build();
    }

    // 팀 정보 페이지 용, wireflow에서 보여지는 선수 정보. 등번호, 포지션, 선수명 -> 맵 반환
    public List<formationPlayerDto> formationPlayerInfo(String teamName) { // teamName -> 테이블 연결 후 팀Id로 변경 필요

        log.info("teamName = {}",teamName);

        List<Player> entityList = playerRepository.findPlayersByTeamName(teamName);
        List<formationPlayerDto> resList = new ArrayList<>();

        for (Player player : entityList) {
            resList.add(formationPlayerDto
                    .builder()
                    .entity(player)
                    .build());
        }

        return resList;
    }

    // 선수 관리 페이지 용, 팀 전체 선수 정보 get
    public List<PlayerDto> getAllPlayerByTeamName(String teamName) {

        List<PlayerDto> players = new ArrayList<>();

        List<Player> entity = playerRepository.findPlayersByTeamName(teamName);

        for (Player player : entity) {
            players.add(PlayerDto
                    .builder()
                    .entity(player)
                    .build());
        }

        return players;
    }

    public PlayerDto patchBackNum(Long playerId, String newNum) {

        Optional<Player> player = playerRepository.findById(playerId);
//
//        playerRepository.save(Player.builder()
//                .id(player.get().getId())
//                .back(newNum)
//                .build());

        playerRepository.updateBack(playerId, newNum);

        return PlayerDto
                .builder()
                .entity(playerRepository.getById(playerId))
                .build();
    }

    public PlayerDto delete(Long playerId) {

        Optional<Player> player = playerRepository.findById(playerId);

        playerRepository.deleteById(player.get().getId());

        return PlayerDto
                .builder()
                .entity(player.get())
                .build();
    }


    // 팀 정보 페이지를 위해서 엔티티를 원하는 정보로 바꿔주는 메소드.
    public Map<String, Object> entityToTeamInfo(Player entity) {
        Map<String, Object> info = new HashMap<>();

        info.put("back", entity.getBack());
        info.put("name", entity.getName());
        info.put("current", entity.getCurrent());
        info.put("want", entity.getWant());

        return info;

    }

    Player toEntity(PlayerDto playerInfo) {
        String playerName = playerInfo.getName();
        String teamName = playerInfo.getTeamName();
        String email = playerInfo.getUserEmail();
        String back = playerInfo.getBack();
        String want = playerInfo.getWant();
        boolean already = playerInfo.isAlready();

        log.info("save start , name : {}, teamName : {}, email : {}",playerName,teamName,email);

        // 기본형 구현, 추가적으로 정보 보내는 내용 확정될 시 수정할 것.
        return Player.builder()
                .name(playerName)
                .teamName(teamName)
                .userEmail(email)
                .already(already)
                .back(back)
                .want(want)
                .current(null)
                .leader(false)
                .formationIndex(null)
                .build();
    }

}
