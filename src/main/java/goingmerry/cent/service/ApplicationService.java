package goingmerry.cent.service;

import goingmerry.cent.dto.ApplicationListDto;
import goingmerry.cent.repository.ApplicationListRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class ApplicationService {

    private final ApplicationListRepository applicationListRepository;

    // 신청 목록
    // 신청한 사람 목록을 테이블을 따로 만들어야 한다.
    // 신청 페이지 용 -> 특정 선수 정보가 아니라 신청한 사람 정보 전부 다!
    public List<ApplicationListDto> getApplicationList_team(String teamName) {

        List<ApplicationListDto> applicationList = null;

        return applicationList;
    }


    public List<ApplicationListDto> getApplicationList_user(String email) {

        List<ApplicationListDto> applicationList = null;

        return applicationList;
    }




}
