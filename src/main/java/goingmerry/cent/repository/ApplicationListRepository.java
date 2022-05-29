package goingmerry.cent.repository;

import goingmerry.cent.domain.ApplicationList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationListRepository extends JpaRepository<ApplicationList, String> {
    //신청 정보 관리

}
