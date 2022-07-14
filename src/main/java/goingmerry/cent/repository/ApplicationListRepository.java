package goingmerry.cent.repository;

import goingmerry.cent.domain.ApplicationList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ApplicationListRepository extends JpaRepository<ApplicationList, Long> {
    //신청 정보 관리

    @Query(value = "SELECT p.* FROM application_list p " +
            "WHERE p.status = :status and p.team_name = :team_name", nativeQuery = true)
    List<ApplicationList> findByTeamName(@Param("team_name")String teamName, String status);

    List<ApplicationList> findByToEmail(String toEmail);
}
