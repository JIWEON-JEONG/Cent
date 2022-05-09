package goingmerry.cent.service;

import goingmerry.cent.repository.FormationRepository;
import goingmerry.cent.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PlayerService {

    private final FormationRepository formationRepository;

    private final PlayerRepository playerRepository;



}
