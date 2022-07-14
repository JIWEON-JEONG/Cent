package goingmerry.cent.repository;


import goingmerry.cent.domain.Formation;
import goingmerry.cent.domain.Player;
import goingmerry.cent.dto.PlayerDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

public interface PlayerRepository extends JpaRepository<Player, Long> {

    //전체 선수 목록 get
    List<Player> findPlayersByTeamName(String teamName);

    int countPlayersByTeamName(String teamName);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value=" UPDATE player "
            + " SET back = :newBack "
            + " WHERE id = :id", nativeQuery = true)
    void updateBack(@Param("id")Long id, @Param("newBack") String newBackNum);

//    // 팀 정보 페이지 용, wireflow에서 보여지는 선수 정보. 등번호, 포지션, 선수명
//    @Query(value = "SELECT p.user_email, p.back, p.name, p.current, p.want" +
//            " FROM player p WHERE p.team_name = :team_name ", nativeQuery = true)
//    List<Player> findPlayerInfoByTeamName(@Param("team_name") String teamName);


    // 테이블 연결 필요요
//   @Query(value = "select DISTINCT c from player c left join fetch c.user")
//    List<Map<String, Object>> findAllPlayerList();
//

}
