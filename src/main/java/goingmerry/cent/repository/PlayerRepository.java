package goingmerry.cent.repository;


import goingmerry.cent.domain.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface PlayerRepository extends JpaRepository<Player, Long> {

//    int countPlayersByTeamName(String teamName);

    @Query(value = "SELECT * FROM player WHERE user_id = :userId", nativeQuery = true)
    Optional<Player> findByUserId(@Param("userId")Long userId);

}
