package goingmerry.cent.service;

import goingmerry.cent.domain.User;
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

}
