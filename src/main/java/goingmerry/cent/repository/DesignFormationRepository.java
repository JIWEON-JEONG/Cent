package goingmerry.cent.repository;

import goingmerry.cent.domain.DesignateFormation;
import goingmerry.cent.domain.Formation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface DesignFormationRepository extends JpaRepository<DesignateFormation, Long> {
    //String back, int circle, String name, int x, int y, String position, String teamName

    // 검증 안 됨.
    @Query(value = "SELECT p.x, p.y, p.circle FROM designate_formation p WHERE p.formation_name = :formation_name ORDER BY circle ASC", nativeQuery = true)
    List<Map<String, String>> findFormationByFormationName(@Param("formation_name") String formationName);

    @Query(value = "SELECT p.formation_name WHERE designate_formation", nativeQuery = true)
    List<String> getFormationList();
}
