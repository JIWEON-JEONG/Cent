package goingmerry.cent.repository;


import goingmerry.cent.domain.Formation;
import goingmerry.cent.domain.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PlayerRepository extends JpaRepository<Player, String> {

    // 검증 안 됨.
    @Query(value = "SELECT p.back, p.name, p.current, p.already, p.team_name" +
            " FROM player p WHERE p.team_name = :team_name ", nativeQuery = true)
    List<Player> findPlayersByTeamName(@Param("team_name") String teamName);
}
