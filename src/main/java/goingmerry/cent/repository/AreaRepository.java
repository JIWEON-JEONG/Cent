package goingmerry.cent.repository;

import goingmerry.cent.domain.Area;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface AreaRepository extends JpaRepository<Area, Long> {

        //@Query 어노테이션을 사용해서 쿼리를 작성하려면 nativeQuery = true 로 설정해줘야 한다.

        //팀 전체 조회, {region, city} 형태의 맵이 될 것.
        @Query(value = "select region,city from area", nativeQuery = true)
        List<Map<String, String>> findAllArea();

        //region에 해당하는 city만 가져올 수 있도록
        @Query(value = "SELECT p.city FROM Area p WHERE p.region = :region", nativeQuery = true)
        List<String> findCityByRegion(@Param("region") String region);

}
