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

        @Query(value = "SELECT p.id, p.user_name, p.email, p.position, p.activity_area, p.age_range" +
                " FROM user p", nativeQuery = true)
        List<Map<String, String>> findUserInfo();
}
