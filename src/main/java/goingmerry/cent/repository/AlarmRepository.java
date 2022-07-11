package goingmerry.cent.repository;

import goingmerry.cent.domain.Alarm;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.stream.Stream;

public interface AlarmRepository extends JpaRepository<Alarm, Long> {

    Stream<Alarm> findAllByToEmailAndValue(String toEmail, String value);


}
