package goingmerry.cent.repository;


import goingmerry.cent.domain.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TeamRepository extends JpaRepository<Team, Long> {

        Optional<Team> findByTeamName(String TeamName);

        @Query(value = "select team_name from team", nativeQuery = true)
        List<String> findTeamName();

        @Query(value = "SELECT p.team_name FROM team p WHERE p.area = :area", nativeQuery = true)
        List<String> findTeamNameByArea(@Param("area") String area);

        void deleteByTeamName(String teamName);

//        @Query("UPDATE Team p SET p.TeamName = :TeamName WHERE p.TeamName = :TeamName")
//        Optional<Team> updateTeamName(String TeamName);
//
//        @Query("UPDATE Team p SET p.Logo = :Logo WHERE p.TeamName = :TeamName")
//        Optional<Team> updateLogo(String TeamName, String Logo);
//
//        @Query("UPDATE Team p SET p.Intro = :Intro WHERE p.TeamName = :TeamName")
//        Optional<Team> updateIntro(String TeamName, String Intro);

//        //팀이 활동지역을 바꿀까?
//        @Query("UPDATE Team p SET p.Area = :Area WHERE p.TeamName = :TeamName")
//        Optional<Team> updateArea(String TeamName, String Area);

}
