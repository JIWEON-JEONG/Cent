package goingmerry.cent.repository;

import goingmerry.cent.domain.Formation;
import goingmerry.cent.domain.SocialType;
import goingmerry.cent.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
        Optional<User> findBySocialTypeAndUserId(SocialType socialType , Long userId);

        Optional<User> findByEmail(String email);

        //List<User> findAll();

        // 팀 관리 페이지에서 팀장이 맴버 목록을 보고 선택할 수 있게 필요한 정보들 select
        @Query(value = "SELECT p.id, p.user_name, p.email, p.position, p.activity_area, p.age_range, p.is_expert" +
                " FROM user p", nativeQuery = true)
        List<Map<String, String>> findUserInfo();

        // 특정 유저 정보의 검색 by email
        @Query(value = "SELECT p.user_name, p.email, p.position, p.activity_area, p.age_range" + " FROM user p WHERE p.email = :email", nativeQuery = true)
        Map<String, String> findUserInfoByEmail(@Param("email") String email);
}
