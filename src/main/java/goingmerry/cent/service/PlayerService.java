package goingmerry.cent.service;

import goingmerry.cent.VO.PlayerVO;
import goingmerry.cent.domain.Player;
import goingmerry.cent.domain.User;
import goingmerry.cent.dto.PlayerDto;
import goingmerry.cent.repository.FormationRepository;
import goingmerry.cent.repository.PlayerRepository;
import goingmerry.cent.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class PlayerService {

    private final FormationRepository formationRepository;

    private final PlayerRepository playerRepository;

    private final UserRepository userRepository;

    public List<Map<String, String>> findAllUser() {
        log.info("##findAllUser##");
        List<Map<String, String>> userList = userRepository.findUserInfo();
        log.debug(userList.toString());
        return userList;
    }

// 선수 저장 구현해야 함
    public String savePlayer(PlayerDto playerInfo) {

        String playerName = playerInfo.getName();
        String teamName = playerInfo.getTeamName();
        String email = playerInfo.getUserEmail();

        Player savePlayer = Player
                .builder()
                .name(playerName)
                .teamName(teamName)
                .userEmail(email)
                .already(false)
                .back(null)
                .want(null)
                .current(null)
                .leader(false)
                .build();

        playerRepository.save(savePlayer);

        return playerName;
    }

    public Map<String, String> findUserInfo(String email) {

        Map<String, String> userInfo = userRepository.findUserInfoByEmail(email);

        return userInfo;
    }

    // 신청 목록
    // 신청한 사람 목록을 테이블을 따로 만들어야 한다.
    // 신청 페이지 용 -> 특정 선수 정보가 아니라 신청한 사람 정보 전부 다!
    public void getApplicationList(String teamName) {

    }

    // 팀 정보 페이지 용, wireflow에서 보여지는 선수 정보. 등번호, 포지션, 선수명
    public List<Map<String, String>> teamInfoPlayerInfo() {

        List<Map<String, String>> playerInfo = null;

        return playerInfo;
    }

    // 선수 관리 페이지 용, 전체 팀 선수 정보 get
    public List<PlayerVO> getAllPlayer(String teamName) {

        List<PlayerVO> players = null;

        List<Player> playerList = playerRepository.findAll();

        for(int i=0;i<playerList.size();i++) {
            PlayerVO playerVO = PlayerVO
                    .builder()
                    .name(playerList.get(i).getName())
                    .teamName(playerList.get(i).getTeamName())
                    .userEmail(playerList.get(i).getUserEmail())
                    .already(playerList.get(i).isAlready())
                    .back(playerList.get(i).getBack())
                    .want(playerList.get(i).getWant())
                    .current(playerList.get(i).getCurrent())
                    .leader(playerList.get(i).isLeader())
                    .build();

            players.add(playerVO);
        }

        return players;
    }


}
